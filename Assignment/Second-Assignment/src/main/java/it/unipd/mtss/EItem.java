////////////////////////////////////////////////////////////////////
// [Gabriel] [Rovesti] [2009088]
// [Luca] [Romio] [2014028]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss;

import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;



public class EItem {
    public enum itemType {
        Processor, 
        Motherboard, 
        Mouse, 
        Keyboard
    };

    public itemType type;   //1°->Processor,...
    public String name;     //Specific name of the model
    public double price;    //the price of the singular item

    public EItem (itemType _type, String _name, double _price) throws IllegalArgumentException, NullPointerException{
        if(_type!=null){
            type=_type; 
        }else {
            throw new NullPointerException("Invalid itemType reference (null)");
        }

        if(_name!=null){
            if(!(_name.length()<3 || _name.length()>20)){
                name = _name;
            }else{
                throw new IllegalArgumentException("Invalid name length");
            }
        }else{
            throw new NullPointerException("Invalid name reference (null)");
        }
        
        if(!(_price<0)){
            price = _price;
        }else{
            throw new IllegalArgumentException("Invalid price (negative)");
        }
    }

    public itemType getItemType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

}