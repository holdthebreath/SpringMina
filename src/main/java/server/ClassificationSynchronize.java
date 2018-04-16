package server;

import dao.SynchronizePlcDataAlarmMapper;
import dao.SynchronizePlcDataDayMapper;
import entities.PlcDataAlarm;
import entities.PlcDataDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
@Component
public class ClassificationSynchronize {
    private final static int Plc_Data_Alarm_Attributes = 27;
    private final static int Plc_Data_Day_Attributes = 11;

    @Autowired
    SynchronizePlcDataAlarmMapper synchronizePlcDataAlarmMapper;
    @Autowired
    SynchronizePlcDataDayMapper synchronizePlcDataDayMapper;

    public int SynchronizePlcDataAlarm(String[] receiveDatas) throws Exception{
        PlcDataAlarm plcDataAlarm;
        ArrayList<PlcDataAlarm> receivePlcDataAlarms = new ArrayList<>();
        for (int j = 0; j < receiveDatas.length - 1; j++) {
            String[] stringPlcDataAlarm = receiveDatas[j].split(",");
            plcDataAlarm = new PlcDataAlarm();
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(int i = 0; i < Plc_Data_Alarm_Attributes; i++){
                switch (i){
                    case 0:
                        String stringId = stringPlcDataAlarm[i].replace(" ", "");
                        BigInteger id = new BigInteger(stringId);
                        plcDataAlarm.setId(id);
                        break;
                    case 1:
                        plcDataAlarm.setStation_id(stringPlcDataAlarm[i]);
                        break;
                    case 2:
                        plcDataAlarm.setQuota_key(stringPlcDataAlarm[i]);
                        break;
                    case 3:
                        plcDataAlarm.setQuota_value(stringPlcDataAlarm[i]);
                        break;
                    case 4:
                        plcDataAlarm.setQuota_type(stringPlcDataAlarm[i]);
                        break;
                    case 5:
                        if(!stringPlcDataAlarm[i].equals("null")) {
                            java.util.Date date1 = df1.parse(stringPlcDataAlarm[i]);
                            String createDate = df1.format(date1);
                            plcDataAlarm.setCreate_date(Timestamp.valueOf(createDate));
                        }
                        else
                            plcDataAlarm.setCreate_date(null);
                        break;
                    case 6:
                        if(!stringPlcDataAlarm[i].equals("null"))
                            plcDataAlarm.setCreate_by(stringPlcDataAlarm[i]);
                        else
                            plcDataAlarm.setCreate_by(null);
                        break;
                    case 7:
                        if(!stringPlcDataAlarm[i].equals("null")) {
                            java.util.Date date2 = df1.parse(stringPlcDataAlarm[i]);
                            String updateDate = df1.format(date2);
                            plcDataAlarm.setUpdate_date(Timestamp.valueOf(updateDate));
                        }
                        else
                            plcDataAlarm.setUpdate_date(null);
                        break;
                    case 8:
                        if(!stringPlcDataAlarm[i].equals("null")) {
                            java.util.Date date3 = df1.parse(stringPlcDataAlarm[i]);
                            String releaseDate = df1.format(date3);
                            plcDataAlarm.setRelease_date(Timestamp.valueOf(releaseDate));
                        }
                        else
                            plcDataAlarm.setRelease_date(null);
                        break;
                    case 9:
                        if(!stringPlcDataAlarm[i].equals("null")) {
                            java.util.Date date4 = df1.parse(stringPlcDataAlarm[i]);
                            String dealDate = df1.format(date4);
                            plcDataAlarm.setDeal_date(Timestamp.valueOf(dealDate));
                        }
                        else
                            plcDataAlarm.setDeal_date(null);
                        break;
                    case 10:
                        if(!stringPlcDataAlarm[i].equals("null"))
                            plcDataAlarm.setDeal_user(stringPlcDataAlarm[i]);
                        else
                            plcDataAlarm.setDeal_user(null);
                        break;
                    case 11:
                        if(!stringPlcDataAlarm[i].equals("null"))
                            plcDataAlarm.setDeal_mark(stringPlcDataAlarm[i]);
                        else
                            plcDataAlarm.setDeal_mark(null);
                        break;
                    case 12:
                        if(!stringPlcDataAlarm[i].equals("null"))
                            plcDataAlarm.setDeal_flag(stringPlcDataAlarm[i]);
                        else
                            plcDataAlarm.setDeal_flag(null);
                        break;
                    case 13:
                        plcDataAlarm.setAlarm_msg(stringPlcDataAlarm[i]);
                        break;
                    case 14:
                        plcDataAlarm.setAlarm_type(stringPlcDataAlarm[i]);
                        break;
                    case 15:
                        String stringAlarmFlag = stringPlcDataAlarm[i].replace(" ", "");
                        int alarm_flag = Integer.parseInt(stringAlarmFlag);
                        plcDataAlarm.setAlarm_flag(alarm_flag);
                        break;
                    case 16:
                        if(!stringPlcDataAlarm[i].equals("null")) {
                            String stringAlarmTime = stringPlcDataAlarm[i].replace(" ", "");
                            BigDecimal alarm_time = new BigDecimal(stringAlarmTime);
                            plcDataAlarm.setAlarm_time(alarm_time);
                        }
                        else
                            plcDataAlarm.setAlarm_time(null);
                        break;
                    case 17:
                        if(!stringPlcDataAlarm[i].equals("null"))
                            plcDataAlarm.setDevice_id(stringPlcDataAlarm[i]);
                        else
                            plcDataAlarm.setDevice_id(null);
                        break;
                    case 18:
                        if(!stringPlcDataAlarm[i].equals("null"))
                            plcDataAlarm.setDevice_type(stringPlcDataAlarm[i]);
                        else
                            plcDataAlarm.setDevice_type(null);
                        break;
                    case 19:
                        if(!stringPlcDataAlarm[i].equals("null")) {
                            double lower_limit = Double.valueOf(stringPlcDataAlarm[i]);
                            plcDataAlarm.setLower_limit(lower_limit);
                        }
                        break;
                    case 20:
                        if(!stringPlcDataAlarm[i].equals("null")) {
                            double upper_limit = Double.valueOf(stringPlcDataAlarm[i]);
                            plcDataAlarm.setLower_limit(upper_limit);
                        }
                        break;
                    case 21:
                        plcDataAlarm.setSubflow_id(stringPlcDataAlarm[i]);
                        break;
                    case 22:
                        plcDataAlarm.setUnit_id(stringPlcDataAlarm[i]);
                        break;
                    case 23:
                        plcDataAlarm.setRelease_flag(stringPlcDataAlarm[i]);
                        break;
                    case 24:
                        plcDataAlarm.setOrigin_flag(stringPlcDataAlarm[i]);
                        break;
                    case 25:
                        if(!stringPlcDataAlarm[i].equals("null"))
                            plcDataAlarm.setPlan_id(stringPlcDataAlarm[i]);
                        else
                            plcDataAlarm.setPlan_id(null);
                        break;
                    case 26:
                        if (stringPlcDataAlarm[i].equals("false"))
                            plcDataAlarm.setIsSend(false);
                        else
                            plcDataAlarm.setIsSend(true);
                        break;
                    default:
                       throw new Exception("PlcDataAlarm: 属性分类错误!");
                }
                receivePlcDataAlarms.add(plcDataAlarm);
            }
        }
        return synchronizePlcDataAlarmMapper.insertPlcDataAlarm(receivePlcDataAlarms);
    }

