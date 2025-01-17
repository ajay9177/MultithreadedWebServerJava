package Singlethreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    public void run() throws IOException, UnknownHostException {
        int port = 8010; // PORT NUMBER THE SERVER WILL LISTEN ON
        ServerSocket socket = new ServerSocket(port); // INITIALIZE SERVER SOCKET ON THE GIVEN PORT
        socket.setSoTimeout(10000); // SET SOCKET TIMEOUT TO 10 SECONDS

        while (true) { // CONTINUOUSLY LISTEN FOR INCOMING CONNECTIONS
            try {
                System.out.println("Server is listening on port " + port); // LOG THAT THE SERVER IS LISTENING

                // ACCEPT AN INCOMING CLIENT CONNECTION
                Socket acceptedConnection = socket.accept(); 
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress()); // LOG CLIENT ADDRESS

                // INITIALIZE OUTPUT STREAM TO SEND DATA TO THE CLIENT
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());

                // INITIALIZE INPUT STREAM TO RECEIVE DATA FROM THE CLIENT
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                // SEND A GREETING MESSAGE TO THE CLIENT
                toClient.println("Hello from the server");
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            } catch (IOException ex) {
                ex.printStackTrace(); // PRINT EXCEPTION STACK TRACE IF AN ERROR OCCURS
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(); // CREATE A NEW SERVER INSTANCE
        try {
            server.run(); // RUN THE SERVER
        } catch (Exception ex) {
            ex.printStackTrace(); // HANDLE ANY EXCEPTION THROWN BY THE SERVER
        }
    }
}
