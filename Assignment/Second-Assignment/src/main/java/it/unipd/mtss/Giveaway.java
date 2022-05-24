////////////////////////////////////////////////////////////////////
// [Gabriel] [Rovesti] [2009088]
// [Luca] [Romio] [2014028]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.lang.NullPointerException;

public class Giveaway {
    static public LocalTime GIVEAWAYSTART = LocalTime.of(18, 0);
    static public LocalTime GIVEAWAYFINISH = LocalTime.of(19, 0);
    static public int NUMBEROFWINNERS = 10;
    private List<Order> candidateOrders = new ArrayList<Order>(0);
    //PRE: Lista di ordini, può essere vuota;
    public Giveaway(List<Order> _inputOrdersList) throws NullPointerException{
        boolean alreadyPresent=false;
        if(_inputOrdersList!=null){
            for(int j=0; j<_inputOrdersList.size(); j++){
                alreadyPresent=false;
                if(_inputOrdersList.get(j).getUser().isUnderage()) {                                                     //minorenne?
                    if(_inputOrdersList.get(j).getTime().isAfter(GIVEAWAYSTART)&&
                       _inputOrdersList.get(j).getTime().isBefore(GIVEAWAYFINISH)){ //tra le 18 e le 19 ?
                        if(!candidateOrders.isEmpty()){
                            for (int i=0; i<candidateOrders.size(); i++) {                                          //L'user è già presente?
                                if(candidateOrders.get(i).getUser().equals(_inputOrdersList.get(j).getUser())){
                                    alreadyPresent=true;
                                }
                            }
                        }
                        if(!alreadyPresent){
                            candidateOrders.add(_inputOrdersList.get(j));
                        }
                    }
                }
            }
        }else{
            throw new NullPointerException("The list of order could not be found");
        }
    }
    //POST: Lista di ordini fatta nell'arco temporale richiesto e da utenti minorenni distinti

    public boolean giveawaySelection(){
        if(!candidateOrders.isEmpty()){
            List<Order> winners= new ArrayList<Order>(0);
            Order selection;
            if(candidateOrders.size()<=NUMBEROFWINNERS){
                winners=candidateOrders;
            }else{
                for(int i=0; i<NUMBEROFWINNERS; i++){
                    selection = candidateOrders.get(((int)Math.random())%candidateOrders.size());
                    winners.add(selection);
                    candidateOrders.remove(selection);
                }
            
            } 
            System.out.println("I vincitori sono: ");
            for(int i=0; i<winners.size(); i++){
                System.out.println( winners.get(i).getUser().getId()+"\t\t"
                                    +winners.get(i).getUser().getName()+"\t\t"
                                    +winners.get(i).getUser().getSurname()
                                    );
            }
            return true;
        }
        return false;
    }

    public List<Order> getCandidateOrders(){
        return candidateOrders;
    }
}