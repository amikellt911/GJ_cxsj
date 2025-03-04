## 实验报告

![Logo](./assets/wps2.png)

- 课程名称 : **高级程序设计**
- 课程号 : **B01080**
- 实验项目 : **编码与面向对象SOLID**
- 专业班级 : **计算 xxxx**
- 学生学号 : **xxxxx**
- 学生姓名 : **xxxx**
- 所在学院 : **计算学院**
- 指导教师 : **郭鸣**

### 实验目的和要求

1. 熟悉编码的基本原理
2. 掌握面向对象设计SOLID原则

### 实验内容和原理

#### 1. 阅读教材第 2 章

- 在VS Code 里面 文本编辑器，安装[binary-viewer](https://marketplace.visualstudio.com/items?itemName=qiaojie.binary-viewer)，输入"ÀÏÂí"，点击状态栏“选择编码”

  - 查看在  “表2-9　尝试不同编码方式进行恢复” 里面不同的二进制表示（Windows-1252/GB18030/BIG5/UTF-8）
- 在 `jshell`里面上输入下面的代码，恢复 "ÀÏÂí" 的乱码

```java
String str = "ÀÏÂí";
String newStr = new String(str.getBytes("windows-1252"),"GB18030");
System.out.println(newStr);
```

- 自己选择一些乱码运行下面代码，需要有成功恢复的例子

```java
public static void recover(String str)
        throws UnsupportedEncodingException{
    String[] charsets = new String[]{
            "windows-1252","GB18030","Big5","UTF-8"};
    for(int i=0;i<charsets.length;i++){
        for(int j=0;j<charsets.length;j++){
            if(i!=j){
                String s = new String(str.getBytes(charsets[i]),charsets[j]);
                System.out.println("---- 原来编码(A)假设是: "
                    +charsets[j]+", 被错误解读为了(B): "+charsets[i]);
                System.out.println(s);
                System.out.println();
            }
        }
    }
}

```

#### 2. 类的组合

- 调试 3.2.3　用类描述电商概念 Product目录

```java
 
```

#### 3.类的继承（BASE项目代码）

- 运行调试 代码清单4-7/8/9　演示继承原理
- 理解，图4-4　继承原理：对象内存布局
- 查看 Readme.md, 学会自己在命令行运行项目
- [类与实例构造次序](https://www.doubao.com/thread/w171206e4951f62a8)

```java
├── src/
│   ├── Base.java
│   ├── Child.java
│   └── Main.java
```

- 理解 4.3.4　方法调用的过程，理解 图4-5　继承原理：虚方法表

  - 动态绑定，而动态绑定实现的机制就是根据对象的实际类型查找要执行的方法，子类型中找不到的时候再查找父类。 这里，因为b和c指向相同的对象，所以执行结果是一样的。
  - 请给Child.java添加一个虚方法 action（）看运行，结果会有哪些不同

#### 4. 接口与抽象类

- 阅读 5.1　接口的本质 5.2　抽象类
- 阅读 Head First Design Pattern一书
  - 学习 p39-58 duck 例子(参考 Demo/Duck)
  - 深入理解面向对象设计与接口的作用（参考Demo/Duck/chat.md）

    - 提示词

      ```text
      #codebase
      我想结合Duck 项目理解接口的使用，请帮我提出5个关于项目的问题。

      #codebase
      我想结合Duck 项目理解面向对象设计的思想，请帮我提出5个关于项目的问题。
      ```
  - 参考[advprog网站][web]，理解构建工具的使用

    - 请给duck 项目添加 `maven`或 `gradle`的构建文件

#### 5. 面向对象设计原则  SOLID 设计原则

- 学习理解 面向对象设计原则
- 理解 compress 项目的代码
- （附加）完成项目中的实验

### 提交方式

- 打包 zip 上传
- 实验报告采用 Markdown 格式
- zip 内容包括 Markdown 文本、代码、部分体现实验过程的典型截屏 (.png 格式 )

### 评阅

指导教师评语 :

实验报告评分（百分制）： 分

指导教师签名：

日 期： 2025 年 3 月 14 日

[web]: https://advprog25.pages.dev/
