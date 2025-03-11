在 Java 中，`StringBuilder` 的 `ensureCapacity(int minimumCapacity)` 方法用于确保当前字符数组的容量至少达到指定值。其实现核心是 **动态扩容机制**，目的是避免频繁的数组拷贝操作。以下是其实现原理和示例：

---

### **1. 扩容机制原理**
`StringBuilder` 内部通过 `char[] value` 存储字符，默认初始容量为 **16**。当容量不足时，`ensureCapacity` 会触发扩容，规则如下：

#### **扩容公式**
1. **新容量计算**：
   - 新容量 = `(旧容量 * 2) + 2`
   - 如果 `minimumCapacity` 大于计算的新容量，则直接使用 `minimumCapacity`。
2. **容量上限**：
   - 最大容量为 `Integer.MAX_VALUE - 8`（避免内存溢出）。

#### **源码关键逻辑**
```java
public void ensureCapacity(int minimumCapacity) {
    if (minimumCapacity > value.length) {
        expandCapacity(minimumCapacity);
    }
}

void expandCapacity(int minimumCapacity) {
    int newCapacity = (value.length * 2) + 2; // 默认扩容公式
    if (newCapacity - minimumCapacity < 0) {
        newCapacity = minimumCapacity; // 直接使用指定容量
    }
    if (newCapacity < 0) { // 处理溢出
        if (minimumCapacity < 0) throw new OutOfMemoryError();
        newCapacity = Integer.MAX_VALUE; // 最大容量
    }
    value = Arrays.copyOf(value, newCapacity); // 创建新数组并拷贝数据
}
```

---

### **2. 示例说明**
#### **示例 1：默认扩容**
```java
StringBuilder sb = new StringBuilder(); // 初始容量 16
sb.ensureCapacity(20); // 要求容量 ≥20

// 计算过程：
// 旧容量 = 16
// 新容量 = 16*2 +2 = 34 → 34 >20 → 最终容量 34
System.out.println(sb.capacity()); // 输出 : 34
```

#### **示例 2：指定容量大于默认扩容**
```java
StringBuilder sb = new StringBuilder(10); // 初始容量 10
sb.ensureCapacity(50); // 要求容量 ≥50

// 计算过程：
// 旧容量 = 10
// 默认新容量 = 10*2 +2 = 22 → 22 <50 → 最终容量 50
System.out.println(sb.capacity()); // 输出 : 50
```

#### **示例 3：接近容量上限**
```java
StringBuilder sb = new StringBuilder();
sb.ensureCapacity(Integer.MAX_VALUE - 7); // 要求容量接近上限

// 计算过程：
// 旧容量 = 16 → 多次扩容后最终容量为 Integer.MAX_VALUE -8
```

---

### **3. 扩容流程总结**
1. **容量检查**：若当前容量不足，触发扩容。
2. **新容量计算**：
   - 优先按 `旧容量*2 +2` 扩容。
   - 若仍不足，直接使用 `minimumCapacity`。
3. **数据迁移**：通过 `Arrays.copyOf()` 将原数据复制到新数组中。

---

### **4. 使用场景**
- **预先分配空间**：已知字符串最终长度时，提前扩容避免多次拷贝。
  ```java
  StringBuilder sb = new StringBuilder();
  sb.ensureCapacity(1000); // 提前分配 1000 容量
  for (int i = 0; i < 1000; i++) {
      sb.append(i); // 避免多次扩容
  }
  ```
- **性能优化**：减少动态扩容次数，提升高频修改操作的效率。

---

### **5. 注意事项**
- **负值参数**：若 `minimumCapacity` 为负数，直接忽略（不执行扩容）。
- **容量上限**：最大容量为 `Integer.MAX_VALUE - 8`，超出可能抛出 `OutOfMemoryError`。

---

通过合理使用 `ensureCapacity`，可以显著优化 `StringBuilder` 的性能，减少内存分配和数据拷贝开销。