    public int SynchronizePlcDataDay(String[] receiveDatas) throws Exception{
        PlcDataDay plcDataDay;
        ArrayList<PlcDataDay> receivePlcDataDays = new ArrayList<>();
        for (int j = 0; j < receiveDatas.length - 1; j++) {
            String[] stringPlcDataDay = receiveDatas[j].split(",");
            plcDataDay = new PlcDataDay();
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            for(int i = 0; i < Plc_Data_Day_Attributes; i++){
                switch (i){
                    case 0:
                        String stringId = stringPlcDataDay[i].replace(" ", "");
                        BigInteger id = new BigInteger(stringId);
                        plcDataDay.setId(id);
                        break;
                    case 1:
                        plcDataDay.setStation_id(stringPlcDataDay[i]);
                        break;
                    case 2:
                        plcDataDay.setQuota_key(stringPlcDataDay[i]);
                        break;
                    case 3:
                        plcDataDay.setQuota_value(stringPlcDataDay[i]);
                        break;
                    case 4:
                        plcDataDay.setQuota_type(stringPlcDataDay[i]);
                        break;
                    case 5:
                        if(!stringPlcDataDay[i].equals("null")) {
                            java.util.Date date1 = df1.parse(stringPlcDataDay[i]);
                            String createDate = df1.format(date1);
                            plcDataDay.setCreate_date(Timestamp.valueOf(createDate));
                        }
                        else
                            plcDataDay.setCreate_date(null);
                        break;
                    case 6:
                        if(!stringPlcDataDay[i].equals("null")) {
                            java.util.Date date2 = df1.parse(stringPlcDataDay[i]);
                            String updateDate = df1.format(date2);
                            plcDataDay.setUpdate_date(Timestamp.valueOf(updateDate));
                        }
                        else
                            plcDataDay.setUpdate_date(null);
                        break;
                    case 7:
                        java.util.Date d = df2.parse(stringPlcDataDay[i]);
                        Date calculate_date = new Date(d.getTime());
                        plcDataDay.setCalculate_date(calculate_date);
                    case 8:
                        if(!stringPlcDataDay[i].equals("null"))
                            plcDataDay.setDevice_id(stringPlcDataDay[i]);
                        else
                            plcDataDay.setDevice_id(null);
                        break;
                    case 9:
                        if(!stringPlcDataDay[i].equals("null"))
                            plcDataDay.setDevice_type(stringPlcDataDay[i]);
                        else
                            plcDataDay.setDevice_type(null);
                        break;
                    case 10:
                        if (stringPlcDataDay[i].equals("false"))
                            plcDataDay.setIsSend(false);
                        else
                            plcDataDay.setIsSend(true);
                        break;
                    default:
                        throw new Exception("PlcDataDay: 属性分类错误!");
                }
                receivePlcDataDays.add(plcDataDay);
            }
        }
        return synchronizePlcDataDayMapper.insertPlcDataDay(receivePlcDataDays);
    }

}
