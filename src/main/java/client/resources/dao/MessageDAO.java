package client.resources.dao;

import entities.Message;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

public class MessageDAO {
    private SqlSessionFactory sqlSessionFactory;

    public void before()throws Exception{
        String resource = "client/resources/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public List<Message> getAllMessages(){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession();
            MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
            List<Message> allMessages = mapper.getAllMessages();
            return allMessages;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
        return null;
    }

    public List<Message> getMessages(Timestamp startTime, Timestamp endTime){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession();
            MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
            List<Message> Messages = mapper.getMessages(startTime, endTime);
            return Messages;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
        return null;
    }
    public int updateMessageById(Message message){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession(true);
            MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
            int i = mapper.updateMessage(message);
            sqlSession.commit();
            return i;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
        return 0;
    }
}
