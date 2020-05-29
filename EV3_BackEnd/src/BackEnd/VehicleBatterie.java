package BackEnd;

import lejos.hardware.Battery;

public class VehicleBatterie {
	
	public VehicleBatterie()
	{
		
	}
	
	public float getBatterieLevel()
	{
		int minVoltage = 6500 ; //voltage when the battery is almost empty
		int maxVoltage = 8400 ; //voltage when the battery is almost full
		return ((Battery.getVoltageMilliVolt()-minVoltage)*100)/(maxVoltage-minVoltage);
	}


}
