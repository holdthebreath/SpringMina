package server;


import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import server.handler.FirstServerHandler;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class FirstServer {
    private static Logger LOGGER = Logger.getLogger(FirstServer.class);
    private static int PORT = 6789;

    public static void main(String[] args) {
        IoAcceptor ioAcceptor;
        try {
            ioAcceptor = new NioSocketAcceptor();
            ioAcceptor.getFilterChain().addLast(
                    "codec",
                    new ProtocolCodecFilter(new TextLineCodecFactory(
                            Charset.forName("utf-8"))
            ));
            ioAcceptor.getSessionConfig().setReadBufferSize(2048);
            ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            ioAcceptor.setHandler(new FirstServerHandler());
            ioAcceptor.bind(new InetSocketAddress(PORT));
            LOGGER.info("服务器启动成功!端口号: " + PORT);
        }catch (Exception e){
            LOGGER.error("服务器端启动异常: " + e);
            e.printStackTrace();
        }
    }
}
