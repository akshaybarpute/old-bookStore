package com.freetests4u;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import javax.persistence.EntityManagerFactory;

@Configuration
public class BeanConfig {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}

	@Bean
	public Module datatypeHibernateModule() {
	  return new Hibernate5Module();
	}
	
	
    @Bean(name="transactionManager")
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory());
        return transactionManager;
    }
    	
//	@Bean
//	public DataSource dataSource() {
//	    return new MysqlDataSource(); // (1)
//	}
//
//	@Bean
//	public PlatformTransactionManager txManager() {
//	    return new HibernateTransactionManager(); // (2)
//	}
	
}
