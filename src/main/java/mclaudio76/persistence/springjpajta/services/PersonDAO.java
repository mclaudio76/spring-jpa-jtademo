package mclaudio76.persistence.springjpajta.services;


import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import mclaudio76.persistence.springjpajta.entities.Person;

/*****
 * Main class. Injects an EntityManagerLocator instance to lookup required EntityManager.
 * Methods are annotated with @Transactional;  the business logic itself it's quite absurd,
 * an Entity with even ID isn't persisted. The goal is let the JTA manager to rollback when
 * an exception is raised.
 * 
 */

@Component("PersonDAO")
public class PersonDAO {
	
	public void savePerson(EntityManager em, Person p) {
		em.persist(em.contains(p) ? p : em.merge(p));
	}

	public void deletePerson(EntityManager em, Person p) {
		em.remove(em.contains(p) ? em : em.merge(p));
	}
	
}
