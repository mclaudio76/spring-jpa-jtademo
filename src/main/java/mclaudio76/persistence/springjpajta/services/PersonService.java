package mclaudio76.persistence.springjpajta.services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mclaudio76.persistence.springjpajta.entities.Person;

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

	
	public void savePerson(Person p) {
		EntityManager em = locator.getEntityManager("primary");
		dao.savePerson(em, p);
	}


	public void removePerson(Person p) {
		EntityManager em = locator.getEntityManager("primary");
		dao.deletePerson(em, p);
	}

	
	public void doBoth(Person p) throws Exception{
		EntityManager em = locator.getEntityManager("primary");
		dao.savePerson(em, p);
		EntityManager em2 = locator.getEntityManager("secondary");
		dao.savePerson(em2, p);
	}

}

