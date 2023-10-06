package eg.ext.listeners;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyzerTest {
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void Test1()
	{
		Assert.assertEquals(false, true);
	}

	@Test
	public void Test2()
	{
		Assert.assertEquals(true, true);
	}
}
