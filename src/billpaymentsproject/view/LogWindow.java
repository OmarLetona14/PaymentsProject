/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.view;

import billpaymentsproject.states.TransactionProcessor;
import billpaymentsproject.states.TransactionVerifier;
import billpaymentsproject.states.GenerateTransaction;
import billpaymentsproject.states.Payer;

public class LogWindow extends javax.swing.JFrame {

    GenerateTransaction generate = null;
    public static boolean finish = true;
    TransactionProcessor transactionProcessor=null;
    TransactionVerifier transactionVerifier =null;
    Payer payer = null;
    
    public LogWindow() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        initBtn = new javax.swing.JButton();
        finishBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTxt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        initBtn.setText("Iniciar");
        initBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initBtnActionPerformed(evt);
            }
        });

        finishBtn.setText("Finalizar");
        finishBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishBtnActionPerformed(evt);
            }
        });

        logTxt.setBackground(new java.awt.Color(0, 0, 0));
        logTxt.setColumns(20);
        logTxt.setForeground(new java.awt.Color(255, 255, 255));
        logTxt.setRows(5);
        jScrollPane1.setViewportView(logTxt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(initBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
                .addComponent(finishBtn)
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initBtn)
                    .addComponent(finishBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initBtnActionPerformed
        initBtn.setEnabled(false);
        finishBtn.setEnabled(true);
        if(logTxt.getText().equals("")){
            generate = new GenerateTransaction(logTxt);
            new Thread(generate).start();
            transactionProcessor= new TransactionProcessor(logTxt);
            new Thread(transactionProcessor).start();
            transactionVerifier = new TransactionVerifier(logTxt);
            new Thread(transactionVerifier).start();
            payer = new Payer(logTxt); 
            new Thread(payer).start();
        }else{
            logTxt.setText("");
            generate = new GenerateTransaction(logTxt);
            new Thread(generate).start();
        }
    }//GEN-LAST:event_initBtnActionPerformed

    private void finishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishBtnActionPerformed
        finishBtn.setEnabled(false);
        initBtn.setEnabled(true);
        finish = false;
    }//GEN-LAST:event_finishBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton finishBtn;
    private javax.swing.JButton initBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logTxt;
    // End of variables declaration//GEN-END:variables
}
