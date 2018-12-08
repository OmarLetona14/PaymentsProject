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

public class UpdateStatus200 implements Runnable {
    
    JList listStatus200;
    DefaultListModel modelStatus200 = new DefaultListModel();
        
    
    public UpdateStatus200(JList listStatus200){
        this.listStatus200 = listStatus200;
        this.listStatus200.setModel(modelStatus200);
    }

    @Override
    public void run() {
        while(LogWindow.finish){
            for(int i = 1; i<=TransactionProcessor.state200.listSize(); i++){
                try {
                    modelStatus200.addElement(TransactionProcessor.state200.getStateAt(i).getTransaction().getCorrelative());
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus200.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
            modelStatus200.clear();
        }  
        if(!LogWindow.finish){
            for(int i = 1; i<=TransactionProcessor.state200.listSize(); i++){
                try {
                    modelStatus200.addElement(TransactionProcessor.state200.getStateAt(i).getTransaction().getCorrelative());
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus200.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        }
    }
    
}
