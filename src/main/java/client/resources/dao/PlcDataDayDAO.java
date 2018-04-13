package client.resources.dao;

import dao.SynchronizePlcDataAlarmMapper;
import entities.PlcDataAlarm;
import entities.PlcDataDay;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

public class PlcDataDayDAO {
    private SqlSessionFactory sqlSessionFactory;
    public void before() throws Exception{
        String resource = "client/resources/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public List<PlcDataDay> getAllPlcDataDays(){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession();
            PlcDataDayMapper mapper = sqlSession.getMapper(PlcDataDayMapper.class);
            List<PlcDataDay> allPlcDataDay = mapper.getAllPlcDataDays();
            return allPlcDataDay;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
        return null;
    }

    public List<PlcDataDay> getSendPlcDataDay(Timestamp startTime, Timestamp endTime){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession();
            PlcDataDayMapper mapper = sqlSession.getMapper(PlcDataDayMapper.class);
            List<PlcDataDay> Messages = mapper.getSendPlcDataDay(startTime, endTime);
            return Messages;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
        return null;
    }

    public int batchUpdatePlcDataDayIsSend(List<PlcDataDay> hasSendPlcDataDays){
        SqlSession sqlSession = null;
        try {
            this.before();
            sqlSession = sqlSessionFactory.openSession();
            PlcDataDayMapper mapper = sqlSession.getMapper(PlcDataDayMapper.class);
            int i = mapper.batchUpdatePlcDataDayIsSend(hasSendPlcDataDays);
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
