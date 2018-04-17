package client.thread;

import client.resources.dao.PlcDataDayDAO;
import entities.PlcDataDay;
import org.apache.mina.core.session.IoSession;
import server.protocol.MessageProtocol;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TimerTask;
public class SendPlcDataDayData extends TimerTask{
    private Timestamp startTime = null;
    //查询数据的间隔时间,单位毫秒(秒 * 分 * 时 * 天 * N)
    private  final static int TIME_INTERVAL= 1000 * 60 * 60 * 24 * 10;
    //打包多少条数据一起发送
    private final static int DATA_PACKAGE = 10;
    private IoSession session;
    public SendPlcDataDayData(IoSession session){
        this.session = session;
    }
    @Override
    public void run() {
        PlcDataDayDAO plcDataDayDAO = new PlcDataDayDAO();
        ArrayList<PlcDataDay> allPlcDataDays;
        ArrayList<PlcDataDay> allSendData = new ArrayList<>();
//      若为第一次同步数据
        if (startTime == null) {
            allPlcDataDays = (ArrayList<PlcDataDay>) plcDataDayDAO.getAllPlcDataDays();
            startTime = allPlcDataDays.get(0).getCreate_date();
            Timestamp endTime = new Timestamp(startTime.getTime() + TIME_INTERVAL);
            for (PlcDataDay plcDataDay : allPlcDataDays) {
                if (plcDataDay.getCreate_date().compareTo(endTime) <= 0) {
                    allSendData.add(plcDataDay);
                    if (plcDataDay.getCreate_date().compareTo(endTime) == 0)
                        startTime = plcDataDay.getCreate_date();
                    else
                        startTime = endTime;
                }
            }
        }
//      非第一次同步数据
        else {
            try {
                Timestamp endTime = new Timestamp(startTime.getTime() + TIME_INTERVAL);
                allSendData = (ArrayList<PlcDataDay>) plcDataDayDAO.getSendPlcDataDay(startTime, endTime);
                if (allSendData.size() == 0) {
                    String content = "PlcDataDay: Between " + startTime + " and " + endTime + " No data!";
                    MessageProtocol objectMessage = new MessageProtocol(1, content);
                    session.write(objectMessage);
                    startTime = endTime;
                } else
                    startTime = allSendData.get(allSendData.size() - 1).getCreate_date();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//      needSendNumber: 需要发送的总次数 hasSendNumber: 已经发送的总次数
        int needSendNumber, hasSendNumber = 0;
        if(((allSendData.size()) % DATA_PACKAGE) != 0)
            needSendNumber = (allSendData.size() / DATA_PACKAGE) + 1;
        else
            needSendNumber = (allSendData.size()) / DATA_PACKAGE;
        try {
            while (allSendData.size() != 0 && hasSendNumber < needSendNumber) {
//              sendData: 总发送字符串
                StringBuilder sendData = new StringBuilder();
//              thisSendDataNumbers: 这次循环数据的总数 hasSendDataNumber: 已经发送数据的总数
                int thisSendDataNumbers, hasSendDataNumbers;
                if((allSendData.size() - DATA_PACKAGE * hasSendNumber) >= DATA_PACKAGE)
                    thisSendDataNumbers = DATA_PACKAGE;
                else
                    thisSendDataNumbers = allSendData.size() - DATA_PACKAGE * hasSendNumber;
                hasSendDataNumbers = DATA_PACKAGE * hasSendNumber;
//              拼接每条数据为一个字符串
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
                    content.append(allSendData.get(i + hasSendDataNumbers).getUpdate_date());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getCalculate_date());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getDevice_id());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getDevice_type());
                    content.append(",");
                    content.append(allSendData.get(i + hasSendDataNumbers).getIsSend());
                    allSendData.get(i + hasSendDataNumbers).setIsSend(true);
                    content.append(";");
                    if (sendData.toString().equals(""))
                        sendData = content;
                    else
                        sendData.append(content);
                }
//              判断全部数据是否已经发送完
                if (hasSendNumber == needSendNumber - 1)
                    sendData.append("PlcDataDay,true");
                else
                    sendData.append("PlcDataDay,false");
                String sendContent = sendData.toString();
                MessageProtocol objectMessage = new MessageProtocol(1, sendContent);
                session.write(objectMessage);
                hasSendNumber += 1;
//              发送一次,线程等待50毫秒
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
//      更新已发送数据库状态
        if(allSendData.size() != 0) {
            int i = plcDataDayDAO.batchUpdatePlcDataDayIsSend(allSendData);
            if (i != 0)
                System.out.println("Update Success!");
            else
                System.out.println("ERROR");
        }
    }
}
