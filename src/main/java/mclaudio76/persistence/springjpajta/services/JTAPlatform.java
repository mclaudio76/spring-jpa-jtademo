package mclaudio76.persistence.springjpajta.services;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.stereotype.Component;

import com.arjuna.ats.internal.jta.transaction.arjunacore.UserTransactionImple;

@Component
public class JTAPlatform extends AbstractJtaPlatform{

	private static final long serialVersionUID = 1L;

	@Override
	protected TransactionManager locateTransactionManager() {
		return com.arjuna.ats.jta.TransactionManager.transactionManager();
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return new UserTransactionImple();
	}

}
