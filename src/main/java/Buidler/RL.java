package Buidler;


public class RL {
    public static void main(String[] args) {
        int number = 1;
        //原始数二进制
        printInfo(number);
        number = number << 16;
        //左移一位
        printInfo(number);
        number = number >> 16;
        //右移一位
        printInfo(number);
    }

    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }
}
