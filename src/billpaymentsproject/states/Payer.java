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

/*En esta clase se pagan todas las transacciones, se agregan a la cola del estado 400
Por cada 5 transacciones pagadas correctamente
una simula un error y se agrega a la cola del estado 301*/

public class Payer implements Runnable {
    
    public static StateList state400 = new StateList();
    public static StateList state301 = new StateList();
    Clock clock = new Clock();
    JTextArea log;
    String line, logMessagge;
    static int count;
    
    public Payer(JTextArea log){
        this.log = log;
    }
    
    public void pay() throws Exception{
        int currentProcess = TransactionVerifier.state300.listSize();
        if(count == 5){
                if(TransactionVerifier.state300.getStateAt(currentProcess-1)!=null){
                    logMessagge = "Ocurrió un error al intentar pagar la transaccion "+ "|" +
                   TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction().getCorrelative()+ ":"+
                        TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction().getAmount();
                    line = log.getText()+"\n"+logMessagge+" at "+clock.getTime();
                    
                    state301.addToFinal(TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction());
                    count=0;
                    TransactionVerifier.state300.delete(currentProcess-1);
                    GenerateTransaction.LOGGER.log(Level.WARNING, logMessagge);
                   log.setText(line);
                }
                
            }else{
                if(TransactionVerifier.state300.getStateAt(currentProcess-1)!=null){
                    logMessagge = "Transaccion "+ "|"+
                   TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction().getCorrelative()+ ":"+
                        TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction().getAmount() +
                        "|" +   " pagada correctamente";
                    line = log.getText()+"\n"+logMessagge+" at "+clock.getTime();
                     if(TransactionVerifier.state300.getStateAt(currentProcess-1)!=null){
                         count++;
                     }
                    state400.addToFinal(TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction());
                    TransactionVerifier.state300.delete(currentProcess-1);
                    GenerateTransaction.LOGGER.log(Level.INFO, logMessagge);
                   log.setText(line);
                }
                
            }    
    }

    @Override
    public void run() {
       while(LogWindow.finish){
           try {
               pay();
           } catch (Exception ex) {
               Logger.getLogger(Payer.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               Thread.sleep(4000);
           } catch (InterruptedException ex) {
               Logger.getLogger(Payer.class.getName()).log(Level.SEVERE, null, ex);
           }
       
       } 
    }
    
}
