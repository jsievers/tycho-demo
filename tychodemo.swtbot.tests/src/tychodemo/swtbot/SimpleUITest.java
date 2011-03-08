package tychodemo.swtbot;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SimpleUITest {

	private SWTBot bot = new SWTBot();

	@Test
	public void testMainWindowText() {
		// test that we can find a shell with title text "Tycho Demo RCP"
		SWTBotShell shell = bot.shell("Tycho Demo RCP");
		Assert.assertNotNull(shell);
	}

	@Test(expected = WidgetNotFoundException.class)
	public void testNonExistingShell() {
		// just to make sure we also tested the negative case
		bot.shell("foo");
	}

}
