package mclaudio76.persistence.springjpajta.services;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.atomikos.icatch.jta.UserTransactionImp;

/****
 * Provides a JTAPlatform wrapper using Atomikos JTA.
 * One may use other JTA open providers.
 *
 */

@Component
public class JTAPlatform extends AbstractJtaPlatform {

	private static final long serialVersionUID = 1L;
	
	private TransactionManager txManager;
	private UserTransaction    userTx;
	
	@Autowired
	public void setJTAPlatformTXManager(@Qualifier("JTAPlatformTransactionManager") TransactionManager txManager) {
		this.txManager = txManager;
	}
	
	@Autowired
	public void setJTAPlatformUserTransaction(@Qualifier("JTAPlatformUserTransaction") UserTransaction userTx) {
		this.userTx = userTx;
	}
	

	@Override
	protected TransactionManager locateTransactionManager() {
		TransactionManager ret = txManager == null ? new com.atomikos.icatch.jta.UserTransactionManager() : txManager;
		return ret;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		UserTransaction uxt = userTx == null ? new UserTransactionImp() : userTx;
		return uxt;
	}
	
	
}
