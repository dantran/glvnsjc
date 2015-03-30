@echo off

REM
REM Convenient script to create and configure eclipse workspace
REM

setlocal

rem configure xml, html, and javascript see corporate pom for detail

if not exist .metadata call mvn validate -Pconfigure-workspace -N %*


call mvn eclipse:eclipse -DdownloadSources=true %*

endlocal