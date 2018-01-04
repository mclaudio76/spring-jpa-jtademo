package mclaudio76.persistence.springjpajta.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/*****
 * Provides an EntityManager configuration for a MySQL datasource.
 * 
 * It's worth noting that :
 * 
 * - LocalContainerEntityManagerFactoryBean is annotatated with @Bean(name=identifier), so that we can later
 *       inject it with @PersistenceContext(unitName=identifier) (@see EntityManagerLocator)
 * 
 * - I did not used an XA Connection.
 *
 * - We need to tell Hibernate to use JTA as transaction manager, putting hibernate.transaction.coordinator_class = jta.
 */

@Configuration
public class PrimaryDatasourceConfig {
	
	
	@Bean(name="mysql-primaryjpa")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(datasource());
        em.setPackagesToScan(new String[] { "mclaudio76.persistence.springjpajta" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName("mysql-primaryjpa");
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.connection.autocommit", "false");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        // MOST IMPORTANT ! We need to tell Hibernate to use JTA as transaction coordinator.
        properties.setProperty("hibernate.transaction.coordinator_class", "jta");
        //properties.setProperty("hibernate.transaction.jta.platform", JTAPlatform.class.getCanonicalName());
        em.setJpaProperties(properties);
        em.afterPropertiesSet();
        return em;
    }
	
	private DataSource datasource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/persistencejpa");
		dataSource.setUsername( "spring" );
		dataSource.setPassword( "spring" );
		return dataSource;
	}
	
}
