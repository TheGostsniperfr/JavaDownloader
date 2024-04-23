import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AzulApiRequest {
    private static final String AZUL_ENDPOINT = "https://api.azul.com/metadata/v1/zulu/packages/";

    private final JavaVersionInfo javaVersionInfo;
    private URL archiveUrl;
    private String javaHomeDirName;

    public AzulApiRequest(JavaVersionInfo javaVersionInfo) throws MalformedURLException {
        this.javaVersionInfo = javaVersionInfo;

        this.parseApiResponse();
    }

    public void parseApiResponse() throws MalformedURLException {
        final JsonArray response = getAPIResponse();
        if (response.isEmpty()) {
            throw new RuntimeException("No response");
        }

        JsonObject javaVersion = response.get(0).getAsJsonObject();
        this.archiveUrl = new URL(javaVersion.get("download_url").getAsString());

        this.javaHomeDirName = javaVersion.get("name").getAsString()
                .replace("." + this.javaVersionInfo.getArchiveType(), "");
    }

    private JsonArray getAPIResponse() {
        try {
            HttpURLConnection conn = (HttpURLConnection) buildRequestUrl().openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Gson gson = new Gson();
                return gson.fromJson(response.toString(), JsonArray.class);
            } else {
                throw new IOException("Failed to load json from URL. Response code: " + responseCode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL buildRequestUrl() throws MalformedURLException {
        return new URL(AZUL_ENDPOINT +
                "?java_version=" + javaVersionInfo.getJavaVersion() +
                "&os=" + javaVersionInfo.getOsType().getType() +
                "&arch=" + javaVersionInfo.getArchType().getType() +
                "&java_package_type=" + javaVersionInfo.getJavaType().getType() +
                "&javafx_bundled=" + javaVersionInfo.isWithJavaFx() +
                "&support_term=lts" +
                "&latest=true" +
                "&archive_type=" + javaVersionInfo.getArchiveType() +
                "&page=1&page_size=100"
        );
    }

    public URL getArchiveUrl() {
        return archiveUrl;
    }

    public String getJavaHomeDirName() {
        return javaHomeDirName;
    }
}
