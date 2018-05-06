import jdk.dio.DeviceManager;
import jdk.dio.gpio.GPIOPin;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;


public class LED { 
	private GPIOPin pin;
    LED(int pin_id){
        try {
			pin=(GPIOPin) DeviceManager.open(pin_id);
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }     
    }
    
	public void on(){
        try {
            pin.setValue(true);
        } catch (IOException ex) {
            Logger.getLogger(LED.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void off(){
        try {
            pin.setValue(false);
        } catch (IOException ex) {
            Logger.getLogger(LED.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void blink(int ms){
		for(int i=0; i<5; i++){
			try{
				pin.setValue(true);
				Thread.sleep(ms);
				pin.setValue(false);
				Thread.sleep(ms);
           } catch (IOException | InterruptedException ex) {
				System.out.println(ex.getMessage());
           }
       }

	}
}
