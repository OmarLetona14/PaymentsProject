/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.model;

/**
 * Clase que define el modelo del estado en el que se está trabajando,
 * contiene un transaccion y un id para identificarlo dentro de la cola.
 */
public class State {
    
    private int id;
    private Transaction transaction;

    public State(int id, Transaction transaction) {
        this.id = id;
        this.transaction = transaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

   
    
}
