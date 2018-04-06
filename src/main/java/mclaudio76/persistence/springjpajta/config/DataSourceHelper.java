package mclaudio76.persistence.springjpajta.config;

import javax.sql.DataSource;

import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

public class DataSourceHelper {

	public static DataSource datasource(String url, String user, String pwd,String uniqueXAName) {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		//mysqlXaDataSource.setUrl("jdbc:mysql://localhost:3306/persistencejpa");
		mysqlXaDataSource.setUrl(url);
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(pwd);
		mysqlXaDataSource.setUser(user);
		//return mysqlXaDataSource;
		if(!uniqueXAName.isEmpty()) {
			AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
			xaDataSource.setXaDataSource(mysqlXaDataSource);
			xaDataSource.setUniqueResourceName(uniqueXAName);
			xaDataSource.setMaxPoolSize(100);
			xaDataSource.setMinPoolSize(1); 
			return xaDataSource;  
		}
		return mysqlXaDataSource;
	}
}
