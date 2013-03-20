package tychodemo.bundle.handlers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AboutHandlerTest {

	@Test
	public void testGetGreeting() {
		assertThat(new AboutHandler().getGreeting(), containsString("Tycho Demo"));
	}

}
