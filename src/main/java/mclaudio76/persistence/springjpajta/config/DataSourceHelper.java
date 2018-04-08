package mclaudio76.persistence.springjpajta.config;

import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/*****
 * Utility class to return a JTA-aware datasource for each of any supported JTA transaction manager in Spring Boot.
 * 
 *
 */


public class DataSourceHelper {

	private static int MAX_POOL = 100;
	private static int MIN_POOL = 1;
	
	public DataSourceHelper(Properties config) {
		// TO DO....
	}
	
	public DataSource datasource(String dataSourceID, String url, String user, String pwd) {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(url);
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(pwd);
		mysqlXaDataSource.setUser(user);
		// Most important: to actually use an XA transaction, we need to "wrap" underlying datasource
		// with a bean handled by JTA implementation. Otherwise, a LCT transaction will be performed.
		return wrapWithJTABean(mysqlXaDataSource,dataSourceID);
	}
	
	// Wrapper for Narayana
	/*private DataSource wrapWithJTABean(XADataSource ds, String uniqueXAName) {
		NarayanaDataSourceBean wrapper = new NarayanaDataSourceBean(ds);
		return wrapper;
	}*/
	
	//Wrapper for Atomikos
	private DataSource wrapWithJTABean(XADataSource ds, String uniqueXAName) {
		AtomikosDataSourceBean wrapper = new AtomikosDataSourceBean();
		wrapper.setUniqueResourceName(uniqueXAName);
		wrapper.setMaxPoolSize(MAX_POOL);
		wrapper.setMinPoolSize(MIN_POOL);
		wrapper.setXaDataSource(ds);
		return wrapper;
	}  
	

	// Wrapper for Bitronix: include spring-boot-starter-jta-bitronix in pom
	/*private DataSource wrapWithJTABean(XADataSource ds, String uniqueXAName) {
		PoolingDataSourceBean wrapper = new PoolingDataSourceBean();
		wrapper.setUniqueName(uniqueXAName);
		wrapper.setMaxPoolSize(MAX_POOL);
		wrapper.setMinPoolSize(MIN_POOL);
		wrapper.setDataSource(ds);
		return wrapper;
	} */ 


}
