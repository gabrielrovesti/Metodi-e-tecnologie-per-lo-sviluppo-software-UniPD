////////////////////////////////////////////////////////////////////
// [Gabriel] [Rovesti] [2009088]
// [Luca] [Romio] [2014028]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss;

import java.util.List;

import it.unipd.mtss.exception.BillException;

public interface Bill {
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException;
}