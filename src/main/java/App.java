import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
public class App {
    public static void main(String[] args) {
//        Jedis jedis = new Jedis("localhost");
//        System.out.println("success");
//        System.out.println("Server is running: " + jedis.ping());
        ClassPathXmlApplicationContext ct =	new ClassPathXmlApplicationContext("spring.xml");
    }
}