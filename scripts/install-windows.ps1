param(
    [string]$InstallDir = "$env:LOCALAPPDATA\MABO",
    [switch]$SkipPath,
    [switch]$StopRunning
)

$ErrorActionPreference = "Stop"

$ProjectRoot = Resolve-Path (Join-Path $PSScriptRoot "..")
$InstallPath = [System.IO.Path]::GetFullPath($InstallDir)
$BinPath = Join-Path $InstallPath "bin"

function Get-MaboInstallProcesses {
    param([string]$Path)

    $escapedPath = $Path.Replace('\', '\\')
    Get-CimInstance Win32_Process |
        Where-Object {
            $_.CommandLine -and (
                $_.CommandLine -like "*$Path*" -or
                $_.CommandLine -like "*$escapedPath*" -or
                $_.CommandLine -like "*mabo.bat*"
            )
        } |
        Select-Object ProcessId,Name,CommandLine
}

function Stop-Or-ReportMaboProcesses {
    param(
        [string]$Path,
        [bool]$ShouldStop
    )

    $processes = @(Get-MaboInstallProcesses -Path $Path)
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

    Write-Host "MABO is currently running from the install directory:"
    foreach ($process in $processes) {
        Write-Host "  PID $($process.ProcessId): $($process.Name)"
    }
    throw "Close all terminals running mabo and retry, or run this installer with -StopRunning."
}

Push-Location $ProjectRoot
try {
    if (Test-Path $InstallPath) {
        Stop-Or-ReportMaboProcesses -Path $InstallPath -ShouldStop $StopRunning.IsPresent
    }

    gradle clean installDist

    $Distribution = Join-Path $ProjectRoot "build\install\mabo"
    if (-not (Test-Path $Distribution)) {
        throw "Gradle distribution was not created at $Distribution"
    }

    if (Test-Path $InstallPath) {
        Remove-Item -LiteralPath $InstallPath -Recurse -Force
    }
    New-Item -ItemType Directory -Force -Path $InstallPath | Out-Null
    Copy-Item -Path (Join-Path $Distribution "*") -Destination $InstallPath -Recurse -Force

    if (-not $SkipPath) {
        $UserPath = [Environment]::GetEnvironmentVariable("Path", "User")
        $Parts = @()
        if (-not [string]::IsNullOrWhiteSpace($UserPath)) {
            $Parts = $UserPath -split ';' | Where-Object { -not [string]::IsNullOrWhiteSpace($_) }
        }

        $AlreadyPresent = $false
        foreach ($Part in $Parts) {
            if ($Part.TrimEnd('\') -ieq $BinPath.TrimEnd('\')) {
                $AlreadyPresent = $true
                break
            }
        }

        if (-not $AlreadyPresent) {
            $NewPath = ($Parts + $BinPath) -join ';'
            [Environment]::SetEnvironmentVariable("Path", $NewPath, "User")
            $env:Path = $env:Path + ";" + $BinPath
        }
    }

    Write-Host "MABO installed at: $InstallPath"
    Write-Host "Command: mabo <script.dsl>"
    Write-Host "If this is a new terminal, reopen it before running mabo."
}
finally {
    Pop-Location
}
