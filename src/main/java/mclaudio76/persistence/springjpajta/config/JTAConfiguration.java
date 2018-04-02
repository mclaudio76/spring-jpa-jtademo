package mclaudio76.persistence.springjpajta.config;

import java.util.Properties;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
public class JTAConfiguration {
	
	
	@Bean(initMethod = "init", destroyMethod = "shutdownForce")
	public UserTransactionService userTransactionProperties(){
		Properties atProps = new Properties();
		atProps.put("com.atomikos.icatch.service", "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
		atProps.put("com.atomikos.icatch.log_base_dir", "D:\\Works\\TransactionLog");
		atProps.put("com.atomikos.icatch.default_jta_timeout", "12345");
		return new UserTransactionServiceImp(atProps);
	}
	
	@Bean("CustomTransactionManager")
	public PlatformTransactionManager getTransactionManager() {
		JtaTransactionManager txManager =  new JtaTransactionManager(userTransaction(), transactionManager());
		txManager.afterPropertiesSet();
		return txManager;
	}
	
	@Bean("JTAPlatformTransactionManager")
	public TransactionManager transactionManager() {
		UserTransactionManager txManager =  new com.atomikos.icatch.jta.UserTransactionManager();
		return txManager;
	}

	@Bean("JTAPlatformUserTransaction")
	protected UserTransaction userTransaction() {
		UserTransactionImp userTransaction = new UserTransactionImp();
		return userTransaction;
	}
	
	
}
