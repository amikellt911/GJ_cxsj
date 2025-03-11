import java.util.ArrayList;
import java.util.List;

class ContravarianceExample {
    public static void main(String[] args) {
        // List of Numbers
        List<Number> numberList = new ArrayList<>();
        numberList.add(1);       // Integer 是 Number 的子类
        numberList.add(3.14);    // Double 是 Number 的子类
        numberList.add(2.71);    // 新增：添加更多元素

        // List of Objects ( 逆变 )
        List<? super Integer> integerList = numberList;

        // 写入数据（逆变支持写入）
        integerList.add(10);     // OK
        integerList.add(20);     // OK
        integerList.add(30);     // 新增：添加更多元素

        // 输出所有写入的元素
        System.out.println("Elements in the contravariant list:");
        for (int i = 0; i < integerList.size(); i++) { // 新增：循环输出所有元素
            Object obj = integerList.get(i);
            System.out.println("Element " + i + ": " + obj);
        }

        // 读取数据（逆变不支持读取为具体类型）
        Object obj = integerList.get(0); // 只能读取为 Object 类型
        System.out.println("First Element: " + obj);

        // Number num = integerList.get(0); // 编译错误：无法读取为 Number 类型
    }
}