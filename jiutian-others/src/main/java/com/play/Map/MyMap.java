package com.play.Map;


/**
 * @Author : lihao
 * Created on : 2020-04-09
 * @Description : TODO描述类作用
 */
public class MyMap<K,V> {
    private Node<K,V>[] nodes;
    private int size;
    public void put(K key ,V value){
        if(nodes ==null){
            nodes=new Node[10];
        }
        int index = indexOfKey(key);
        if(index!=-1){
            nodes[index].value=value;
        }else {
            nodes[size]=new Node<>(key,value);
            size++;
        }
    }
    public V get(K key ){
        int index = indexOfKey(key);
        if(index!=-1){
            return nodes[index].value;
        }
        return null;
    }
    public int size(){
        return size;
    }

    private int indexOfKey(K key) {
        for(int index=0;index <size;index++){
            if(key.equals(this.nodes[index].key)){
                return index;
            }
        }
        return -1;
    }


    private static class Node<K,V>{
        K key;
        V value;
        Node(K key,V value){
            this.key=key;
            this.value=value;
        }
    }
}
