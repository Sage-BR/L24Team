@echo off
title L2J-4Team: Game Server Console
mode con: cols=140
:start
echo Starting L2J-4Team Core Game Server.
echo Official website : http://www.4teambr.com
echo Enjoy by server core. Bee happy!
echo ------------------------------
echo.


REM -------------------------------------
REM Default parameters for a basic server.
java -Dfile.encoding=UTF8 -server -XX:SurvivorRatio=8 -XX:NewRatio=4 -XX:+UseCodeCacheFlushing -XX:+OptimizeStringConcat -XX:+UseG1GC -XX:+TieredCompilation -XX:+UseCompressedOops -Xmx2g -cp ./lib/*;./lib/uMad/*;l24team-core.jar com.l24team.gameserver.GameServer
REM
REM If you have a big server and lots of memory, you could experiment for example with
REM java -server -Xmx1536m -Xms1024m -Xmn512m -XX:PermSize=256m -XX:SurvivorRatio=8 -Xnoclassgc -XX:+AggressiveOpts
REM -------------------------------------

if ERRORLEVEL 7 goto telldown
if ERRORLEVEL 6 goto tellrestart
if ERRORLEVEL 5 goto taskrestart
if ERRORLEVEL 4 goto taskdown
REM 3 - abort
if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
goto end
:tellrestart
echo.
echo Telnet server Restart ...
echo Send you bug to : http://www.4teambr.com
echo.
goto start
:taskrestart
echo.
echo Auto Task Restart ...
echo Send you bug to : http://www.4teambr.com
echo.
goto start
:restart
echo.
echo Admin Restart ...
echo Send you bug to : http://www.4teambr.com
echo.
goto start
:taskdown
echo .
echo Server terminated (Auto task)
echo Send you bug to : http://www.4teambr.com
echo .
:telldown
echo .
echo Server terminated (Telnet)
echo Send you bug to : http://www.4teambr.com
echo .
:error
echo.
echo Server terminated abnormally
echo Send you bug to : http://www.4teambr.com
echo.
:end
echo.
echo server terminated
echo Send you bug to : http://www.4teambr.com
echo.
:question
set choix=q
set /p choix=Restart(r) or Quit(q)
if /i %choix%==r goto start
if /i %choix%==q goto exit
:exit
exit
pause
