

##  项目介绍

### 项目简介

智人App（简称智人）主要服务于周大福内部员工，能基本满足各职能部门的自我管理、考勤管理、移动打卡、移动学习、电子签署和绩效考核等功能需要。由于智人系统是早期研发，多年以来未进行大的技术升级，随着时间的推移和新技术的更新迭代，现产品在安全性、灵活度及扩展性方面都比较差，严重制约了平台的功能扩展。因此为了保障系统数据安全，满足周大福各职能部门个性化需求功能的建设需求，智人产品希望可以进行重构，以公司实际发展需要为导向，为公司的快速发展提供强有力的支持。

### 项目要点
**请一定要遵守研发规范**  地址：ctf-platform\file

### 项目特性
* 为了规范项目结构
* 项目采用服服务框架，确保所有服务独立运行，互不影响
* 项目结构
  * 服务注册与发现，配置中心[nacos]
    * 具体地址周大福提供
  * 消息中间件[kafka]
  * 分布式系统协调[zookeeper与kafka结合使用]
  * 镜像中心地址[Harbor]
    * 具体地址周大福提供
  * Feign[声明式服务调用]
  * SpringCloud Gateway[API网关]
  * 缓存采用redis
  * 文件服务器FasfDFs
  * 定时任务采用XXL-JOB


### 项目技术栈

- 项目使用皆是当前主流前后端技术栈；
- 极速启动，在IDEA和Java环境OK的情况下`1分钟之内`可正常启动微服务，可以快速拥有微服务环境和上手微服务；
- SpringBoot 2.7、SpringCloud 2021 & Alibaba 2021 一站式微服务开箱即用的解决方案；
- Spring Security OAuth2 、 Spring Cloud Gateway 、 JWT 统一认证鉴权和常用 OAuth2 授权模式扩展；
- Jenkins、K8s、Docker实现微服务持续集成与交付(CI/CD)。


### 项目目录结构
* ctf-common  帮助类
  * common-core 帮助核心
  * common-elasticsearch   ES帮助类
  * common-web web统一配置
  * common-file 统一文件上传
  * common-sms 短信
  * common-log  日志
  * common-mybatis mybatis配置
  * common-rebbitmq 消息中心
  * common-redis redis帮助类
  
* ctf-gateway 统一路由转发
* ctf-auth  统一授权服务


* ctf-admin 系统后台管理[租户管理]
    * admin-api 微服务对内API
    * admin-service 管理后台系统服务
* ctf-generator 开发工具服务
* ctf-ums 用户信息管理[租户用户管理]
    * ums-api 微服务对内API
    * ums-service 用户信息服务
* ctf-sms 运营管理[运营管理]
    * sms-api 微服务对内API
    * sms-service 运营管理服务
* ctf-wms 工作台管理[工作台]
    * wms-api 微服务对内API
    * wms-service 工作台服务
* ctf-dms 数据分析[数据分析]
    * dms-api 微服务对内API
    * dms-service 数据分析服务
    
* ctf-lab 实验室[服务限流等]  
   1. 使用ex-oms 做了限流测试 提供举例 
    
* ex-oms 订单服务[模块服务使用举例01]
    * oms-api 微服务对内API
    * oms-service 订单服务
* ex-pms 商品服务[模块服务使用举例02]
    * pms-api 无服务对内API
    * pms-service 商品服务
    
* devops  提供容器话部署等样例配置SSH
* docs 
    * diagram 对于认证授权的几种方式的时序图
    * nacos 项目基础配置
    * seata 分布式事务配置
    * sql 项目基础数据SQL文件

* middleware [上线前删除-本地中间件] 

> 项目规划按照业务模块-数据库做分库，不同的业务模块对应不同的业务数据库



### 分支管理

GIT版本管理规则
master分支
用于更新生产，必须保持与生产代码一致
master-年月日
develop分支
由开发人员在各自的feature分支开发完成后，合并至该分支
develop分支-年月日
release分支
测试环境中出现的bug，统一在该分支下进行修改，并推送至远程分支。修改内容必须合并回develop分支和master分支


### 开发&提交注释规则

命名规范
##### 包名
统一使用单数形式，全小写
##### 类名
驼峰命名+首字母大写
##### 实体类名
|名称|规则|
| ---- | ---- |
|entity	|映射数据库实体，和数据表字段完全一致|
|query	|查询传参(查询参数大于5个建议封装query对象)|
|form	|前端表单数据传参|
|dto	|RPC调用传参|
|vo	|返回前端显示|

dto、vo实体类名带后缀且全大写

正例：UserDTO、UserVO

反例：UserDto、UserVo

#####动态排序字段
|名称	|字段名	|值|
 | ---- | ---- |----|
|排序字段名|	sortField|	数据库对应列名|
|排序规则	|sortRule	|asc:升序;desc:降序|


 
#####分页参数
|名称	|字段名	|默认值|
 | ---- | ---- |----|
