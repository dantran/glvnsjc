@echo off

setlocal

cd %~dps0../../

mvn clean eclipse:clean eclipse:eclipse eclipse:add-maven-repo

endlocal