# 多租户

- 实现方法：通过 Mybatis-Plus多租户SQL解析器
- 官方文档：[多租户SQL解析器](https://mybatis.plus/guide/tenant.html)

### 使用方法 
- 添加依赖
    ```
 		<dependency>
 			<groupId>com.baomidou</groupId>
 			<artifactId>mybatis-plus-boot-starter</artifactId>
 			<version>3.0-RC3</version>
 		</dependency>
    ```

- 添加 MybatisPlusConfig

- 替换配置
注释掉bootstrap.yml里的 MyBatis 配置
在application.yml里添加 MyBatis-Plus 配置

### 注意点

- 数据库表不存在company_id字段的,应在MybatisPlusConfig进行排除，因为多租户SQL解析器会自动为添加条件语句:company_id = XXX
- 数据库表存在company_id字段的,应附上初值，以免查询出错。