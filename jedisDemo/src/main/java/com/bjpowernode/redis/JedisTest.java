package com.bjpowernode.redis;

import com.bjpowernode.util.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

public class JedisTest {

    public static void main(String[] args) {
       /* // 创建Jedis对象
        Jedis jedis = new Jedis("192.168.169.128",6379);
        // 使用jedis对象操作Redis
        String result = jedis.set("str5", "hello");
        System.out.println(result);

        // 关闭Jedis对象
        jedis.close();*/

        // 使用连接池来操作Redis
        // 调用RedisUtils的open方法，返回一个Jedis连接池对象
        /*JedisPool open = RedisUtils.open("192.168.169.128", 6379);
        // 从连接池中获取一个Jedis对象
        Jedis jedis = open.getResource();
        String str1 = jedis.get("str1");
        System.out.println(str1);
        Long hset = jedis.hset("stu", "name", "zhangsan");
        System.out.println(hset);
        // 关闭连接池
        RedisUtils.close();*/

        // 事务的操作
        JedisPool open = RedisUtils.open("192.168.169.128", 6379);
        Jedis jedis = open.getResource();
        Transaction multi = jedis.multi();
        multi.flushDB();
        multi.set("str1","hello");
        multi.set("str2","java");
        List<Object> exec = multi.exec();
        for (Object o : exec) {
            System.out.println(o);
        }

        // 关闭连接池
        RedisUtils.close();
    }
}
