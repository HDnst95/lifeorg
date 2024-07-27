@echo off
setlocal EnableDelayedExpansion

REM Check if a version increment type is provided
if "%1"=="" (
    echo Usage: bump_version.bat [major|minor|patch]
    exit /b 1
)

set increment_type=%1

REM Get the current version from the pom.xml
for /f "delims=" %%i in ('mvn help:evaluate -Dexpression=project.version -q -DforceStdout') do set current_version=%%i

REM Split the version into an array
for /f "tokens=1,2,3 delims=." %%a in ("%current_version%") do (
    set major=%%a
    set minor=%%b
    set patch=%%c
)

REM Increment the appropriate part of the version
if "%increment_type%"=="major" (
    set /a major+=1
    set minor=0
    set patch=0
) else if "%increment_type%"=="minor" (
    set /a minor+=1
    set patch=0
) else if "%increment_type%"=="patch" (
    set /a patch+=1
) else (
    echo Invalid increment type. Use [major|minor|patch].
    exit /b 1
)

REM Join the version parts back together
set new_version=%major%.%minor%.%patch%

REM Set the new version in the pom.xml
mvn versions:set -DnewVersion=%new_version%

REM Commit the version change
git add pom.xml
git commit -m "Bump version to %new_version%"

REM Create a new Git tag
git tag -a v%new_version% -m "Release version %new_version%"
git push origin v%new_version%
git push origin main

endlocal