|排序字段名	|pageNum|	1|
|排序规则	|pageSize	|10|
 
#####方法命名
以下命名涵盖了Controller、Service和Mapper层

|作用域	|示例|
 | ---- | ---- |
|列表查询	|listUsers|
|分页查询	|listUserPages|
|单个查询	|getUser/getUserDetail/getUserInfo ...|
|新增	|saveUser/insertUser/addUser|
|修改	|updateUser|
|删除	|deleteUser|
 
#####泛型通配符
|名称	|规则|
 | ---- | ---- |
|E	|Element (在集合中使用，因为集合中存放的是元素)|
|T	|Type（Java类）|
|K	|Key（键）|
|V	|Value（值）|
|N	|Number（数值类型）|
|？	|不确定的Java类型|
|S、U、V	|2nd、3rd、4th types|
 
###方法规范
#####禁止Service跨域调用Mapper
正例：UserService 直接调用 UserMapper
反例：UserService 直接调用 DeptMapper
#####方法体行数
单个方法体不建议超过50行或一屏，超过建议封装


###API规范
#####RESTful风格
在RESTful架构中，每个URL代表一种资源，所以不能有动词，只能有名词，而且所用的名词往往与数据库的表格名对应。
一般来说，数据库中的表都是同种记录的"集合"，所以API中的名词也应该使用复数。

|请求名称	|请求方法|请求路径|
 | ---- | ---- |----|
|获取所有用户信息	|GET|	/api/v1/users|
|获取标识为1用户信息|	GET|	/api/v1/users/1|
|删除标识为1用户信息|	DELETE|	/api/v1/users/1|
|新增用户	|POST|	/api/v1/users|
|修改标识为1用户信息|	PUT	|/api/v1/users/1|
|修改标识为1用户部分信息|	Patch|	/api/v1/users/1|
|获取当前登录用户信息	|GET|	/api/v1/users/me|
 


## 项目启动

###  后端启动

> `极速启动` 是方便快速启动查看效果的启动方式，其中的数据库和Redis等中间件使用的是提供的云环境，切勿修改数据，有时间条件建议`本地启动`。

####  快速启动

1. **启动 Nacos**


- 启动 Nacos 服务。


2. **服务启动**

    - `ctf-gateway` 模块的启动类 GatewayApplication 启动网关；

    - `ctf-auth` 模块的启动类 AuthApplication 启动认证中心；

    - `ctf-admin`  → `admin-service` 模块的启动类 AdminApplication 启动系统服务；

    - 至此完成基础服务的启动，商城服务按需启动，启动方式和 `ctf-admin` 一致；

    - 访问接口文档地址测试: [http://localhost:9999/doc.html](http://localhost:9999/doc.html)

#### 本地启动

1. **中间件安装**

   > 为了避免数据和线上环境冲突，MySQL 和 Redis 必装，其他不安装可默认使用线上环境(🔴必装 ⚪可选)

    - 🔴 MySQL &nbsp;&nbsp;
    - 🔴 Redis &nbsp;&nbsp;
    - ⚪ RabbitMQ &nbsp;&nbsp;
    - ⚪ Seata &nbsp;&nbsp;
    - ⚪ Sentinel &nbsp;&nbsp;
    - ⚪ Canal &nbsp;&nbsp;

2. **数据库创建和数据初始化**

    - **系统数据库**

      进入 `docs/sql` 目录 ， 根据 MySQL 版本选择对应的脚本；

      先执行 `database.sql` 完成数据库的创建；

      再执行 `ctf_manager.sql` 完成数据表的创建和数据初始化。

   

3.**导入Nacos配置**
   地址栏输入 Nacos 控制台的地址 [ http://localhost:8848/nacos]( http://localhost:8848/nacos) ；

   输入用户名/密码：nacos/nacos ；

   进入控制台，点击左侧菜单 `配置管理` → `配置列表` 进入列表页面，点击 `导入配置` 选择项目中的 `docs/nacos/DEFAULT_GROUP.zip` 文件。

4.**修改Nacos配置**

   在 Nacos 控制台配置列表选择共享配置 `ctf-common.yaml` 进行编辑，修改 MySQL、Redis、RabbitMQ等中间件信息为您自己本地环境，默认「」线上环境。

5.**修改Nacos配置中心地址**

   批量替换应用的 bootstrap-dev.yml 文件的配置中心地址 `http://localhost:8848` → `http://localhost:8848` ，默认「」线上的配置中心地址。

6.**服务启动**

    - 进入 `ctf-gateway` 模块的启动类 GatewayApplication 启动网关；

    - 进入 `ctf-auth` 模块的启动类 AuthApplication 启动认证授权中心；

    - 进入 `ctf-admin`  → `admin-service` 模块的启动类 AdminApplication 启动系统服务；

    - 至此完成基础服务的启动，其他服务按需启动，启动方式和 `ctf-admin` 一致;

    - 访问接口文档地址测试:  [http://localhost:9999/doc.html](http://localhost:9999/doc.html)
