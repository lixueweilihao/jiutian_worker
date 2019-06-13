package execfile;

import java.nio.ByteBuffer;


public class Program3 {
    public static void main(String[] args) {
        ByteBuffer bBuf = ByteBuffer.allocate(512);
        bBuf.putInt(1);
        bBuf.putInt(2);
        bBuf.putInt(3);
        bBuf.putInt(4);
        bBuf.putInt(5);
        bBuf.putInt(6);
        bBuf.putInt(7);
        System.out.println("缓冲区Pos：" + bBuf.position() + "  缓冲区Limit："
                + bBuf.limit());
        bBuf.flip();
        System.out.println("缓冲区Pos：" + bBuf.position() + "  缓冲区Limit："
                + bBuf.limit());
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
}
