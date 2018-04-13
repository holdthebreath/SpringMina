package server.codeFactory;

import dao.ReceiveMapper;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.springframework.beans.factory.annotation.Autowired;
import server.protocol.MessageProtocol;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;


public class TestEncoder extends ProtocolEncoderAdapter {
    private final static Logger LOGGER = Logger.getLogger(TestEncoder.class);
    @Override
    public void encode(IoSession session, Object objectMessage, ProtocolEncoderOutput outPut) throws Exception{
        CharsetEncoder charsetEncoder = Charset.forName("utf-8").newEncoder();
        MessageProtocol protocol = (MessageProtocol) objectMessage;
        IoBuffer buffer = IoBuffer.allocate(1024);
        //自动扩展空间
        buffer.setAutoExpand(true);
        buffer.putInt(protocol.getLength());
        buffer.putInt(protocol.getType());
        buffer.putString(protocol.getContent(), charsetEncoder);

        buffer.flip();
        outPut.write(buffer);
    }
}
