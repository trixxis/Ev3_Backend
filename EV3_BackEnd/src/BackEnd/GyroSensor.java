package BackEnd;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class GyroSensor {
	
	private EV3GyroSensor sensor;
	private SampleProvider angleSamp;
	private float[] angleSampTab;

	public GyroSensor() {
		
		sensor = new EV3GyroSensor(SensorPort.S3);
		
	}
	
	public int getAngle() {
		
		angleSamp = sensor.getAngleMode();
		angleSampTab = new float[angleSamp.sampleSize()];
		angleSamp.fetchSample(angleSampTab, 0);
		float result = angleSampTab[0];
		return (int) (result);
	}
	
	
}
