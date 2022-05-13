package com.home.spring.restapi.configuration;

import java.util.Properties;
import java.util.logging.Logger;

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
@PropertySource("classpath:persistence.properties")
public class HibernateConfiguration {
	
	@Autowired
	Environment env;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	private Properties getHibernateProperty() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.setProperty("javax.persistence.validation.mode", "NONE");
		return props;
	}
	
	@Bean
	public DataSource dataSource() throws Exception{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
		logger.info("Data Source Configuration COmpleted");
		
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws Exception{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setHibernateProperties(getHibernateProperty());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		logger.info("Session Factory Configuration completed");
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(sessionFactory);
		
		return tx;
	}

}
