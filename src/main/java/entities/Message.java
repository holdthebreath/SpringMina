package entities;

import java.io.Serializable;
import java.sql.Timestamp;
public class Message implements Serializable {
    private int id;
    private String address;
    private String message;
    private Timestamp date;
    private boolean isSend;

    public Message(){

    }
    public Message(int id, String address, String message,Timestamp date, boolean isSend){
        this.id = id;
        this.address = address;
        this.message = message;
        this.date = date;
        this.isSend = isSend;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public void setDate(Timestamp date){
        this.date = date;
    }
    public Timestamp getDate(){
        return date;
    }
    public void setIsSend(boolean isSend){
        this.isSend = isSend;
    }
    public boolean getIsSend(){
        return isSend;
    }
}
