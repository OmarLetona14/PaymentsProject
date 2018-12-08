/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.states;

import billpaymentsproject.view.LogWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;


public class UpdateStatus201 implements Runnable {
    
    JList listStatus201;
    DefaultListModel modelStatus201 = new DefaultListModel();
        
    
    public UpdateStatus201(JList listStatus201){
        this.listStatus201 = listStatus201;
        this.listStatus201.setModel(modelStatus201);
    }

    @Override
    public void run() {
        while(LogWindow.finish){
            for(int i = 1; i<=TransactionProcessor.state201.listSize(); i++){
                try {
                    modelStatus201.addElement("|"+TransactionProcessor.state201.getStateAt(i).getTransaction().getCorrelative()+":"
                            +TransactionProcessor.state201.getStateAt(i).getTransaction().getAmount()
                    +"|");
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus201.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
            modelStatus201.clear();
        } 
        if(!LogWindow.finish){
            for(int i = 1; i<=TransactionProcessor.state201.listSize(); i++){
                try {
                    modelStatus201.addElement("|"+TransactionProcessor.state201.getStateAt(i).getTransaction().getCorrelative()+":"
                            +TransactionProcessor.state201.getStateAt(i).getTransaction().getAmount()
                    +"|");
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus201.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        }
    }
    
}
