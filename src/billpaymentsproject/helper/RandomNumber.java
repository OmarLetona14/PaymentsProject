/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.helper;

import java.util.Random;

public class RandomNumber {
    
    public int generateRandomTime(){
        Random r = new Random();
        int valorEntero = r.nextInt(1000)+1000;
        return valorEntero;         
    }
    
    public int generateRandomAmount(){
        int valorEntero = (int) Math.floor(Math.random()*((-1000)-1000+1)+1000);
        return valorEntero;
    }
    
    public int generateCorrelative(){
        Random r = new Random();
        int valorEntero = r.nextInt(1000);
        return valorEntero;  
    }
    
    public int probability(int total){
        return (1/5)*(total);
    }
    
}
