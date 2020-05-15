package BackEnd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GyroSensorTest {
	protected GyroSensor GS;

	@Before
	public void setUp() throws Exception {
		GS = new GyroSensor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAngle() throws Exception {
		assertNotNull(GS.getAngle());
	}

}
