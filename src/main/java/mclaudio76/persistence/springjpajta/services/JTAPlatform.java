package mclaudio76.persistence.springjpajta.services;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.stereotype.Component;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

/****
 * Provides a JTAPlatform wrapper using Narayana JTA implementation by RedHat / JBoss.
 * One may use other JTA open providers, like Atomikos for example.
 *
 */

@Component
public class JTAPlatform extends AbstractJtaPlatform{

	private static final long serialVersionUID = 1L;

	@Override
	protected TransactionManager locateTransactionManager() {
		UserTransactionManager txManager =  new com.atomikos.icatch.jta.UserTransactionManager();
		return txManager;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		try {
			UserTransactionImp userTransaction = new UserTransactionImp();
			log("Current User transaction "+userTransaction.toString());
			
			return userTransaction;
		}
		catch(Exception exp) {
			log("Unable to locate an user transaction");
			return null;
		}
	}
	
	private void log(String mex) {
		System.err.println("[JTAPlatform] >> "+mex);
	}

}
