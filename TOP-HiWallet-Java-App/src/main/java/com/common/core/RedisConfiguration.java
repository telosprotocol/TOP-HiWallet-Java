//package com.common.core;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisPoolingClientConfigurationBuilder;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * Redis集群配置
// * @author start
// *
// */
//@Configuration
//public class RedisConfiguration {
//	
//
//    
//	/**
//	 * 引入自定义配置
//	 * @return
//	 */
//	@Bean
//	public JedisPoolConfig jedisPoolConfig() {
//        JedisPoolConfig config=new JedisPoolConfig();
//        //最大连接数
//        config.setMaxTotal(3000);
//        //最小空闲连接数
//        config.setMaxIdle(2000);
//        //当池内没有可用连接时，最大等待时间
//        config.setMaxWaitMillis(30000);
//        config.setTestOnBorrow(false);
//        config.setTestOnReturn(false);
//        return config;
//	}
//
//	@Value("${redis.cluster.nodes}")
//	private List<String> nodes;
//	@Value("${redis.cluster.maxRedirects:3}")
//	private int maxRedirects;
//	@Bean
//	@Autowired
//	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig poolConfig) {
//		RedisClusterConfiguration configuration = new RedisClusterConfiguration(nodes);
//        configuration.setMaxRedirects(maxRedirects);
//		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf =(JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
//		jpcf.poolConfig(poolConfig);
//		JedisClientConfiguration jedisClientConfiguration = jpcf.build();
//		return new JedisConnectionFactory(configuration, jedisClientConfiguration);
//	}
//
////	@Value("${redis.hostName}")
////	private String rHostName;
////	@Value("${redis.password}")
////	private String rPassword;
////	@Value("${redis.port}")
////	private int rPort;
////	@Value("${redis.database:0}")
////	private int database;
////
////	@Bean
////	@Autowired
////	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig poolConfig) {
////		RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();
////		configuration.setHostName(rHostName);
////		configuration.setPort(rPort);
////		configuration.setPassword(rPassword);
////		configuration.setDatabase(database);
////		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf =(JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
////		jpcf.poolConfig(poolConfig);
////		JedisClientConfiguration jedisClientConfiguration = jpcf.build();
////		return new JedisConnectionFactory(configuration, jedisClientConfiguration);
////	}
//
//
//
//}
