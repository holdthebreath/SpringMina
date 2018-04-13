package client.resources.dao;

import entities.PlcDataDay;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PlcDataDayMapper {
    List<PlcDataDay> getAllPlcDataDays();
    List<PlcDataDay> getSendPlcDataDay(@Param("startTime")Timestamp startTime, @Param("endTime")Timestamp endTime);
    int batchUpdatePlcDataDayIsSend(List<PlcDataDay> hasSendPlcDataAlarms);
}
