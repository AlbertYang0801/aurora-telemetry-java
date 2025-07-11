# aurora-telemetry-java

## 📌 项目简介

这是一个基于gRPC协议实现的Java端指标采集系统。该系统旨在从终端设备收集实时数据，通过一系列处理流程后存储至ClickHouse数据库，并根据预设规则触发告警通知。系统设计注重高性能、高可用性和可扩展性。

## 🧩 架构概述

![image-20250708183247773](https://s2.loli.net/2025/07/08/fjzuOlPIxQyA8TJ.png)

- **终端设备**：负责上报指标数据。
- **Nginx集群**：作为负载均衡器，接收并分发gRPC请求。
- **Kafka消息队列**：暂存metric与alarm数据以供后续处理。
- **Flink数据处理组件**：进行实时流式计算，窗口聚合批量写入ClickHouse。
- **告警组件**：匹配告警规则，产生告警并通过短信/微信/钉钉/飞书等渠道通知。
- **ClickHouse数据库**：用于存储和查询历史数据。

## 🔧 技术栈

- gRPC - 高效跨语言的远程过程调用
- Nginx - 负载均衡和反向代理服务器
- Kafka - 分布式消息队列
- Flink - 实时流处理引擎
- ClickHouse - OLAP数据库

## 📦 安装指南

### 前提条件

确保已安装以下软件和服务：

- Java Development Kit (JDK)
- Apache Maven 或 Gradle
- Docker（如果使用容器化部署）
- Zookeeper（用于Kafka）

### 安装步骤

1. 克隆仓库到本地机器上：
   ```shell
   git clone <repository-url>
   cd <project-directory>
   ```

2. 使用Maven或Gradle构建项目：
   ```shell
   mvn clean install
   # 或者
   gradle build
   ```

3. 配置并启动所需的服务（如Kafka, Zookeeper, ClickHouse）。

4. 启动应用服务：
   ```shell
   java -jar target/<your-application>.jar
   ```

## 🚀 快速开始

一旦所有服务都成功启动，您可以开始发送指标数据到指定的gRPC端点。请参照文档中的API接口定义来构造您的请求。

## 📊 功能特性

- 实时数据采集
- 高性能的数据处理能力
- 支持多种告警通知方式
- 数据持久化存储于ClickHouse中

## 📁 目录结构

```
├── src/
│   ├── main/
│   │   ├── java/           # Java源代码目录
│   │   └── resources/      # 配置文件和其他资源文件
│   └── test/               # 测试代码
├── pom.xml                 # Maven项目配置文件
└── README.md               # 项目说明文档
```

## 📝 文档

更多详细的开发文档、API参考以及贡献指南，请查看[这里](docs/)。

## 👥 贡献者

感谢所有为本项目做出贡献的人士。如果您有兴趣参与开发或有任何建议，请提交PR或者issue给我们。

## 💬 反馈与支持

对于任何问题或想要提出反馈，请访问我们的[论坛/GitHub Issues页面]。

## 📜 许可证

该项目采用MIT License，详情见[LICENSE](LICENSE)文件。

