package com.prowings.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.prowings")
@PropertySource("classpath:dp.properties")
public class SpringWebMVC {

	@Autowired
	private Environment environment;
	
	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(environment.getRequiredProperty("database.driver"));
	        dataSource.setUrl(environment.getRequiredProperty("database.url"));
	        dataSource.setUsername(environment.getRequiredProperty("database.user"));
	        dataSource.setPassword(environment.getRequiredProperty("database.password"));
	        return dataSource;
	    }

	    @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan(new String[] {
	            "com.prowings.model"
	        });
	        sessionFactory.setHibernateProperties(hibernateProperties());
	        return sessionFactory;
	    }

	    private Properties hibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
	        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	        return properties;
	    }
	    
//	    @Bean  // needed for JSON conversion of bean responses
//	    public View jsonTemplate() {
//	        MappingJackson2JsonView view = new MappingJackson2JsonView();
//	        Properties props = new Properties();
//	        props.put("order", 1);
//	        view.setAttributes(props);
//	        view.setPrettyPrint(true);
//	        return view;
//	    }
//	
//	    public ViewResolver viewResolver() {
//	    	return new BeanNameViewResolver();
//	    }
}
