package com.play.data.linked.dan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright @ 2018
 * All right reserved.
 * 参考 https://www.cnblogs.com/whgk/p/6589920.html
 * https://blog.csdn.net/u011373710/article/details/54024366
 * https://www.jianshu.com/p/fc88c9b4ec12
 *
 * @author Li Hao
 * @since 2018/12/13  20:46
 */
public class DemoTest {
    int size;
    Node head = new Node();

    public void addNode(Node node) {
        Node temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setData(node.getData());
        temp.setNext(node);
        size++;
    }

    public int getLength() {
        return size;
    }

    public void insertNodeByIndex(int index, Node node) {
        //首先需要判断指定位置是否合法，
        if (index < 1 || index > getLength() + 1) {
            System.out.println("插入位置不合法。");
            return;
        }
        int length = 1;            //记录我们遍历到第几个结点了，也就是记录位置。
        Node temp = head;        //可移动的指针
        while (head.getNext() != null) {//遍历单链表
            if (index == length++) {        //判断是否到达指定位置。
                //注意，我们的temp代表的是当前位置的前一个结点。
                //前一个结点        当前位置        后一个结点
                //temp            temp.next     temp.next.next
                //插入操作。
                node.setNext(temp.getNext());
                temp.setNext(node);
                return;
            }
            temp = temp.getNext();
        }
    }

    public void delNodeByIndex(int index) {
        //判断index是否合理
        if (index < 1 || index > getLength()) {
            System.out.println("给定的位置不合理");
            return;
        }
        //步骤跟insertNodeByIndex是一样的，只是操作不一样。
        int length = 1;
        Node temp = head;
        while (temp.getNext() != null) {
            if (index == length++) {
                //删除操作。
                temp.setNext(temp.getNext().getNext());
                return;
            }
            temp = temp.getNext();
        }
    }

    public void selectSortNode() {
        //判断链表长度大于2，不然只有一个元素，就不用排序了。
        if (getLength() < 2) {
            System.out.println("无需排序");
            return;
        }
        //选择排序
        Node temp = head;            //第一层遍历使用的移动指针，最处指向头结点，第一个结点用temp.next表示
        while (temp.getNext() != null) {    //第一层遍历链表，从第一个结点开始遍历
            Node secondTemp = temp.getNext();        //第二层遍历使用的移动指针，secondTemp指向第一个结点，我们需要用到是第二个结点开始，所以用secondNode.next
            while (secondTemp.getNext() != null) {//第二层遍历,从第二个结点开始遍历
                if (temp.getNext().getData() > secondTemp.getNext().getData()) {    //第二层中的所有结点依次与第一次遍历中选定的结点进行比较，
                    int t = secondTemp.getNext().getData();
                    secondTemp.getNext().setData(temp.getNext().getData());
                    temp.getNext().setData(t);
                }
                secondTemp = secondTemp.getNext();
            }
            temp.setData(temp.getData());
        }
    }

    public void insertSortNode() {
        //判断链表长度大于2，不然只有一个元素，就不用排序了。
        if (getLength() < 2) {
            System.out.println("无需排序");
            return;
        }
        //创建新链表
        Node newHead = new Node(0);    //新链表的头结点
        Node newTemp = newHead;        //新链表的移动指针
        Node temp = head;        //旧链表的移动指针
        if (newTemp.getNext() == null) {        //将第一个结点直接放入新链表中。
            Node node = new Node(temp.getNext().getData());
            newTemp.setNext(node);
            temp = temp.getNext();    //旧链表中指针移到下一位(第二个结点处)。
        }
        while (temp.getNext() != null) {     //    遍历现有链表
            while (newTemp.getNext() != null) {
                //先跟新链表中的第一个结点进行比较,如果符合条件则添加到新链表，注意是在第一个位置上增加结点
                //如果不符合，则跟新链表中第二个结点进行比较，如果都不符合，跳出while，判断是否是到了新链表的最后一个结点，如果是则直接在新链表后面添加即可

                if (newTemp.getNext().getData() < temp.getNext().getData()) {
                    Node node = new Node(temp.getNext().getData());
                    node.setNext(newTemp.getNext());
                    newTemp.setNext(node);
                    break;
                }
                newTemp = newTemp.getNext();
            }
            if (newTemp.getNext() == null) {//到达最末尾还没符合，那么说明该值是新链表中最小的数，直接添加即可到链表中即可
                //直接在新链表后面添加
                Node node = new Node(temp.getNext().getData());
                newTemp.setNext(node);
            }
            //旧链表指针指向下一位结点，继续重复和新链表中的结点进行比较。
            temp = temp.getNext();
            //新链表中的移动指针需要复位，指向头结点
            newTemp = newHead;
        }
        //开始使用新链表，旧链表等待垃圾回收机制将其收回。
        head = newHead;

    }

