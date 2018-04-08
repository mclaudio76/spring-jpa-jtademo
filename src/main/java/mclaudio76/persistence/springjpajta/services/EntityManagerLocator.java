package mclaudio76.persistence.springjpajta.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

/****
 * Acts as service locator.
 * This class injects all of available PersistenceUnits, and returns a selected one via getEntityManager().
 * 
 *
 */

@Component
public class EntityManagerLocator {
	
	private EntityManager firstEntityManager   = null;
	private EntityManager secondEntityManager = null;
	
	@PersistenceContext(unitName="firstEntityManager")
    public void setPrimaryEntityManager(EntityManager entityManager) {
        this.firstEntityManager = entityManager;
    }
	
	@PersistenceContext(unitName="secondEntityManager")
    public void setSecondaryEntityManager(EntityManager entityManager) {
        this.secondEntityManager = entityManager;
    }
	
	public EntityManager lookupEntityManager(String enviroment) {
		if(enviroment.trim().equals("primary")) {
			return firstEntityManager;
		}
		else {
			return secondEntityManager;
		}
	}
}
