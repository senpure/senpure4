@echo off
set currentPath=%cd%
echo %currentPath%
set fileName=""
for  %%a in (*) do (
   echo %%a|find /i "senpure-permission-" >nul && set fileName=%%a
)
if %fileName% =="" (
    echo "û���ҵ������е�jar�ļ�"
    goto :end
)

echo %fileName%
java -jar %fileName%
:end
pause