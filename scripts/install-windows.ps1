param(
    [string]$InstallDir = "$env:LOCALAPPDATA\MABO",
    [switch]$SkipPath,
    [switch]$StopRunning,
    [switch]$FromSource,
    [string]$Repository = "Tomas1802/MABO",
    [string]$Version = "latest",
    [string]$SourceZipUrl
)

$ErrorActionPreference = "Stop"

$InstallPath = [System.IO.Path]::GetFullPath($InstallDir)
$BinPath = Join-Path $InstallPath "bin"
$ScriptRoot = if ([string]::IsNullOrWhiteSpace($PSScriptRoot)) { $null } else { $PSScriptRoot }
$ProjectRoot = $null
if ($ScriptRoot) {
    $CandidateRoot = [System.IO.Path]::GetFullPath((Join-Path $ScriptRoot ".."))
    if (Test-Path (Join-Path $CandidateRoot "build.gradle")) {
        $ProjectRoot = $CandidateRoot
    }
}
$BuildInstallPath = if ($ProjectRoot) { Join-Path $ProjectRoot "build\install\mabo" } else { $null }

function Get-MaboInstallProcesses {
    param([string[]]$Paths)

    $ExistingPaths = @($Paths | Where-Object { -not [string]::IsNullOrWhiteSpace($_) })
    Get-CimInstance Win32_Process |
        Where-Object {
            if (-not $_.CommandLine) {
                return $false
            }
            if ($_.CommandLine -like "*mabo.bat*") {
                return $true
            }
            foreach ($Path in $ExistingPaths) {
                $EscapedPath = $Path.Replace('\', '\\')
                if ($_.CommandLine -like "*$Path*" -or $_.CommandLine -like "*$EscapedPath*") {
                    return $true
                }
            }
            return $false
        } |
        Select-Object ProcessId,Name,CommandLine
}

function Stop-Or-ReportMaboProcesses {
    param(
        [string[]]$Paths,
        [bool]$ShouldStop
    )

    $processes = @(Get-MaboInstallProcesses -Paths $Paths)
    if ($processes.Count -eq 0) {
        return
    }

    if ($ShouldStop) {
        foreach ($process in $processes) {
            Stop-Process -Id $process.ProcessId -Force
        }
        Start-Sleep -Milliseconds 500
        return
    }

    Write-Host "MABO is currently running and is using files needed by this installer:"
    foreach ($process in $processes) {
        Write-Host "  PID $($process.ProcessId): $($process.Name)"
    }
    throw "Close all terminals running mabo and retry, or run this installer with -StopRunning."
}

function Add-ToUserPath {
    param([string]$PathToAdd)

    $UserPath = [Environment]::GetEnvironmentVariable("Path", "User")
    $Parts = @()
    if (-not [string]::IsNullOrWhiteSpace($UserPath)) {
        $Parts = $UserPath -split ';' | Where-Object { -not [string]::IsNullOrWhiteSpace($_) }
    }

    $AlreadyPresent = $false
    foreach ($Part in $Parts) {
        if ($Part.TrimEnd('\') -ieq $PathToAdd.TrimEnd('\')) {
            $AlreadyPresent = $true
            break
        }
    }

    if (-not $AlreadyPresent) {
        $NewPath = ($Parts + $PathToAdd) -join ';'
        [Environment]::SetEnvironmentVariable("Path", $NewPath, "User")
        $env:Path = $env:Path + ";" + $PathToAdd
    }
}

function Get-ReleaseZipUrl {
    if (-not [string]::IsNullOrWhiteSpace($SourceZipUrl)) {
        return $SourceZipUrl
    }
    if ($Version -eq "latest") {
        return "https://github.com/$Repository/releases/latest/download/mabo-windows.zip"
    }
    return "https://github.com/$Repository/releases/download/$Version/mabo-windows.zip"
}

function Get-JavaMajorVersion {
    $javaCommand = Get-Command java -ErrorAction SilentlyContinue
    if (-not $javaCommand) {
        return $null
    }

    $previousErrorActionPreference = $ErrorActionPreference
    $ErrorActionPreference = "Continue"
    try {
        $versionOutput = & java -version 2>&1
    }
    finally {
        $ErrorActionPreference = $previousErrorActionPreference
    }
    $versionLine = $versionOutput | Where-Object { $_ -match 'version' } | Select-Object -First 1
    if (-not $versionLine) {
        return $null
    }
    if ($versionLine -notmatch '"([^"]+)"') {
        return $null
    }

    $version = $Matches[1]
    $parts = $version.Split('.')
    if ($parts[0] -eq "1" -and $parts.Length -gt 1) {
        return [int]$parts[1]
    }
    return [int]$parts[0]
}

function Write-JavaRequirementWarning {
    $major = Get-JavaMajorVersion
    if ($null -eq $major) {
        Write-Warning "MABO requires Java 17 or newer, but no Java runtime was found in PATH."
        Write-Host "Install Java with: winget install EclipseAdoptium.Temurin.21.JDK"
        Write-Host "Then reopen PowerShell and run: java -version"
        return
    }
    if ($major -lt 17) {
        Write-Warning "MABO requires Java 17 or newer, but PATH currently uses Java $major."
        Write-Host "Install Java with: winget install EclipseAdoptium.Temurin.21.JDK"
        Write-Host "Then reopen PowerShell and confirm: java -version"
    }
}

function Install-FromDistribution {
    param([string]$Distribution)

    if (-not (Test-Path $Distribution)) {
        throw "MABO distribution was not found at $Distribution"
    }
    if (Test-Path $InstallPath) {
        Remove-Item -LiteralPath $InstallPath -Recurse -Force
    }
    New-Item -ItemType Directory -Force -Path $InstallPath | Out-Null
    Copy-Item -Path (Join-Path $Distribution "*") -Destination $InstallPath -Recurse -Force
}

function Install-FromSource {
    if (-not $ProjectRoot) {
        throw "No local project was found. Run from the repository, or omit -FromSource to install from GitHub Releases."
    }
    Push-Location $ProjectRoot
    try {
        & gradle clean installDist
        if ($LASTEXITCODE -ne 0) {
            throw "Gradle build failed with exit code $LASTEXITCODE. MABO was not installed."
        }

        Install-FromDistribution -Distribution (Join-Path $ProjectRoot "build\install\mabo")
    }
    finally {
        Pop-Location
    }
}

function Install-FromRelease {
    $ZipUrl = Get-ReleaseZipUrl
    $TempRoot = Join-Path ([System.IO.Path]::GetTempPath()) ("mabo-install-" + [System.Guid]::NewGuid().ToString("N"))
    $ZipPath = Join-Path $TempRoot "mabo-windows.zip"
    $ExtractPath = Join-Path $TempRoot "extract"

    New-Item -ItemType Directory -Force -Path $TempRoot | Out-Null
    try {
        Write-Host "Downloading MABO from $ZipUrl"
        Invoke-WebRequest -Uri $ZipUrl -OutFile $ZipPath
        Expand-Archive -LiteralPath $ZipPath -DestinationPath $ExtractPath -Force

        $Distribution = Join-Path $ExtractPath "mabo"
        if (-not (Test-Path $Distribution)) {
            $Distribution = $ExtractPath
        }
        Install-FromDistribution -Distribution $Distribution
    }
    finally {
        Remove-Item -LiteralPath $TempRoot -Recurse -Force -ErrorAction SilentlyContinue
    }
}

$PathsToCheck = @($InstallPath)
if ($BuildInstallPath) {
    $PathsToCheck += $BuildInstallPath
}

Stop-Or-ReportMaboProcesses -Paths $PathsToCheck -ShouldStop $StopRunning.IsPresent

$CanBuildLocal = $ProjectRoot -and (Test-Path (Join-Path $ProjectRoot "build.gradle"))
if ($FromSource -or $CanBuildLocal) {
    Install-FromSource
} else {
    Install-FromRelease
}

if (-not $SkipPath) {
    Add-ToUserPath -PathToAdd $BinPath
}

Write-JavaRequirementWarning

Write-Host "MABO installed at: $InstallPath"
Write-Host "Command: mabo <script.mabo>"
Write-Host "If this is a new terminal, reopen it before running mabo."
