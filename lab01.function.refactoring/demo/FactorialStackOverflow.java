public class FactorialStackOverflow {
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        // 传入一个非常大的数，这里使用 Integer.MAX_VALUE 作为示例
        int ret = factorial(Integer.MAX_VALUE);
        System.out.println(ret);
    }
}