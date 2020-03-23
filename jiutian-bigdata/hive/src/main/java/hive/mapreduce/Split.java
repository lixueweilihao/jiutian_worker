package hive.mapreduce;

/**
 * Created by lihao on 2017/6/29.
 */
public class Split {
    public static String str = "boo:and:foo";
    public static void main(String[] args){
        String [] slices = str.split(":",-2);
        for(int i = 0;i<slices.length;i++) {
            System.out.print(slices[i]+" ,");
        }

    }
}
