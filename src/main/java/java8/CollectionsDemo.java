package java8;

import java.util.HashMap;

public class CollectionsDemo {
    public static void main(String[] a){
        HashMap map = new HashMap();
        map.put("a", "c");
        map.put("b", "h");
        map.put("c", "e");

        map.compute("a", (k, v) -> "C") ;
        map.compute("a", (k, v) -> "E") ;
        map.merge("b", "h", (k, v) -> "H") ;
        map.compute("d", (k, v) -> "D") ;
        map.compute("d", (k, v) -> null) ;
        map.merge("c", "e", (k, v) -> null) ;
        System.out.println(map.toString());
    }
}