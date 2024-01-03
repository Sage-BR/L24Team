@echo off
title L2J-4Team: Login Server Console
:start

java -Dfile.encoding=UTF8 -server -XX:SurvivorRatio=8 -XX:NewRatio=4 -XX:+UseCodeCacheFlushing -XX:+OptimizeStringConcat -XX:+UseG1GC -XX:+TieredCompilation -XX:+UseCompressedOops -Xmx64m -cp ./lib/*;l24team-core.jar com.l24team.loginserver.L2LoginServer

if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
goto end
:restart
echo.
echo Admin Restarted ...
ping -n 5 localhost > nul
echo.
goto start
:error
echo.
echo LoginServer terminated abnormaly
ping -n 5 localhost > nul
echo.
goto start
:end
echo.
echo LoginServer terminated
echo.
:question
set choix=q
set /p choix=Restart(r) or Quit(q)
if /i %choix%==r goto start
if /i %choix%==q goto exit
:exit
exit
pause
