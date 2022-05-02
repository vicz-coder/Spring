package com.home.spring.config;

import java.util.Properties;
import java.util.logging.Logger;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement

@PropertySource({"classpath:persistence.properties"})
public class HibernateConfig {

	@Autowired
	Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private Properties getHibernateProps() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.setProperty("javax.persistence.validation.mode", "NONE");
		
		return props;
	}
	
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		logger.info("Configuration of JDBC "+env.getProperty("jdbc.url"));
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		
		logger.info("Configuration of Connection Pool Size");
		
		//Pool Configuration
		dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
		
		return dataSource;
	}
	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		logger.info("Setting Session Bean");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setHibernateProperties(getHibernateProps());;
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		return sessionFactory;
	}
	
	
	  @Bean 
	  @Autowired
	  public HibernateTransactionManager transactionManager(SessionFactory
	  sessionFactory) { HibernateTransactionManager tx = new
	  HibernateTransactionManager(); tx.setSessionFactory(sessionFactory); return
	  tx; }
	 
	
}
