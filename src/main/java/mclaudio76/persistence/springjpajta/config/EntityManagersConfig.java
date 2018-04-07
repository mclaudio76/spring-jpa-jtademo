package mclaudio76.persistence.springjpajta.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
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
public class EntityManagersConfig {
	
		
   @Bean(name="mysql-primaryjpa")
   @DependsOn("CustomJTAPlatform")
   public EntityManagerFactory firstEntityManagerFactory(@Qualifier("hibernate-props") Properties properties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(DataSourceHelper.datasource("jdbc:mysql://localhost:3306/persistencejpa", "spring", "spring", "XA1"));
        em.setPackagesToScan(new String[] { "mclaudio76.persistence.springjpajta" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName("mysql-primaryjpa");
        em.setJpaProperties(properties);
        em.afterPropertiesSet();
        return em.getNativeEntityManagerFactory();
    }
	
	@Bean(name="mysql-secondaryjpa")
	@DependsOn("CustomJTAPlatform")
    public EntityManagerFactory secondEntityManagerFactory(@Qualifier("hibernate-props") Properties properties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(DataSourceHelper.datasource("jdbc:mysql://localhost:3306/secondpersistence", "spring", "spring", "XA2"));
        em.setPackagesToScan(new String[] { "mclaudio76.persistence.springjpajta" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName("mysql-secondaryjpa");
        em.setJpaProperties(properties);
        em.afterPropertiesSet();
        return em.getNativeEntityManagerFactory();
    }
	
}
