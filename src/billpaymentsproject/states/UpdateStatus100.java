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

public class UpdateStatus100 implements Runnable {
    
    JList listStatus100;
    DefaultListModel modelStatus100 = new DefaultListModel();
        
    
    public UpdateStatus100(JList listStatus100){
        this.listStatus100 = listStatus100;
        this.listStatus100.setModel(modelStatus100);
    }

    @Override
    public void run() {
        while(LogWindow.finish){
            for(int i = 1; i<=GenerateTransaction.state100.listSize(); i++){
                try {
                    modelStatus100.addElement("|"+GenerateTransaction.state100.getStateAt(i).getTransaction().getCorrelative() +":"
                            +GenerateTransaction.state100.getStateAt(i).getTransaction().getAmount()
                    +"|");
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus100.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            modelStatus100.clear();
        }
        if(!LogWindow.finish){
            for(int i = 1; i<=GenerateTransaction.state100.listSize(); i++){
                try {
                    modelStatus100.addElement("|"+GenerateTransaction.state100.getStateAt(i).getTransaction().getCorrelative() +":"
                            +GenerateTransaction.state100.getStateAt(i).getTransaction().getAmount()
                    +"|");
                } catch (Exception ex) {
                    Logger.getLogger(UpdateStatus100.class.getName()).log(Level.SEVERE, null, ex);
                }     
            }
        }
    }
    
}
