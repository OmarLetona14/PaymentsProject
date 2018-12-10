package billpaymentsproject.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*En esta clase se definen los metodos para poder obtener la hora y fecha actuales y
asi poder mostrarlas en el log*/

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
