@echo off
set currentPath=%cd%
echo %currentPath%
set fileName=""
for  %%a in (*) do (
   echo %%a|find /i "senpure-permission-" >nul && set fileName=%%a
)
if %fileName% =="" (
    echo "没有找到可运行的jar文件"
    goto :end
)

echo %fileName%
java -jar %fileName%
:end
pause