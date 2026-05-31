param(
    [string]$InstallDir = "$env:LOCALAPPDATA\MABO"
)

$ErrorActionPreference = "Stop"

$InstallPath = [System.IO.Path]::GetFullPath($InstallDir)
$BinPath = Join-Path $InstallPath "bin"

if (Test-Path $InstallPath) {
    Remove-Item -LiteralPath $InstallPath -Recurse -Force
}

$UserPath = [Environment]::GetEnvironmentVariable("Path", "User")
if (-not [string]::IsNullOrWhiteSpace($UserPath)) {
    $Parts = $UserPath -split ';' | Where-Object {
        -not [string]::IsNullOrWhiteSpace($_) -and $_.TrimEnd('\') -ine $BinPath.TrimEnd('\')
    }
    [Environment]::SetEnvironmentVariable("Path", ($Parts -join ';'), "User")
}

Write-Host "MABO removed from: $InstallPath"
