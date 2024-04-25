import TestSuiteList.ITestSuite;
import TestSuiteList.LinuxJavaDownloader;
import TestSuiteList.WinJavaDownloader;

import java.util.ArrayList;
import java.util.List;

public class LaunchTestSuite {
    public static void main(String[] args) {
        List<ITestSuite> iTestSuiteList = new ArrayList<>();
        iTestSuiteList.add(new WinJavaDownloader());
        iTestSuiteList.add(new LinuxJavaDownloader());

        iTestSuiteList.forEach(ITestSuite::runTests);
    }
}
