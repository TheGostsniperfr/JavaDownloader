package fr.thegostsniperfr.java_downloader;

import fr.thegostsniperfr.java_toolbox.distribution.OsType;
import fr.thegostsniperfr.java_toolbox.archive.TazGzUtils;
import fr.thegostsniperfr.java_toolbox.archive.ZipUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class JavaDownloader {

    private final Path installPath;
    private final JavaVersionInfo javaVersionInfo;
    private final Callback callback;
    private final boolean cleanArchive;

    public JavaDownloader(Path installPath, JavaVersionInfo javaVersionInfo, Callback callback, boolean cleanArchive) {
        this.installPath = installPath;
        this.javaVersionInfo = javaVersionInfo;
        this.callback = callback;
        this.cleanArchive = cleanArchive;
    }

    public Path downloadAndExtract() throws IOException {
        final Path archivePath = this.getInstallPath().resolve(this.javaVersionInfo.getArchiveName());

        if(!Files.exists(archivePath)) {
            Files.createDirectories(this.getInstallPath());
        }

        // Fetching archive
        if(this.callback != null) {
            this.callback.onStep(Callback.Step.FETCHING);
        }

        AzulApiRequest apiRequest = new AzulApiRequest(this.javaVersionInfo);

        System.out.println(apiRequest.getArchiveUrl());

        // Download archive
        if(this.callback != null) {
            this.callback.onStep(Callback.Step.DOWNLOADING);
        }

        if(!Files.exists(archivePath)){
            Files.copy(apiRequest.getArchiveUrl().openStream(), archivePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Extract archive
        if(this.callback != null) {
            this.callback.onStep(Callback.Step.EXTRACTING);
        }

        if(this.javaVersionInfo.getOsType() == OsType.WINDOWS) {
            ZipUtils.Unzip(archivePath, this.getInstallPath());
        } else {
            TazGzUtils.decompressTarGz(archivePath, this.getInstallPath());
        }

        if(this.callback != null) {
            this.callback.onStep(Callback.Step.DONE);
        }

        if(this.cleanArchive) {
            Files.delete(archivePath);
        }

        return this.installPath.resolve(apiRequest.getJavaHomeDirName()).toAbsolutePath();
    }

    public Path getInstallPath() {
        return installPath;
    }
}
