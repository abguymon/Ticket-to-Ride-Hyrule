package cs240.lib.communicator;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cs240.lib.common.Command;
import cs240.lib.common.EncoderDecoder;
import cs240.lib.common.ResultTransferObject;

/**
 * Created by David on 1/13/2018.
 */

public class ClientCommunicator {
    //Singleton
    public static ClientCommunicator SINGLETON = new ClientCommunicator();
    //Static variables
    private static Gson gson = new Gson();
    private String authToken;
    private String serverHost = "localhost";
    private String serverPort = "8080";


    //Constructors
    public ClientCommunicator() {}

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    //Commands
    public Object send(Command command) {
        HttpURLConnection connection = openConnection(ServerCommunicator.COMMAND_DESIGNATOR);
        sendToServerCommunicator(connection, command);
        Object result = getResult(connection);

        return result;
    }

    private HttpURLConnection openConnection(String contextIdentifier) {
        HttpURLConnection result = null;
        try {
            String urlPrefix = "http://" + serverHost + ":" + serverPort;
            URL url = new URL(urlPrefix + contextIdentifier);
            result = (HttpURLConnection)url.openConnection();
            result.setRequestMethod(HTTP_POST);
            result.addRequestProperty("Authorization", authToken);
            result.setDoOutput(true);
            result.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void sendToServerCommunicator(HttpURLConnection connection, Command command) {
        try (OutputStreamWriter outputStreamWriter =
                     new OutputStreamWriter(connection.getOutputStream())){
            //Encoding in JSON
            gson.toJson(command, outputStreamWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object getResult(HttpURLConnection connection) {
        Object result = null;
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //0 means the body response length == 0; it was empty
                if(connection.getContentLength() == 0) {
                    System.out.println("Response body was empty");
                } else if(connection.getContentLength() == -1) {
                    //-1 means the body was not empty but has an unknown about of information
                    try (InputStreamReader inputStreamReader =
                                 new InputStreamReader(connection.getInputStream()))
                    {
                        ResultTransferObject transferObject =
                                (ResultTransferObject)gson.fromJson(inputStreamReader,
                                        ResultTransferObject.class);
                        result = transferObject.getResult();
                    }
                }
            } else {
                throw new Exception(String.format("http code %d",	connection.getResponseCode()));
            }
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            result = e;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //Auxiliary Constants, Attributes, and Methods
    //private static final String URL_PREFIX = "http://" + ServerCommunicator.SERVER_HOST + ":" + ServerCommunicator.getServerPortNumber();
    private static final String HTTP_POST = "POST";
}