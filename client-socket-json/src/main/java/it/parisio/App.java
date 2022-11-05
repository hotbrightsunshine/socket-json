package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App 
{   
    static Listener l;
    static ObjectMapper om;
    static BufferedReader reader;
    static DataOutputStream writer;

    public static void main( String[] args ) throws UnknownHostException, IOException
    {
        om = new ObjectMapper();
        Socket socket = new Socket("localhost", 12321);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new DataOutputStream(socket.getOutputStream());

        l = new Listener(socket);
        Thread t = new Thread(l);
        t.start();

        while(true){
            System.out.print("Scrivi un messaggio: ");
            Scanner scan = new Scanner(System.in);
            String str = scan.nextLine();

            Message msg = new Message(str);
            String toSend = om.writeValueAsString(msg);
            
            writer.writeBytes(toSend);
        }
    }
}
