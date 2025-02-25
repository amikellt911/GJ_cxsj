public class Sum {

    public static int sum(int a, int b) {
        int sum = a + b;
        return sum;
    }

    public static void print3Lines() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 测试 sum 方法
        int result = sum(3, 5);
        System.out.println("Sum of 3 and 5 is: " + result);

        // 测试 print3Lines 方法
        System.out.println("Printing 3 blank lines:");
        print3Lines();
    }
}