package fr.thegostsniperfr;

import fr.thegostsniperfr.distribution.ArchType;
import fr.thegostsniperfr.distribution.JavaType;
import fr.thegostsniperfr.distribution.OsType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class main {
    public static void main(String[] args) {
        Path installPath = new File("").toPath();
        JavaDownloader javaDownloader = new JavaDownloader(
                installPath, new JavaVersionInfo(
                        "17",
                        JavaType.JRE,
                        OsType.WINDOWS,
                        ArchType.X64,
                        true),
                        System.out::println,
                        false);

        try {
            Path javaDirPath = javaDownloader.downloadAndExtract();
            System.out.println("Java dir path is " + javaDirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
