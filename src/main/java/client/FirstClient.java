package client;

import client.handler.FirstClientHandler;
import client.thread.SendPlcDataAlarmData;
import client.thread.SendPlcDataDayData;
import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import server.codeFactory.TestFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FirstClient {
    private static Logger LOGGER = Logger.getLogger(FirstClient.class);
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 9876;

    public static void main(String[] args) {
        IoConnector ioConnector = new NioSocketConnector();
        ioConnector.setConnectTimeoutMillis(30000);
        ioConnector.getFilterChain().addLast(
                "codec",
                new ProtocolCodecFilter(new TestFactory())
        );
        ioConnector.setHandler(new FirstClientHandler());
        IoSession session = null;
        try {
            ConnectFuture future = ioConnector.connect(new InetSocketAddress(HOST, PORT));
            future.awaitUninterruptibly();
            session = future.getSession();

//            Date date = new Date();
//            Timestamp timeStamep = new Timestamp(date.getTime());
//            Message message = new Message(14, session.getLocalAddress() + "", "hello", timeStamep, false);
//
//
//            String content = "id: " + message.getId() + ",address: " + message.getAddress() + ",message: " + message.getMessage() + ",date: " + message.getDate()
//                    + ",isSend: " + message.getIsSend();
//            MessageProtocol objectMessage = new MessageProtocol(1, content);
//
//            session.write(objectMessage);

//            Timer timer = new Timer();
//            timer.schedule(new TimerSendData(session), 1000, 1000);
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleWithFixedDelay(new SendPlcDataDayData(session), 2, 3, TimeUnit.SECONDS);
            service.scheduleWithFixedDelay(new SendPlcDataAlarmData(session), 3, 3, TimeUnit.SECONDS);
        }catch (Exception e){
            LOGGER.error("客户端链接异常: ", e);
        }
        session.getCloseFuture().awaitUninterruptibly();
        ioConnector.dispose();
    }
}
