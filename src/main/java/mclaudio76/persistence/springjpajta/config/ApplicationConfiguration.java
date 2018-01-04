package mclaudio76.persistence.springjpajta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public PlatformTransactionManager getTransactionManager() {
		return new JtaTransactionManager(com.arjuna.ats.jta.TransactionManager.transactionManager()); 
	}
}
