////////////////////////////////////////////////////////////////////
// [Gabriel] [Rovesti] [2009088]
// [Luca] [Romio] [2014028]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.unipd.mtss.exception.BillException;

public class OrderTest{
	User user;
	List<EItem> items;
	Order order, ordr;
	@BeforeEach
	public void initialize(){
		items = List.of(
			new EItem(EItem.itemType.Keyboard, "Razer", 25.0), 
			new EItem(EItem.itemType.Keyboard, "Corsair", 20.0),
			new EItem(EItem.itemType.Keyboard, "Logitech", 23.0),
			new EItem(EItem.itemType.Motherboard, "ASUS_ROG", 170.0),
			new EItem(EItem.itemType.Mouse, "Logitech", 15.0)
			);
		user = new User("2014028", "Luca", "Romio", LocalDate.of(1999, 8, 16));
		try{
			order = new Order(items, user, LocalTime.of(1,1,1));
		}
		catch(BillException b){}
	}

	//TEST COSTRUTTORE
	//Test su argomenti nulli
	@Test
	public void testOnCreationSetNullList(){
		try{
			ordr = new Order(null, user, LocalTime.now());
		}
		catch(BillException b){
			Assertions.assertEquals(b.getMessage(), "The list of items could not be found");
		}
	}

	@Test
	public void testOnCreationSetNullUser(){
		try{
			ordr = new Order(items, null, LocalTime.now());
		}
		catch(BillException b){
			Assertions.assertEquals(b.getMessage(), "The user does not exist");
		}
	}

	@Test
	public void testOnCreationSetNullTime(){
		try{
			ordr = new Order(items, user, null);
		}
		catch(BillException b){
			Assertions.assertEquals(b.getMessage(), "The date is missing");
		}
	}

	//Test su argomenti non nei parametri specificati
	@Test
	public void testOnCreationSetInvalidList(){
		try{
			ordr = new Order(Collections.<EItem>emptyList(), user, LocalTime.now() ); 
		}
		catch(BillException b){
			Assertions.assertEquals("Invalid itemList length", b.getMessage());
		}

		try{
			List<EItem> lst =Collections.nCopies(30, new EItem(EItem.itemType.Keyboard, "Logitech", 1.0));
			ordr = new Order(lst, user, LocalTime.now());
		}catch(BillException b){
			Assertions.assertEquals("Invalid itemList length", b.getMessage());
		}
	}

	//TEST METODI DI GET
	@Test
	public void testGetItemList(){
		Assertions.assertEquals(items, order.getItemList());
	}

	@Test
	public void testGetUser(){
		Assertions.assertEquals(user, order.getUser());
	}

	@Test
	public void testGetTime(){
		Assertions.assertEquals(LocalTime.of(1,1,1), order.getTime());
	}

