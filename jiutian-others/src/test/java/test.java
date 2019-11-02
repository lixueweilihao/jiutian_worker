import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created on 2019-11-01
 *
 * @author :hao.li
 */
public class test {
    public static void main(String[] args) {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        boolean abc = queue.offer("哈哈哈");
        System.out.println(abc);
        System.out.println("offer后，队列是否空？" + queue.isEmpty());
        System.out.println("从队列中peek：" + queue.peek());
        System.out.println("从队列中peek：" + queue.peek());
        System.out.println("从队列中peek：" + queue.peek());
        System.out.println("pool后，队列是否空？" + queue.isEmpty());
    }
}
