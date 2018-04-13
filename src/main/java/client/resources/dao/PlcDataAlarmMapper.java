package client.resources.dao;

import entities.PlcDataAlarm;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PlcDataAlarmMapper {
    List<PlcDataAlarm> getAllPlcDataAlarms();
    List<PlcDataAlarm> getFirstPlcDataAlarms();
    List<PlcDataAlarm> getSendPlcDataAlarm(@Param("startTime")Timestamp startTime, @Param("endTime")Timestamp endTime);
//    int batchUpdatePlcDataAlarm(List<PlcDataAlarm> newPlcDataAlarms);
    int batchUpdatePlcDataAlarmIsSend(List<PlcDataAlarm> hasSendPlcDataAlarms);
}
