package com.dxf.myjediscluster;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterDemo {
	private static JedisCluster jc;
	static {
		Set<HostAndPort> andPorts = new HashSet<HostAndPort>();
		andPorts.add(new HostAndPort("127.0.0.1", 7001));
		andPorts.add(new HostAndPort("127.0.0.1", 7002));
		andPorts.add(new HostAndPort("127.0.0.1", 7003));
		andPorts.add(new HostAndPort("127.0.0.1", 7004));
		andPorts.add(new HostAndPort("127.0.0.1", 7005));
		andPorts.add(new HostAndPort("127.0.0.1", 7006));
		 // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        jc = new JedisCluster(andPorts, jedisPoolConfig);
        jc.set("wukonglai", "www.wukonglai.com");
        System.out.println(jc.get("wukonglai"));
	}
	public static void main(String[] args) {
		System.out.println("Jedis-Cluster测试！！！");
	}

}
