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