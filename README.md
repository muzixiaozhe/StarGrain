# StarGrain
## 项目介绍
StarGrain学生社区作为本人的毕业设计作品,初衷是为了让同学们可以在该社区发表博客，记录学习经历,进行技术问答，话题讨论等,使用的Springboot2.0版本,由Maven管理项目,ORM框架使用了SpringDataJPA自动生成数据库表，权限管理用的是SpringSecurity.数据库使用MySQL,使用Elasticsearch实现搜索和排名,模板引擎使用的Thymeleaf,前端使用 Bootstrap模板,支持响应式,方便移动端,电脑端不同用户。 
### 由于时间问题,只实现了登录注册,发表博客,修改博客,个人主页,博客最新最热排行等部分功能,还存在很多设计不足和实现不当,后期会继续完善

## 使用说明
- 1.该项目需要手动创建数据库blog_db(可自行修改),项目启动后会自动生成数据库表和初始信息
- 2.该项目因为使用到了Elasticsearch,所以启动时需要开启Elasticsearch,可以到官网下载,开发时使用的Elasticsearch-5.5.0,不支持2.x版本



管理员账号admin 密码123456       可进入admins后台页面
测试账号zhe 密码123456

## 效果演示

![   ](https://github.com/muzixiaozhe/StarGrain/blob/master/blog/src/main/resources/static/images/1.png)

![   ](https://github.com/muzixiaozhe/StarGrain/blob/master/blog/src/main/resources/static/images/2.png)
