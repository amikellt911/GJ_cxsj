import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformanceTest {

    // 新增多线程性能测试方法
    public static void multiThreadTest() {
        int iterations = 10000; // 每个线程的拼接次数
        int threadCount = 10; // 线程数量

        // 测试 StringBuffer 多线程性能
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        StringBuffer sharedBuffer = new StringBuffer();
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                for (int j = 0; j < iterations; j++) {
                    sharedBuffer.append("a");
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("StringBuffer 多线程拼接耗时 : " + (endTime - startTime) + " ms");

        // 测试 StringBuilder 多线程性能
        startTime = System.currentTimeMillis();
        ExecutorService executor2 = Executors.newFixedThreadPool(threadCount);
        StringBuilder sharedBuilder = new StringBuilder();
        for (int i = 0; i < threadCount; i++) {
            executor2.submit(() -> {
                for (int j = 0; j < iterations; j++) {
                    sharedBuilder.append("a");
                }
            });
        }
        executor2.shutdown();
        try {
            executor2.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder 多线程拼接耗时 : " + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        int iterations = 100000; // 拼接次数

        // 测试 String 性能
        long startTime = System.currentTimeMillis();
        String resultString = "";
        for (int i = 0; i < iterations; i++) {
            resultString += "a"; // 使用 + 拼接
        }
        long endTime = System.currentTimeMillis();
        System.out.println("String 拼接耗时 : " + (endTime - startTime) + " ms");

        // 测试 StringBuffer 性能
        startTime = System.currentTimeMillis();
        StringBuffer resultStringBuffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            resultStringBuffer.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer 拼接耗时 : " + (endTime - startTime) + " ms");

        // 测试 StringBuilder 性能
        startTime = System.currentTimeMillis();
        StringBuilder resultStringBuilder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            resultStringBuilder.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder 拼接耗时 : " + (endTime - startTime) + " ms");

        // 调用多线程性能测试
        multiThreadTest();
    }
}