package mclaudio76.persistence.springjpajta.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mclaudio76.persistence.springjpajta.services.JTAPlatform;

@Configuration
public class HibernateProperties {

	@Bean("hibernate-props")
	public Properties getHibernateProperties() {
		 Properties properties = new Properties();
         properties.setProperty("hibernate.hbm2ddl.auto", "update");
         properties.setProperty("hibernate.connection.autocommit", "false");
         properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
         // MOST IMPORTANT ! We need to tell Hibernate to use JTA as transaction coordinator.
         properties.setProperty("hibernate.transaction.coordinator_class", "jta");
         properties.setProperty("hibernate.transaction.jta.platform", JTAPlatform.class.getCanonicalName());
         
         return properties;
	}
}

