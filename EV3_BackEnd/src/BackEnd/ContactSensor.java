package BackEnd;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class ContactSensor 
{
	private EV3TouchSensor sensor; 
	private SampleProvider contactSamp;
	private float[] contactSampTab;

	public ContactSensor() //Constructor initializing the sensor variable using port S4
	{
		
		sensor = new EV3TouchSensor(SensorPort.S4);
		
	}
	
	public int getContact() //Method for returning 0 if no push and 1 if push
	{
		
		contactSamp = sensor.getTouchMode();
		contactSampTab = new float[contactSamp.sampleSize()];
		contactSamp.fetchSample(contactSampTab, 0);
		float result = contactSampTab[0];
		return (int) (result);
		
	}
}
