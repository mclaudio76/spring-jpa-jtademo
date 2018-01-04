package mclaudio76.persistence.springjpajta.services;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import com.atomikos.icatch.jta.UserTransactionManager;

public class JTAPlatform extends AbstractJtaPlatform{

	private static final long serialVersionUID = 1L;

	@Override
	protected TransactionManager locateTransactionManager() {
		return new UserTransactionManager();
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return new UserTransactionManager();
	}

}
