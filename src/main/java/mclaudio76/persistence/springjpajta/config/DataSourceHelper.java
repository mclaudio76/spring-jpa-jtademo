package mclaudio76.persistence.springjpajta.config;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

public class DataSourceHelper {

	public static DataSource datasource(String url, String user, String pwd,String uniqueXAName) {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(url);
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(pwd);
		mysqlXaDataSource.setUser(user);
		return mysqlXaDataSource;
	}
}
