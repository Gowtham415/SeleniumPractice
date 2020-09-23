package eg.ext.listeners;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

public class ReporterListener implements IReporter {

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			String suiteName = suite.getName();
			for (ISuiteResult result : suiteResults.values()) {
				ITestContext tc = result.getTestContext();
				System.out.println("Suite Name:"+suiteName);
				System.out.println("Passed Tests:" + tc.getPassedTests().getAllResults().size());
				System.out.println("Failed Tests:" + tc.getFailedTests().getAllResults().size());
				System.out.println("Skipped Tests:" + tc.getSkippedTests().getAllResults().size());
			}

		}

	}
}
