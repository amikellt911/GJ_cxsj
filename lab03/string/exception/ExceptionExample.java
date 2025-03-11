import java.io.FileReader;
import java.io.FileNotFoundException;

public class ExceptionExample {
    public static void main(String[] args) {
        try {
            FileReader file = new FileReader("nonexistent.txt"); // 可能抛出 FileNotFoundException
        } catch (FileNotFoundException e) {
            System.out.println(" msg: 文件未找到 : " + e.getMessage());
        }
    }
}