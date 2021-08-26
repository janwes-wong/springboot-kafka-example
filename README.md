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