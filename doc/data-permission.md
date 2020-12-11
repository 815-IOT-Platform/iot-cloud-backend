# 数据权限

### DataScope 注解

- 功能：目前该注解支持以下数据过滤
  - 全部数据权限
  - 自定数据权限：目前支持自定义查看哪些部门的数据
  - 部门数据权限
  - 部门及以下数据权限
  - 仅本人数据权限
- 使用位置：方法上

### 实现方法

方法一：可参考ananops-amc模块 <查询告警列表> 接口的实现
1. 业务表中添加 dept_id 和 creator_id 字段（延续之前的数据库规范，表结构中该字段是一定有的），
   从而实现和部门表和用户表的关联
2. 业务方法的参数需加入 domain 实体类，从而提供sql拼接参数 Map<String, Object> params
3. xml的sql语句中需添加条件语句 ${params.dataScope},从而实现sql的动态拼接
4. 在service层相应的方法上添加注解 @DataScope(deptAlias = "业务表的别名", hasCreatorId = "1")

方法二：
1. 添加业务表和部门表的关联关系表，业务表中加入 creator_id 字段，从而实现和部门表和用户表的关联
2. 业务方法的参数需加入 domain 实体类，从而提供sql拼接参数 Map<String, Object> params  
3. xml的sql语句中需要和<业务表和部门表的关联关系表>进行关联
   并且需添加条件语句 ${params.dataScope},从而实现sql的动态拼接。
4. 在service层相应的方法上添加注解 @DataScope(deptAlias = "d", hasCreatorId = "1")
                       
方法三： 可参考ananops-system模块 <查询用户列表> 接口实现
1. 添加业务表和部门表的关联关系表，业务表和用户表的关联关系表，从而实现和部门表和用户表的关联
2. 业务方法的参数需加入 domain 实体类，从而提供sql拼接参数 Map<String, Object> params  
3. xml的sql语句中需要和<业务表和部门表的关联关系表>以及<业务表和用户表的关联关系表进行关联，
   并且需添加条件语句 ${params.dataScope},从而实现sql的动态拼接。
4. 在service层相应的方法上添加注解 @DataScope(deptAlias = "d", userAlias = "u")

#### 每个业务模块需参考上述方法，在实现业务时，对那些需要数据拦截的业务，进行数据权限的过滤


