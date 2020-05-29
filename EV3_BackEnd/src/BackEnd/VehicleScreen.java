package BackEnd;

import lejos.hardware.lcd.LCD;

public class VehicleScreen {
	
	public VehicleScreen()
	{
		
	}
	
	public void setLcdInformation(String texte, int ligne)//Method to draw strings on the screen
	{
		LCD.drawString(texte, 0, ligne);
		LCD.refresh();
	}
	
	public void ClearLcd()//method to clear the screen
	{
		LCD.clearDisplay();
	}

}
