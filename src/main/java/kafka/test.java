package kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/8  16:55
 */
public class test {
//    public static void main(String[] args) {
//        long now = System.currentTimeMillis();
//        System.out.println(now);
////        long nextIntegrationPoint = ((long) (now / 1000 / 3600 + 1)) * 3600*1000;
//        long nextIntegrationPoint = ((long) now / (1000));
//        System.out.println(nextIntegrationPoint);
//        long nextIntegrationPoint11 = ((long) now / (1000*1000));
//        System.out.println(nextIntegrationPoint11);
//        long commitEndTime = System.nanoTime();
//        System.out.println(commitEndTime/(1000*1000));
//    }
    public static void main(String[] args) {

        List<Person> list = new ArrayList();
        list.add(new Person(1, "haha","man"));
        list.add(new Person(2, "rere","man"));
        list.add(new Person(3, "fefe","man"));



        Map<Integer, Person> mapp = list.stream().collect(Collectors.toMap(Person::getId, Function.identity()));

        System.out.println(mapp);

        System.out.println(mapp.get(1).getName());

        Map<Integer, String> map = list.stream().collect(Collectors.toMap(Person::getId, Person::getName));

        System.out.println(map);

    }

}
