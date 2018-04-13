package dao;

import entities.PlcDataDay;

import java.util.List;

public interface SynchronizePlcDataDayMapper {
    int insertPlcDataDay(List<PlcDataDay> allPlcDataDay);
}
