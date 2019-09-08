@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin
if not exist \src\main\data mkdir \src\main\data

REM delete output from previous run
del ACTUAL.TXT
cd .\src\main\data
del duke.txt
cd ..\..\..

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\main\*.java ..\src\main\java\main\Commands\*.java ..\src\main\java\main\DukeOperations\*.java ..\src\main\java\main\ErrorHandling\*.java ..\src\main\java\main\Tasks\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT