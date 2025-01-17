package com.play.data.linked.mianshiti;

import com.play.data.linked.dan.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : lihao
 * Created on : 2020-04-13
 * @Description : 链表相关题目：https://www.jianshu.com/p/699a88da8b9c
 *
 * 参考：https://www.jianshu.com/p/6782f3d96471
 */
public class Main {
    int size;
    static ListNode head = new ListNode();

    public void addNode(ListNode node) {
        ListNode temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setVal(node.getVal());
        temp.setNext(node);
        size++;
    }
    /**
     *
     * 链表反转
    * */
    ListNode reverse(ListNode node){
        ListNode prev = null;
        while(node!=null){
            ListNode tmp = node.next;
            node.next = prev;
            prev = node;
            node = tmp;
        }
        return prev;
    }
    //翻转链表(递归方式)
    ListNode reverse2(ListNode head){
        if(head.next == null){
            return head;
        }
        ListNode reverseNode = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return reverseNode;
    }
/**
 * 判断是否有环
 * */
    boolean hasCycle(ListNode head){
        if(head == null|| head.next == null){
            return false;
        }
        ListNode slow,fast;
        fast = head;
        slow = head;
        while(slow.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast==slow){
                return true;
            }
        }
        return false;
    }
    /**
     * 链表排序
     *
     * */
    ListNode sortList(ListNode head){
        if(head == null|| head.next == null){
            return head;
        }
        ListNode mid = middleNode(head);
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        return merge(left, right);
    }
    ListNode middleNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast!=null&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    ListNode merge(ListNode n1,ListNode n2){
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (n1!=null&&n2!=null) {
            if(n1.val<n2.val){
                node.next = n1;
                n1 = n1.next;
            }else{
                node.next = n2;
                n2 = n2.next;
            }
            node = node.next;
        }
        if(n1!=null){
            node.next = n1;
        }else{
            node.next = n2;
        }
        return dummy.next;
    }
    /**
     * 链表求和
     * */
    ListNode addLists(ListNode l1,ListNode l2){
        if(l1==null&&l2==null){
            return null;
        }
        ListNode head = new ListNode();
        ListNode point = head;
        int carry = 0;
        while(l1!=null&&l2!=null){
            int sum = carry + l1.val + l2.val;
            point.next = new ListNode(sum%10);
            carry = sum/10;
            l1 = l1.next;
            l2 = l2.next;
            point = point.next;
        }
        while(l1!=null){
            int sum = carry + l1.val;
            point.next = new ListNode(sum%10);
            carry = sum/10;
            l1 = l1.next;
            point = point.next;
        }
        while(l2!=null){
            int sum = carry + l2.val;
            point.next = new ListNode(sum%10);
            carry = sum/10;
            l2 = l2.next;
            point = point.next;
        }
        if(carry!=0){
            point.next = new ListNode(carry);

        }
        return head.next;
    }

    /**
     * 链表倒数第n个节点
     * */
    ListNode nthToLast(ListNode head,int n ){
        if(head == null||n<1){
            return null;
        }
        ListNode l1 = head;
        ListNode l2 = head;
        for(int i = 0;i<n-1;i++){
            if(l2 == null){
                return null;
            }
            l2 = l2.next;
        }
        while(l2.next!=null){
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;

    }
    //删除链表中重复的元素
    ListNode deleteMuNode(ListNode head){
        if(head == null){
            return null;
        }
        ListNode node = head;
        while(node.next != null){
            if(node.val == node.next.val){
                node.next = node.next.next;
            }else{
                node = node.next;
            }
        }
        return head;

    }
   // 删除链表中重复的元素ii,去掉重复的节点
   ListNode deleteMuNode2(ListNode head){
       if(head == null||head.next == null){
           return head;
       }
       ListNode dummy = new ListNode(0);
       dummy.next = head;
       head = dummy;
       while(head.next!=null&&head.next.next!=null){
           if(head.next.val == head.next.next.val){
               int val = head.next.val;
               while(head.next.val == val&&head.next != null){
                   head.next = head.next.next;
               }
           }else{
               head = head.next;
           }
       }
       return dummy.next;
   }
//   //旋转链表
//   ListNode rotateRight(ListNode head,int k){
//       if(head ==null){
//           return null;
//       }
//       int length = size(head);//长度
//       k = k % length;
//       ListNode dummy = new ListNode(0);
//       dummy.next = head;
//       head = dummy;
//       ListNode tail = dummy;
//       for(int i = 0;i<k;i++){
//           head = head.next;
//       }
//       while(head.next!= null){
//           head = head.next;
//           tail = tail.next;
//       }
//       head.next = dummy.next;
//       dummy.next = tail.next;
//       tail.next = null;
//       return dummy.next;
//   }
//重排链表
 public ListNode reOrder(ListNode head){
    if(head == null||head.next == null){
        return head;
    }
    ListNode mid = middleNode(head);
    ListNode tail = reverse(mid.next);
    mergeIndex(head, tail);
    return mid;
}
    private void mergeIndex(ListNode head1,ListNode head2){
        int index = 0;
        ListNode dummy = new ListNode(0);
        while (head1!=null&&head2!=null) {
            if(index%2==0){
                dummy.next = head1;
                head1 = head1.next;
            }else{
                dummy.next = head2;
                head2 = head2.next;
            }
            dummy = dummy.next;
            index ++;
        }
        if(head1!=null){
            dummy.next = head1;
        }else{
            dummy.next = head2;
        }
    }
//链表划分
ListNode partition(ListNode head,int x){
    if(head == null){
        return null;
    }
    ListNode left = new ListNode(0);
    ListNode right = new ListNode(0);
    ListNode leftDummy = left;
    ListNode rightDummy = right;
    while(head!=null){
        if(head.val<x){
            left.next = head;
            left = head;
        }else{
            right.next = head;
            right = head;
        }
        head = head.next;
    }
    left.next = rightDummy.next;
    right.next = null;
    return leftDummy.next;
}
//翻转链表的n到m之间的节点
ListNode reverseN2M(ListNode head,int m,int n){
    if(m>=n||head == null){
        return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;
    for(int i = 1;i<m;i++){
        if(head == null){
            return null;
        }
        head = head.next;
    }
    ListNode pmNode = head;
    ListNode mNode = head.next;
    ListNode nNode = mNode;
    ListNode pnNode = mNode.next;
    for(int i = m;i<n;i++){
        if(pnNode == null){
            return null;
        }
        ListNode tmp = pnNode.next;
        pnNode.next = nNode;
        nNode = pnNode;
        pnNode = tmp;
    }
    pmNode.next = nNode;
    mNode.next = pnNode;
    return dummy.next;
}
//合并K个排序过的链表
ListNode mergeKListNode(ArrayList<ListNode> k){
    if(k.size()==0){
        return null;
    }
    return mergeHelper(k,0,k.size()-1);
}
    ListNode mergeHelper(List<ListNode> lists, int start, int end){
        if(start == end){
            return lists.get(start);
        }
        int mid = start + ( end - start )/2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid+1, end);
        return mergeTwoLists(left,right);
    }
    ListNode mergeTwoLists(ListNode list1,ListNode list2){
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(list1!=null&&list2!=null){
            if(list1.val<list2.val){
                tail.next = list1;
                tail = tail.next;
                list1 = list1.next;
            }else{
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            }

        }
        if(list1!=null){
            tail.next = list1;
        }else{
            tail.next = list2;
        }
        return dummy.next;
    }



    public static void main(String[] args) {
        Main mm = new Main();
        ListNode ln = new ListNode(5);
        ListNode ln1 = new ListNode(6);
        ListNode ln2 = new ListNode(7);
        ListNode ln3 = new ListNode(8);
        mm.addNode(ln);
        mm.addNode(ln1);
        mm.addNode(ln2);
        mm.addNode(ln3);
        mm.print();
    }


    public void print() {
        ListNode temp = head.getNext();
//        Node temp = head;
        while (temp.getNext() != null) {
            System.out.println(temp.getVal() + "");
            temp = temp.getNext();
        }
        System.out.println();
    }



}
