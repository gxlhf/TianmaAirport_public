# 航班信息管理系统开发规范

## 1.变量命名规范
变量都使用英文全称，如果该变量为多个词组成，如`我的名字`，那么为`myName`，即第二个词首字母大写
最好在自己的java文件的开头有注释并写上变量名的含义。如：   `//name:姓名`
每个函数头需有简单的注释说明，尤其是会供他人使用的部分    如：   `String func(String a){   // func：输入：任意字符串        输出：该字符串的逆序`


## 2.问题汇总
1.jre版本不同引起报错。解决：项目右键->Properties->Java Build Path->Add Library->JRE System Library->Workspace default JRE