    public Boolean get(Node n) {
        Node tmp1 = n;
        Node tmp2 = n.getNext();
        while (tmp2 != null) {
            int d1 = tmp1.getData();
            int d2 = tmp2.getData();
            if (d1 == d2) return true;//当两个指针重逢时，说明存在环，否则不存在。
            tmp1 = tmp1.getNext();  //每次迭代时，指针1走一步，指针2走两步
            tmp2 = tmp2.getNext().getNext();
            if (tmp2 == null) return false;//不存在环时，退出
        }
        return true; //如果tmp2为null，说明元素只有一个，也可以说明是存在环
    }

    public static boolean hasLoop2(Node n) {
        Node temp1 = n;
        HashMap<Node, Node> ns = new HashMap<Node, Node>();
        while (n != null) {
            if (ns.get(temp1) != null)
                return true;
            else ns.put(temp1, temp1);
            temp1 = temp1.getNext();
            if (temp1 == null)
                return false;
        }
        return true;
    }


    public void print() {
        Node temp = head.getNext();
//        Node temp = head;
        while (temp.getNext() != null) {
            System.out.println(temp.getData() + "");
            temp = temp.getNext();
        }
        System.out.println();
    }

    //列表翻转
    public static Node reverseNode(Node head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.getNext() == null) {
            return head;
        }
        //前一个节点指针
        Node preNode = null;
        //当前节点指针
        Node curNode = head;
        //下一个节点指针
        Node nextNode = null;

        while (curNode != null) {
            nextNode = curNode.getNext();//nextNode 指向下一个节点
            curNode.setNext(preNode);//将当前节点next域指向前一个节点
            preNode = curNode;//preNode 指针向后移动
            curNode = nextNode;//curNode指针向后移动
        }
        return preNode;
    }
//翻转部分单链表
    public Node reverseBetween(Node head, int m, int n) {
        if (head == null) return null;
        if (head.getNext() == null) return head;
        int i = 1;
        Node reversedNewHead = null;// 反转部分链表反转后的头结点
        Node reversedTail = null;// 反转部分链表反转后的尾结点
        Node oldHead = head;// 原链表的头结点
        Node reversePreNode = null;// 反转部分链表反转前其头结点的前一个结点
        Node reverseNextNode = null;
        while (head != null) {
            if (i > n) {
                break;
            }
            if (i == m - 1) {
                reversePreNode = head;
            }
            if (i >= m && i <= n) {
                if (i == m) {
                    reversedTail = head;
                }
                reverseNextNode = head.getNext();
                head.setNext(reversedNewHead);
                reversedNewHead = head;
                head = reverseNextNode;
            } else {
                head = head.getNext();
            }
            i++;
        }
        reversedTail.setNext(reverseNextNode);
        if (reversePreNode != null) {
            reversePreNode.setNext(reversedNewHead);
            return oldHead;
        } else {
            return reversedNewHead;
        }
    }
    //旋转单链表
    public Node rotateRight(Node head, int k) {
        if (head==null) return null;
        int n = 1;
        Node cur = head;
        while (cur.getNext()==null) {
            ++n;
            cur = cur.getNext();
        }
        cur.setNext(head);
        int m = n - k % n;
        for (int i = 0; i < m; ++i) {
            cur = cur.getNext();
        }
        Node newhead = cur.getNext();
        cur.setNext(null);
        return newhead;
    }
    //删除单链表倒数第 n 个节点
    public Node removeNthFromEnd(Node head, int n) {
        //声明两个指针，表示为当前所在节点以及前驱节点
        Node prev = head;
        Node cur = prev.getNext();
        int tmp = 2;
        //删除头节点单独处理
        if(n==1){
            return head.getNext();
        }
        while(cur!=null){
            if(tmp == n){
                prev.setNext(cur.getNext());
                return head;
            }
            cur = cur.getNext();
            prev= prev.getNext();
            tmp++;
        }
        return null;
    }
    //求单链表的中间节点
