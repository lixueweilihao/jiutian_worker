package java8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/10/11  20:31
 */
public class TESTdEMO {
    public static void main(String[] args) {
        System.out.println(Stream.of("a", "b", "c").collect(Collectors.joining(",")));

        System.out.println(Stream.of("a", "b", "c").collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.joining(","))));
        Map<Integer, List<Integer>> map = Stream.of(1, 3, 3, 4).collect(Collectors.groupingBy(x -> x));
        System.out.println(map);
        Map<Integer, Long> map1 = Stream.of(1, 3, 3, 4).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(map1);
        HashMap<Integer, Long> hashMap = Stream.of(1, 3, 3, 4).collect(Collectors.groupingBy(x -> x, HashMap::new, Collectors.counting()));
        System.out.println(hashMap);

    }
}
