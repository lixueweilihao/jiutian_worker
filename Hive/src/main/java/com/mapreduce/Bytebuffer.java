package com.mapreduce; /**
 * Created by lihao on 2017/6/30.
 */
import java.nio.ByteBuffer;
public class Bytebuffer {
    public static void asIntBuffer() {
        ByteBuffer bBuf = ByteBuffer.allocate(512);
        bBuf.putInt(1);
        bBuf.putInt(2);
        bBuf.putInt(3);
        bBuf.putInt(4);
        bBuf.putInt(5);
        bBuf.putInt(6);
        bBuf.putInt(7);
        bBuf.flip();
        bBuf.putInt(8);
        bBuf.putInt(9);
        System.out.println("缓冲区Pos：" + bBuf.position() + "  缓冲区Limit："
                + bBuf.limit());
        System.out.println(bBuf.getInt());
        System.out.println(bBuf.getInt());
        System.out.println(bBuf.getInt());
        System.out.println(bBuf.getInt());
        System.out.println(bBuf.getInt());
    }
    public static void main(String[] args){
        Bytebuffer.asIntBuffer();
    }
}
