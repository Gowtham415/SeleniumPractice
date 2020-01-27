package eg.ext.crossbrowser;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ChromeBrowserTest extends BaseTest {
	@Test
	public void test01() {
		  Assert.assertEquals(driver.getTitle(), "Expedia Travel: Vacations, Cheap Flights, Airline Tickets & Airfares");
	}
}
