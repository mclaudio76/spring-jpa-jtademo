package mclaudio76.persistence.springjpajta.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service
public class EntityManagerLocator {
	
	private EntityManager primaryJPA   = null;
	private EntityManager secondaryJPA = null;
	
	@PersistenceContext(unitName="mysql-primaryjpa")
    public void setPrimaryEntityManager(EntityManager entityManager) {
        this.primaryJPA = entityManager;
    }
	
	@PersistenceContext(unitName="mysql-secondaryjpa")
    public void setSecondaryEntityManager(EntityManager entityManager) {
        this.secondaryJPA = entityManager;
    }
	
	public EntityManager getEntityManager(String enviroment) {
		if(enviroment.trim().equals("primary")) {
			return primaryJPA;
		}
		else {
			return secondaryJPA;
		}
	}
}
