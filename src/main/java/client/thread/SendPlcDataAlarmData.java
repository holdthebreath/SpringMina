package client.thread;

import client.resources.dao.MessageDAO;
import client.resources.dao.PlcDataAlarmDAO;
import entities.Message;
import entities.PlcDataAlarm;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.mina.core.session.IoSession;
import server.protocol.MessageProtocol;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TimerTask;
public class SendPlcDataAlarmData extends TimerTask{
    private Timestamp startTime = null;
    //间隔时间,单位毫秒(秒 * 分 * 时 * 天 * N)
    private static int TIME_INTERVAL= 1000 * 60 * 60 * 24 * 5;
    private IoSession session;
//    private MessageMapper messageMapper;
//    public IoSession getSession() {
//        return session;
//    }
//    public void setSession(IoSession session){
//        this.session = session;
//    }
    public SendPlcDataAlarmData(IoSession session){
        this.session = session;
//        this.messageMapper = messageMapper;
    }

    @Override
    public void run() {
//        ArrayList<Message> allMessages;
//        ArrayList<Message> messages = new ArrayList<>();
//        MessageDAO messageDAO = new MessageDAO();
        PlcDataAlarmDAO plcDataAlarmDAO = new PlcDataAlarmDAO();
        ArrayList<PlcDataAlarm> allPlcDataAlarms;
        ArrayList<PlcDataAlarm> allSendData = new ArrayList<>();

        if(startTime == null){
//            messageDAO = new MessageDAO();
//            allMessages = (ArrayList<Message>) messageDAO.getAllMessages();
//            startTime = allMessages.get(0).getDate();
            allPlcDataAlarms = (ArrayList<PlcDataAlarm>) plcDataAlarmDAO.getAllPlcDataAlarms();
//
//            for (PlcDataAlarm e:allPlcDataAlarms) {
//                System.out.println(e.toString());
//                System.out.println();
//            }

            startTime = allPlcDataAlarms.get(0).getCreate_date();
            Timestamp endTime = new Timestamp(startTime.getTime() + TIME_INTERVAL);
//            for (Message message: allMessages) {
//                if(message.getDate().compareTo(endTime) <= 0) {
//                    messages.add(message);
//                    if (message.getDate().compareTo(endTime) == 0)
//                        startTime = message.getDate();
//                    else
//                        startTime = endTime;
//                }
//            }
            for (PlcDataAlarm plcDataAlarm: allPlcDataAlarms) {
                if(plcDataAlarm.getCreate_date().compareTo(endTime) <= 0) {
                    allSendData.add(plcDataAlarm);
                    if (plcDataAlarm.getCreate_date().compareTo(endTime) == 0)
                        startTime = plcDataAlarm.getCreate_date();
                    else
                        startTime = endTime;
                }
            }
        }
        else {
            Timestamp endTime = new Timestamp(startTime.getTime() + TIME_INTERVAL);
//            messages = (ArrayList<Message>) messageDAO.getMessages(startTime, endTime);
            allSendData = (ArrayList<PlcDataAlarm>) plcDataAlarmDAO.getSendPlcDataAlarm(startTime, endTime);

//            if (messages.size() == 0) {
            if(allSendData.size() == 0){
                String content = "PlcDataAlarm: Between " + startTime + " and " + endTime + " No data!";
                MessageProtocol objectMessage = new MessageProtocol(1, content);
                session.write(objectMessage);
                startTime = endTime;
            }
            else
                startTime = allSendData.get(allSendData.size() - 1).getCreate_date();
//                startTime = messages.get(messages.size() - 1).getDate();
        }
//        for (Message message: messages) {
        String sendContent = null;
        for (PlcDataAlarm plcDataAlarm: allSendData){
//            String content = " id: " + message.getId() + ",address: " + message.getAddress() + ",message: " + message.getMessage() + ",date: " + message.getDate()
//                    + ",isSend: " + message.getIsSend();
//            String content = " " + message.getId() + "," + message.getAddress() + "," + message.getMessage() + "," + message.getDate() + "," + message.getIsSend();
//            MessageProtocol objectMessage = new MessageProtocol(1, content);
//            session.write(objectMessage);
//            message.setIsSend(true);
//            int i = messageDAO.updateMessageById(message);
            String content = plcDataAlarm.getId() + "," + plcDataAlarm.getStation_id() + "," + plcDataAlarm.getQuota_key() + "," + plcDataAlarm.getQuota_value()
                    + "," + plcDataAlarm.getQuota_type() + "," + plcDataAlarm.getCreate_date() + "," + plcDataAlarm.getCreate_by() + "," + plcDataAlarm.getUpdate_date()
                    + "," + plcDataAlarm.getRelease_date() + "," + plcDataAlarm.getDeal_date() + "," + plcDataAlarm.getDeal_user() + "," + plcDataAlarm.getDeal_mark()
                    + "," + plcDataAlarm.getDeal_flag() + "," + plcDataAlarm.getAlarm_msg() + "," + plcDataAlarm.getAlarm_type() + "," + plcDataAlarm.getAlarm_flag()
                    + "," + plcDataAlarm.getAlarm_time() + "," + plcDataAlarm.getDevice_id() + "," + plcDataAlarm.getDevice_type() + "," + plcDataAlarm.getLower_limit()
                    + "," + plcDataAlarm.getUpper_limit() + "," + plcDataAlarm.getSubflow_id() + "," + plcDataAlarm.getUnit_id() + "," + plcDataAlarm.getRelease_flag()
                    + "," + plcDataAlarm.getOrigin_flag() + "," + plcDataAlarm.getPlan_id() + "," + plcDataAlarm.getIsSend();
            plcDataAlarm.setIsSend(true);
            content = content + ";";
            if(sendContent == null)
                sendContent = content;
            else
                sendContent += content;
//            if(i != 0)
//                System.out.println("Update success!");
//            else
//                System.out.println("ERROR");
//            session.write("id: " + message.getId() + " address: " + message.getAddress() + " message: " + message.getMessage() + " date: "+ message.getDate());
        }
        MessageProtocol objectMessage;
        if(sendContent != null) {
            sendContent += "PlcDataAlarm";
            objectMessage = new MessageProtocol(1, sendContent);
            session.write(objectMessage);
            int i = plcDataAlarmDAO.batchUpdatePlcDataAlarmIsSend(allSendData);
            if(i != 0)
                System.out.println("Update success!");
            else
                System.out.println("ERROR");
        }
    }
}
