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
 * - We need to tell Hibernate to use JTA as transaction manager, putting hibernate.transaction.coordinator_class = jta, because
 *   we are going to use multiple sql datasources.
 *   
 */

@Configuration
public class EntityManagersConfig {
	
   @Bean
   public DataSourceHelper dataSourceHelper() {
	   return new DataSourceHelper(new Properties());
   }
	
	
   @Bean(name="firstEntityManager")
   @DependsOn("CustomJTAPlatform")
   public EntityManagerFactory firstEntityManagerFactory(@Qualifier("hibernate-props") Properties properties, DataSourceHelper dsHelper) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dsHelper.datasource("XADS1","jdbc:mysql://localhost:3306/persistencejpa", "spring", "spring"));
        em.setPackagesToScan(new String[] { "mclaudio76.persistence.springjpajta" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName("firstEntityManager");
        em.setJpaProperties(properties);
        em.afterPropertiesSet();
        return em.getNativeEntityManagerFactory();
    }
	
	@Bean(name="secondEntityManager")
	@DependsOn("CustomJTAPlatform")
    public EntityManagerFactory secondEntityManagerFactory(@Qualifier("hibernate-props") Properties properties, DataSourceHelper dsHelper) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dsHelper.datasource("XADS2","jdbc:mysql://localhost:3306/secondpersistence", "spring", "spring"));
        em.setPackagesToScan(new String[] { "mclaudio76.persistence.springjpajta" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName("secondEntityManager");
        em.setJpaProperties(properties);
        em.afterPropertiesSet();
        return em.getNativeEntityManagerFactory();
    }
	
}
