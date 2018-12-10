/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
    
    public String getTime(){
        String time;
        time = getCurrentTime()+" "+getCurrentDate();
        return time;
    }
    
    public String getCurrentTime(){
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        return hourFormat.format(date);
    }
    
    public String getCurrentDate(){
        Date time = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(time);
    } 
}
