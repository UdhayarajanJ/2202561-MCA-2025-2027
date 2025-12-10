@echo off
echo ============================================
echo     Building Servlets + Restarting Tomcat
echo ============================================

REM ---- Tomcat root folder ----
REM ---- 1. Compile Servlet Files ----
echo Compiling Servlets...
javac -cp ".;WEB-INF/lib/servlet-api.jar;WEB-INF/lib/mysql-connector-j-9.5.0.jar" -d "WEB-INF/classes" WEB-INF/src/*.java

if %errorlevel% neq 0 (
    echo Compilation FAILED!
    pause
    exit /b
)
echo Compilation Successful!
