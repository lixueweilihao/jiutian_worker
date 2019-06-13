package SecurityManager;


import java.io.FileInputStream;
import java.io.IOException;

public class TestMySecurityManager {
    public static void main(String[] args) {
        System.setSecurityManager(new MySecurityManager());
        try {
            FileInputStream fis = new FileInputStream("kafka");
            System.out.println(fis.read());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
