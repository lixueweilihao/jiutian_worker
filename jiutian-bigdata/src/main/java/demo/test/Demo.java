package demo.test;

/**
 * Created on 2019-10-14
 *
 * @author :hao.li
 */
public class Demo {
    public static void main(String[] args) {
        String str ="db.odbc.select[Aborted_clients,test-one]";
        if(str.contains("db.odbc.select")){
            String[] split = str.split("\\[");
            System.out.println(split[1].split(",")[0]);
        }else {
            System.out.println("no");
        }
    }
}
