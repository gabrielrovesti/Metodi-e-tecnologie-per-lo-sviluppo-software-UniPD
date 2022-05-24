////////////////////////////////////////////////////////////////////
// [Gabriel] [Rovesti] [2009088]
// [Luca] [Romio] [2014028]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;


public class UserTest {
	public User user1, user2, usr;
	@BeforeEach
	public void initialize() {
		user1 = new User("2014028", "Luca", "Romio", LocalDate.of(1999, 1, 1));
		user2 = new User("2009088", "Gabriel", "Rovesti", LocalDate.of(2005, 11, 2));
	}

	//TEST COSTRUTTORE
	//Test su argomenti nulli
	@Test
	public void testOnCreationSetNullId() {
	 	try {
	 		usr = new User(null, "Luca", "Romio", LocalDate.of(1999, 1, 1));
	 	}
	 	catch(NullPointerException e) {
	 		Assertions.assertEquals("Invalid id reference (null)", e.getMessage());
	 	}
 	}
    @Test
    public void testOnCreationSetNullName() {
	 	try {
	 		usr = new User("2014028", null, "Romio", LocalDate.of(1999, 1, 1));
	 	}
	 	catch(NullPointerException e) {
	 		Assertions.assertEquals("Invalid name reference (null)", e.getMessage());
	 	}
 	}
    @Test
    public void testOnCreationSetNullSurname() {
	 	try {
	 		usr = new User("2014028", "Luca", null, LocalDate.of(1999, 1, 1));
	 	}
	 	catch(NullPointerException e) {
			Assertions.assertEquals("Invalid surname reference (null)", e.getMessage());
	 	}
 	}
    @Test
    public void testOnCreationSetNullDate() {
	 	try {
 			usr = new User("2014028", "Luca", "Romio", null);
 		}
 		catch(NullPointerException e) {
			Assertions.assertEquals("Invalid birthDate reference (null)", e.getMessage());
	 	}
 	}
	
	//Test su argomenti non nei parametri specificati
	@Test
	public void testOnCreationSetInvalidid(){
		try{
			usr = new User("", "Luca", "Romio", LocalDate.of(1999, 1, 1));
		}
		catch(IllegalArgumentException e){
			Assertions.assertEquals("Invalid id",e.getMessage());
		}
	}
	@Test
	public void testOnCreationSetInvalidName(){
		Assertions.assertAll(
			()->{
				try{
					usr = new User("2014028", "A", "Romio", LocalDate.of(1999, 1, 1));
				}
				catch(IllegalArgumentException e){
					Assertions.assertEquals("Invalid name length", e.getMessage());
				}
			},
			()->{
				try{
					usr = new User("2014028", "ApparentlyYouCantPutAVeryHugeNameIntoThisField", "Romio", LocalDate.of(1999, 1, 1));
				}
				catch(IllegalArgumentException e){
					Assertions.assertEquals("Invalid name length", e.getMessage());
				}
			}
		);
	}
	@Test
	public void testOnCreationSetInvalidSurname(){
		Assertions.assertAll(
			()->{
				try{
					usr = new User("2014028", "Luca", "B", LocalDate.of(1999, 1, 1));
				}
				catch(IllegalArgumentException e){
					Assertions.assertEquals("Invalid surname length", e.getMessage());
				}
			},
			()->{
				try{
					usr = new User("2014028", "Luca", "ApparentlyYouCantPutAVeryHugeSurnameIntoThisField", LocalDate.of(1999, 1, 1));
				}
				catch(IllegalArgumentException e){
					Assertions.assertEquals("Invalid surname length", e.getMessage());
				}
			}
		);

	}


	//TEST METODI DI GET
	@Test
	public void testGetName() {
		//user2 = new User("2009088", "Gabriel", "Rovesti", LocalDate.of(2005, 11, 2));
	    Assertions.assertEquals("Gabriel", user2.getName());
	}
	@Test
	public void testGetSurname() {
		//user2 = new User("2009088", "Gabriel", "Rovesti", LocalDate.of(2005, 11, 2));
	    Assertions.assertEquals("Rovesti", user2.getSurname());
	}
	@Test
	public void testGetId() {
		//user2 = new User("2009088", "Gabriel", "Rovesti", LocalDate.of(2005, 11, 2));
	    Assertions.assertEquals("2009088", user2.getId());
	}
	@Test
	public void testGetAge() {
		//user2 = new User("2009088", "Gabriel", "Rovesti", LocalDate.of(2005, 11, 2));
	    Assertions.assertEquals(LocalDate.now().getYear() - LocalDate.of(2005, 11, 2).getYear(), user2.getAge());
	}


	//TEST METODI BOOLEANI
	@Test
    public void testisUnderage() {
		//user1 = new User("2014028", "Luca", "Romio", LocalDate.of(1999, 1, 1));
		//user2 = new User("2009088", "Gabriel", "Rovesti", LocalDate.of(2005, 11, 2));
		Assertions.assertAll(
			()->Assertions.assertEquals(false, user1.isUnderage()),
			()->Assertions.assertEquals(true, user2.isUnderage())
		);
    }
}
