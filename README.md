# *springboot整合kafka示例项目*
## *Windows本地安装运行kafka*
### kafka安装
- 下载地址：http://kafka.apache.org/downloads （本项目中使用的kafka版本为kafka_2.13-2.8.0.tgz）
- 下载完成后解压即可
### kafka运行启动
- 首先启动zookeeper:
进入bin\windows路径 cmd命令进入，执行命令 -> zookeeper-server-start.bat ..\..\config\zookeeper.properties
- 再次启动kafka:
进入bin\windows路径 cmd命令进入，执行命令 -> kafka-server-start.bat ..\..\config\server.properties
## *kafka监控中心下载安装运行（Kafka Eagle）*
### Kafka Eagle下载
- 下载地址：https://github.com/smartloli/kafka-eagle-bin/tags （本项目中使用的kafka eagle版本为v2.0.6）
- 下载完成后解压即可
### Kafka Eagle system-config.properties配置文件修改
- 默认数据库配置为SQLite，将其注释掉，更改为MySQL数据库，在MySQL数据库中需创建数据库名为ke的库或自定义
```properties
######################################
# kafka sqlite jdbc driver address
######################################
#kafka.eagle.driver=org.sqlite.JDBC
#kafka.eagle.url=jdbc:sqlite:/hadoop/kafka-eagle/db/ke.db
#kafka.eagle.username=root
#kafka.eagle.password=www.kafka-eagle.org

######################################
# kafka mysql jdbc driver address
######################################
kafka.eagle.driver=com.mysql.cj.jdbc.Driver
kafka.eagle.url=jdbc:mysql://127.0.0.1:3306/ke?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull
kafka.eagle.username=root
kafka.eagle.password=root
```
### 开启kafka JMX监控端口
- Kafka Eagle监控平台涉及到需要采集数据，所以需打开JMX端口
- 进入已安装的kafka 进入bin\Windows目录打开kafka-server-start.bat，修改后代码如下：
```bat
@echo off
IF [%1] EQU [] (
	echo USAGE: %0 server.properties
	EXIT /B 1
)

SetLocal
IF ["%KAFKA_LOG4J_OPTS%"] EQU [""] (
    set KAFKA_LOG4J_OPTS=-Dlog4j.configuration=file:%~dp0../../config/log4j.properties
)
IF ["%KAFKA_HEAP_OPTS%"] EQU [""] (
    rem detect OS architecture
    wmic os get osarchitecture | find /i "32-bit" >nul 2>&1
    IF NOT ERRORLEVEL 1 (
        rem 32-bit OS
        set KAFKA_HEAP_OPTS=-Xmx512M -Xms512M
		set JMX_PORT="9999" # 新增JMX端口
    ) ELSE (
        rem 64-bit OS
        set KAFKA_HEAP_OPTS=-Xmx1G -Xms1G
		set JMX_PORT="9999" # 新增JMX端口
    )
)
"%~dp0kafka-run-class.bat" kafka.Kafka %*
EndLocal
```
### Kafka Eagle运行
- 进入bin\ke.bat 双击ke.bat运行即可
- 访问 http://localhost:8048/ke 即可进入监控平台