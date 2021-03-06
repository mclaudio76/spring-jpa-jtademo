package mclaudio76.persistence.springjpajta.config;

import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.boot.jta.narayana.NarayanaDataSourceBean;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/*****
 * Utility class to return a JTA-aware datasource for each of any supported JTA transaction manager in Spring Boot.
 * 
 *
 */


public class DataSourceHelper {

	private final static int MAX_POOL = 100;
	private final static int MIN_POOL = 1;
	private enum JTAImplementation  {NARAYANA,ATOMIKOS,BITRONIX};
	
	private static JTAImplementation jtaPlatform = JTAImplementation.NARAYANA;
	
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
		// Not all of provided JTA Transaction Manager offer a wrapper able to support a MAX, MIN Pool.
		return wrapXADataSource(mysqlXaDataSource,dataSourceID);
	}
	
	
	private DataSource wrapXADataSource(XADataSource ds, String uniqueXAIdentifier) {
		switch(jtaPlatform) {
			case  NARAYANA : return narayanaWrapper(ds,uniqueXAIdentifier);
			case  ATOMIKOS : return atomikosWrapper(ds, uniqueXAIdentifier);
			case  BITRONIX : return bitronixWrapper(ds, uniqueXAIdentifier);
		}
		return (DataSource) ds;
	}
	
	
	
	// Wrapper for Narayana
	private DataSource narayanaWrapper(XADataSource ds, String uniqueXAName) {
		NarayanaDataSourceBean wrapper = new NarayanaDataSourceBean(ds);
		
		return wrapper;
	}
	
	//Wrapper for Atomikos
	private DataSource atomikosWrapper(XADataSource ds, String uniqueXAName) {
		/*AtomikosDataSourceBean wrapper = new AtomikosDataSourceBean();
		wrapper.setUniqueResourceName(uniqueXAName);
		wrapper.setMaxPoolSize(MAX_POOL);
		wrapper.setMinPoolSize(MIN_POOL);
		wrapper.setXaDataSource(ds); 
		return wrapper */
		return (DataSource) ds;
	}  
	

	// Wrapper for Bitronix: include spring-boot-starter-jta-bitronix in pom
	private DataSource bitronixWrapper(XADataSource ds, String uniqueXAName) {
		/*PoolingDataSourceBean wrapper = new PoolingDataSourceBean();
		wrapper.setDataSource(ds);
		wrapper.setMaxPoolSize(MAX_POOL);
		wrapper.setMaxPoolSize(MIN_POOL);
		wrapper.setUniqueName(uniqueXAName);
		return wrapper; */
		return (DataSource) ds;
	} 


}
