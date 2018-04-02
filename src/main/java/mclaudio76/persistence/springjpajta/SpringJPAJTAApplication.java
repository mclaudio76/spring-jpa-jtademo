package mclaudio76.persistence.springjpajta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mclaudio76.persistence.springjpajta.entities.Person;
import mclaudio76.persistence.springjpajta.services.PersonService;

/****
 * We need to exclude DataSoruceAutoConfiguration.class and similar to avoid nasty errors due to Spring trying to instantiate
 * default datasources and other default configs.
 * 
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,XADataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableTransactionManagement
public class SpringJPAJTAApplication {
	
	@Autowired
	PersonService service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJPAJTAApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
        	System.err.println("Init....");
        	Person p = new Person();
        	p.id     = 39;
        	p.firstName = "John";
        	p.lastName  = "Doe"; 
        	service.savePerson(p);
        	System.err.println("Saved");
        	service.removePerson(p);
        	service.doBoth(p);
        	service.doBoth(p);
        	service.doBoth(p);
        };
    }
}
