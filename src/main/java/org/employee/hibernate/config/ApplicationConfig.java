package org.employee.hibernate.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.employee.spring.hibernate.dao.CustomStatistics;
import org.employee.spring.hibernate.dao.DepartmentDaoImpl;
import org.employee.spring.hibernate.dao.DependentDaoImpl;
import org.employee.spring.hibernate.dao.EmployeeDaoImpl;
import org.employee.spring.hibernate.dao.ProjectDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence-h2.properties")
public class ApplicationConfig {

	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("org.employee.hibernate.models");
		sessionFactoryBean.setHibernateProperties(additionalProperties());
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean;
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() throws IOException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		hibernateProperties.put("hibernate.jmx.enabled", true);
		hibernateProperties.put("hibernate.generate_statistics", true);
		hibernateProperties.put("hibernate.session_factory_name", "sessionFactory");

		return hibernateProperties;
	}

	@Bean
	public CacheManager cacheManager() {

		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
		return cacheManager;
	}

	@Bean
	public DepartmentDaoImpl createDepartment() throws IOException {

		return new DepartmentDaoImpl();
	}

	@Bean
	public EmployeeDaoImpl createEmployee() throws IOException {

		return new EmployeeDaoImpl();
	}

	@Bean
	public ProjectDaoImpl createProject() throws IOException {
		return new ProjectDaoImpl();
	}

	@Bean
	public DependentDaoImpl createDependent() throws IOException {

		return new DependentDaoImpl();
	}
	@Bean
	public CustomStatistics createCustomStatistics() throws IOException {

		return new CustomStatistics();
	}

	@Bean
	MBeanExporter jmxExporter() throws IOException {
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<>();
		beans.put("bean:name=ProSpring5SingerApp", createEmployee());
		beans.put("bean:name=Prospring5SingerApp-hibernate", createCustomStatistics());
		exporter.setBeans(beans);
		return exporter;
	}
}
