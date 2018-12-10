/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.states;

import billpaymentsproject.cola.StateList;
import billpaymentsproject.helper.Clock;
import billpaymentsproject.view.LogWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class TransactionVerifier implements Runnable {
    
    private final JTextArea log;
    public static StateList state300 = new StateList();
    Clock clock = new Clock();
    String line, logMessage;
    
    public TransactionVerifier(JTextArea log){
        this.log = log;
    }
    public void verify() throws Exception{
        int currentProcess = TransactionProcessor.state200.listSize();
        if(TransactionProcessor.state200.getStateAt(currentProcess-1)!=null){
            if(TransactionProcessor.state200.getStateAt(currentProcess-1).getTransaction().getAmount()>=0){
                logMessage = "Transaccion "+ 
                  "|"+ TransactionProcessor.state200.getStateAt(currentProcess-1).getTransaction().getCorrelative()+":"
                    + TransactionProcessor.state200.getStateAt(currentProcess-1).getTransaction().getAmount() + "|"
               +" verificada correctamente";
            line = log.getText()+"\n"+logMessage+" at "+clock.getTime();
                state300.addToFinal(TransactionProcessor.state200.getStateAt(currentProcess-1).getTransaction());
                TransactionProcessor.state200.delete(currentProcess-1);
                GenerateTransaction.LOGGER.log(Level.INFO, logMessage);
               log.setText(line);
            }else {
                logMessage = "Transaccion "+ "|"+ TransactionProcessor.state200.getStateAt(currentProcess-1).getTransaction().getCorrelative()+ ":"
                        +TransactionProcessor.state200.getStateAt(currentProcess-1).getTransaction().getAmount() + "|"
                   +" denegada, ocurrió un error; Estado 201";
                line = log.getText()+"\n"+logMessage+" at "+clock.getTime();
                TransactionProcessor.state201.addToFinal(TransactionProcessor.state200.getStateAt(currentProcess-1).getTransaction());
                TransactionProcessor.state200.delete(currentProcess-1);
                GenerateTransaction.LOGGER.log(Level.WARNING, logMessage);
                log.setText(line);
           }
        }
    }
    @Override
    public void run() {
        while(LogWindow.finish){
            try {
            verify();
            } catch (Exception ex) {
                Logger.getLogger(TransactionVerifier.class.getName()).log(Level.SEVERE, null, ex);
            }
           try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TransactionProcessor.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }       
    }
    
}
