package yarn.rpc.test2;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  14:50
 */
public class MyProtocolImpl implements MyProtocol {

    @Override
    public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
        return MyProtocol.versionID;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String protocol, long clientVersion,
                                                  int clientMethodsHash) {
        return new ProtocolSignature(MyProtocol.versionID, null);
    }

    @Override
    public String echo() {
        Calendar cal = Calendar.getInstance() ;
        Date date = cal.getTime() ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss") ;
        return sdf.format(date) ;
    }

}
