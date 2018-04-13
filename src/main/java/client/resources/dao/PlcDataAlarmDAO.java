package client.resources.dao;

import dao.SynchronizePlcDataAlarmMapper;
import entities.PlcDataAlarm;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

public class PlcDataAlarmDAO {
    private SqlSessionFactory sqlSessionFactory;
    public void before() throws Exception{
        String resource = "client/resources/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public List<PlcDataAlarm> getAllPlcDataAlarms(){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession();
            PlcDataAlarmMapper mapper = sqlSession.getMapper(PlcDataAlarmMapper.class);
            List<PlcDataAlarm> allPlcDataAlarm = mapper.getAllPlcDataAlarms();
            return allPlcDataAlarm;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
        return null;
    }

    public List<PlcDataAlarm> getFirstPlcDataAlarms(){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession();
            PlcDataAlarmMapper mapper = sqlSession.getMapper(PlcDataAlarmMapper.class);
            List<PlcDataAlarm> allPlcDataAlarm = mapper.getFirstPlcDataAlarms();
            return allPlcDataAlarm;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
        return null;
    }

    public List<PlcDataAlarm> getSendPlcDataAlarm(Timestamp startTime, Timestamp endTime){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession();
            PlcDataAlarmMapper mapper = sqlSession.getMapper(PlcDataAlarmMapper.class);
            List<PlcDataAlarm> Messages = mapper.getSendPlcDataAlarm(startTime, endTime);
            return Messages;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
        return null;
    }

    public int batchUpdatePlcDataAlarmIsSend(List<PlcDataAlarm> hasSendPlcDataAlarms){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession();
            PlcDataAlarmMapper mapper = sqlSession.getMapper(PlcDataAlarmMapper.class);
            int i = mapper.batchUpdatePlcDataAlarmIsSend(hasSendPlcDataAlarms);
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
