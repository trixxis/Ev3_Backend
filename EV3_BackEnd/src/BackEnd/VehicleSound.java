package BackEnd;

import lejos.hardware.Sound;

public class VehicleSound {
	
	public VehicleSound()
	{
		
	}
	
	public void beep()
	{
		Sound.beep();
	}
	
	public void Ton1(int durée)
	{
		Sound.playTone(500, durée);
	}
	
	public void Ton2(int durée)
	{
		Sound.playTone(1000, durée);
	}
	
	public void Ton3(int durée)
	{
		Sound.playTone(1500, durée);
	}

}

