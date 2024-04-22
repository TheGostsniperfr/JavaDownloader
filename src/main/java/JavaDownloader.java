import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.sun.istack.internal.Nullable;
import distribution.OsType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class JavaDownloader {

    private final Path installPath;
    private final JavaVersionInfo javaVersionInfo;
    private final Callback callback;

    public JavaDownloader(Path installPath, JavaVersionInfo javaVersionInfo, @Nullable Callback callback) {
        this.installPath = installPath;
        this.javaVersionInfo = javaVersionInfo;
        this.callback = callback;
    }

    public void downloadAndExtract() throws IOException {
        final Path archivePath = this.getInstallPath().resolve(this.javaVersionInfo.getName());

        if(!Files.exists(archivePath)) {
            Files.createDirectories(this.getInstallPath());
        }

        // Fetching archive
        if(this.callback != null) {
            this.callback.onStep(Callback.Step.FETCHING);
        }

        final URL archiveUrl = AzulApiRequest.getArchiveUrl(this.javaVersionInfo);
        System.out.println(archiveUrl);

        // Download archive
        if(this.callback != null) {
            this.callback.onStep(Callback.Step.DOWNLOADING);
        }

        if(!Files.exists(archivePath)){
            Files.copy(archiveUrl.openStream(), archivePath, StandardCopyOption.REPLACE_EXISTING);
        }


        // Extract archive
        if(this.callback != null) {
            this.callback.onStep(Callback.Step.EXTRACTING);
        }

        // TODO
        if(this.javaVersionInfo.getOsType() == OsType.WINDOWS) {

        } else {

        }

        if(this.callback != null) {
            this.callback.onStep(Callback.Step.DONE);
        }
    }

    public Path getInstallPath() {
        return installPath;
    }

    public JavaVersionInfo getJavaVersionInfo() {
        return javaVersionInfo;
    }
}
