package TestSuiteList;

import fr.thegostsniperfr.java_downloader.JavaDownloader;
import fr.thegostsniperfr.java_downloader.JavaVersionInfo;
import fr.thegostsniperfr.java_downloader.distribution.JavaType;
import fr.thegostsniperfr.java_toolbox.distribution.ArchType;
import fr.thegostsniperfr.java_toolbox.distribution.OsType;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LinuxJavaDownloader implements ITestSuite{



    @Override
    public void runTests() {
        Path installPath = Paths.get("testsuite/linux/");

        JavaDownloader javaDownloader = new JavaDownloader(
                installPath, new JavaVersionInfo(
                "11",            // Java version
                JavaType.JDK,               // Java type
                OsType.LINUX,               // Os type
                ArchType.AARCH32,           // Arch type
                false),                     // With JavaFx
                System.out::println,        // Callback function
                true);                      // Delete archive when finished

        try {
            Path javaDirPath = javaDownloader.downloadAndExtract();
            System.out.println("Java home dir path is " + javaDirPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
