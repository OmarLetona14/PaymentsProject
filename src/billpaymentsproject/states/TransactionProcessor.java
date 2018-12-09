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

public class TransactionProcessor implements Runnable{
    
    private final JTextArea log;
    public static StateList state200 = new StateList();
    public static StateList state201 = new StateList();
    Clock clock = new Clock();
    String line;
    
    //Se recibe el JTextArea de la ventana LogWindow
    public TransactionProcessor(JTextArea log){
        this.log = log;
    }
    
    //Ejecuta un proceso en donde se toma la ultima transaccion de la cola
    //y se pasa del estado 100 al estado 200
    public void process() throws Exception{
        int currentProcess = GenerateTransaction.state100.listSize();
        if(GenerateTransaction.state100.getStateAt(currentProcess-1)!=null){
            line = log.getText()+"\n"+"Pasando transaccion "+ "|"+
                GenerateTransaction.state100.getStateAt(currentProcess-1).getTransaction().getCorrelative()+ ":"+
                    GenerateTransaction.state100.getStateAt(currentProcess-1).getTransaction().getAmount()+ "|"+
                " de estado 100 ---> estado 200..."+" at "+clock.getTime();
        state200.addToFinal(GenerateTransaction.state100.getStateAt(currentProcess-1).getTransaction());
        GenerateTransaction.state100.delete(currentProcess-1);
        log.setText(line); 
        } 
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
