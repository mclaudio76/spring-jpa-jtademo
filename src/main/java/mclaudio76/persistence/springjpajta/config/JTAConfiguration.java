package mclaudio76.persistence.springjpajta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import mclaudio76.persistence.springjpajta.services.JTAPlatform;

@Configuration
public class JTAConfiguration {
	
	@Bean("CustomTransactionManager")
	public PlatformTransactionManager getTransactionManager() {
		JTAPlatform jtaPlatform = new JTAPlatform();
		JtaTransactionManager txManager =  new JtaTransactionManager(jtaPlatform.retrieveUserTransaction(), jtaPlatform.retrieveTransactionManager());
		txManager.afterPropertiesSet();
		return txManager;
	}
	
}
