# aurora-telemetry-java

## 📌 项目简介

aurora-telemetry 是一款面向终端设备的实时指标数据采集与分析平台。

该平台采用 gRPC 协议实现高效数据传输，通过 Kafka 消息队列进行流量削峰，经数据处理后存储于 ClickHouse 数据库，最终提供低延迟、高吞吐的指标数据查询服务。  

核心设计目标包括：  
✅ 毫秒级数据采集响应  
✅ 支持每秒数十万级指标写入  
✅ 横向扩展的分布式架构  
✅ 端到端数据可靠性保障


## 🧩 架构概述

系统采用模块化设计，主要包含以下组件：

- **终端设备**：负责上报指标数据。
- **aurora-translator**：接收并处理 gRPC 请求，将数据转发至 Kafka。
- **Kafka消息队列**：高吞吐的分布式消息队列，用于数据缓冲。
- **aurora-processor**：从 Kafka 消费数据，进行处理并写入 ClickHouse。
- **ClickHouse数据库**：高性能的 OLAP 数据库，用于存储和查询历史数据。
- **aurora-query**：提供 REST API 接口，用于查询 ClickHouse 中的数据。
- **aurora-common**：通用模块，包含公共依赖和工具类。

## 🔧 技术栈

- Java 17
- Spring Boot 3.x
- gRPC - 高效跨语言的远程过程调用
- Kafka - 分布式消息队列
- ClickHouse - 高性能的 OLAP 数据库
- MyBatis-Plus - ORM 框架
- Druid - 数据库连接池

## 📦 安装指南

### 前提条件

确保已安装以下软件和服务：

- Java Development Kit (JDK) 17
- Apache Maven 3.8+
- Kafka
- ClickHouse

### 安装步骤

1. 克隆仓库到本地机器上：
   ```shell
   git clone <repository-url>
   cd aurora-telemetry-java
   ```

2. 使用Maven构建项目：
   ```shell
   mvn clean install
   ```

3. 配置并启动所需的服务：
   - 启动 Kafka 服务
   - 启动 ClickHouse 服务
   - 配置各模块的 application.yml 文件，包括 Kafka 和 ClickHouse 连接信息

4. 分别启动各个模块：
   ```shell
   # 启动 translator 模块（接收 gRPC 请求）
   cd aurora-translator
   mvn spring-boot:run
   
   # 启动 processor 模块（处理数据并写入 ClickHouse）
   cd ../aurora-processor
   mvn spring-boot:run
   
   # 启动 query 模块（提供数据查询 API）
   cd ../aurora-query
   mvn spring-boot:run
   ```

## 🚀 快速开始

一旦所有服务都成功启动，您可以开始发送指标数据到指定的 gRPC 端点。gRPC 服务默认监听 19090 端口。

## 📊 功能特性

- 基于 gRPC 的高效数据采集
- 高吞吐的 Kafka 消息队列缓冲
- 高性能的数据处理和存储
- 灵活的 REST API 查询接口
- 模块化设计，易于扩展和维护

## 📁 项目结构

项目采用多模块 Maven 结构，包含以下核心模块：

```
├── aurora-common/          # 通用模块，包含公共依赖和工具类
├── aurora-translator/      # gRPC 服务模块，接收指标数据并转发到 Kafka
├── aurora-processor/       # 数据处理模块，从 Kafka 消费数据并写入 ClickHouse
│   ├── sql/                # 数据库 SQL 脚本
│   ├── clickhouse.sql      # ClickHouse 数据库初始化脚本
├── aurora-query/           # 查询模块，提供 REST API 接口
├── pom.xml                 # 父项目 Maven 配置
└── README.md               # 项目说明文档
```

## 📝 各模块功能说明

### aurora-common
通用模块，提供各模块共享的依赖和工具类，包括 gRPC 和 Protocol Buffers 相关配置。

### aurora-translator
负责接收终端设备发送的 gRPC 请求，解析请求数据，并将数据转发至 Kafka 消息队列。主要功能：
- 提供 gRPC 服务接口
- 数据格式转换
- 将数据写入Kafka 指定主题

### aurora-processor
从 Kafka 消费数据，进行处理，并将处理后的数据写入 ClickHouse 数据库。主要功能：
- 配置 Kafka 消费者
- 处理不同类型的指标数据
- 批量写入 ClickHouse 数据库

### aurora-query
提供 REST API 接口，用于查询 ClickHouse 中的指标数据。主要功能：
- 提供数据查询接口
- 集成 MyBatis-Plus 进行数据访问
- 使用 Druid 连接池管理数据库连接

## 👥 贡献者

感谢所有为本项目做出贡献的人士。如果您有兴趣参与开发或有任何建议，请提交 PR 或者 issue 给我们。

## 💬 反馈与支持

对于任何问题或想要提出反馈，请访问我们的 GitHub Issues 页面。

## 📜 许可证

该项目采用 MIT License，详情见[LICENSE](LICENSE)文件。

