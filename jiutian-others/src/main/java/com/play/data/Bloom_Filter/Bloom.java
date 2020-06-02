package com.play.data.Bloom_Filter;

/**
 * @Author : lihao
 * Created on : 2020-05-09
 * @Description : TODO描述类作用
     * https://blog.csdn.net/weixin_37373020/article/details/99306145
 * 布隆过滤器(BloomFilter)的原理、实现和探究:https://my.oschina.net/LucasZhu/blog/1813110
 * 大数据去重统计之BloomFilter:http://lxw1234.com/archives/2015/12/580.htm
 */
/**
 * @author wang66
 * 2019-8-12
 * Bloom for String
 */
public class Bloom {
    private int[] record=new int[1024];//每一个int代表32位
    private int size=32*1024;//位总长度
    private int hash1(String s){//第一个hash值
        int h=0;
        for(char c:s.toCharArray()){
            h=h*37+(int)(c);
        }
        return Math.abs(h);
    }
    private int hash2(String s){//第二个hash值
        int h=0;
        for(char c:s.toCharArray()){
            h=h*61+(int)c;
        }
        return Math.abs(h);
    }
    private int hash3(String s){//第三个hash值
        int h=0;
        char[] chars=s.toCharArray();
        for(int i=chars.length-1;i>=0;i--){
            h+=h*73+(int)chars[i];
        }
        return Math.abs(h);
    }
    public int hash4(String s){//第四个hash值
        int h=0;
        int j=1;
        char[] chars=s.toCharArray();
        for(char c:chars){
            h=h*41+(int)(c)*j;
            j*=-1;
        }
        return Math.abs(h);
    }
    public void put(String s){//把一个单词放进去
        int h1=hash1(s)%size;
        int h2=hash2(s)%size;
        int h3=hash3(s)%size;
        int h4=hash4(s)%size;
        record[h1/32]|=(1<<(h1%32));
        record[h2/32]|=(1<<(h2%32));
        record[h3/32]|=(1<<(h3%32));
        record[h4/32]|=(1<<(h4%32));
    }
    public boolean isMaybeExist(String s){//判断是否“可能存在”
        int h1=hash1(s)%size;
        int h2=hash2(s)%size;
        int h3=hash3(s)%size;
        int h4=hash4(s)%size;
        return (record[h1/32]&(1<<(h1%32)))!=0&&
                (record[h2/32]&(1<<(h2%32)))!=0&&
                (record[h3/32]&(1<<(h3%32)))!=0&&
                (record[h4/32]&(1<<(h4%32)))!=0;
    }
    public static void main(String[] args){
        Bloom bloom=new Bloom();
        bloom.put("Hello");
        bloom.put("world");
        bloom.put("friend");
        bloom.put("family");
        bloom.put("future");
        System.out.println(bloom.isMaybeExist("world"));
        System.out.println(bloom.isMaybeExist("poor"));
        System.out.println(bloom.isMaybeExist("future"));
    }
}

