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
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\*.java ..\src\main\java\Commands\*.java ..\src\main\java\Duke\*.java ..\src\main\java\ErrorHandling\*.java ..\src\main\java\Tasks\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM move data file to text-ui-test directory
REM cd ..\src\main\data
REM move duke.txt  ..\..\..\text-ui-test\src\main\data\

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
REM cd ..\..\..\text-ui-test
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM move data file back to original directory
REM cd .\src\main\data
REM move duke.txt ..\..\..\..\src\main\data\

REM to delete temp src directory created
REM cd ..\..\..
REM rd /s/q "src"