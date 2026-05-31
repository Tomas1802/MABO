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
$VsCodeExtensionDir = Join-Path $ProjectRoot "vscode\mabo-language"
$VsixPath = Join-Path $OutputPath "mabo-language-0.1.0.vsix"

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

    if (Test-Path $VsCodeExtensionDir) {
        $Npx = Get-Command npx -ErrorAction SilentlyContinue
        if (-not $Npx) {
            throw "npx was not found. Install Node.js to package the VS Code extension."
        }
        if (Test-Path $VsixPath) {
            Remove-Item -LiteralPath $VsixPath -Force
        }
        Push-Location $VsCodeExtensionDir
        try {
            & npx --yes @vscode/vsce package --out $VsixPath
            if ($LASTEXITCODE -ne 0) {
                throw "VS Code extension packaging failed with exit code $LASTEXITCODE."
            }
        }
        finally {
            Pop-Location
        }
    }

    Write-Host "Release package created:"
    Write-Host "  $ZipPath"
    Write-Host "  $ReleaseInstallScript"
    Write-Host "  $ReleaseUninstallScript"
    if (Test-Path $VsixPath) {
        Write-Host "  $VsixPath"
    }
}
finally {
    Pop-Location
}
