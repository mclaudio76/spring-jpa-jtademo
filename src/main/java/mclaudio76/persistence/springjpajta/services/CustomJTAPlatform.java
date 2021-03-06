package mclaudio76.persistence.springjpajta.services;

import java.util.logging.Logger;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/****
 * Provides a JTAPlatform concrete implementation to be set as hibernate.transaction.jta.platform property 
 * while configure EntityManagers.
 *
 */

@Component("CustomJTAPlatform")
public class CustomJTAPlatform extends AbstractJtaPlatform {

	private static final long serialVersionUID = 1L;
	
	private static TransactionManager txManager;
	private static UserTransaction    userTx;
	
	@Autowired
	public void setJTAPlatformTXManager(TransactionManager txManager) {
		this.txManager = txManager;
		Logger.getLogger(this.getClass().getCanonicalName()).info("Inject TxManager "+txManager);
	}
	
	@Autowired
	public void setJTAPlatformUserTransaction(UserTransaction userTx) {
		this.userTx = userTx;
	}
	

	@Override
	protected TransactionManager locateTransactionManager() {
		return txManager;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return userTx;
	}
	
	
}
