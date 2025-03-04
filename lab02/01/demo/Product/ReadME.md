# Product 项目说明

## 项目概述
本项目是一个产品管理项目，主要用于演示如何使用 Java 进行面向对象编程，同时包含了 JUnit 5 单元测试框架的使用。

## 项目结构
项目采用标准的 Maven 项目结构，主要包含以下几个部分：
- `src/main/java`：存放项目的主要 Java 源代码。
- `src/test/java`：存放项目的测试代码。
- `build.gradle`： Gradle 构建脚本，用于配置项目的构建和依赖管理。
- `pom.xml`： Maven 项目配置文件，同样用于管理项目的依赖和构建。

## 项目类图
下面是本项目的类图，展示了主要类及其关系。

```mermaid
classDiagram
    class User {
        + // 这里可根据实际 User 类的方法添加，如登录、注册等
    }
    class Product {
        +getPrice()
        +setPrice(double)
    }
    class Order {
        +computeTotalPrice()
        +setItems(OrderItem[])
    }
    class OrderItem {
        +OrderItem(Product, int)
        +computePrice()
    }
    User "1" -- "*" Order : creates
    Order "1" -- "*" OrderItem : contains
    OrderItem "1" -- "1" Product : refers to