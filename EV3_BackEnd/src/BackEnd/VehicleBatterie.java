package BackEnd;

import lejos.hardware.Battery;

public class VehicleBatterie {
	
	public VehicleBatterie()
	{
		
	}
	
	public float getBatterieLevel()
	{
		int minVoltage = 6500 ;
		int maxVoltage = 8400 ;
		return ((Battery.getVoltageMilliVolt()-minVoltage)*100)/(maxVoltage-minVoltage);
	}


}
