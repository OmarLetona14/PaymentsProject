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

public class UpdateStatus301 implements Runnable {

    JList listStatus301;
    DefaultListModel modelStatus301 = new DefaultListModel();
        
    
    public UpdateStatus301(JList listStatus301){
        this.listStatus301 = listStatus301;
        this.listStatus301.setModel(modelStatus301);
    }
        

    @Override
    public void run() {
        while(LogWindow.finish){
            for(int i = 1; i<=Payer.state301.listSize(); i++){
                    try {
                        modelStatus301.addElement("|"+Payer.state301.getStateAt(i).getTransaction().getCorrelative()+ ":"
                        +Payer.state301.getStateAt(i).getTransaction().getAmount()
                        +"|");
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateStatus300.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                }
                modelStatus301.clear();                      
        }    
        if(!LogWindow.finish){
            for(int i = 1; i<=Payer.state301.listSize(); i++){
                try {
                    modelStatus301.addElement("|"+Payer.state301.getStateAt(i).getTransaction().getCorrelative()+ ":"
                        +Payer.state301.getStateAt(i).getTransaction().getAmount()
                        +"|");
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus300.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        }
    }
    
}
