////////////////////////////////////////////////////////////////////
// [Gabriel] [Rovesti] [2009088]
// [Luca] [Romio] [2014028]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class EItemTest {
    public EItem item;

    @BeforeEach
    public void initialize() {
        item = new EItem(EItem.itemType.Keyboard, "Tastiera bella", 25.0);
    }

    //TEST COSTRUTTORE
    //Test su argomenti null
    @Test
    public void testOnCreationSetNullitemType(){
        try{
            new EItem(null, "Tastiera bella", 25.0);
        }
        catch(NullPointerException e){
            Assertions.assertEquals("Invalid itemType reference (null)", e.getMessage());
        }
    }

    @Test
    public void testOnCreationSetNullName(){
        try{
            new EItem(EItem.itemType.Keyboard, null, 25.0);
        }
        catch(NullPointerException e){
            Assertions.assertEquals(e.getMessage(), "Invalid name reference (null)");
        }
    }

    //Test su argomenti non nei parametri indicati
    @Test
    public void testOnCreationSetInvalidName(){
        Assertions.assertAll(
            ()->{
                try{
                    new EItem(EItem.itemType.Keyboard, "a", 25.0);
                }
                catch(IllegalArgumentException e){
                    Assertions.assertEquals(e.getMessage(), "Invalid name length");
                }
            },
            ()->{
                try{
                    new EItem(EItem.itemType.Keyboard, "RazerUltimateIncredibleKeyboardOfDestinyVersionTwo", 25.0);
                }
                catch(IllegalArgumentException e){
                    Assertions.assertEquals(e.getMessage(),"Invalid name length");
                }
            }
        );
    }
    
    @Test
    public void testOnCreationSetNegativePrice() {
    	try {
        	new EItem(EItem.itemType.Keyboard, "Tastiera bella", -25.0);
    	}
	 	catch(IllegalArgumentException e) {
            Assertions.assertEquals(e.getMessage(), "Invalid price (negative)");
	 	}
    }
   

    //TEST METODI DI GET
	@Test
    public void testGetItemType() {
        //item=new EItem(EItem.itemType.Keyboard, "Tastiera bella", 25.0);
        Assertions.assertEquals(EItem.itemType.Keyboard, item.getItemType());
    }

    @Test
    public void testGetName() {
        //item=new EItem(EItem.itemType.Keyboard, "Tastiera bella", 25.0);
        Assertions.assertEquals("Tastiera bella", item.getName());
    }

    @Test
    public void testGetPrice() {
        //item=new EItem(EItem.itemType.Keyboard, "Tastiera bella", 25.0);
        Assertions.assertEquals(25.0, item.getPrice(), 1e-8);
    }
}
