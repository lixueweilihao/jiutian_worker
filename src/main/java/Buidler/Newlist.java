package Buidler;

import java.util.ArrayList;
import java.util.List;

public class Newlist {
    public  static  void main(String[] args){
        List<String> strList = new ArrayList<String>();
        strList.add("123");
        strList.add("234");
        strList.add("345");
        strList.add("456");
        strList.add("567");
        strList.forEach(a->{System.out.println(a);});
    }
}
