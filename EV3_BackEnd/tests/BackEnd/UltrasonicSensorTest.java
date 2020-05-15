package BackEnd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UltrasonicSensorTest {
	protected UltrasonicSensor US;

	@Before
	public void setUp() throws Exception {
		US = new UltrasonicSensor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDistance() throws Exception {
		assertNotNull(US.getDistance());
	}

}
