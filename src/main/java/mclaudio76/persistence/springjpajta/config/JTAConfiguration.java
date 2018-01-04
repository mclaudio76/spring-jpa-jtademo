package mclaudio76.persistence.springjpajta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import mclaudio76.persistence.springjpajta.services.JTAPlatform;

@Configuration
public class JTAConfiguration {
	@Autowired
	JTAPlatform jtaPlatform;
	
	@Bean
	public PlatformTransactionManager getTransactionManager() {
		return new JtaTransactionManager(jtaPlatform.retrieveUserTransaction(), jtaPlatform.retrieveTransactionManager());
	}
	
}
