package billpaymentsproject.helper;

import java.util.Random;

public class RandomNumber {
    
    //Método para generar un numero aleatorio comprendido entre 1000 y 9000, este
    //será el tiempo en el que se generará una nueva transacción (En milisegundos)
    public int generateRandomTime(){
        int valorEntero = (int) (Math.floor(Math.random()*(6-3+1)+3)*1000);
        return valorEntero;         
    }
    
    //Método para generar un numero aleatorio entre -1000 y 1000, este será el monto
    //de la transacción
    public int generateRandomAmount(){
        int valorEntero = (int) Math.floor(Math.random()*((-1000)-1000+1)+1000);
        return valorEntero;
    }
    
    //Método para generar un numero aleatorio  entre 0 y 1000 el cual representa el correlativo
    //de una transaccion
    public int generateCorrelative(){
        Random r = new Random();
        int valorEntero = r.nextInt(1000);
        return valorEntero;  
    }
    
}
