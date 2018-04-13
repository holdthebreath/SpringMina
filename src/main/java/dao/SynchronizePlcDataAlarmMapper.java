package dao;

import entities.PlcDataAlarm;

import java.util.List;

public interface SynchronizePlcDataAlarmMapper {
    int insertPlcDataAlarm(List<PlcDataAlarm> allPlcDataAlarm);
}
