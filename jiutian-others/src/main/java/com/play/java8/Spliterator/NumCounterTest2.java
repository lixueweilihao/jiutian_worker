package com.play.java8.Spliterator;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/21  21:42
 */
public class NumCounterTest2 {
    public static void main(String[] args) {
        String arr = "12%3 21sdas s34d dfsdz45 R3 jo34 sjkf8 3$1P 213ikflsd fdg55 kfd";
        System.out.println(arr);

        Spliterator<Character> spliterator = new NumCounterSpliterator3(0, arr.length(), arr.toCharArray(), true);
// 传入true表示是并行流
        Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
        System.out.println("parallel total: " + countNum(parallelStream));
    }

    private static int countNum(Stream<Character> stream) {
        NumCounter numCounter = stream.reduce(new NumCounter(0, 0, false), NumCounter::accumulate, NumCounter::combine);
        return numCounter.getSum();
    }
}
