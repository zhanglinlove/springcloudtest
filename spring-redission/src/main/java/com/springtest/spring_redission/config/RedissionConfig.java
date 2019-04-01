package com.springtest.spring_redission.config;

import javax.validation.constraints.Null;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedissionConfig {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private Integer port;
	@Value("${spring.redis.password}")
	private String password;
	@Value("${spring.redis.maxIdle}")
	private Integer maxIdle;
	@Value("${spring.redis.maxActive}")
	private Integer maxActive;
	@Value("${spring.redis.maxWait}")
	private Integer maxWait;
	@Value("${spring.redis.data}")
	private Integer index;
	
	@Bean
	public Redisson getRedisson() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(getPassword());
		return (Redisson) Redisson.create(config);
	}
	
	@Bean
	public RedisTemplate getInstance() {
		RedisTemplate template = new RedisTemplate<>();
		template.setConnectionFactory(getFactory());
		return template;
	}
	
	public RedisConnectionFactory getFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPassword(getPassword());
		factory.setPort(port);
		factory.setDatabase(index);
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(maxIdle);
		config.setMaxTotal(maxActive);
		config.setMaxWaitMillis(60000);
		factory.setPoolConfig(config);
		return factory;
	}
	
	public String getPassword() {
		if (StringUtils.isEmpty(password)) {
			return null;
		}
		return password;
	}
}
