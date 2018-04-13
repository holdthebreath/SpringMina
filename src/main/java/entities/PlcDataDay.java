package entities;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

public class PlcDataDay {
    private BigInteger id;
    private String station_id;
    private String quota_key;
    private String quota_value;
    private String quota_type;
    private Timestamp create_date;
    private Timestamp update_date;
    private Date calculate_date;
    private String device_id;
    private String device_type;
    private boolean isSend;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
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

    public void setQuota_value(String quota_value) {
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

    public Timestamp getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }

    public Date getCalculate_date() {
        return calculate_date;
    }

    public void setCalculate_date(Date calculate_date) {
        this.calculate_date = calculate_date;
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

    public boolean getIsSend() {
        return isSend;
    }

    public void setIsSend(boolean isSend){
        this.isSend = isSend;
    }

    @Override
    public String toString(){
        return "id: " + id + " , stationId: " + station_id + " , quotaKey: " + quota_key + " , quotaValue: " + quota_value +
                " ,  quotaType: " + quota_type + " , createDate: " + create_date + " , createBy: " + " , updateDate: " + update_date +
                ", Calculate_date: " + calculate_date + " , deviceId: " + device_id + " , deviceType: " + device_type + " , isSend: " + isSend;
    }


}
