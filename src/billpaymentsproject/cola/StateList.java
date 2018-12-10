/*
    Clase que define el comportamiento de la cola de datos que se genera
    al procesar transacciones. Es una estructura de datos.
 */
package billpaymentsproject.cola;

import billpaymentsproject.model.State;
import billpaymentsproject.model.StateNodo;
import billpaymentsproject.model.Transaction;

public class StateList {
    private StateNodo first;
    int size=1;
    
    public StateList(){
        first = null;
    }
    
    public int listSize(){
        return size;
    }
    
    public boolean empty(){
        return(first==null);
    }
    
    public void addToFinal(Transaction transaction){
        State state = new State(size, transaction);
        StateNodo newNodo = new StateNodo(state);
        if(empty()){   
            first = newNodo;
        }else{
            StateNodo aux = first;
            while(aux.sig!=null){
                aux = aux.sig;
            }
            aux.sig = newNodo;           
        }
       size++;
    }
    
    public State getStateAt(int idRef) throws Exception{
        if(idRef>=1 && !empty()){
            if(idRef==1){
            return first.info;
            }else{
                StateNodo aux = first;
                for(int i=1; i<idRef; i++){
                    aux = aux.sig;
                }
                if(aux!=null){
                    return aux.info;
                } 
            }
        }
        return null;
    }
    
    public boolean serch(int idRef){
        StateNodo aux = first;
        boolean found = false;
        while(aux != null && found!=true){
            if(idRef==aux.info.getId()){
                found = true;
            }else{
                aux = aux.sig;
            }
        }
        return found;
    }
    
    public void delete(int idRef){
        if(serch(idRef)){
            if(first.info.getId()==idRef){
                first = first.sig;
            }else{
                StateNodo aux = first;
                while(aux.sig.info.getId()!=idRef){
                    aux = aux.sig;
                }
                StateNodo next = aux.sig.sig;
                aux.sig = next;
            }
            size--;
        }
    }
    
}
