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
    private final static int TIME_INTERVAL= 1000 * 60 * 60 * 24 * 3;
    private IoSession session;
    private final static int DATA_PACKAGE = 10;
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
            try {
                Timestamp endTime = new Timestamp(startTime.getTime() + TIME_INTERVAL);
//            messages = (ArrayList<Message>) messageDAO.getMessages(startTime, endTime);
                allSendData = (ArrayList<PlcDataAlarm>) plcDataAlarmDAO.getSendPlcDataAlarm(startTime, endTime);

//            if (messages.size() == 0) {
                if (allSendData.size() == 0) {
                    String content = "PlcDataAlarm: Between " + startTime + " and " + endTime + " No data!";
                    MessageProtocol objectMessage = new MessageProtocol(1, content);
                    session.write(objectMessage);
                    startTime = endTime;
                } else
                    startTime = allSendData.get(allSendData.size() - 1).getCreate_date();
            }catch (Exception e){
                e.printStackTrace();

            }//                startTime = messages.get(messages.size() - 1).getDate();
        }
//        for (Message message: messages) {
        int needSendNumber, hasSendNumber = 0;
        if(((allSendData.size()) % DATA_PACKAGE) != 0)
            needSendNumber = (allSendData.size() / DATA_PACKAGE) + 1;
        else
            needSendNumber = (allSendData.size()) / DATA_PACKAGE;
        try {
            while (allSendData.size() != 0 && hasSendNumber < needSendNumber) {
//            String content = " id: " + message.getId() + ",address: " + message.getAddress() + ",message: " + message.getMessage() + ",date: " + message.getDate()
//                    + ",isSend: " + message.getIsSend();
//            String content = " " + message.getId() + "," + message.getAddress() + "," + message.getMessage() + "," + message.getDate() + "," + message.getIsSend();
//            MessageProtocol objectMessage = new MessageProtocol(1, content);
//            session.write(objectMessage);
//            message.setIsSend(true);
//            int i = messageDAO.updateMessageById(message);
                StringBuilder sendData = new StringBuilder();
                int thisSendDataNumbers, hasSendDataNumbers;
                if((allSendData.size() - DATA_PACKAGE * hasSendNumber) >= DATA_PACKAGE)
                    thisSendDataNumbers = DATA_PACKAGE;
                else
                    thisSendDataNumbers = allSendData.size() - DATA_PACKAGE * hasSendNumber;
                hasSendDataNumbers = DATA_PACKAGE * hasSendNumber;
                for (int i = 0; i < thisSendDataNumbers; i++) {
                    StringBuilder content = new StringBuilder();
                    content.append(allSendData.get(i + hasSendDataNumbers).getId());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getStation_id());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getQuota_key());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getQuota_value());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getQuota_type());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getCreate_date());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getCreate_by());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getUpdate_date());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getRelease_date());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getDeal_date());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getDeal_user());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getDeal_mark());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getDeal_flag());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getAlarm_msg());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getAlarm_type());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getAlarm_flag());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getAlarm_time());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getDevice_id());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getDevice_type());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getLower_limit());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getUpper_limit());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getSubflow_id());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getUnit_id());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getRelease_flag());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getOrigin_flag());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getPlan_id());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getIsSend());
                    allSendData.get(i + hasSendDataNumbers).setIsSend(true);
                    content.append(";");
                    if (sendData.toString().equals(""))
                        sendData = content;
                    else
                        sendData.append(content);
                }
                if (hasSendNumber == needSendNumber - 1)
                    sendData.append("PlcDataAlarm,true");
                else
                    sendData.append("PlcDataAlarm,false");
                String sendContent = " " + sendData.toString();
                MessageProtocol objectMessage = new MessageProtocol(1, sendContent);
                session.write(objectMessage);
                hasSendNumber += 1;
                if(hasSendDataNumbers == needSendNumber -1) {
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//            if(i != 0)
//                System.out.println("Update success!");
//            else
//                System.out.println("ERROR");
//            session.write("id: " + message.getId() + " address: " + message.getAddress() + " message: " + message.getMessage() + " date: "+ message.getDate());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(allSendData.size() != 0) {
            int i = plcDataAlarmDAO.batchUpdatePlcDataAlarmIsSend(allSendData);
            if (i != 0)
                System.out.println("Update Success!");
            else
                System.out.println("ERROR");
        }
    }
}
