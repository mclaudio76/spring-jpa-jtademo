package mclaudio76.persistence.springjpajta.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;

import mclaudio76.persistence.springjpajta.services.JTAPlatform;

@Configuration
public class JTAConfiguration {
	
	private JTAPlatform platform;
	
	@Autowired
	public void setJTAPlatform(JTAPlatform platform) {
		this.platform = platform;
	}
	
	@Bean(initMethod = "init", destroyMethod = "shutdownForce")
	public UserTransactionService userTransactionService(){
		Properties atProps = new Properties();
		atProps.put("com.atomikos.icatch.service", "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
		atProps.put("com.atomikos.icatch.log_base_dir", "D:\\Works\\TransactionLog");
		atProps.put("com.atomikos.icatch.default_jta_timeout", "12345");
		return new UserTransactionServiceImp(atProps);
	}
	
	@Bean("CustomTransactionManager")
	public PlatformTransactionManager getTransactionManager() {
		JtaTransactionManager txManager =  new JtaTransactionManager(platform.retrieveUserTransaction(), platform.retrieveTransactionManager());
		txManager.afterPropertiesSet();
		return txManager;
	}
	
}
