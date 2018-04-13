package server.protocol;

import dao.ReceiveMapper;
import entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import server.SerializeUtil;

import java.nio.charset.Charset;

public class MessageProtocol {
    private int type;
    private Integer length;                                                                                             //数据包总长度
    private String content;
    public MessageProtocol(int type, String content){
        this.type = type;
        this.content = content;
    }
    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public Integer getLength(){
        length = 4 * 2 + this.content.length();
        return length;
    }

}
