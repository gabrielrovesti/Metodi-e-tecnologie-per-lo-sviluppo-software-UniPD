////////////////////////////////////////////////////////////////////
// [Gabriel] [Rovesti] [2009088]
// [Luca] [Romio] [2014028]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss;

import java.time.LocalTime;
import java.util.List;
import java.lang.NullPointerException;
import it.unipd.mtss.exception.BillException;

public class Order implements Bill{
    private List<EItem> itemList;
    private User user;
    private LocalTime time;

    public Order(List<EItem> _list, User _user, LocalTime _time) throws BillException {
        //PRE: The list is made of well constructed EItem objects, or a null reference, or empty ->check
        if(_list!=null){
            if(!(_list.size() == 0 || _list.size() > 30)){ 
                itemList = _list;
            }else{
                throw new BillException("Invalid itemList length");
            }          
        }else{
            throw new BillException("The list of items could not be found");
        }
        //POST: The list is made of 1<=n<=30 well constructed EItem obj

        //PRE: The user is a well constructed User object, or a null reference ->check
        if(_user!=null){
            user=_user;
        }else{
            throw new BillException("The user does not exist");
        }
        //POST: The user is a well constructed User obj

        //PRE: The time of the order is a well constructed LocalTime object, or a null reference ->check
        try{
            if(!_time.equals(null)){
                time=_time;
            }
        }catch(NullPointerException e){
            throw new BillException("The date is missing");
        }
        //POST: The time is a well constructed LocalTime obj
    }

    //Return the total of the order while applying the discounts
    public double getOrderPrice(List<EItem> _list, User _user) throws BillException{
        int proc_n=0, mouse_n=0, keyboard_n=0;
        double total=0.0;
        double cheapest_m=Double.MAX_VALUE, cheapest_k=Double.MAX_VALUE, cheapest_p=Double.MAX_VALUE;
        boolean discountAlreadyApplied = false;
        Order order = new Order(_list, _user, LocalTime.now());

        for(EItem i: order.getItemList()) {
            if(i.getItemType() == EItem.itemType.Processor) {
                ++proc_n;
                if(i.getPrice() < cheapest_p) {
                     cheapest_p = i.getPrice();
                }
            }
            if(i.getItemType() == EItem.itemType.Mouse) {
                ++mouse_n;
                if(i.getPrice() < cheapest_m) {
                     cheapest_m = i.getPrice();
                }
            }
            if(i.getItemType() == EItem.itemType.Keyboard) {
                ++keyboard_n;
                if(i.getPrice() < cheapest_k) {
                     cheapest_k = i.getPrice();
                }
            }
            total += i.getPrice();
        }
        //Here we apply the first discount possible, regardless of which one gives the biggest benefit to the customer
        if(proc_n > 5 && !discountAlreadyApplied) {                             //#processor > 5
            cheapest_p = cheapest_p/2.0;
            total = total-cheapest_p;
            discountAlreadyApplied = true;
        }
        if(mouse_n > 10 && !discountAlreadyApplied) {                           //#mouse > 10
            total -= cheapest_m;
            discountAlreadyApplied = true;
        }
        if(mouse_n == keyboard_n  && mouse_n!=0 && !discountAlreadyApplied) {   //#mouse = #keyboard
            if(cheapest_m <= cheapest_k){
                total -= cheapest_m;
            }else{
                total -= cheapest_k;
            }
            discountAlreadyApplied = true;
        }
        
        if(total < 10){                                            //total < 10
            total += 2;                                
        }
        return total;
    }

    public User getUser(){
        return user;
    }

    public List<EItem> getItemList(){
        return itemList;
    }

    public LocalTime getTime(){
        return time;
    }

}