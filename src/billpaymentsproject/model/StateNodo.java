/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.model;

/**
 * Nodo utilizado para guardar datos dentro de la cola.
 */
public class StateNodo {
    
    public State info;
    public StateNodo sig;
    
    public StateNodo(State info){
        this.info = info;
        sig = null;
    }
    
    
    
}
