package BackEnd;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class UltrasonicSensor {
	
		private EV3UltrasonicSensor sensor;
		private SampleProvider distanceSamp;
		private float[] distanceSampTab;
	
		public UltrasonicSensor() {
			
			sensor = new EV3UltrasonicSensor(SensorPort.S2);
			sensor.enable();
			
		}
		
		public int getDistance() {
			
			distanceSamp = sensor.getDistanceMode();
			distanceSampTab = new float[distanceSamp.sampleSize()];
			distanceSamp.fetchSample(distanceSampTab, 0);
			float result = distanceSampTab[0];
			//return (int) (result * 100);
			if (Float.isInfinite(distanceSampTab[0]))
			{
				result = 0;
			}
			else
			{
				result = distanceSampTab[0];
			}
			return (int) (result * 100);
			
		}
		
		
		public void finalize()
	     {
	        sensor.disable();
	     }

}
