package server.codeFactory;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

public class TestDecoder extends CumulativeProtocolDecoder{
    private final static Logger LOGGER = Logger.getLogger(TestDecoder.class);
    @Override
    protected boolean doDecode(IoSession session, IoBuffer ioBuffer, ProtocolDecoderOutput output)throws Exception {
        //标记位置，重置则从此处重置
        ioBuffer.mark();
        //报文类型
        int type = ioBuffer.getInt();
        //报文长度
        int length = ioBuffer.getInt();
        if (length > ioBuffer.remaining()) {
            //若长度不够，则重置iobuffer，继续解析
            ioBuffer.reset();
            return false;
        } else {
            //
            String content = ioBuffer.getString(Charset.forName("utf-8").newDecoder());
            if (type == 0)
                output.write("心跳包," + content);
            else
                output.write(content);
            if(ioBuffer.remaining() > 0){//如果读取一个完整包内容后还粘了包，就让父类再调用一次，进行下一次解析
                return true;
            }
            return true;
        }
    }
}
