package com.jnit.app.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
//@ComponentScan(basePackages={"com.jnit.app.model","com.jnit.app.model.existing"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.jnit.app.repositories"})
//@PropertySource("classpath:app.properies")
@PropertySources({@PropertySource("connection.properties")})
public class JpaConfiguration {

	@Autowired
	private Environment env;
	
	@Bean
	public Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("hibernate.dialect", MySQL5InnoDBDialect.class.getName());
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.show_sql", true);
		return props;
	}

//	@Bean
//	public JpaVendorAdapter jpaVendorAdapter() {
//		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//		hibernateJpaVendorAdapter.setShowSql(false);
//		//hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
//		return hibernateJpaVendorAdapter;
//	}


	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource());
		lef.setJpaPropertyMap(jpaProperties());
		//lef.setJpaVendorAdapter(this.jpaVendorAdapter());
		lef.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		lef.setPackagesToScan("com.jnit.app.model");
		lef.afterPropertiesSet();
		return lef.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager( entityManagerFactory() );
	}

	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		try {
			dataSource.setDriverClass(env.getRequiredProperty("database.driverclass"));
			dataSource.setJdbcUrl(env.getRequiredProperty("database.url"));
			dataSource.setUser(env.getRequiredProperty("database.username"));
			dataSource.setPassword(env.getRequiredProperty("database.password"));
			dataSource.setMaxPoolSize(15);
			dataSource.setMinPoolSize(5);
		} catch (IllegalStateException | PropertyVetoException e) {
			throw new RuntimeException("An error occured when creating datasource", e);
		}

		return dataSource;
	}
	
}
