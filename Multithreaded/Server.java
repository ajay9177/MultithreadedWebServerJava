package Multithreaded;

import java.net.ServerSocket;

public class Server {
    public static void main(String[] args){
        int port=8010;
        try{
            ServerSocket serverSocket=new ServerSocket(port);
            //serverSocket.getSoTimeout(10000);
        }catch(Exception ex){

        }
    }
}
