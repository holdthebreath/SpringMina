package client.handler;

//import dao.MessageMapper;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import server.protocol.MessageProtocol;

public class FirstClientHandler extends IoHandlerAdapter {
    private static Logger LOGGER = Logger.getLogger(FirstClientHandler.class);
//
//    @Autowired
//    MessageMapper messageMapper;

    @Override
    public void messageReceived(IoSession session, Object message){
        String msg = message.toString();
        LOGGER.info("客户端收到的信息为: " + msg);
        if(message.toString().equals("NMSL")){
            LOGGER.info("收到心跳包");
//            MessageProtocol messageProtocol = new MessageProtocol(0, "WSND");
//            session.write(messageProtocol);
        }
    }
    @Override
    public void exceptionCaught(IoSession session, Throwable cause){
        LOGGER.error("客户端发生异常: " + cause);
    }
}
