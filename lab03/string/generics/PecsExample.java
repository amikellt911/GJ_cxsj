import java.util.ArrayList;
import java.util.List;

public class PecsExample {

    // 读操作：从源集合中读取数据，体现协变（ Covariance）
    public static <Number> void readFromSource(List<? extends Number> source) {
        // 协变：允许从子类型集合中读取数据到父类型集合
        for (Number item : source) {
            System.out.println("Read item: " + item); // 仅显示数据
        }
        return ; // 返回一个空集合，避免返回 null
    }

    // 新增：读操作，不使用协变
    public static <T> void readFromSourceWithoutCovariance(List<T> source) {
        // 不使用协变：直接操作具体类型 List<T>
        for (T item : source) {
            System.out.println("Read item without covariance: " + item); // 仅显示数据
        }
    }

    // 写操作：将数据写入目标集合，体现逆变（ Contravariance）
    public static <T> void writeToTarget(List<? super T> target, List<T> data) {
        // 逆变：允许将子类型数据写入父类型集合
        for (T item : data) {
            target.add(item); // 每个元素从 data 写入到 target
        }
    }

    public static void main(String[] args) {
        // 示例：定义不同类型的集合
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4); // 新增：添加更多元素

        List<Number> numbers = new ArrayList<>();
        numbers.add(4.0);
        numbers.add(5.0);
        numbers.add(6.0);

        List<Double> doubles = new ArrayList<>();
        doubles.add(7.0);
        doubles.add(8.0);
        doubles.add(9.0);
        doubles.add(10.0); // 新增：添加更多元素

        // 调用读操作和写操作
        System.out.println("Using readFromSource with integers:");
        readFromSource(integers);

        System.out.println("Using readFromSource with doubles ( 协变灵活性 ):");
        readFromSource(doubles);

        System.out.println("Using readFromSourceWithoutCovariance with integers:");
        readFromSourceWithoutCovariance(integers);

        // 输出完整的集合内容
        System.out.println("Final content of numbers list:");
        for (Number num : numbers) { // 新增：循环输出所有元素
            System.out.println("Number: " + num);
        }

        List<Integer> data = new ArrayList<>();
        writeToTarget(numbers, data);

        // 输出结果
        System.out.println("Numbers List: " + numbers);
    }
}