package Buidler;
interface A{            //函数接口
    int add(int a, int b);
    boolean equals(Object obj);
    default void say(){
        System.out.println("Hello,I am A");
    }
}

public class Lambda implements A{
    public static void main(String[] args){
//         A a = new A(){    //匿名内部类实现add()
//            public int add(int a,int b){
//                return (a+b);
//            }
//        };
//         a.say();
//         int c = a.add(5,3);
//        System.out.println(c);
        A a = (x,y)->{return (x+y);};
        a.say();
        System.out.println(a.add(5,3));
    }

    @Override
    public int add(int a, int b) {
        return 0;
    }
}