package com.play.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lihao
 * @Date: Create in 10:08 2017/12/21
 * @Description:
 * @Modified By:
 */
public class SourceA {
    private List<String> list = new ArrayList<String>();
    public synchronized void getSource(){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
    public synchronized void setSource(String id){
        list.add(id);
    }

    public static void main(String[] args) {
        SourceA s = new SourceA();
        TestThread tt = new TestThread(s);
        TestRunnable tr = new TestRunnable(s);
        Thread t = new Thread(tr);
        System.out.println("调用线程1");
        tt.start();
        System.out.println("调用线程2");
        t.start();
    }
}
