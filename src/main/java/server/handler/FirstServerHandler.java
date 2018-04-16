package server.handler;

//import dao.MessageMapper;
import dao.ReceiveMapper;
        import entities.Message;
import org.apache.mina.core.service.IoHandlerAdapter;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;
import server.ClassificationSynchronize;

        import java.sql.Timestamp;
        import java.util.*;

@Component
public class FirstServerHandler extends IoHandlerAdapter{
    private Timestamp startTime = null;
    //单位毫秒
    private static int TIME_INTERVAL= 5000;
//    @Autowired
//    MessageMapper messageMapper;
    @Autowired
    ReceiveMapper receiveMapper;
    @Autowired
    ClassificationSynchronize classificationSynchronize;
    private static Logger LOGGER = Logger.getLogger(FirstServerHandler.class);
    @Override
    public void sessionCreated(IoSession session) throws Exception{
        SocketSessionConfig sessionConfig = (SocketSessionConfig)session.getConfig();
        sessionConfig.setReadBufferSize(1024 * 1024 * 10);
        sessionConfig.setReceiveBufferSize(1024 * 1024 * 10);
        LOGGER.info("客户端与服务器端创建连接!");
    }
    @Override
    public void sessionOpened(IoSession session) throws Exception{
        LOGGER.info("客户端与服务器端连接打开!");
    }
    @Override
    public void messageReceived(IoSession session, Object receivedMessage) throws Exception{
        String msg = receivedMessage.toString();
//        LOGGER.info("服务器端接收的数据为: " + msg);
//        ArrayList<Message> allMessages;
        try {
            //添加信息
//            String address = session.getLocalAddress().toString();
//            allMessages = messageMapper.getAllMessages();
//            Message message = new Message();
//            message.setId(allMessages.size());
//            message.setAddress(address);
//            message.setMessage(msg);
//            Date date = new Date();
//            Timestamp timeStamep = new Timestamp(date.getTime());
//            message.setDate(timeStamep);
//            message.setIsSend(false);
//            messageMapper.insertMessage(message);
            //查询信息
//            ArrayList<Message> messages = new ArrayList<>();
//            if(startTime == null){
//                allMessages = messageMapper.getAllMessages();
//                startTime = allMessages.get(0).getDate();
//                Timestamp endTime = new Timestamp(startTime.getTime() + TIME_INTERVAL);
//                for (Message message: allMessages) {
//                    if(message.getDate().compareTo(endTime) <= 0) {
//                        messages.add(message);
//                        if (message.getDate().compareTo(endTime) == 0)
//                            startTime = message.getDate();
//                        else
//                            startTime = endTime;
//                    }
//                }
//            }
//            else {
//                Timestamp endTime = new Timestamp(startTime.getTime() + TIME_INTERVAL);
//                messages = messageMapper.getMessages(startTime, endTime);
//                if (messages.size() == 0) {
//                    session.write("Between " + startTime + " and " + endTime + " No data!");
//                    startTime = endTime;
//                }
//                else
//                    startTime = messages.get(messages.size() - 1).getDate();
//            }
//            for (Message message: messages) {
//                session.write("id: " + message.getId() + " address: " + message.getAddress() + " message: " + message.getMessage() + " date: "+ message.getDate());
//            }

            //显示收到的信息
            String message[] = msg.split(";");
            if(message.length != 1) {
                String[] tail = message[message.length - 1].split(",");
                String tableName = tail[0];
                boolean isReceiveAll = Boolean.parseBoolean(tail[1]);
                if (tableName.equals("PlcDataAlarm"))
                    if (classificationSynchronize.SynchronizePlcDataAlarm(message) != 0)
                        System.out.println("同步PlcDataAlarm表成功!");
                    else
                        System.out.println("同步PlcDataAlarm表失败!");
                else if (tableName.equals("PlcDataDay"))
                    if(classificationSynchronize.SynchronizePlcDataDay(message) != 0) {
                        if (isReceiveAll)
                            System.out.println("同步PlcDataDay表成功!");
                        else
                            System.out.println("等待后续数据......");
                    }
                    else
                        System.out.println("同步PlcDataDay表失败!");
            }
            else
                System.out.println(message[0]);


//            if(message.length == 5) {
//                int id = -123456789;
//                String address = null;
//                String data = null;
//                Timestamp date = null;
//                boolean isSend = false;
//                for (int i = 0; i < message.length; i++) {
//                    switch (i) {
//                        case 0:
//                            String stringId = message[0].replace(" ", "");
//                            id = Integer.parseInt(stringId);
//                            break;
//                        case 1:
//                            address = message[1];
//                            break;
//                        case 2:
//                            data = message[2];
//                            break;
//                        case 3:
//                            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                            java.util.Date date11 = df1.parse(message[3]);
//                            String time = df1.format(date11);
//                            date = Timestamp.valueOf(time);
//                            break;
//                        case 4:
//                            if (message[4].equals("false"))
//                                isSend = false;
//                            else
//                                isSend = true;
//                            break;
//                    }
//                }
//                Message receiveData = new Message(id, address, data, date, isSend);
//                receiveMapper.insertReceive(receiveData);
//            }else
//                System.out.println(message[0]);

//            for (String e: message) {
//                System.out.println(e);
//            }

//            Timer timer = new Timer();
//            timer.schedule(new TimerSendData(session, messageMapper), 5000, 5000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void messageSent(IoSession session, Object message) throws Exception{
        LOGGER.info("服务器发送信息成功!");
    }
    @Override
    public void sessionClosed(IoSession session) throws Exception{
        LOGGER.info("客户端与服务器端断开连接!");
    }
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception{
        LOGGER.info("服务器进入空闲状态!");
    }
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception{
        LOGGER.error("服务器端发送异常: " + cause);
    }
}
