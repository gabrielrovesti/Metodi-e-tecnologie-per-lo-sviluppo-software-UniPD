////////////////////////////////////////////////////////////////////
// [Gabriel] [Rovesti] [2009088]
// [Luca] [Romio] [2014028]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unipd.mtss.exception.BillException;

public class GiveawayTest{
	List<Order> templateList = new ArrayList<Order>(0), dummyList = new ArrayList<Order>(0), coolList = new ArrayList<Order>(0), shorter_coolList = new ArrayList<Order>(0);
	Giveaway giv;
	@BeforeEach
	public void initialize() throws BillException{
		List<EItem> NoExtra_List = List.of(												
			new EItem(EItem.itemType.Processor, "Beta", 1.0),					
			new EItem(EItem.itemType.Processor, "Gamma", 4.0),					
			new EItem(EItem.itemType.Mouse, "Orange", 2.0),						
			new EItem(EItem.itemType.Mouse, "Banana", 5.0),
			new EItem(EItem.itemType.Keyboard, "Albatross", 3.0),				
			new EItem(EItem.itemType.Keyboard, "Bird", 6.0),
			new EItem(EItem.itemType.Keyboard, "Koala", 7.0),
			new EItem(EItem.itemType.Motherboard, "ASUS_ROG", 30.0),				
			new EItem(EItem.itemType.Motherboard, "Kimberlite_Elec", 60.0)
		);
		List<User> userList = List.of(
			new User("0000000", "Alfa", "Zero", LocalDate.of(1999, 12, 30)),		//Maggiorenne
			new User("0000001", "Primo", "Levi", LocalDate.of(2010, 12,5)),		//Minorenne1
			new User("0000002", "Eta", "Beta", LocalDate.of(2020, 5, 4)),		//Minorenne2
			new User("0000003", "Joe", "White", LocalDate.of(2012, 12, 12)),		//Minorenne3
			new User("1000000", "Matusa", "Lemme", LocalDate.of(1908, 1, 3)),	//Maggiorenne
			new User("0000004", "Bob", "Marley", LocalDate.of(2014, 4, 4)),		//Minorenne4
			new User("0000005", "Jim", "Marley", LocalDate.of(2017, 4, 4)),		//Minorenne5
			new User("0000006", "Steven", "Marley", LocalDate.of(2016, 4, 4)),	//Minorenne6
			new User("0000007", "Steven", "Seagull", LocalDate.of(2018, 4, 4)),	//Minorenne7
			new User("0000008", "Al", "Pine", LocalDate.of(2010, 4, 4)),			//Minorenne8
			new User("0000009", "Rick", "Shaw", LocalDate.of(2010, 4, 4)),		//Minorenne9
			new User("0000010", "Richard", "Tudor", LocalDate.of(2010, 4, 4)),	//Minorenne10
			new User("0000011", "Alfredo", "Biachi", LocalDate.of(2010, 4, 4)),	//Minorenne11
			new User("0000012", "Himuro", "Rei", LocalDate.of(2010, 4, 4)),		//Minorenne12
			new User("0000013", "Luke", "Skywalker", LocalDate.of(2010, 4, 4)),	//Minorenne13
			new User("0000014", "Leia", "Organa", LocalDate.of(2010, 4, 4)),		//Minorenne14
			new User("0000015", "Bob", "Dylan", LocalDate.of(2010, 4, 4))		//Minorenne15
		);
		templateList = List.of(
			new Order(NoExtra_List, userList.get(0), LocalTime.of(18,30,0)),		//M O	NO
			new Order(NoExtra_List, userList.get(1), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(2), LocalTime.of(18,30,0)),		//m O	OK	
			new Order(NoExtra_List, userList.get(3), LocalTime.of(20,30,0)),		//m X	NO	
			new Order(NoExtra_List, userList.get(4), LocalTime.of(19,30,0)),		//M X	NO	
			new Order(NoExtra_List, userList.get(5), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(6), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(7), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(8), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(9), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(10), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(11), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(2), LocalTime.of(18,45,0)),		//m O D NO
			new Order(NoExtra_List, userList.get(12), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(13), LocalTime.of(18,30,0)),		//m O	OK
			new Order(NoExtra_List, userList.get(14), LocalTime.of(18,30,0))		//m O	OK
		);
		dummyList=List.copyOf(templateList);
		coolList = List.of(
			templateList.get(1),
			templateList.get(2),
			templateList.get(5),
			templateList.get(6),
			templateList.get(7),
			templateList.get(8),
			templateList.get(9),
			templateList.get(10),
			templateList.get(11),
			templateList.get(13),
			templateList.get(14),
			templateList.get(15)
		);

        shorter_coolList = List.of(
            templateList.get(1),
			templateList.get(2),
			templateList.get(5),
			templateList.get(6)
        );

        

	}

	//TEST COSTRUTTORE
	//Test su argomenti nulli
	@Test
	public void testOnCreationSetNullList(){
		try{
			new Giveaway(null);
		}
		catch(NullPointerException e){
			Assertions.assertEquals("The list of order could not be found", e.getMessage());
		}
	}

	//Test sulla corretta selezione dei partecipanti
	@Test
	public void testCorrectInitialCandidateParsing(){
		giv = new Giveaway(dummyList);

		
		Assertions.assertAll(
			()->{
				Assertions.assertTrue(giv.getCandidateOrders().size()==coolList.size()); 
			},
			()->{
				
					boolean equivalent=true;	
					for(int i=0; i<giv.getCandidateOrders().size() && equivalent; i++){
						equivalent=giv.getCandidateOrders().get(i).equals(coolList.get(i));
					}
					Assertions.assertTrue(equivalent);
				}
				
			
		);
		
	}

	

	//Testa se, data una lista di candidati correttamente selezionati, qualcuno vince
	@Test
	public void testGiveawaySelection(){
        Assertions.assertAll(
            ()->Assertions.assertTrue(new Giveaway(coolList).giveawaySelection()),                                  //Lista 10+ candidati
            ()->Assertions.assertFalse(new Giveaway(new ArrayList<Order>(0)).giveawaySelection()),  //Lista vuota
            ()->Assertions.assertTrue(new Giveaway(shorter_coolList).giveawaySelection())                           //Lista <10 candidati ->> "Tutti vincono"
        );	
	}

}
