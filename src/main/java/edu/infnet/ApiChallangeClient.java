package edu.infnet;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiChallangeClient {

    public HttpsURLConnection getConnection(String resource, String method) throws IOException, URISyntaxException {
        URI uri = new URI("https://apichallenges.eviltester.com" + resource);
        URL url = uri.toURL();

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Accept", "application/json");
        connection.setConnectTimeout(5000);

        return connection;
    }

    public String responseData(HttpsURLConnection connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();
        String responseLine;

        while ((responseLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(responseLine.trim());
        }

        return stringBuilder.toString();
    }

    public String getEntities() throws IOException, URISyntaxException {
        HttpsURLConnection connection = this.getConnection("/sim/entities", "GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("Response Code: " + connection.getResponseCode());
            return this.responseData(connection);
        } else {
            throw new IOException("Error in GET /sim/entities/: " + connection.getResponseCode());
        }
    }


    public String getEntityById(int id) throws IOException, URISyntaxException {
        HttpsURLConnection connection = this.getConnection("/sim/entities/" + id, "GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("Response Code: " + connection.getResponseCode());
            return this.responseData(connection);
        } else {
            throw new IOException("Error in GET /sim/entities/" + id + ": " + connection.getResponseCode());
        }
    }

    public String getEntityWithFakeParams(String param) throws IOException, URISyntaxException {
        HttpsURLConnection connection = this.getConnection("/sim/entities" + param, "GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("Response Code: " + connection.getResponseCode());
            return this.responseData(connection);
        } else {
            throw new IOException("Error in GET /sim/entities/" + param + ": " + connection.getResponseCode());
        }
    }

    public void postEntity(String json, int id) throws IOException, URISyntaxException {
        HttpsURLConnection connection = this.getConnection("/sim/entities/" + id, "POST");
        connection.setDoOutput(true);
        connection.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));

        if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED || connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("Response Code: " + connection.getResponseCode());
        } else {
            throw new IOException("Error in POST /sim/entities/" + id + " " + connection.getResponseCode());
        }
    }

    public void putEntity(String json, int id) throws IOException, URISyntaxException {
        HttpsURLConnection connection = this.getConnection("/sim/entities/" + id, "PUT");
        connection.setDoOutput(true);
        connection.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));

        if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED || connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("Response Code: " + connection.getResponseCode());
        } else {
            throw new IOException("Error in PUT /sim/entities/" + id + " " + connection.getResponseCode());
        }
    }

    public void removeEntity(int id) throws IOException, URISyntaxException {
        HttpsURLConnection connection = this.getConnection("/sim/entities/" + id, "DELETE");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {
            System.out.println("Response Code: '" + connection.getResponseCode() + " No Content'");
        } else {
            throw new IOException("Error in DELETE /sim/entities/" + id + " '" + connection.getResponseCode() + " Forbidden'");
        }
    }

    public String optionsEntities() throws IOException, URISyntaxException {
        HttpsURLConnection connection = this.getConnection("/sim/entities", "OPTIONS");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {
            System.out.println("Response Code: " + connection.getResponseCode());
            return connection.getHeaderField("Allow");
        } else {
            throw new IOException("Error in OPTIONS /sim/entities/: " + connection.getResponseCode());
        }
    }

}
