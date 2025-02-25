public class FactorialTest {
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        int i = 1;
        try {
            while (true) {
                factorial(i);
                System.out.println(" 当前可以计算阶乘的参数 : " + i);
                i++;
            }
        } catch (StackOverflowError e) {
            System.out.println(" 发生栈溢出错误，最大允许的参数大致为 : " + (i - 1));
        }
    }
}