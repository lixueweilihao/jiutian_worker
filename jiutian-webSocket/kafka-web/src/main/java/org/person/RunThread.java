package org.person;

/**
 * Created by youtNa on 2017/5/22.
 *
 * https://www.cnblogs.com/nayt/p/6931499.html
 */
public class RunThread {
    public RunThread(){
        ConsumerKafka kafka = new ConsumerKafka();
        kafka.start();
    }
}
