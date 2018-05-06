import javax.microedition.midlet.MIDlet;
import jdk.dio.gpio.*;
import jdk.dio.*;
import java.io.*;


public class PIR extends MIDlet{
	
    private LED          led;
    private GPIOPin      pin;
	private final int pir = 1; //GPIO4
	private final int green_led = 4; //GPIO26 
    boolean mov_detected = false;
   
    PirThread pir_thread;
    
    @Override
    public void startApp() {
		try {
			led = new LED(green_led);
			pin = (GPIOPin) DeviceManager.open(pir);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
        }          
        pir_thread = new PirThread();
        pir_thread.start();
    }
    
    @Override
    public void pauseApp() {
    }
    
    @Override
    public void destroyApp(boolean unconditional) {
       mov_detected=true;
    }
    
    private class PirThread extends Thread{

        @Override
		public void run(){
			while(!mov_detected){
				System.out.println("No intruders");
				try {
					if(pin.getValue()){
						System.out.println("Intruder detected");
						led.blink(50);
					}
					led.off();
					Thread.sleep(1000);
					} catch (IOException | InterruptedException ex) {
						System.out.println(ex.getMessage());
					}
			}
		}
   }
}
