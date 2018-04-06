package mclaudio76.persistence.springjpajta.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class SecondaryDatasourceConfig {
	
	
	@Bean(name="mysql-secondaryjpa")
	@DependsOn("JTAPlatform")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(@Qualifier("hibernate-props") Properties properties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(datasource());
        em.setPackagesToScan(new String[] { "mclaudio76.persistence.springjpajta" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName("mysql-secondaryjpa");
        em.setJpaProperties(properties);
        em.afterPropertiesSet();
        return em;
    }
	
   
	private DataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/secondpersistence");
		dataSource.setUsername( "spring" );
		dataSource.setPassword( "spring" );
		return dataSource;
	} 
	
	
}
