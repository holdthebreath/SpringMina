package server.codeFactory;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class TestFactory implements ProtocolCodecFactory {
    private final TestDecoder decoder;
    private final TestEncoder encoder;

    //构造
    public TestFactory() {
        encoder = new TestEncoder();
        decoder = new TestDecoder();
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        return decoder;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        return encoder;
    }
}