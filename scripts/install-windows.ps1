param(
    [string]$InstallDir = "$env:LOCALAPPDATA\MABO",
    [switch]$SkipPath
)

$ErrorActionPreference = "Stop"

$ProjectRoot = Resolve-Path (Join-Path $PSScriptRoot "..")
$InstallPath = [System.IO.Path]::GetFullPath($InstallDir)
$BinPath = Join-Path $InstallPath "bin"

Push-Location $ProjectRoot
try {
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
