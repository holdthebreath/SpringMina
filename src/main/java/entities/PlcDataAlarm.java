package entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class PlcDataAlarm {
    private BigInteger id;
    private String station_id;
    private String quota_key;
    private String quota_value;
    private String quota_type;
    private Timestamp create_date;
    private String create_by;
    private Timestamp update_date;
    private Timestamp release_date;
    private Timestamp deal_date;
    private String deal_user;
    private String deal_mark;
    private String deal_flag;
    private String alarm_msg;
    private String alarm_type;
    private int alarm_flag;
    private BigDecimal alarm_time;
    private String device_id;
    private String device_type;
    private double lower_limit;
    private double upper_limit;
    private String subflow_id;
    private String unit_id;
    private String release_flag;
    private String origin_flag;
    private String plan_id;
    private boolean isSend;
//
//    public PlcDataAlarm(BigInteger id, String station_Id, String quota_Key, String quota_Value, String quota_Type, Timestamp create_Date, String create_By,
//    Timestamp update_Date, Timestamp release_Date, Timestamp deal_Date, String ){
//
//    }

    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getStation_id() {
        return station_id;
    }
    public void setStation_id(String station_Id) {
        this.station_id = station_Id;
    }

    public String getQuota_key() {
        return quota_key;
    }
    public void setQuota_key(String quota_key) {
        this.quota_key = quota_key;
    }

    public String getQuota_value() {
        return quota_value;
    }
    public void setQuota_value(String quotaValue) {
        this.quota_value = quota_value;
    }

    public String getQuota_type() {
        return quota_type;
    }
    public void setQuota_type(String quota_type) {
        this.quota_type = quota_type;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }
    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public String getCreate_by() {
        return create_by;
    }
    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Timestamp getUpdate_date() {
        return update_date;
    }
    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }

    public Timestamp getRelease_date() {
        return release_date;
    }
    public void setRelease_date(Timestamp release_date) {
        this.release_date = release_date;
    }

    public Timestamp getDeal_date() {
        return deal_date;
    }
    public void setDeal_date(Timestamp deal_date) {
        this.deal_date = deal_date;
    }

    public String getDeal_user() {
        return deal_user;
    }
    public void setDeal_user(String deal_user) {
        this.deal_user = deal_user;
    }

    public String getDeal_mark() {
        return deal_mark;
    }
    public void setDeal_mark(String deal_mark) {
        this.deal_mark = deal_mark;
    }

    public String getDeal_flag() {
        return deal_flag;
    }
    public void setDeal_flag(String deal_flag) {
        this.deal_flag = deal_flag;
    }

    public String getAlarm_msg() {
        return alarm_msg;
    }
    public void setAlarm_msg(String alarm_msg) {
        this.alarm_msg = alarm_msg;
    }

    public String getAlarm_type() {
        return alarm_type;
    }
    public void setAlarm_type(String alarm_type) {
        this.alarm_type = alarm_type;
    }

    public int getAlarm_flag() {
        return alarm_flag;
    }
    public void setAlarm_flag(int alarm_flag) {
        this.alarm_flag = alarm_flag;
    }

    public BigDecimal getAlarm_time() {
        return alarm_time;
    }
    public void setAlarm_time(BigDecimal alarm_time) {
        this.alarm_time = alarm_time;
    }

    public String getDevice_id() {
        return device_id;
    }
    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_type() {
        return device_type;
    }
    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public double getLower_limit() {
        return lower_limit;
    }
    public void setLower_limit(double lower_limit) {
        this.lower_limit = lower_limit;
    }

    public double getUpper_limit() {
        return upper_limit;
    }
    public void setUpper_limit(double upper_limit) {
        this.upper_limit = upper_limit;
    }

    public String getSubflow_id() {
        return subflow_id;
    }
    public void setSubflow_id(String subflow_id) {
        this.subflow_id = subflow_id;
    }

    public String getUnit_id() {
        return unit_id;
    }
    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getRelease_flag() {
        return release_flag;
    }
    public void setRelease_flag(String release_flag) {
        this.release_flag = release_flag;
    }

    public String getOrigin_flag() {
        return origin_flag;
    }
    public void setOrigin_flag(String origin_flag) {
        this.origin_flag = origin_flag;
    }

    public String getPlan_id() {
        return plan_id;
    }
    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public boolean getIsSend() {
        return isSend;
    }
    public void setIsSend(boolean isSend){
        this.isSend = isSend;
    }

    @Override
    public String toString(){
        return "id: " + id + " , stationId: " + station_id + " , quotaKey: " + quota_key + " , quotaValue: " + quota_value +
                " ,  quotaType: " + quota_type + " , createDate: " + create_date + " , createBy: " + create_by + " , updateDate: " + update_date +
                " , releaseDate: " + release_date + " , dealDate: " + deal_date + " , dealUser: " + deal_user + " , dealMark: " + deal_mark +
                " , dealFlag: " + deal_flag + " , alarmMsg: " + alarm_msg + " , alarmType: " + alarm_type + " , alarmFlag: " + alarm_flag +
                " , alarmTime: " + alarm_time + " , deviceId: " + device_id + " , deviceType: " + device_type + " , lowerLimit: " + lower_limit +
                " , upperLimit: " + upper_limit + " , subflowId: " + subflow_id + " , unitId: " + unit_id + " , releaseFlag: " + release_flag +
                " , originId: " + origin_flag + " , planId: " + plan_id + " , isSend: " + isSend;
    }

}
