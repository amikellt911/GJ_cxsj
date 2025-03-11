### 1. 泛型的协变与逆变概述
在 Java 中，泛型默认是**不变的（Invariant）**，即 `List<String>` 和 `List<Object>` 之间没有继承关系。然而，通过使用通配符（`? extends` 和 `? super`），可以实现**协变（Covariance）**和**逆变（Contravariance）**。

- **协变（Covariance）**：允许泛型类型参数是某个类或其子类（只读场景）。  
  - 使用 `? extends` 表示协变。
  - 示例：`List<? extends Number>` 可以存储 `Number` 或其子类（如 `Integer`、`Double`）的对象。

- **逆变（Contravariance）**：允许泛型类型参数是某个类或其父类（只写场景）。  
  - 使用 `? super` 表示逆变。
  - 示例：`List<? super Integer>` 可以存储 `Integer` 或其父类（如 `Number`、`Object`）的对象。

---

### 2. 协变（Covariance）详细说明

#### 定义
协变允许泛型类型参数是某个类或其子类，适用于**只读场景**。  
语法：`? extends T`，表示泛型类型可以是 `T` 或其子类。

#### 示例代码
```java
import java.util.ArrayList;
import java.util.List;

class CovarianceExample {
    public static void main(String[] args) {
        // List of Integers
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);

        // List of Numbers (协变)
        List<? extends Number> numberList = intList;

        // 读取数据（协变支持读取）
        Number firstNumber = numberList.get(0); // OK
        System.out.println("First Number: " + firstNumber);

        // 写入数据（协变不支持写入）
        // numberList.add(3.14); // 编译错误：无法向协变列表中添加元素
    }
}
```

#### 解释
- `List<? extends Number>` 表示该列表可以存储 `Number` 或其子类（如 `Integer`、`Double`）的对象。
- **协变支持读取**：可以从列表中读取 `Number` 类型的对象。
- **协变不支持写入**：不能向协变列表中添加任何对象（除了 `null`），因为编译器无法确定具体类型。

---

### 3. 逆变（Contravariance）详细说明

#### 定义
逆变允许泛型类型参数是某个类或其父类，适用于**只写场景**。  
语法：`? super T`，表示泛型类型可以是 `T` 或其父类。

#### 示例代码
```java
import java.util.ArrayList;
import java.util.List;

class ContravarianceExample {
    public static void main(String[] args) {
        // List of Numbers
        List<Number> numberList = new ArrayList<>();
        numberList.add(1);       // Integer 是 Number 的子类
        numberList.add(3.14);    // Double 是 Number 的子类

        // List of Objects (逆变)
        List<? super Integer> integerList = numberList;

        // 写入数据（逆变支持写入）
        integerList.add(10);     // OK
        integerList.add(20);     // OK

        // 读取数据（逆变不支持读取为具体类型）
        Object obj = integerList.get(0); // 只能读取为 Object 类型
        System.out.println("First Element: " + obj);

        // Number num = integerList.get(0); // 编译错误：无法读取为 Number 类型
    }
}
```

#### 解释
- `List<? super Integer>` 表示该列表可以存储 `Integer` 或其父类（如 `Number`、`Object`）的对象。
- **逆变支持写入**：可以向列表中添加 `Integer` 类型的对象。
- **逆变不支持读取为具体类型**：只能将元素读取为 `Object` 类型，因为编译器无法确定具体类型。

---

### 4. 总结对比

| 特性              | 协变（`? extends T`）                     | 逆变（`? super T`）                     |
|-------------------|------------------------------------------|-----------------------------------------|
| **用途**          | 只读场景                                 | 只写场景                                |
| **支持读取**      | 支持，可读取为 `T` 类型                 | 不支持，只能读取为 `Object` 类型        |
| **支持写入**      | 不支持，无法添加任何对象（除了 `null`） | 支持，可添加 `T` 或其子类的对象         |
| **典型用例**      | 数据处理（如返回值）                    | 数据写入（如方法参数）                  |

---

### 5. 实际应用场景
- **协变**：适用于需要从集合中读取数据但不需要修改的场景，例如：
  ```java
  public void processNumbers(List<? extends Number> numbers) {
      for (Number num : numbers) {
          System.out.println(num);
      }
  }
  ```

- **逆变**：适用于需要向集合中写入数据但不需要读取的场景，例如：
  ```java
  public void addIntegers(List<? super Integer> integers) {
      integers.add(1);
      integers.add(2);
  }
  ```

通过合理使用协变和逆变，可以在保证类型安全的同时提高代码的灵活性。