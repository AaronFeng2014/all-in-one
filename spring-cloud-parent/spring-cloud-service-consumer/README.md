# 手机商城展示页面

## 接口设计原则
* 基于[RESTful api设计原则]()设计本系统的各个接口
* 接口返回类型都为ResponseEntity，可以自定义返回状态码，无需再返回json中添加类似于status的字段，防止网络攻击

## 接口文档管理
* 使用[swagger 2](https://swagger.io/)进行文档管理， 方便测试和联调