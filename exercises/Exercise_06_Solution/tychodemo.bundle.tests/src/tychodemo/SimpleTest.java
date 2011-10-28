package tychodemo;

import static org.junit.Assert.*;

import org.junit.Test;

import tychodemo.bundle.ApplicationWorkbenchAdvisor;

public class SimpleTest {

	@Test
	public void testGetInitialWindowPerspectiveId() {
		assertEquals("tychodemo.bundle.perspective",
				new ApplicationWorkbenchAdvisor()
						.getInitialWindowPerspectiveId());
	}

}
