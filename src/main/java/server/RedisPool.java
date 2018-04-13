package server;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RedisPool {
    private static String ADDR = "127.0.0.1";
    private static int PORT = 6379;
    private static int MAX_ACTIVE = 1024;
    private static int MAX_IDLE = 200;
    private static int MAX_WAIT = 10000;
    private static int TIMEOUT = 10000;
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;
    public static JedisPool getJedisPool(){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jedisPool;
    }
    public synchronized static Jedis getJedis(){
        try {
            if(jedisPool != null){
                Jedis resource = jedisPool.getResource();
                return resource;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static Long hset(String key, String filed, String values){
        Jedis jedis = null ;
        try {
            jedis = RedisPool.getJedis();
            Long result = jedis.hset(key, filed, values);
            return  result;
        }catch (Exception e){
            if(jedis != null)
                jedis.close();
        }finally {
            if (jedis != null)
                jedis.close();
        }
        return null;
    }
    public static String hget(String key, String filed){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            String result = jedis.hget(key, filed);
            return result;
        }catch (Exception e){
            if (jedis != null)
                jedis.close();
        }finally {
            if(jedis != null)
                jedis.close();
        }
        return null;
    }

    public static String hmset(String key, Map<String, String> map){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            String result = jedis.hmset(key, map);
            return result;
        }catch (Exception e){
            if (jedis != null)
                jedis.close();
            e.printStackTrace();
        }finally {
            if(jedis != null)
                jedis.close();
        }
        return null;
    }
    public static List<String> hmget(String key, String id, String address, String message){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            List<String> result = jedis.hmget(key, id, address, message);
            return result;
        }catch (Exception e){
            if(jedis != null)
                jedis.close();
            e.printStackTrace();
        }finally {
            if(jedis != null)
                jedis.close();
        }
        return null;
    }

//    public static List<String> hmget(String key, String... keys) {
//        List<String> result = null;
//        JedisPool pool;
//        Jedis jedis = null;
//        try {
//            pool = getPool();
//            jedis = pool.getResource();
//            result = jedis.hmget(key, keys);
//        } catch (Exception e) {
//            //释放redis对象
//            if (null != jedis) {
//                jedis.close();
//            }
//            LOG.info("failed to connect redis", e);
//        } finally {
//            if (null != jedis) {
//                jedis.close();
//            }
//        }
//        return result;
//    }

}
