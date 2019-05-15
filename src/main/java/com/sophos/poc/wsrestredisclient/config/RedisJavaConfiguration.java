package com.sophos.poc.wsrestredisclient.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@Configuration
@ComponentScan("com.sophos.poc.wsrestredisclient.config")
@EnableRedisRepositories(basePackages = "com.sophos.poc.wsrestredisclient.repositories")
@PropertySource("classpath:application.properties")
public class RedisJavaConfiguration {

	private static final Logger logger = LogManager.getLogger(RedisJavaConfiguration.class);
	
	@Value("${redis.host}")
	private String redisHost;
	@Value("${redis.port}")
	private String redisPort;
	@Value("${redis.pass}")
	private String redisPass;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, Integer.parseInt(redisPort));
	    redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPass));
	    return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	 
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    logger.info("Redis connection factory: " + redisHost + ":" +redisPort);
	    return template;
	}

}
