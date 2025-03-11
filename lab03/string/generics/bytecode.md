 

### 案例：通过Java代码与字节码展示类型擦除

#### 1. Java代码示例
```java
// 泛型类示例：Box<T>
public class Box<T> {
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    // 泛型方法示例
    public static <U> void printGeneric(U value) {
        System.out.println(value);
    }

    // 主方法调用泛型方法和泛型类
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.setContent("Hello");
        System.out.println("String content: " + stringBox.getContent());

        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(123);
        System.out.println("Integer content: " + integerBox.getContent());

        printGeneric("Java");
        printGeneric(42);
    }
}
```

#### 2. 编译并反编译字节码
使用 `javac` 编译代码生成 `Box.class`，然后用 `javap -v` 反编译查看字节码：

```bash
javac Box.java
javap -v Box.class > BoxBytecode.txt
```

#### 3. 关键字节码分析（反编译结果片段）

##### 3.1 泛型类 `Box<T>` 的字段
```java
private java.lang.Object content; // 类型擦除为 Object
```

##### 3.2 泛型方法 `setContent(T)` 的字节码
```java
public void setContent(java.lang.Object);
  descriptor: (Ljava/lang/Object;)V // 参数类型为 Object
```

##### 3.3 泛型方法 `getContent()` 的字节码
```java
public java.lang.Object getContent();
  descriptor: ()Ljava/lang/Object; // 返回类型为 Object
```

##### 3.4 静态泛型方法 `printGeneric<U>` 的字节码
```java
public static <U> void printGeneric(java.lang.Object);
  descriptor: (Ljava/lang/Object;)V // 参数类型为 Object
```

##### 3.5 主方法 `main` 的字节码（关键指令）
```java
// 创建 Box 对象（擦除为 Box）
new Box
// 调用 setContent 时参数类型为 Object
invokevirtual Box.setContent (Ljava/lang/Object;)V
// 调用 printGeneric 时参数类型为 Object
invokestatic Box.printGeneric (Ljava/lang/Object;)V
```

#### 4. 类型擦除的观察点
1. **字段类型擦除**  
   `Box<T>` 的字段 `content` 在字节码中变为 `Object`：
   ```java
   private T content;  // Java代码
   private java.lang.Object content; // 字节码
   ```

2. **方法参数与返回值类型擦除**  
   泛型方法 `getContent()` 和 `setContent()` 在字节码中参数/返回值类型均为 `Object`：
   ```java
   public T getContent(); // Java代码
   public java.lang.Object getContent(); // 字节码
   ```

3. **泛型方法签名消失**  
   泛型方法 `printGeneric<U>` 的参数 `U` 被替换为 `Object`，方法签名中不再保留泛型信息：
   ```java
   public static <U> void printGeneric(U value); // Java代码
   public static void printGeneric(java.lang.Object); // 字节码
   ```

4. **运行时类型信息缺失**  
   尝试通过反射获取泛型参数类型会失败：
   ```java
   // 运行时无法获取实际类型参数
   Box<String> box = new Box<>();
   Type type = box.getClass().getGenericSuperclass(); // 返回 raw type
   ```

#### 5. 类型擦除的影响
- **运行时无泛型信息**：无法通过反射获取泛型参数类型。
- **编译时类型检查**：编译器插入类型转换，但运行时可能发生 `ClassCastException`：
  ```java
  Box<String> box = new Box<>();
  box.setContent("Test");
  Object obj = box.getContent(); // 运行时类型为 String，但字节码返回 Object
  ```
- **无法使用原始类型**：`Box<int>` 无效，必须使用包装类型 `Integer`。

#### 6. 总结
类型擦除将泛型代码转换为原始类型（如 `Object`），并保留编译时类型检查。这使得旧代码与泛型代码兼容，但导致运行时无法保留泛型类型信息。通过分析字节码，可以直观看到泛型参数被替换为 `Object`，从而验证类型擦除的核心机制。

```bash
# 反编译命令示例
javap -c Box.class
javap -s Box.class # 查看方法签名
javap -p Box.class # 查看私有字段
```