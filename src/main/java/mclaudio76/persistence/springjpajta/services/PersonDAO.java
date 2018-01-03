package mclaudio76.persistence.springjpajta.services;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mclaudio76.persistence.springjpajta.entities.Person;


@Service
@Transactional
public class PersonDAO {
	
	@Autowired
	EntityManagerLocator locator;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void savePerson(String which, Person p) {
		EntityManager em = locator.getEntityManager(which);
		em.persist(p);
		em.flush();
	}
	
	
}
