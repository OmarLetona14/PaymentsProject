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


public class UpdateStatus300 implements Runnable{
    
    JList listStatus300;
    DefaultListModel modelStatus300 = new DefaultListModel();
        
    
    public UpdateStatus300(JList listStatus300){
        this.listStatus300 = listStatus300;
        this.listStatus300.setModel(modelStatus300);
    }
        

    @Override
    public void run() {
        while(LogWindow.finish){
            for(int i = 1; i<=TransactionVerifier.state300.listSize(); i++){
                    try {
                        modelStatus300.addElement(TransactionVerifier.state300.getStateAt(i).getTransaction().getCorrelative());
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateStatus300.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                }
                modelStatus300.clear();                      
        }    
        if(!LogWindow.finish){
            for(int i = 1; i<=TransactionVerifier.state300.listSize(); i++){
                try {
                    modelStatus300.addElement(TransactionVerifier.state300.getStateAt(i).getTransaction().getCorrelative());
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus300.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        }
    }
    
}