// 遍历一次，找出单链表的中间节点
    public Node findMiddleNode(Node head) {
        if (null == head) {
            return null;
        }
        Node slow = head;
        Node fast = head;

        while (null != fast && null != fast.getNext()) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        return slow;
    }
    //链表划分
    public Node partition(Node head, int x) {
        // write your code here
        if(head == null) return null;
        Node leftDummy = new Node(0);
        Node rightDummy = new Node(0);
        Node left = leftDummy, right = rightDummy;

        while (head != null) {
            if (head.getData() < x) {
                left.setNext(head);
                left = head;
            } else {
                right.setNext(head);
                right = head;
            }
            head = head.getNext();
        }

        right.setNext(null);
        left.setNext(rightDummy.getNext());
        return leftDummy.getNext();
    }
    // 链表求和
    public Node addTwoNumbers(Node l1, Node l2) {
        Node c1 = l1;
        Node c2 = l2;
        Node sentinel = new Node(0);
        Node d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.getData();
                c1 = c1.getNext();
            }
            if (c2 != null) {
                sum += c2.getData();
                c2 = c2.getNext();
            }
            d.setNext(new Node(sum % 10));
            d = d.getNext();
        }
        if (sum / 10 == 1)
            d.setNext(new Node(1));
        return sentinel.getNext();
    }
    //单链表排序
    public Node sortList1(Node head) {
        //采用快速排序
        quickSort(head, null);
        return head;
    }
    public static void quickSort(Node head, Node end) {
        if (head != end) {
            Node node = partion(head, end);
            quickSort(head, node);
            quickSort(node.getNext(), end);
        }
    }

    public static Node partion(Node head, Node end) {
        Node p1 = head, p2 = head.getNext();

        //走到末尾才停
        while (p2 != end) {

            //大于key值时，p1向前走一步，交换p1与p2的值
            if (p2.getData() < head.getData()) {
                p1 = p1.getNext();

                int temp = p1.getData();
                p1.setData(p2.getData());
                p2.setData(temp);
            }
            p2 = p2.getNext();
        }

        //当有序时，不交换p1和key值
        if (p1 != head) {
            int temp = p1.getData();
            p1.setData(head.getData());
            head.setData(temp);
        }
        return p1;
    }
    public Node sortList(Node head) {
        //采用归并排序
        if (head == null || head.getNext() == null) {
            return head;
        }
        //获取中间结点
        Node mid = getMid(head);
        Node right = mid.getNext();
        mid.setNext(null);
        //合并
        return mergeSort(sortList(head), sortList(right));
    }

    /**
     * 获取链表的中间结点,偶数时取中间第一个
     *
     * @param head
     * @return
     */
    private Node getMid(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        //快慢指针
        Node slow = head, quick = head;
        //快2步，慢一步
        while (quick.getNext() != null && quick.getNext().getNext() != null) {
            slow = slow.getNext();
            quick = quick.getNext().getNext();
        }
        return slow;
    }

    /**
     *
     * 归并两个有序的链表
     *
     * @param head1
     * @param head2
     * @return
     */
    private Node mergeSort(Node head1, Node head2) {
        Node p1 = head1, p2 = head2, head;
        //得到头节点的指向
        if (head1.getData() < head2.getData()) {
            head = head1;
            p1 = p1.getNext();
        } else {
            head = head2;
            p2 = p2.getNext();
        }

        Node p = head;
        //比较链表中的值
        while (p1 != null && p2 != null) {

            if (p1.getData() <= p2.getData()) {
                p.setNext(p1);
                p1 = p1.getNext();
                p = p.getNext();
            } else {
                p.setNext(p2);
                p2 = p2.getNext();
                p = p.getNext();
            }
        }
        //第二条链表空了
        if (p1 != null) {
            p.setNext(p1);
        }
        //第一条链表空了
        if (p2 != null) {
            p.setNext(p2);
        }
        return head;
    }

    //合并两个排序的链表
    public Node Merge(Node list1,Node list2) {
        if(list1==null)
            return list2;
        if(list2==null)
            return list1;
        Node res=null;
        if(list1.getData()<list2.getData())
        {
            res=list1;
            list1.setNext(Merge(list1.getNext(),list2));
        }
        else
        {
            res=list2;
            list2.setNext(Merge(list1,list2.getNext()));
        }
        return res;
    }
// 复杂链表的复制
    //https://blog.csdn.net/wmh1152151276/article/details/88077670
