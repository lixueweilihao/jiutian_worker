package kafka;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/5  17:42
 */
public class test3 {
    public static void main(String[] args) {
        Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new HashSet<>();
        set1.add("a");
        set1.add("b");
        set1.add("c");
        set2.add("a");
        set2.add("b");
        set2.add("d");
        Set<String> delete = Sets.difference(set1, set2);
        System.out.println(delete);
        Set<String> add = Sets.difference(set2, set1);
        System.out.println(add);
        Sets.SetView<String> union = Sets.union(delete, add);
        System.out.println(union);
    }
}
