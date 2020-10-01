## DVDManager
> 简单的Java学习记录,应用了各种基础知识(封装,继承,抽象,设计原则,设计模式等)

本项目简单地演示了命令行借阅系统，原型为CSDN的同名小练习。

在此项目中，对各个类和功能进行了较好的封装和分离，也“强行”应用一些设计模式和原则，以达到练习和强化基础的作用。

`master`分支适配数据库为`oracle 11g`

`mysql_ver`分支适配数据库为`mysql 5.7`

### 系统具体功能

> 可接收参数，可定义数据库地址，以方便探索学习.
>
> 不输入缺省为`192.168.113.104`,`dvd_manager`
>
> `java -jar DVDManager.jar -ip address -name dbusername -pwd password`
>
> ![image-20201001171704597](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001171707.png)

#### Admin管理系统

![image-20201001171756288](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001171758.png)

#### Reader借阅系统

![image-20201001171952904](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001171954.png)

### 简单演示

| Execute                                                      |
| ------------------------------------------------------------ |
| ![2020-10-01 16.55.54](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001172556.gif) |

| Selector |
| ------- |
| ![2020-10-01 16.56.13](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001172641.gif) |

| Login  |
| ------- |
| ![2020-10-01 16.58.46](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001172918.gif) |

| Admin |
| ------- |
| ![2020-10-01 16.56.40](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001172730.gif) |

| Reader |
| ------- |
| ![2020-10-01 16.58.23](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001172837.gif) |
| ![2020-10-01 16.59.11](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001173000.gif) |

| Handle Input |
| ------- |
| ![2020-10-01 16.56.13](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001172641.gif) |

| Others                                                       |
| ------------------------------------------------------------ |
| ![2020-10-01 16.45.57](https://cdn.jsdelivr.net/gh/gentlemanhu/public-store/images/20201001173314.gif) |

---



## TODO:

- [ ] 引入配置文件,外部配置各种参数
- [ ] Admin和Reader权限更清晰分离
- [ ] Admin权限归类和整合
- [ ] 接口与类之间的冗余耦合优化
- [ ] 更好的接口和抽象重设计
- [ ] WEB引入,GUI引入
- [ ] Spring系列结合引入改装
- [ ] ......


## Bug to fix
- [x] 添加reader打印箭头
- [x] 添加DVD显示toString
- [x] 输出reader table下边箭头错位
- [ ] Reader中借阅输入其他字符validate
- [ ] 。
