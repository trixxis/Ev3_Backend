package BackEnd;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class LuxSensor{

	private EV3ColorSensor sensor;
	private SampleProvider luxSamp;
	private float[] luxSampTab;

	public LuxSensor() {
		
		sensor = new EV3ColorSensor(SensorPort.S1);
	}
	
	public int getLux() {
		
		luxSamp = sensor.getAmbientMode();
		luxSampTab = new float[luxSamp.sampleSize()];
		luxSamp.fetchSample(luxSampTab, 0);
		float result = luxSampTab[0];
		return (int) (result * 100);
	}

}
