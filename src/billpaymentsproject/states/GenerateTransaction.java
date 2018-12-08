/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.states;

import billpaymentsproject.cola.StateList;
import billpaymentsproject.helper.RandomNumber;
import billpaymentsproject.model.Transaction;
import billpaymentsproject.view.LogWindow;
import billpaymentsproject.view.StatusView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class GenerateTransaction implements Runnable {
    
    private final JTextArea log;
    static Transaction transaction = null;
    static StateList state100 = new StateList();
    RandomNumber random = new RandomNumber();
    TransactionProcessor transactionProcessor=null;
    public static int size = 1;
    static StatusView statusView = new StatusView();
    static UpdateStatus100 updateStatus100 = new UpdateStatus100(StatusView.state100);
    static UpdateStatus200 updateStatus200 = new UpdateStatus200(StatusView.state200);
    static UpdateStatus201 updateStatus201 = new UpdateStatus201(StatusView.state201);
    static UpdateStatus300 updateStatus300 = new UpdateStatus300(StatusView.state300);
    static UpdateStatus400 updateStatus400 = new UpdateStatus400(StatusView.state400);
    static UpdateStatus301 updateStatus301 = new UpdateStatus301(StatusView.state301);
        

    public GenerateTransaction(JTextArea logTxt){
        log = logTxt;
    }
    
    public void generateNewTransaction(){
        transaction = new Transaction(size,random.generateCorrelative(),"","",random.generateRandomAmount());
        state100.addToFinal(transaction);
        String line = log.getText()+"\n"+ "Transaccion --->  " + 
                transaction.getCorrelative()+":"+transaction.getAmount()+ 
                " estado ---> 100";
        log.setText(line);
        size++;        
    }
    
    @Override
    public void run() {
        statusView.setVisible(true);
        new Thread(updateStatus100).start();
        new Thread(updateStatus200).start();
        new Thread(updateStatus201).start();
        new Thread(updateStatus300).start();
        new Thread(updateStatus400).start();
        new Thread(updateStatus301).start();
        while(LogWindow.finish){
            generateNewTransaction();
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException ex) {
                Logger.getLogger(GenerateTransaction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
}
