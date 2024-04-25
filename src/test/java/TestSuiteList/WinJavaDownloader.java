package TestSuiteList;

import fr.thegostsniperfr.java_downloader.JavaDownloader;
import fr.thegostsniperfr.java_downloader.JavaVersionInfo;
import fr.thegostsniperfr.java_downloader.distribution.JavaType;
import fr.thegostsniperfr.java_toolbox.distribution.OsType;
import fr.thegostsniperfr.java_toolbox.distribution.ArchType;


import java.nio.file.Path;
import java.nio.file.Paths;

public class WinJavaDownloader implements ITestSuite{



    @Override
    public void runTests() {
        Path installPath = Paths.get("testsuite/win/");

        JavaDownloader javaDownloader = new JavaDownloader(
                installPath, new JavaVersionInfo(
                "17",            // Java version
                JavaType.JRE,               // Java type
                OsType.WINDOWS,             // Os type
                ArchType.X64,               // Arch type
                true),                      // With JavaFx
                System.out::println,        // Callback function
                false);                     // Delete archive when finished

        try {
            Path javaDirPath = javaDownloader.downloadAndExtract();
            System.out.println("Java home dir path is " + javaDirPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
