
import org.apache.geode.internal.tcp.ByteBufferInputStream.ByteSource;
import org.apache.geode.internal.tcp.ByteBufferInputStream.ByteSourceFactory;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/23  18:20
 */
public class test {
    protected ByteSource createByteSource(byte[] bytes) {
        return ByteSourceFactory.wrap(bytes);
    }
    @Test
    public void testGetLongInt() {
        ByteBuffer bb = ByteBuffer.allocate(40);
        LongBuffer lb = bb.asLongBuffer();
        lb.put(0x1110223344556677L);
        lb.put(0x2220334455667788L);
        lb.put(0x3330445566778899L);
        lb.put(0x4440556677889900L);
        lb.put(0x55506677889900AAL);
        byte[] bytes = bb.array();
        ByteSource bs = createByteSource(bytes);
        bs.position(3);
        long l = bs.getLong(0);
        assertEquals(0x1110223344556677L, l);
        assertEquals(3, bs.position());
        l = bs.getLong(8);
        assertEquals(0x2220334455667788L, l);
        assertEquals(3, bs.position());
        l = bs.getLong(4 * 8);
        assertEquals(0x55506677889900AAL, l);
        assertEquals(3, bs.position());
        try {
            bs.getLong((4 * 8) + 1);
            fail("expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException expected) {
        }
    }
}
