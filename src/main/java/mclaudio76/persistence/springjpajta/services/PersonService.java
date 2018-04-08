package mclaudio76.persistence.springjpajta.services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mclaudio76.persistence.springjpajta.entities.Person;

/**
 * Trivial service persisting via a DAO bean an entity on two different datasources.
 *  
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class PersonService {

	private PersonDAO dao;
	private EntityManagerLocator locator;
	
	@Autowired
	public PersonService(PersonDAO dao, EntityManagerLocator locator) {
		this.dao = dao;
		this.locator = locator;
	}
	
	@Transactional
	public void testTransaction(Person p) throws Exception{
		EntityManager em  = locator.lookupEntityManager("primary");
		EntityManager em2 = locator.lookupEntityManager("secondary");
		// First: clean up
		dao.deletePerson(em, p);
		dao.deletePerson(em2, p);
		// Second: save.
		dao.savePerson(em, p);
		dao.savePerson(em2, p);
	}

}

