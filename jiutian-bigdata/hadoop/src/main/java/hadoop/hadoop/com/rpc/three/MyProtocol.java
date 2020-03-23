package hadoop.hadoop.com.rpc.three;
import org.apache.hadoop.ipc.VersionedProtocol;
/**
 * Created on 2020-02-03
 *
 * @author :hao.li
package testrpc.MyProtocol;

/**
 创建一个协议的接口,server端真正需要被远程调用的服务需要实现这个接口
 *这个接口需要实现RPC的顶级的父类VersionedProtocol的类,让这个接口可以实现RPC的通信
 */
public interface MyProtocol extends VersionedProtocol {
    //定义一个协议的版本,注意这里有坑,这个属性的名字必须是versionID,因为源码中默认就是通过反射调用的这个名字的属性
    //更改成其他的都是不行的********
    long versionID=1L;
    //创建几个方法来供远程来调用
    //创建一个链接的方法来供client来连接,返回值是一个字符串,接收一个字符串的命令
    ConnectionStatus connection(String command);
    //创建一个心跳的方法,来供客户端和服务端采用心跳机制来保障连接客户端输入一个字符串的心跳报文,服务端接收到之后返回
    String HeatBeat(String command_HeartBeant);
    //这里模拟一个其他的功能,比如说在服务端有一个计算的服务,用来计算两个数的和,只需要客户端将两个数字串过来就行,将结果自动返回去
    int Add(int num1,int num2);

}



