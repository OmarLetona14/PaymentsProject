/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.states;

import billpaymentsproject.cola.StateList;
import billpaymentsproject.view.LogWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class TransactionProcessor implements Runnable{
    
    private final JTextArea log;
    public static StateList state200 = new StateList();
    public static StateList state201 = new StateList();
    
    String line;
    
    public TransactionProcessor(JTextArea log){
        this.log = log;
    }
    
    public void process() throws Exception{
        int currentProcess = GenerateTransaction.state100.listSize();
        line = log.getText()+"\n"+"Pasando transaccion "+ "|"+
                GenerateTransaction.state100.getStateAt(currentProcess-1).getTransaction().getCorrelative()+
                    GenerateTransaction.state100.getStateAt(currentProcess-1).getTransaction().getAmount()+ "|"+
                " de estado 100 ---> estado 200...";
        state200.addToFinal(GenerateTransaction.state100.getStateAt(currentProcess-1).getTransaction());
        GenerateTransaction.state100.delete(currentProcess-1);
       log.setText(line); 
    }

    @Override
    public void run() {
        while(LogWindow.finish){
            try {
              process();
            } catch (Exception ex) {
                Logger.getLogger(TransactionProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TransactionProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }   
        } 
    }
    
}
