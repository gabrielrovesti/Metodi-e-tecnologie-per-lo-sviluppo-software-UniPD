////////////////////////////////////////////////////////////////////
// [Gabriel] [Rovesti] [2009088]
// [Luca] [Romio] [2014028]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss;

import java.time.LocalDate;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;

public class User {
    private String id, name, surname;
    private LocalDate birthDate;

    public User(String _id, String _name, String _surname, LocalDate _birthDate) throws IllegalArgumentException, NullPointerException{
        if(_id!=null){
            if(!_id.isEmpty()){
                id=_id;
            }else{
                throw new IllegalArgumentException("Invalid id");
            }
        }else{
            throw new NullPointerException("Invalid id reference (null)");
        }

        if(_name!=null){
            if(!(_name.length()<2 || _name.length()>20)){
                name=_name;
            }else{
                throw new IllegalArgumentException("Invalid name length");
            }
        }else{
            throw new NullPointerException("Invalid name reference (null)");
        }

        if(_surname!=null){
            if(!(_surname.length()<2 || _surname.length()>20)){
                surname=_surname;
            }else{
                throw new IllegalArgumentException("Invalid surname length");
            }
        }else{
            throw new NullPointerException("Invalid surname reference (null)");
        }

        try{
            if(!_birthDate.equals(null)){
                birthDate=_birthDate;
            }
        }catch(NullPointerException e){
            throw new NullPointerException("Invalid birthDate reference (null)");
        }
    }

    public String getId(){
        return id;
    };

    public String getName(){
        return name;
    };

    public String getSurname(){
        return surname;
    };

    public int getAge(){
        return LocalDate.now().getYear()-this.birthDate.getYear();
    };
    
    public boolean isUnderage(){
        return this.getAge()<18;
    }

}