# MABO shell integration: our prompt + OSC 133 command marks, OSC 9;9 cwd, OSC 0 tab title.
# Uses the 16 ANSI colors, so the prompt follows the selected theme.
$Global:__tt_started = $false

function Global:__tt_branch([string]$dir) {
    # Reads .git/HEAD directly: no git process, so the prompt stays instant.
    while ($dir) {
        $head = Join-Path $dir '.git/HEAD'
        if (Test-Path -LiteralPath $head -PathType Leaf) {
            $ref = (Get-Content -LiteralPath $head -TotalCount 1)
            if ($ref -match '^ref: refs/heads/(.+)$') { return $Matches[1] }
            return $ref.Substring(0, 7)
        }
        $dir = Split-Path $dir -Parent
    }
}

function Global:prompt {
    $ok = $?
    $code = if ($ok) { 0 } elseif ($LASTEXITCODE) { $LASTEXITCODE } else { 1 }
    $e = [char]27; $b = [char]7
    $dim = "$e[90m"; $blue = "$e[94m"; $bold = "$e[1m"; $mag = "$e[95m"; $r = "$e[0m"

    $out = ''
    if ($Global:__tt_started) { $out += "$e]133;D;$code$b`n" }
    $Global:__tt_started = $true
    $out += "$e]133;A$b"

    $loc = $executionContext.SessionState.Path.CurrentLocation
    $path = $loc.ProviderPath
    if ($loc.Provider.Name -eq 'FileSystem') { $out += "$e]9;9;`"$path`"$b" }

    $display = $path
    if ($HOME -and $path.StartsWith($HOME, 'OrdinalIgnoreCase')) { $display = '~' + $path.Substring($HOME.Length) }
    $parts = $display -split '[\\/]' | Where-Object { $_ }
    $leaf = if ($parts) { $parts[-1] } else { $display }
    $out += "$e]0;$leaf$b"
    $parent = if ($parts.Count -gt 1) { ($parts[0..($parts.Count - 2)] -join '/') + '/' } else { '' }

    $out += "$dim$parent$r$blue$bold$leaf$r"
    $branch = if ($loc.Provider.Name -eq 'FileSystem') { __tt_branch $path }
    if ($branch) { $out += " $dim on $r$mag$branch$r" }
    if (-not $ok) { $out += "  $e[31m✗ $code$r" }

    $arrow = if ($ok) { "$e[32m❯$r" } else { "$e[31m❯$r" }
    $out + "`n$arrow $e]133;B$b"
}
