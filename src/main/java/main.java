import distribution.ArchType;
import distribution.JavaType;
import distribution.OsType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class main {
    public static void main(String[] args) {
        Path installPath = new File("C:\\Users\\brian\\Desktop\\installJava").toPath();
        JavaDownloader javaDownloader = new JavaDownloader(
                installPath,
                new JavaVersionInfo(
                        "17",
                        JavaType.JRE,
                        OsType.WINDOWS,
                        ArchType.X64,
                        true),
                System.out::println);

        try {
            javaDownloader.downloadAndExtract();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
