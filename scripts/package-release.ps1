param(
    [string]$OutputDir = "build\release",
    [switch]$SkipTests
)

$ErrorActionPreference = "Stop"

$ProjectRoot = Resolve-Path (Join-Path $PSScriptRoot "..")
$OutputPath = Join-Path $ProjectRoot $OutputDir
$ZipPath = Join-Path $OutputPath "mabo-windows.zip"
$InstallScript = Join-Path $ProjectRoot "scripts\install-windows.ps1"
$UninstallScript = Join-Path $ProjectRoot "scripts\uninstall-windows.ps1"
$ReleaseInstallScript = Join-Path $OutputPath "install-windows.ps1"
$ReleaseUninstallScript = Join-Path $OutputPath "uninstall-windows.ps1"

Push-Location $ProjectRoot
try {
    if ($SkipTests) {
        & gradle clean installDist
    } else {
        & gradle clean test installDist
    }
    if ($LASTEXITCODE -ne 0) {
        throw "Gradle build failed with exit code $LASTEXITCODE."
    }

    $Distribution = Join-Path $ProjectRoot "build\install\mabo"
    if (-not (Test-Path $Distribution)) {
        throw "Gradle distribution was not created at $Distribution"
    }

    New-Item -ItemType Directory -Force -Path $OutputPath | Out-Null
    if (Test-Path $ZipPath) {
        Remove-Item -LiteralPath $ZipPath -Force
    }
    Compress-Archive -Path $Distribution -DestinationPath $ZipPath -Force
    Copy-Item -LiteralPath $InstallScript -Destination $ReleaseInstallScript -Force
    Copy-Item -LiteralPath $UninstallScript -Destination $ReleaseUninstallScript -Force

    Write-Host "Release package created:"
    Write-Host "  $ZipPath"
    Write-Host "  $ReleaseInstallScript"
    Write-Host "  $ReleaseUninstallScript"
}
finally {
    Pop-Location
}
