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

public class Payer implements Runnable {
    
    public static StateList state400 = new StateList();
    public static StateList state301 = new StateList();
    JTextArea log;
    String line;
    int count;
    
    public Payer(JTextArea log){
        this.log = log;
    }
    
    public void pay() throws Exception{
        int currentProcess = TransactionVerifier.state300.listSize();
            if(count == 5){
                line = log.getText()+"\n"+"Transaccion "+ "|"+
                   TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction().getCorrelative()+
                        TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction().getAmount() +
                "|" +   " pagada correctamente";
            state301.addToFinal(TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction());
            count=0;
            TransactionVerifier.state300.delete(currentProcess-1);
           log.setText(line);
            }else{
                line = log.getText()+"\n"+"Ocurrió un error al intentar pagar la transaccion "+ "|" +
                   TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction().getCorrelative()+ ":"+
                        TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction().getAmount()+
               "|";
                if(TransactionVerifier.state300.getStateAt(currentProcess-1)!=null){
                    count++;
                }
            state400.addToFinal(TransactionVerifier.state300.getStateAt(currentProcess-1).getTransaction());
            TransactionVerifier.state300.delete(currentProcess-1);
           log.setText(line);
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
