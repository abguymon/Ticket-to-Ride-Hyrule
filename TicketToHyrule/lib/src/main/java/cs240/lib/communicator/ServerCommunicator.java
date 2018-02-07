package cs240.lib.communicator;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import cs240.lib.Model.Login;
import cs240.lib.common.Command;
import cs240.lib.common.ResultTransferObject;
import cs240.lib.server.ServerFacade;
import cs240.lib.server.Target;


/**
 * Created by David on 1/13/2018.
 */

public class ServerCommunicator {
    private static final int SERVER_PORT_NUMBER = 8080;
    private static final int MAX_WAITING_CONNECTIONS = 10;
    private static Gson gson = new Gson();

    private HttpServer server;

    private ServerCommunicator() {}

    private void run() {

        try {
            server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            System.out.println("Could not create HTTP server: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        server.setExecutor(null); // use the default executor

        server.createContext(DEFAULT_DESIGNATOR, defaultHandler);
        server.createContext(COMMAND_DESIGNATOR, commandHandler);


        server.start();
    }

    private HttpHandler defaultHandler = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("Doing nothing right now, replace with default code");
        }
    };

    private HttpHandler commandHandler = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Object result = null;
            try (InputStreamReader inputStreamReader =
                         new InputStreamReader(exchange.getRequestBody()))
            {
                Command command = new Command(inputStreamReader);
                if (!command.getMethodName().equals("login") && !command.getMethodName().equals("register")) {
                    String authToken = null;
                    Headers requestHeaders = exchange.getRequestHeaders();
                    if (requestHeaders.containsKey("Authorization")) {
                        authToken = exchange.getRequestHeaders().getFirst("Authorization");
                    }
                    if (authToken != null) {
                        Object[] parameters = command.getParameters();
                    /*if (command.getParameterTypeNames()[0].equals(String.class)) {*/
                        String username = (String) parameters[0];
                        boolean isValidAuthToken = Target.SINGLETON.isValidAuthToken(username, authToken);
                        if (isValidAuthToken) {
                            ServerFacade.SINGLETON.addCommand(command);
                            result = command.execute();
                        }
                    }
                }else {
                    ServerFacade.SINGLETON.addCommand(command);
                    result = command.execute();
                }
            }

            try (OutputStreamWriter outputStreamWriter =
                         new OutputStreamWriter(exchange.getResponseBody()))
            {
                //0 means there are some bytes in the response body but the number is unknown
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                ResultTransferObject resultTransferObject =
                        new ResultTransferObject(result.getClass().getName(), result);
                gson.toJson(resultTransferObject, outputStreamWriter);
                outputStreamWriter.close();
            }
        }
    };



    public static int getServerPortNumber() {
        return SERVER_PORT_NUMBER;
    }

    public static void main(String[] args) {
        new ServerCommunicator().run();
    }

    public static final String COMMAND_DESIGNATOR = "/command";
    public static final String DEFAULT_DESIGNATOR = "/";
    public static final String SERVER_HOST = "localhost";
}
