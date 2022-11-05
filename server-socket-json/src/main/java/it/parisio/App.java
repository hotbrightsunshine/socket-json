package it.parisio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class App 
{
    public static ArrayList<Handler> handlers = new ArrayList<>();
    public static void main( String[] args ) throws IOException
    {
        // Apre la porta
       ServerSocket ss = new ServerSocket(12321);

       while(true){
        // Crea un nuovo Thread per ogni nuova connessione
        Socket s = ss.accept();
        System.out.println("Connection accepted.");
        Handler h = new Handler(s);
        new Thread(h).start();
        
        handlers.add(h);
       }
    }
}
