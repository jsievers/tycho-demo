package tychodemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ApplicationWorkbenchAdvisorTest {

	@Test
	public void testgetPerspectiveId() throws Exception {
		ApplicationWorkbenchAdvisor applicationWorkbenchAdvisor = new ApplicationWorkbenchAdvisor();
		assertEquals("tychodemo.perspective",
				applicationWorkbenchAdvisor.getInitialWindowPerspectiveId());
	}

}
