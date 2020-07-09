package yarn.rpc.demo.dtdl;

/**
 * @Author : lihao
 * Created on : 2020-07-09
 * @Description : TODO描述类作用
 */

/**
 * @Date May 7, 2015
 *
 * @Author dengjie
 */
public class JReflect {
    public static void main(String[] args) {
        Fruit f = Factory.getInstance(Orange.class.getName());
        if (f != null) {
            f.eat();
        }
    }
}

interface Fruit {
    public abstract void eat();
}

class Apple implements Fruit {

    @Override
    public void eat() {
        System.out.println("apple");
    }

}

class Orange implements Fruit {

    @Override
    public void eat() {
        System.out.println("orange");
    }

}

class Factory {
    public static Fruit getInstance(String className) {
        Fruit f = null;
        try {
            f = (Fruit) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