//删除链表中重复的结点
    public Node deleteDeplication(Node pHead){

        if (pHead == null)
            return null;

        //注意备用头结点，头结点可能被删除
        Node first = new Node(-1);

        first.setNext(pHead);
        Node p = pHead;
        //前节点
        Node preNode = first;

        while (p != null && p.getNext() != null){

            if (p.getData() == p.getNext().getData()){ //两节点相等

                int val = p.getData(); //记录val用于判断后面节点是否重复
                while(p != null && p.getData() == val){   //这一步用于跳过相等的节点，用于删除
                    p = p.getNext();
                }
                preNode.setNext(p); //删除操作，前节点的next直接等于现在的节点，把中间的节点直接跨过
            }else {
                preNode = p;
                p = p.getNext();
            }
        }
        return first.getNext();
    }
    //判断单链表是否存在环
    public boolean hasCycle(Node head) {
        //声明一个set存放已遍历的节点，即为标记节点（Set中不允许重复元素）
        Set<Node> set = new HashSet<>();
        while(head!=null) {
            if(set.contains(head)) {
                return true;
            }else {
                set.add(head);
                head = head.getNext();
            }
        }
        return false;
    }
    public boolean hasCycle1(Node head) {

        Node quick = head;
        Node slow = head;
        //当快指针能够走到头表示无环
        while(quick!=null&&quick.getNext()!=null){
            quick = quick.getNext().getNext();
            slow = slow.getNext();
            if(quick==slow){
                return true;
            }
        }
        return false;
    }
    //单链表是否有环扩展：找到环的入口点
    public Node detectCycle(Node head) {
        Node fast = head;
        Node slow = head;
        while(fast != null && fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if(fast == slow){
                break;
            }
        }
        if(fast == null || fast.getNext() == null){
            return null;
        }

        slow = head;
        while(fast != slow){
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return slow;

    }
    //判断两个无环单链表是否相交
    public boolean isIntersect(Node headA, Node headB) {
        if (null == headA || null == headB) {
            return false;
        }
        if (headA == headB) {
            return true;
        }
        while (null != headA.getNext()) {
            headA = headA.getNext();
        }
        while (null != headB.getNext()) {
            headB = headB.getNext();
        }
        return headA == headB;
    }
    // 两个链表相交扩展：求两个无环单链表的第一个相交点
    public int getLength(Node head){
        int n=0;
        for(Node cur=head;cur!=null;cur=cur.getNext()){
            n++;
        }
        return n;
    }
    public Node getPoint(Node headA,Node headB){
        int A=getLength(headA);
        int B=getLength(headB);
        int C=A-B;
        Node longList=headA;
        Node shortList=headB;
        if(A<B){
            longList=headB;
            shortList=headA;
            C=B-A;
        }
        for(int i=0;i<C;i++){
            longList=longList.getNext();
        }
        while(longList!=shortList){
            longList=longList.getNext();
            shortList=shortList.getNext();
        }
        return longList;
    }
    public Node getIntersectionNode(Node headA, Node headB) {
        if (null == headA || null == headB) {
            return null;
        }
        if (headA == headB) {
            return headA;
        }

        Node p1 = headA;
        Node p2 = headB;
        while (p1 != p2) {
            // 遍历完所在链表后从另外一个链表再开始
            // 当 p1 和 p2 都换到另一个链表时，它们对齐了：
            // （1）如果链表相交，p1 == p2 时为第一个相交点
            // （2）如果链表不相交，p1 和 p2 同时移动到末尾，p1 = p2 = null，然后退出循环
            p1 = (null == p1) ? headB : p1.getNext();
            p2 = (null == p2) ? headA : p2.getNext();
        }
        return p1;
    }
    //判断两个有环单链表是否相交
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }
    /*
     * 判断是否存在环，如果存在，则找出环的入口点。
     * 入口点找法：快慢指针，块指针走两步，满指针走一步，如果存在循环，则在慢指针走完环前，总会和快指针相遇。
     * 从头指针和相遇点同时向后走，相遇的点必定是入口点。*/
    public static Node getLoopNode(Node head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            return null;
        }
        Node n1 = head.getNext(); // n1 -> slow
        Node n2 = head.getNext().getNext(); // n2 -> fast
        while (n1 != n2) {
            if (n2.getNext() == null || n2.getNext().getNext() == null) {
                return null;
            }
            n2 = n2.getNext().getNext();
            n1 = n1.getNext();
        }
        n2 = head; // n2 -> walk again from head
        while (n1 != n2) {
            n1 = n1.getNext();
            n2 = n2.getNext();
        }
        return n1;
    }
    /*无环时的判断方法*/
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.getNext() != null) {
            n++;
            cur1 = cur1.getNext();
        }
        while (cur2.getNext() != null) {
            n--;
            cur2 = cur2.getNext();
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.getNext();
        }
        while (cur1 != cur2) {
            cur1 = cur1.getNext();
            cur2 = cur2.getNext();
        }
        return cur1;
    }
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.getNext();
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.getNext();
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.getNext();
            }
            while (cur1 != cur2) {
                cur1 = cur1.getNext();
                cur2 = cur2.getNext();
            }
            return cur1;
        } else {
            cur1 = loop1.getNext();
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.getNext();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        DemoTest dt = new DemoTest();
        Node node = new Node(5);
        Node node1 = new Node(6);
        Node node2 = new Node(7);
        Node node3 = new Node(8);
        dt.addNode(node);
        dt.addNode(node1);
        dt.addNode(node2);
        dt.insertNodeByIndex(2, node3);
        dt.print();
        System.out.println("--------------------------");
        Node node4 = dt.reverseNode(dt.head);
        Node temp = node4.getNext();
//        Node temp = head;
        while (temp.getNext() != null) {
            System.out.println(temp.getData() + "");
            temp = temp.getNext();
        }
        System.out.println();

    }
}
