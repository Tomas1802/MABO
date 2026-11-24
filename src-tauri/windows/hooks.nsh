; Explorer right-click entries: "Open MABO here" on folders and folder backgrounds (per-user).
; On Windows 11 they appear under "Show more options".

!macro NSIS_HOOK_POSTINSTALL
  WriteRegStr HKCU "Software\Classes\Directory\shell\MABO" "" "Open MABO here"
  WriteRegStr HKCU "Software\Classes\Directory\shell\MABO" "Icon" "$INSTDIR\${MAINBINARYNAME}.exe"
  WriteRegStr HKCU "Software\Classes\Directory\shell\MABO\command" "" '"$INSTDIR\${MAINBINARYNAME}.exe" --cwd "%V"'
  WriteRegStr HKCU "Software\Classes\Directory\Background\shell\MABO" "" "Open MABO here"
  WriteRegStr HKCU "Software\Classes\Directory\Background\shell\MABO" "Icon" "$INSTDIR\${MAINBINARYNAME}.exe"
  WriteRegStr HKCU "Software\Classes\Directory\Background\shell\MABO\command" "" '"$INSTDIR\${MAINBINARYNAME}.exe" --cwd "%V"'
  WriteRegStr HKCU "Software\Classes\Drive\shell\MABO" "" "Open MABO here"
  WriteRegStr HKCU "Software\Classes\Drive\shell\MABO" "Icon" "$INSTDIR\${MAINBINARYNAME}.exe"
  WriteRegStr HKCU "Software\Classes\Drive\shell\MABO\command" "" '"$INSTDIR\${MAINBINARYNAME}.exe" --cwd "%V"'
!macroend

!macro NSIS_HOOK_PREUNINSTALL
  DeleteRegKey HKCU "Software\Classes\Directory\shell\MABO"
  DeleteRegKey HKCU "Software\Classes\Directory\Background\shell\MABO"
  DeleteRegKey HKCU "Software\Classes\Drive\shell\MABO"
!macroend
