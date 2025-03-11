import java.util.ArrayList;
import java.util.List;

class CovarianceExample {
    public static void main(String[] args) {
        // List of Integers
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3); // 新增：添加更多元素

        // List of Numbers ( 协变 )
        List<? extends Number> numberList = intList;

        // 读取数据（协变支持读取）
        System.out.println("Reading elements using covariance:");
        for (int i = 0; i < numberList.size(); i++) { // 新增：循环输出所有元素
            Number num = numberList.get(i);
            System.out.println("Element " + i + ": " + num);
        }
        Number firstNumber = numberList.get(0); // OK
        System.out.println("First Number: " + firstNumber);

        // 写入数据（协变不支持写入）
        // numberList.add(3.14); // 编译错误：无法向协变列表中添加元素
    }
}