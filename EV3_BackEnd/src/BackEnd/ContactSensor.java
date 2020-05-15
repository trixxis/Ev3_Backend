package BackEnd;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class ContactSensor {
	private EV3TouchSensor sensor;
	private SampleProvider contactSamp;
	private float[] contactSampTab;

	public ContactSensor() {
		
		sensor = new EV3TouchSensor(SensorPort.S4);
		
	}
	
	public int getContact() {
		
		contactSamp = sensor.getTouchMode();
		contactSampTab = new float[contactSamp.sampleSize()];
		contactSamp.fetchSample(contactSampTab, 0);
		float result = contactSampTab[0];
		return (int) (result);
		
	}
}
