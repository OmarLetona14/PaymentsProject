/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.states;

import billpaymentsproject.cola.StateList;
import billpaymentsproject.helper.Clock;
import billpaymentsproject.helper.RandomNumber;
import billpaymentsproject.model.Transaction;
import billpaymentsproject.view.LogWindow;
import billpaymentsproject.view.StatusView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;
import javax.swing.JTextArea;

public class GenerateTransaction implements Runnable {
    
    private final JTextArea log;
    static Transaction transaction = null;
    static StateList state100 = new StateList();
    RandomNumber random = new RandomNumber();
    TransactionProcessor transactionProcessor=null;
    Clock clock = new Clock();
    Handler fileHandler;
    public final static Logger LOGGER = Logger.getLogger("billpaymentsproject.Log.LogEvent");
    SimpleFormatter simpleFormatter;
    public static int size = 1;
    static StatusView statusView = new StatusView();
    String line, logMessage;
    static UpdateStatus100 updateStatus100 = new UpdateStatus100(StatusView.state100);
    static UpdateStatus200 updateStatus200 = new UpdateStatus200(StatusView.state200);
    static UpdateStatus201 updateStatus201 = new UpdateStatus201(StatusView.state201);
    static UpdateStatus300 updateStatus300 = new UpdateStatus300(StatusView.state300);
    static UpdateStatus400 updateStatus400 = new UpdateStatus400(StatusView.state400);
    static UpdateStatus301 updateStatus301 = new UpdateStatus301(StatusView.state301);
        
    public void init(){
        try {
            this.fileHandler = new FileHandler("C:\\Users\\Omar\\Desktop\\BillPaymentsProject\\src\\Log\\Archivo del log.log"
                    + "",false);
        } catch (IOException ex) {
            Logger.getLogger(GenerateTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GenerateTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        simpleFormatter = new SimpleFormatter();
    }
    public GenerateTransaction(JTextArea logTxt){
        init();
        log = logTxt;
        fileHandler.setFormatter(simpleFormatter);
        LOGGER.addHandler(fileHandler);
    }
    
    public void generateNewTransaction(){
        transaction = new Transaction(size,random.generateCorrelative(),"","",random.generateRandomAmount());
        state100.addToFinal(transaction);
        logMessage = "Generando transaccion " + 
                transaction.getCorrelative()+":"+transaction.getAmount()+ 
                "  Estado:100";
        line = log.getText()+"\n"+ logMessage+" at "+clock.getTime();
        LOGGER.log(Level.INFO, logMessage);
        log.setText(line);
        size++;        
    }
    
    @Override
    public void run() {
        statusView.setVisible(true);
        new Thread(updateStatus100).start();
        new Thread(updateStatus200).start();
        new Thread(updateStatus201).start();
        new Thread(updateStatus300).start();
        new Thread(updateStatus400).start();
        new Thread(updateStatus301).start();
        while(LogWindow.finish){
            generateNewTransaction();
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException ex) {
                Logger.getLogger(GenerateTransaction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
}
