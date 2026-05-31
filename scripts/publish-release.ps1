param(
    [Parameter(Mandatory = $true)]
    [string]$Tag,
    [string]$Title,
    [switch]$Draft,
    [switch]$Prerelease,
    [switch]$SkipTests
)

$ErrorActionPreference = "Stop"

$ProjectRoot = Resolve-Path (Join-Path $PSScriptRoot "..")
$ReleaseDir = Join-Path $ProjectRoot "build\release"
$ZipPath = Join-Path $ReleaseDir "mabo-windows.zip"
$InstallScript = Join-Path $ReleaseDir "install-windows.ps1"
$UninstallScript = Join-Path $ReleaseDir "uninstall-windows.ps1"
$VsixPath = Join-Path $ReleaseDir "mabo-language-0.1.0.vsix"

Push-Location $ProjectRoot
try {
    & (Join-Path $ProjectRoot "scripts\package-release.ps1") -SkipTests:$SkipTests

    $Gh = Get-Command gh -ErrorAction SilentlyContinue
    if (-not $Gh) {
        throw "GitHub CLI was not found. Install it from https://cli.github.com/ and run 'gh auth login'."
    }

    $Assets = @($ZipPath, $InstallScript, $UninstallScript)
    if (Test-Path $VsixPath) {
        $Assets += $VsixPath
    }
    $Args = @("release", "create", $Tag) + $Assets + @("--notes", "MABO $Tag")
    if (-not [string]::IsNullOrWhiteSpace($Title)) {
        $Args += @("--title", $Title)
    } else {
        $Args += @("--title", "MABO $Tag")
    }
    if ($Draft) {
        $Args += "--draft"
    }
    if ($Prerelease) {
        $Args += "--prerelease"
    }

    & gh @Args
    if ($LASTEXITCODE -ne 0) {
        throw "gh release create failed with exit code $LASTEXITCODE."
    }
}
finally {
    Pop-Location
}
