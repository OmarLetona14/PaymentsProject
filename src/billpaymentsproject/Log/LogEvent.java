/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.Log;

import java.util.logging.Level;
import java.util.logging.Logger;


public class LogEvent {
    
    private final static Logger LOGGER =  Logger.getLogger("billpaymentsproject.Log.LogEvent");
    public void writeLog(String messagge){
        LOGGER.log(Level.INFO, messagge );
    }
    
}
