# ProPlatform
项目发布平台
---------------
## 开发环境
>**IDEA+JDK 8+Tomcat+Maven+MySQL+Redis**

## 项目技术栈
>**SSM(Spring+SpringMVC+Mybatis)+Shiro**

## 项目基础框架下载与开发准备
* 使用git从github上fork到自己的本地仓库，再clone到本地（详细团队协作过程可搜 forking 工作流）。
* 下载并配置Maven，pom.xml会自动导入Jar包
* 使用项目的sql目录下的sql脚本初始化数据库，修改配置文件中数据库的用户名密码
* 在IDEA-->File-->Settings-->Plugins搜索并安装lombok插件

## 注意事项

### 规范
* 参数命名使用驼峰命名法，并与接口文档相对应。
* 方法注释规范：
> /** 方法功能说明 \
>   * @param 参数 \
>   * @return 返回值说明 \
>   * @xception 捕获异常说明 \
>   */ \
* 单元测试的规范间项目test文件夹中的例子，功能模块开发完成后需要完成单元测试再提交。

### 开发中
* 项目的中的 .mwb文件是数据库的E-R图，用MysqlWorkbench打开。
* 开发时方法要考虑到成功和失败两种结果。
* 功能模块开发并测试完成后push到项目的dev分支。

