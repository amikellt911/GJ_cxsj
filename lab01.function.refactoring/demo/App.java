// 定义一个名为 HelloWorld 的公共类
public class App {
    // 程序的入口点， Java 程序从这里开始执行   
     // 程序的入口点， Java 程序从这里开始执行
    public static void main(String[] args) {
        // 生成前100个素数并存储在数组中
        int[] res = PrimeGenerator.generatePrimes(1000);
        // 遍历素数数组并打印每个素数
        for (int i : res) {
            System.out.println(i);
        }
    }
}