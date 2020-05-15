package BackEnd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContactSensorTest {
	protected ContactSensor CS;

	@Before
	public void setUp() throws Exception {
		CS = new ContactSensor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetContactOff() throws Exception {
		assertEquals(0,CS.getContact());
	}
	
	@Test
	public void testGetContactOn() throws Exception {
		assertEquals(1,CS.getContact());
	}

}
