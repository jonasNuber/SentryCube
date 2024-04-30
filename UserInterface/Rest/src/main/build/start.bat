@echo off
setlocal

rem Set Java home to the JDK folder next to this script
set "JAVA_HOME=%~dp0jdk"

rem Set the path to the Java executable
set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"

rem Set the classpath to the libs folder
set "CLASSPATH=%~dp0libs;%~dp0libs\third-party"

rem Set the module path to include both libs and third-party folders
set "MODULEPATH=%~dp0libs;%~dp0libs\third-party"

rem Start the jar file
"%JAVA_EXE%" -cp "%CLASSPATH%" --module-path "%MODULEPATH%" -jar "%~dp0libs\org.nuberjonas.sentrycube.userinterface.rest-1.0.0-SNAPSHOT.jar"

endlocal