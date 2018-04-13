package server;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class MyKeepAliveMessageFactory implements KeepAliveMessageFactory {
    private static Logger LOGGER = Logger.getLogger(MyKeepAliveMessageFactory.class);
    private static final String HEART_BEAT_REQURST = "NMSL";
    private static final String HEART_BEAT_RESPONSE = "WSND";
    public Object getRequest(IoSession session){
        LOGGER.info("请求预设信息: " + HEART_BEAT_REQURST);
        return HEART_BEAT_REQURST;
    }
    public Object getResponse(IoSession session, Object Message){
        LOGGER.info("响应预设信息: " + HEART_BEAT_RESPONSE);
        return HEART_BEAT_RESPONSE;
    }
    public boolean isRequest(IoSession session, Object message){
        LOGGER.info("请求心跳包信息: " + message);
        if(message.equals(HEART_BEAT_REQURST))
            return true;
        else
            return false;
    }
    public boolean isResponse(IoSession session, Object message){
        LOGGER.info("响应心跳包信息: " + message);
        if(message.equals(HEART_BEAT_RESPONSE))
            return true;
        else
            return false;
    }
}
