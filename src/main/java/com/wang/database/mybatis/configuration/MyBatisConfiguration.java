package com.wang.database.mybatis.configuration;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@EnableConfigurationProperties({ PropertySourcesPlaceholderConfigurer.class })
@PropertySource(value = "classpath:config/mybatis.properties")
@ImportResource("classpath:config/applicationContext-mybatis.xml")
public class MyBatisConfiguration {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired @Qualifier("MyBatisDataSource") private DataSource dataSource;

	@Value("${mybatis.mapper.locations}") String mapperLocations;
	@Value("${mybatis.config.location:classpath:com/wang/database/mybatis/configuration/Configuration.xml}") String configLocation;

	@Bean(name = "myBatisSqlSessionFactory")
	protected SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources(mapperLocations);
		sqlSessionFactoryBean.setMapperLocations(resources);
		sqlSessionFactoryBean.afterPropertiesSet();
		return sqlSessionFactoryBean;
	}

}
