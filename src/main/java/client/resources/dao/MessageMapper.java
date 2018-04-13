package client.resources.dao;

import entities.Message;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface MessageMapper {
    List<Message> getAllMessages();
    List<Message> getMessageById(int messageId);
    int insertMessage(Message message);
    int deleteMessageById(int messageId);
    List<Message> getMessages(@Param("startTime")Timestamp startTime, @Param("endTime")Timestamp endTime);
    int updateMessage(Message message);
}
