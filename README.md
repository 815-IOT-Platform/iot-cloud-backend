## 安安运维平台简介
![架构图](./doc/ruoyi-cloud.png)

## 快速开始

数据库文件放在了阿里云服务器上: /home/ananops-sql

依次绑定host：

127.0.0.1 ananops-eureka

127.0.0.1 ananops-geteway

127.0.0.1 ananops-discovery

127.0.0.1 ananops-db-mysql

127.0.0.1 ananops-db-redis

如果要使用eureka集群，请依次绑定eureka7002.com,eureka7003.com后修改各项目中的注释部分

部署到服务器时，默认在服务器的/home/ananops/ananops-config-repo路径下存放配置文件，本地部署时需要将discovery的bootstrap.yml文件中的basedir修改为本地配置文件所在目录（注：原配置文件放在了config目录下，在discovery启动时会被加编译到classpath中，因此每次更改配置文件，都需要重启discovery，所以目前替换为此方式）。

如果想借助服务器资源，在本地调试业务。只能使用数据库、中间件等基础设施，以及服务器上的eureka、discovery，进行服务注册、发现和配置文件获取。需要修改本地的hosts文件，把上面提到的域名对应的ip地址改为服务器的即可。

启动顺序：
- eureka
- discovery
- gateway
- system
- auth
- gen 可选
- dfs 可选

```
ananops-cloud
|
├──ananops-common --通用包
|  |
|  ├──ananops-common-core --核心工具包
|  |
|  ├──ananops-common-redis --redis工具包
|  |
|  ├──ananops-common-log --日志工具包
|  |
|  ├──ananops-common-auth --权限工具包
|
├──ananops-discovery --cloud统一配置中心
|
├──ananops-eureka --注册中心
|
├──ananops-gateway --网关
|
├──ananops-service-api --服务api模块
|  |
|  ├──ananops-system-api --系统业务api
|
├──ananops-service --微服务
|  |
|  ├──ananops-system --系统业务
|  |
|  ├──ananops-auth --授权中心
|  |
|  ├──ananops-gen --代码生成
|  |
|  ├──ananops-dfs --文件
|
├──ananops-tool --工具
|  |
|  ├──ananops-monitor --监控中心

```

## 文档
- [代码生成器使用](./doc/ananops-gen.md)
- [多数据源实现参考文章](https://mianshenglee.github.io/2020/01/16/multi-datasource-2.html)
- [如何嵌入业务模块](./doc/ananops-xxx.md)
- [数据权限实现](./doc/data-permission.md)
- [多租户实现](./doc/ananops-tenant.md)
- [单点登录接入](./doc/ananops-cas.md)