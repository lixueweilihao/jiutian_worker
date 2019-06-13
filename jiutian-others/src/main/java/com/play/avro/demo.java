package com.play.avro;

/**
 * Created on 2019-05-21
 *
 * @author :hao.li
 */
public class demo {
    public static void main(String[] args) {
//        FileSystem fs = FileSystems.getDefault();
//        PathMatcher pm = fs.getPathMatcher("glob:*.log");
//        System.out.println(pm.matches(Paths.get("a.log")));
        String string = "aaa456ac";
        //查找指定字符是在字符串中的下标。在则返回所在字符串下标；不在则返回-1.
        System.out.println(string.indexOf(97)); // indexOf(String str); 返回结果：-1，"b"不存在

//        // 从第四个字符位置开始往后继续查找，包含当前位置
//        System.out.println(string.indexOf("a",3));//indexOf(String str, int fromIndex); 返回结果：6
//
//        //（与之前的差别：上面的参数是 String 类型，下面的参数是 int 类型）参考数据：a-97,b-98,c-99
//
//        // 从头开始查找是否存在指定的字符
//        System.out.println(string.indexOf(99));//indexOf(int ch)；返回结果：7
//        System.out.println(string.indexOf('c'));//indexOf(int ch)；返回结果：7
//
//        //从fromIndex查找ch，这个是字符型变量，不是字符串。字符a对应的数字就是97。
//        System.out.println(string.indexOf(97,3));//indexOf(int ch, int fromIndex); 返回结果：6
//        System.out.println(string.indexOf('a',3));//indexOf(int ch, int fromIndex); 返回结果：6
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
        ASCIIToConvert();
    }

    public static void ConvertToASCII() {
        String string = "193521.641";
        StringBuilder sb = new StringBuilder();
        char[] ch = string.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            sb.append(Integer.valueOf(ch[i]).intValue()).append("  ");// 加空格
            // sb.append(Integer.valueOf(ch[i]).intValue());// 不加空格
            System.out.println(sb.toString());
        }
    }
    public static void ASCIIToConvert() {

        String value = "49  58  51  53  50  49  46  54  52  49  ";

        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split("  ");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        System.out.println(sbu.toString());
    }
}
