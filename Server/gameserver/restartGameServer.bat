@echo off
title L2J-4Team: Game Server Restart
echo L2J-4Team: Game Server Restart
echo ATTENTION: It needs XMLRPC Server Enabled in Powerpak in order to work

REM -------------------------------------
REM Default parameters for a basic server.
java -Dfile.encoding=UTF8 -cp ./lib/*;l24team-core.jar com.l24team.gameserver.powerpak.xmlrpc.XMLRPCClient_ManagementTester
REM
