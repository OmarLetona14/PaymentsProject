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

public class UpdateStatus400 implements Runnable {
    

    JList listStatus400;
    DefaultListModel modelStatus400 = new DefaultListModel();
        
    
    public UpdateStatus400(JList listStatus400){
        this.listStatus400 = listStatus400;
        this.listStatus400.setModel(modelStatus400);
    }
    @Override
    public void run() {
        while(LogWindow.finish){
            for(int i = 1; i<=Payer.state400.listSize(); i++){
                try {
                    modelStatus400.addElement("|"+Payer.state400.getStateAt(i).getTransaction().getCorrelative()+":"
                            +Payer.state400.getStateAt(i).getTransaction().getAmount()
                    +"|");
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus400.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
            modelStatus400.clear();
        }    
        if(!LogWindow.finish){
            for(int i = 1; i<=Payer.state400.listSize(); i++){
                try {
                    modelStatus400.addElement("|"+Payer.state400.getStateAt(i).getTransaction().getCorrelative()+":"
                            +Payer.state400.getStateAt(i).getTransaction().getAmount()
                    +"|");
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus400.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        }
        
    }
    
}
