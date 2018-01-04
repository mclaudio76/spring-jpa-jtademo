package mclaudio76.persistence.springjpajta.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/*** Trivial Entity Class. 
 * 
 */

@Entity
public class Person {
	
	@Id
	public Integer id;
	
	@Column(length=50)
	public String firstName;
	
	@Column(length=50)
	public String lastName;
	
	@Column(length=100)
	public String address;
	
	
}
