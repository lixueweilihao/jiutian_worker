package kafka;

import java.io.IOException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/15  18:22
 */
public class testMain {
    private static void testA() throws IOException {
        System.out.println("aaaa");
        throw new IOException();
    }
    private void testB() throws InterruptedException{
        System.out.println("Before function B");
        try {
            testA();
        }catch (IOException ex){
            throw new ChannelException("Put failed due to IO error ",ex);
        }
        System.out.println("After function b");
    }
    public static void main(String[] args)  {
        testMain tm = new testMain();
        try {
            tm.testB();
        } catch (InterruptedException e) {
            System.out.println("finally");
        }
    }
}
