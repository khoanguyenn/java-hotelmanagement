package fraus.javaproject.api;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     This class is utilized BUILDER pattern to create communication channel to web-server using JSON
 *     ,accepting 4 important methods GET, POST, PUT, DELETE request methods.
 * </p>
 * @author Huy Ha Xuan, Dang Khoa Nguyen
 * @version 1.0.2
 * */
public class Client {
    private String host = "http://localhost:8080/hotelmanagement/";
    private String requestMethod;
    private String encodeString;
    private String response;

    /**
     * <p>
     *     Select which <strong>route</strong> connects to server.
     * </p>
     * @param route as route, may be the database's name.
     * @return this to chain the next options
     * */
    public Client(String route) {
        this.host += route;
    }
    /**
     * <p>
     *     Attach the request method to send
     *     Accept 4 request's methods including: GET, POST, PUT, DELETE
     * </p>
     * Example: setMethod("GET")
     * @param requestMethod as String to set the request method.
     * @return this
     * */
    public Client setMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }
    /**
     * <p>
     *     Attach which params to send to server <br>
     *     If you use GET method, you don't need to set params
     * </p>
     * @param params as HashMap<String, String>
     * @return this to change the next options
     * */
    public Client setParams(HashMap<String, String> params) {
        StringBuilder result  = new StringBuilder();

        if (params != null) {
            for (Map.Entry<String, String> element : params.entrySet()) {
                result.append(element.getKey());
                result.append("=");
                result.append(URLEncoder.encode(element.getValue(), StandardCharsets.UTF_8));
                result.append("&");
            }
        }

        //Cut the last redundant character
        encodeString = (result.length() > 1)
                ? result.substring(0, result.length() -1)
                : result.toString();

        return this;
    }

    /**
     * <p>
     *     Send built request to back-end server.
     * </p>
     * @return this to complete the request and send to web-server, then return the next chain for mapping purpose
     * */
    public Client sendRequest() {
        try {
            //Open HTTP's connection
            URL localhost = new URL(host + "?" + encodeString);
            HttpURLConnection connection = (HttpURLConnection) localhost.openConnection();

            //Set request' headers
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent", "Java-Client");
            connection.setRequestProperty("Accept", "UTF-8");
            connection.setDoOutput(true);

            //Read the response from server
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            this.response = response.toString();
            return this;
        } catch (IOException exception) {
            return null;
        }
    }

    /**
     * <p>
     *     Mapping the response from server to as a List<Type>. <br>
     * </p>
     * @param typeOfT type of the class included in List
     * @return as List<Type>
     * */
    public <T> T mappingTo(Type typeOfT) {
        Gson gson = new Gson();
        return gson.fromJson(response, typeOfT);
    }
    /**
     * <p>
     *     Mapping the response from server to as a class. <br>
     * </p>
     * @param classOfT type of the class
     * @return as List<Type>
     * */
    public <T> T mappingTo(Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(response, classOfT);
    }
}