	//The test tries all the possible edge cases as well as the "normal" one
	@Test
	public void testgetOrderPrice(){
		List<EItem> FivePlus_TenPlus_Equal_Extra_List = List.of(
			new EItem(EItem.itemType.Processor, "Alfa", 1.0),					//6>5 processors
			new EItem(EItem.itemType.Processor, "Beta", 4.0),
			new EItem(EItem.itemType.Processor, "Gamma", 7.0),
			new EItem(EItem.itemType.Processor, "Delta", 10.0),
			new EItem(EItem.itemType.Processor, "Epsilon", 13.0),
			new EItem(EItem.itemType.Processor, "Omega", 16.0),
			new EItem(EItem.itemType.Mouse, "Apple", 2.0),						//11>10 mouse
			new EItem(EItem.itemType.Mouse, "Orange", 5.0),
			new EItem(EItem.itemType.Mouse, "Banana", 8.0),
			new EItem(EItem.itemType.Mouse, "Grape", 11.0),
			new EItem(EItem.itemType.Mouse, "Pear", 14.0),
			new EItem(EItem.itemType.Mouse, "Peach", 17.0),
			new EItem(EItem.itemType.Mouse, "Apricot", 19.0),
			new EItem(EItem.itemType.Mouse, "Strawberry", 21.0),
			new EItem(EItem.itemType.Mouse, "Blackberry", 23.0),
			new EItem(EItem.itemType.Mouse, "Coconut", 25.0),
			new EItem(EItem.itemType.Mouse, "Lemon", 27.0),
			new EItem(EItem.itemType.Keyboard, "Albatross", 3.0),				//11 = 11
			new EItem(EItem.itemType.Keyboard, "Bird", 6.0),
			new EItem(EItem.itemType.Keyboard, "Camel", 9.0),
			new EItem(EItem.itemType.Keyboard, "Dinosaur", 12.0),
			new EItem(EItem.itemType.Keyboard, "Elephant", 15.0),
			new EItem(EItem.itemType.Keyboard, "Frog", 18.0),
			new EItem(EItem.itemType.Keyboard, "Giraffe", 20.0),
			new EItem(EItem.itemType.Keyboard, "Hippo", 22.0),
			new EItem(EItem.itemType.Keyboard, "Indian_Rhino", 24.0),
			new EItem(EItem.itemType.Keyboard, "Jungle_Spider", 26.0),
			new EItem(EItem.itemType.Keyboard, "Koala", 28.0),
			new EItem(EItem.itemType.Motherboard, "ASUS_ROG", 30.0),				//extra
			new EItem(EItem.itemType.Motherboard, "Kimberlite_Elec", 60.0)
		);//496-0.5

		List<EItem> TenPlus_Equal_Extra_List = List.of(												
			new EItem(EItem.itemType.Processor, "Beta", 4.0),					//5 processors
			new EItem(EItem.itemType.Processor, "Gamma", 7.0),
			new EItem(EItem.itemType.Processor, "Delta", 10.0),
			new EItem(EItem.itemType.Processor, "Epsilon", 13.0),
			new EItem(EItem.itemType.Processor, "Omega", 16.0),
			new EItem(EItem.itemType.Mouse, "Apple", 2.0),						//11>10 mouse
			new EItem(EItem.itemType.Mouse, "Orange", 5.0),
			new EItem(EItem.itemType.Mouse, "Banana", 8.0),
			new EItem(EItem.itemType.Mouse, "Grape", 11.0),
			new EItem(EItem.itemType.Mouse, "Pear", 14.0),
			new EItem(EItem.itemType.Mouse, "Peach", 17.0),
			new EItem(EItem.itemType.Mouse, "Apricot", 19.0),
			new EItem(EItem.itemType.Mouse, "Strawberry", 21.0),
			new EItem(EItem.itemType.Mouse, "Blackberry", 23.0),
			new EItem(EItem.itemType.Mouse, "Coconut", 25.0),
			new EItem(EItem.itemType.Mouse, "Lemon", 27.0),
			new EItem(EItem.itemType.Keyboard, "Albatross", 3.0),				//11 = 11
			new EItem(EItem.itemType.Keyboard, "Bird", 6.0),
			new EItem(EItem.itemType.Keyboard, "Camel", 9.0),
			new EItem(EItem.itemType.Keyboard, "Dinosaur", 12.0),
			new EItem(EItem.itemType.Keyboard, "Elephant", 15.0),
			new EItem(EItem.itemType.Keyboard, "Frog", 18.0),
			new EItem(EItem.itemType.Keyboard, "Giraffe", 20.0),
			new EItem(EItem.itemType.Keyboard, "Hippo", 22.0),
			new EItem(EItem.itemType.Keyboard, "Indian_Rhino", 24.0),
			new EItem(EItem.itemType.Keyboard, "Jungle_Spider", 26.0),
			new EItem(EItem.itemType.Keyboard, "Koala", 28.0),
			new EItem(EItem.itemType.Motherboard, "ASUS_ROG", 30.0),				//extra
			new EItem(EItem.itemType.Motherboard, "Kimberlite_Elec", 60.0)
		);//495-2

		List<EItem> Equal_Extra_List_A = List.of(												
			new EItem(EItem.itemType.Processor, "Beta", 4.0),					//5 processors
			new EItem(EItem.itemType.Processor, "Gamma", 7.0),
			new EItem(EItem.itemType.Processor, "Delta", 10.0),
			new EItem(EItem.itemType.Processor, "Epsilon", 13.0),
			new EItem(EItem.itemType.Processor, "Omega", 16.0),					
			new EItem(EItem.itemType.Mouse, "Orange", 5.0),						//10 mouse
			new EItem(EItem.itemType.Mouse, "Banana", 8.0),
			new EItem(EItem.itemType.Mouse, "Grape", 11.0),
			new EItem(EItem.itemType.Mouse, "Pear", 14.0),
			new EItem(EItem.itemType.Mouse, "Peach", 17.0),
			new EItem(EItem.itemType.Mouse, "Apricot", 19.0),
			new EItem(EItem.itemType.Mouse, "Strawberry", 21.0),
			new EItem(EItem.itemType.Mouse, "Blackberry", 23.0),
			new EItem(EItem.itemType.Mouse, "Coconut", 25.0),
			new EItem(EItem.itemType.Mouse, "Lemon", 27.0),
			new EItem(EItem.itemType.Keyboard, "Bird", 6.0),						//10 = 10
			new EItem(EItem.itemType.Keyboard, "Camel", 9.0),
			new EItem(EItem.itemType.Keyboard, "Dinosaur", 12.0),
			new EItem(EItem.itemType.Keyboard, "Elephant", 15.0),
			new EItem(EItem.itemType.Keyboard, "Frog", 18.0),
			new EItem(EItem.itemType.Keyboard, "Giraffe", 20.0),
			new EItem(EItem.itemType.Keyboard, "Hippo", 22.0),
			new EItem(EItem.itemType.Keyboard, "Indian_Rhino", 24.0),
			new EItem(EItem.itemType.Keyboard, "Jungle_Spider", 26.0),
			new EItem(EItem.itemType.Keyboard, "Koala", 28.0),
			new EItem(EItem.itemType.Motherboard, "ASUS_ROG", 30.0),				//extra
			new EItem(EItem.itemType.Motherboard, "Kimberlite_Elec", 60.0)
		);//490-5

        List<EItem> Equal_Extra_List_B = List.of(												
			new EItem(EItem.itemType.Processor, "Beta", 4.0),					//5 processors
			new EItem(EItem.itemType.Processor, "Gamma", 7.0),
			new EItem(EItem.itemType.Processor, "Delta", 10.0),
			new EItem(EItem.itemType.Processor, "Epsilon", 13.0),
			new EItem(EItem.itemType.Processor, "Omega", 16.0),					
			new EItem(EItem.itemType.Mouse, "Orange", 6.0),						//10 mouse
			new EItem(EItem.itemType.Mouse, "Banana", 8.0),
			new EItem(EItem.itemType.Mouse, "Grape", 11.0),
			new EItem(EItem.itemType.Mouse, "Pear", 14.0),
			new EItem(EItem.itemType.Mouse, "Peach", 17.0),
			new EItem(EItem.itemType.Mouse, "Apricot", 19.0),
			new EItem(EItem.itemType.Mouse, "Strawberry", 21.0),
			new EItem(EItem.itemType.Mouse, "Blackberry", 23.0),
			new EItem(EItem.itemType.Mouse, "Coconut", 25.0),
			new EItem(EItem.itemType.Mouse, "Lemon", 27.0),
			new EItem(EItem.itemType.Keyboard, "Bird", 5.0),						//10 = 10
			new EItem(EItem.itemType.Keyboard, "Camel", 9.0),
			new EItem(EItem.itemType.Keyboard, "Dinosaur", 12.0),
			new EItem(EItem.itemType.Keyboard, "Elephant", 15.0),
			new EItem(EItem.itemType.Keyboard, "Frog", 18.0),
			new EItem(EItem.itemType.Keyboard, "Giraffe", 20.0),
			new EItem(EItem.itemType.Keyboard, "Hippo", 22.0),
			new EItem(EItem.itemType.Keyboard, "Indian_Rhino", 24.0),
			new EItem(EItem.itemType.Keyboard, "Jungle_Spider", 26.0),
			new EItem(EItem.itemType.Keyboard, "Koala", 28.0),
			new EItem(EItem.itemType.Motherboard, "ASUS_ROG", 30.0),				//extra
			new EItem(EItem.itemType.Motherboard, "Kimberlite_Elec", 60.0)
		);//490-5

		List<EItem> NoExtra_List = List.of(												
			new EItem(EItem.itemType.Processor, "Beta", 1.0),					//2 processors
			new EItem(EItem.itemType.Processor, "Gamma", 4.0),					
			new EItem(EItem.itemType.Mouse, "Orange", 2.0),						//2 mouse
			new EItem(EItem.itemType.Mouse, "Banana", 5.0),
			new EItem(EItem.itemType.Keyboard, "Albatross", 3.0),				//2 != 3
			new EItem(EItem.itemType.Keyboard, "Bird", 6.0),
			new EItem(EItem.itemType.Keyboard, "Koala", 7.0),
			new EItem(EItem.itemType.Motherboard, "ASUS_ROG", 30.0),				//extra
			new EItem(EItem.itemType.Motherboard, "Kimberlite_Elec", 60.0)
		);

		List<EItem> Low_List = List.of(												
			new EItem(EItem.itemType.Processor, "Beta", 1.0),					//1 processors					
			new EItem(EItem.itemType.Mouse, "Orange", 2.0),						//1 mouse
			new EItem(EItem.itemType.Keyboard, "Albatross", 3.0),				//2 keyboards
			new EItem(EItem.itemType.Keyboard, "Bird", 1.0)
		);

		List<EItem> OnlyProcessor_List = List.of(												
			new EItem(EItem.itemType.Processor, "Alfa", 1.0),					//2 processors					
			new EItem(EItem.itemType.Processor, "Beta", 1.0)
		);

		List<EItem> OnlyMouse_List = List.of(												
			new EItem(EItem.itemType.Mouse, "Lemon", 1.0),					//2 mouse					
			new EItem(EItem.itemType.Mouse, "Coconut", 1.0)
		);

		List<EItem> OnlyKeyboard_List = List.of(												
			new EItem(EItem.itemType.Keyboard, "Alfa", 1.0),					//2 keyboard					
			new EItem(EItem.itemType.Keyboard, "Beta", 1.0)
		);

		List<EItem> OnlyMotherboard_List = List.of(												
			new EItem(EItem.itemType.Motherboard, "ASUS_ROG", 100.0),					//2 motherboard					
			new EItem(EItem.itemType.Motherboard, "Kimberlite_Elec", 203.25)
		);

		Assertions.assertAll(
			()-> Assertions.assertEquals(495.5, order.getOrderPrice(FivePlus_TenPlus_Equal_Extra_List, user)),
			()-> Assertions.assertEquals(493.0, order.getOrderPrice(TenPlus_Equal_Extra_List, user)),
			()-> Assertions.assertEquals(485, order.getOrderPrice(Equal_Extra_List_A, user)),
            ()-> Assertions.assertEquals(485, order.getOrderPrice(Equal_Extra_List_B, user)),
			()-> Assertions.assertEquals(118, order.getOrderPrice(NoExtra_List, user)),
			()-> Assertions.assertEquals(9, order.getOrderPrice(Low_List, user)),
			()-> Assertions.assertEquals(4.0, order.getOrderPrice(OnlyProcessor_List, user)),
			()-> Assertions.assertEquals(4.0, order.getOrderPrice(OnlyMouse_List, user)),
			()-> Assertions.assertEquals(4.0, order.getOrderPrice(OnlyKeyboard_List, user)),
			()-> Assertions.assertEquals(303.25, order.getOrderPrice(OnlyMotherboard_List, user))
		);
	}	
}
