import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZX on 2017/7/31 15:04.
 */
public class JedisTest {

    @Test
    public void test_jedis() {
        Jedis jedis = new Jedis("192.168.25.134");
        jedis.set("test_key1", "123");
        System.out.println(jedis.get("test_key1"));
        jedis.close();
    }

    @Test
    public void test_jedisPool() {
        JedisPool pool = new JedisPool("192.168.25.134");
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("test_key1"));
        jedis.close();
        pool.close();
    }

    /**
     * redis集群测试
     */
    @Test
    public void test_jedisCluster() {
        //192.168.25.135:7001 192.168.25.135:7002 192.168.25.135:7003 192.168.25.135:7004 192.168.25.135:7005 192.168.25.135:7006
        Set<HostAndPort> set = new HashSet<>();
        set.add(new HostAndPort("192.168.25.135", 7001));
        set.add(new HostAndPort("192.168.25.135", 7002));
        set.add(new HostAndPort("192.168.25.135", 7003));
        set.add(new HostAndPort("192.168.25.135", 7004));
        set.add(new HostAndPort("192.168.25.135", 7005));
        set.add(new HostAndPort("192.168.25.135", 7006));
        JedisCluster cluster = new JedisCluster(set);
        cluster.set("test", "123456");
        System.out.println(cluster.get("test"));
        cluster.close();
    }
}
