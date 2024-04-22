import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AzulApiRequest {
    private static final String AZUL_ENDPOINT = "https://api.azul.com/metadata/v1/zulu/packages/";

    public static URL getArchiveUrl(JavaVersionInfo javaVersionInfo) throws MalformedURLException {
        final JsonArray response = getAPIResponse(javaVersionInfo);
        if (response.isEmpty()) {
            throw new RuntimeException("No response");
        }

        String downloadUrl = response.get(0).getAsJsonObject().get("download_url").getAsString();
        return new URL(downloadUrl);
    }

    private static JsonArray getAPIResponse(JavaVersionInfo javaVersionInfo) {
        try {
            HttpURLConnection conn = (HttpURLConnection) buildRequestUrl(javaVersionInfo).openConnection();
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

    private static URL buildRequestUrl(JavaVersionInfo javaVersionInfo) throws MalformedURLException {
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
}
