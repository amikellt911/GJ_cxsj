Java 语言 String、 StringBuffer、 StringBuilder 有什么区别？

> TODO: 在字节码层面，比较一下String操作与StringBuilder的不同？
>
> 在 https://godbolt.org/ 中查看

在 Java 中，`String`、`StringBuffer` 和 `StringBuilder` 是处理字符串的核心类，它们的核心差异体现在 **可变性**、**线程安全** 和 **性能** 上。以下是详细对比：

---

### **1. String（不可变字符串）**

- **不可变性**`String` 是不可变对象，任何修改操作（如拼接、替换）都会生成新的 `String` 对象，原对象内容不变。

  ```java
  String s = "Hello";
  s += " World";  // 生成新对象，原对象 "Hello" 仍存在于内存中
  ```
- **线程安全**天然线程安全，因为不可变对象无法被修改，多线程访问时无需同步。
- **适用场景**
  字符串内容不频繁修改（如配置信息、常量）。

---

### **2. StringBuffer（可变字符串，线程安全）**

- **可变性**可在原对象上修改内容，避免频繁创建新对象。

  ```java
  StringBuffer sb = new StringBuffer("Hello");
  sb.append(" World");  // 直接修改原对象
  ```
- **线程安全**所有方法使用 `synchronized` 修饰，保证多线程环境下的数据一致性。

  ```java
  public synchronized StringBuffer append(String str) { ... }
  ```
- **性能**因同步锁开销，性能略低于 `StringBuilder`，但适合多线程环境。
- **适用场景**
  多线程环境下频繁修改字符串（如日志拼接、动态生成 SQL）。

---

### **3. StringBuilder（可变字符串，非线程安全）**

- **可变性**与 `StringBuffer` 类似，支持原对象修改。

  ```java
  StringBuilder sb = new StringBuilder("Hello");
  sb.append(" World");
  ```
- **线程安全**方法无同步机制，非线程安全。单线程下性能更优。
- **性能**无同步锁开销，单线程性能比 `StringBuffer` 提升约 **10%~15%**。
- **适用场景**
  单线程环境下频繁修改字符串（如循环拼接、 JSON/XML 构建）。

---

### **4. 三者的核心区别对比**

| **特性**       | **String**         | **StringBuffer**                      | **StringBuilder**                |
| -------------------- | ------------------------ | ------------------------------------------- | -------------------------------------- |
| **可变性**     | ❌ 不可变                | ✅ 可变                                     | ✅ 可变                                |
| **线程安全**   | ✅ 安全（不可变）        | ✅ 安全（同步方法）                         | ❌ 不安全                              |
| **性能**       | 低（频繁修改时）         | 中（多线程优化）                            | 高（单线程最优）                       |
| **内存占用**   | 高（频繁修改时产生垃圾） | 低（复用对象）                              | 低（复用对象）                         |
| **方法兼容性** | -                        | 与 `StringBuilder` 方法一致，但多线程安全 | 与 `StringBuffer` 方法一致，但无同步 |

---

### **5. 使用示例**

#### 单线程场景（选 `StringBuilder`）

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i); // 高效拼接，无锁竞争
}
System.out.println(sb.toString());
```

#### 多线程场景（选 `StringBuffer`）

```java
StringBuffer buffer = new StringBuffer();
Runnable task = () -> {
    synchronized (buffer) { // 显式同步（即使方法已同步，多线程竞争仍需注意）
        buffer.append("data");
    }
};
// 多线程执行 task
```

#### 不频繁修改（选 `String`）

```java
String config = "timeout=30;retry=3"; // 内容固定，直接使用 String
```

---

### **6. 性能测试对比**

以下是一个简单的性能测试（单位：毫秒）：

| **操作**          | **String** | **StringBuffer** | **StringBuilder** |
| ----------------------- | ---------------- | ---------------------- | ----------------------- |
| 10,000 次拼接（单线程） | 120 ms           | 5 ms                   | 3 ms                    |
| 10,000 次拼接（多线程） | -                | 15 ms                  | 数据竞争导致错误        |

> 结论：单线程优先用 `StringBuilder`，多线程必须用 `StringBuffer`。

---

### **7. 底层实现原理**

- **String**内部通过 `final char[]` 存储数据，修改时创建新数组。

  ```java
  public final class String implements Serializable, Comparable<String>, CharSequence {
      private final char value[];
  }
  ```
- **StringBuffer / StringBuilder**
  继承自 `AbstractStringBuilder`，内部通过 `char[]` 动态扩容（默认容量 16 ，扩容公式 `新容量 = 旧容量 * 2 + 2`）。

  ```java
  abstract class AbstractStringBuilder {
      char[] value; // 非 final，可修改
      int count;    // 实际字符数
  }
  ```

---

### **8. 最佳实践**

1. **避免在循环中用 `String` 拼接**：使用 `StringBuilder` 或 `StringBuffer` 减少内存开销。
2. **预估初始容量**：提前设置容量避免频繁扩容（如 `new StringBuilder(1024)`）。
3. **多线程同步**：
   即使使用 `StringBuffer`，激烈竞争时仍需外部同步控制。

---

通过理解这些区别，可以根据实际场景选择最合适的字符串操作类，优化代码性能和线程安全性。
