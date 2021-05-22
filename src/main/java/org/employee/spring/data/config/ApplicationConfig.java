package org.employee.spring.data.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.employee.spring.data.dao.DepartmentServiceImpl;
import org.employee.spring.data.dao.DependentServiceImpl;
import org.employee.spring.data.dao.EmployeeServiceImpl;
import org.employee.spring.data.dao.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence-h2.properties")
@EnableJpaRepositories(basePackages = { "org.employee.spring.data.*" })
public class ApplicationConfig {

	@Autowired
	private Environment env;

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("org.employee.spring.data.*");
		factoryBean.setDataSource(dataSource());
		factoryBean.setJpaProperties(additionalProperties());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.afterPropertiesSet();
		return factoryBean.getNativeEntityManagerFactory();
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
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");

		return hibernateProperties;
	}

	@Bean
	public CacheManager cacheManager() {

		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
		return cacheManager;
	}

	@Bean
	public EmployeeServiceImpl createEmployeeService() throws IOException {

		return new EmployeeServiceImpl();
	}

	@Bean
	public DepartmentServiceImpl createDepartmentService() throws IOException {

		return new DepartmentServiceImpl();
	}

	@Bean
	public ProjectServiceImpl createProjectService() throws IOException {
		return new ProjectServiceImpl();

	}

	@Bean
	public DependentServiceImpl createDependent() throws IOException {

		return new DependentServiceImpl();
	}

}
