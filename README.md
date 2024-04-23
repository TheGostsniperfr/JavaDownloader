# Java Downloader

Easy and fast java downloader.

Download Java from `azul.com`

### Windows example :
``` java
JavaDownloader javaDownloader = new JavaDownloader(
                installPath, new JavaVersionInfo(
                        "17",                       // Java version
                        JavaType.JRE,               // Java type
                        OsType.WINDOWS,             // Os type
                        ArchType.X64,               // Arch type
                        true),                      // With JavaFx
                        null,                       // Callback function
                        false);                     // Delete archive when finished
                        
Path javaDirPath = javaDownloader.downloadAndExtract();
System.out.println("Java home dir path is " + javaDirPath);
```

### Linux example :
``` java
JavaDownloader javaDownloader = new JavaDownloader(
                installPath, new JavaVersionInfo(
                        "11",                       // Java version
                        JavaType.JDK,               // Java type
                        OsType.LINUX,               // Os type
                        ArchType.AMD64,             // Arch type
                        false),                     // With JavaFx
                        null,                       // Callback function
                        true);                      // Delete archive when finished
                        
Path javaDirPath = javaDownloader.downloadAndExtract();
System.out.println("Java home dir path is " + javaDirPath);
```





## License

This project is licensed under the CC BY-NC-ND 4.0. licence. You can consult the complete text of the licence in the file [LICENSE](LICENSE).