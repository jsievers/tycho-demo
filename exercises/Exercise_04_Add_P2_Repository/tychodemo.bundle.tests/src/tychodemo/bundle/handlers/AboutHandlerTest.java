package tychodemo.bundle.handlers;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class AboutHandlerTest {

	@Test
	public void testGetGreeting() {
		AboutHandler aboutHandler = new AboutHandler();
		assertThat(aboutHandler.getGreeting(), containsString("Hello Tycho Demo"));
	}

}
