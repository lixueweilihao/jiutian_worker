
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

@WebServlet("/chat")
public class ChatWebSocketServlet extends WebSocketServlet {
    private final Map<Integer, WsOutbound> map = new HashMap<Integer, WsOutbound>();
    private static final long serialVersionUID = -1058445282919079067L;
    @Override
    protected StreamInbound createWebSocketInbound(String s) {
        return new ChatMessageInbound();
    }

    class ChatMessageInbound extends MessageInbound {
        // MessageInbound：基于消息的WebSocket实现类(带内消息)，应用程序应当扩展这个类并实现其抽象方法onBinaryMessage和onTextMessage。
        @Override
        protected void onOpen(WsOutbound outbound) {
            map.put(outbound.hashCode(), outbound);
            super.onOpen(outbound);
        }
        @Override
        protected void onClose(int status) {
            map.remove(getWsOutbound().hashCode());
            super.onClose(status);
        }
        @Override
        protected void onBinaryMessage(ByteBuffer buffer) throws IOException {
        }
        @Override
        protected void onTextMessage(CharBuffer buffer) throws IOException {
            String msg = buffer.toString();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            msg = " <font color=green>匿名用戶  " + sdf.format(date) + "</font><br/> " + msg;
            broadcast(msg);
        }
        private void broadcast(String msg) {
            Set<Integer> set = map.keySet();
            for (Integer integer : set) {
                WsOutbound outbound = map.get(integer);
                CharBuffer buffer = CharBuffer.wrap(msg);
                try {
                    outbound.writeTextMessage(buffer);
                    outbound.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public int getReadTimeout() {
            return 0;
        }
    }
}

