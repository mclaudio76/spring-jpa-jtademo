package mclaudio76.persistence.springjpajta.services;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mclaudio76.persistence.springjpajta.entities.Person;

/*****
 * Main class. Injects an EntityManagerLocator instance to lookup required EntityManager.
 * Methods are annotated with @Transactional;  the business logic itself it's quite absurd,
 * an Entity with even ID isn't persisted. The goal is let the JTA manager to rollback when
 * an exception is raised.
 * 
 */

@Service
@Transactional
public class PersonDAO {
	
	@Autowired
	EntityManagerLocator locator;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void savePerson(String which, Person p) {
		EntityManager em = locator.getEntityManager(which);
		em.persist(p);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void savePerson(Person p) throws Exception {
		EntityManager em = locator.getEntityManager("secondary");
		em.persist(p);
		em = locator.getEntityManager("primary");
		em.persist(p);
		if(p.id % 2 == 0) {
			throw new Exception("Person IDs must be odd values.");
		}
	}
	
}
