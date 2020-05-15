package BackEnd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LuxSensorTest {
	protected LuxSensor LS;

	@Before
	public void setUp() throws Exception {
		LS = new LuxSensor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetLux() throws Exception {
		assertNotNull(LS.getLux());
	}

}
