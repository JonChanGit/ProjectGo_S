@echo OFF
Echo  ==========================================================================
ECHO.
Echo  开始复制文件
ECHO.
Echo  1.common_utils-1.0-SNAPSHOT.jar
Echo  2.common_entity-1.0-SNAPSHOT.jar
::Echo  3.jsp/*
::Echo  4.web_html/*
ECHO.
Echo  ==========================================================================
copy /y common_utils\target\common_utils-1.0-SNAPSHOT.jar web_dynamic\target\web_dynamic\WEB-INF\lib\common_utils-1.0-SNAPSHOT.jar
copy /y common_entity\target\common_entity-1.0-SNAPSHOT.jar web_dynamic\target\web_dynamic\WEB-INF\lib\common_entity-1.0-SNAPSHOT.jar
::xcopy web_dynamic\src\main\webapp web_dynamic\target\web_dynamic /e /s /y
::xcopy web_html\src\main\webapp\dist web_dynamic\target\web_dynamic\dist  /e /s /y

time /t
