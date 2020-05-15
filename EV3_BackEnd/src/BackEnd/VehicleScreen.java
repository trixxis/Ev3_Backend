package BackEnd;

import lejos.hardware.lcd.LCD;

public class VehicleScreen {
	
	public VehicleScreen()
	{
		
	}
	
	public void setLcdInformation(String texte, int ligne)
	{
		LCD.drawString(texte, 0, ligne);
		LCD.refresh();
	}
	
	public void ClearLcd()
	{
		LCD.clearDisplay();
	}

}
