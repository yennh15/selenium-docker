package com.yenpractice.listener;

import com.yenpractice.utilities.DataLoader;
import com.yenpractice.utilities.TestConstants;
import com.yenpractice.utilities.VerificationFailures;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.internal.Utils;

import java.util.List;

public class MethodListener implements IInvokedMethodListener, ITestListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        log.debug("Before invocation of " + method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        log.debug("After invocation of " + method.getTestMethod().getMethodName());
        Reporter.setCurrentTestResult(result);
        if (method.isTestMethod()) {
            VerificationFailures allFailures = VerificationFailures.getFailures();


            // Add an existing failure for the result to the failure list.
            if (result.getThrowable() != null) {
                allFailures.addFailureForTest(result, result.getThrowable());
            }

            List<Throwable> failures = allFailures.getFailuresForTest(result);
            int size = failures.size() - 1;

            if (size > 0) {
                result.setStatus(ITestResult.FAILURE);
                if (size == 1) {
                    result.setThrowable(failures.get(0));
                } else {
                    StringBuffer message = new StringBuffer("Multiple failures (").append(size).append("):\n");
                    for (int failure = 0; failure < size - 1; failure++) {
                        message.append("Failure ").append(failure + 1).append(" of ").append(size).append("\n");
                        message.append(Utils.longStackTrace(failures.get(failure), false)).append("\n");
                    }
                    Throwable last = failures.get(size - 1);
                    message.append("Failure ").append(size).append(" of ").append(size).append("\n");
                    message.append(last.toString());
                    Throwable merged = new Throwable(message.toString());
                    merged.setStackTrace(last.getStackTrace());
                    result.setThrowable(merged);
                }
            }
        }

    }

    public void onTestFailure(ITestResult result) {
        TakesScreenshot driver = (TakesScreenshot) result.getTestContext().getAttribute(TestConstants.DRIVER);
        String screenshot = driver.getScreenshotAs(OutputType.BASE64);
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s'>";
        String htmlImage = String.format(htmlImageFormat, screenshot);
        Reporter.log(htmlImage);
    }

    public void onTestSuccess(ITestResult result) {
        TakesScreenshot driver = (TakesScreenshot)result.getTestContext().getAttribute(TestConstants.DRIVER);
        String screenshot = driver.getScreenshotAs(OutputType.BASE64);
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s'>";
        String htmlImage = String.format(htmlImageFormat,screenshot);
        Reporter.log(htmlImage);
    }

    private static final Logger log = LoggerFactory.getLogger(MethodListener.class);
}
