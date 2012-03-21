package tychodemo.bundle.handlers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.JUnitCore;

public class AboutHandlerTest {

	@Test
	public void testGetGreeting() {
		assertThat(new AboutHandler().getGreeting(), JUnitMatchers.containsString("Tycho Demo"));
	}

}
