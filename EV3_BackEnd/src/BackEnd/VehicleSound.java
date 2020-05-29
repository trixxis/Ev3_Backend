package BackEnd;

import lejos.hardware.Sound;

public class VehicleSound {
	
	public VehicleSound()
	{
		
	}
	
	public void beep()//play a simple beep sound
	{
		Sound.beep();
	}
	
	public void Ton1(int durée) //play a ton at a frequency of 500 Hzr
	{
		Sound.playTone(500, durée);
	}
	
	public void Ton2(int durée)//play a ton at a frequency of 1000 Hzr
	{
		Sound.playTone(1000, durée);
	}
	
	public void Ton3(int durée)//play a ton at a frequency of 1500 Hzr
	{
		Sound.playTone(1500, durée);
	}

}

