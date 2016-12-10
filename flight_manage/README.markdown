# 机场信息管理系统开发规范

## 1.变量命名规范
变量都使用英文全称，如果该变量为多个词组成，如`我的名字`，那么为`myName`，即第二个词首字母大写
最好在自己的java文件的开头有注释并写上变量名的含义。如：   `//name:姓名`
每个函数头需有简单的注释说明，尤其是会供他人使用的部分    如：   `String func(String a){   // func：输入：任意字符串        输出：该字符串的逆序`

## 2.权限名称确定
该系统共有五大权限：
###角色管理
###用户管理
###机场设施管理
###航班信息管理
###新闻管理
以上权限名称在数据库中需一致，如需修改请对应修改Admin.java文件中authorityValidate函数的内容。

## 3.问题汇总
1.jre版本不同引起报错。解决：项目右键->Properties->Java Build Path->Add Library->JRE System Library->Workspace default JRE
2.当页面有数据表格，需要选择数据表格中的数据项传入jsp页面时，tr标签中的data-id属性填写的是需要进行修改或删除时向对应jsp传送的参数字符串。eg.需要将变量名为"EmpNo"值为"1004"和变量名为"Type"值为"1"的两个参数传送到jsp页面，则data-id填写的是"EmpNo=1004&Type=1"