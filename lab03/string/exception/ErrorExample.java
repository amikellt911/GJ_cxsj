public class ErrorExample {
    public static void main(String[] args) {
        recursiveMethod(); // 栈溢出错误
    }

    public static void recursiveMethod() {
        recursiveMethod(); // 无限递归，最终导致 StackOverflowError
    }
}