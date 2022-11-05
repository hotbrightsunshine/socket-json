package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Listener implements Runnable {

    Socket socket;
    BufferedReader reader;
    DataOutputStream writer;
    ObjectMapper mapper;

    public Listener(Socket s) throws IOException{
        socket = s;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new DataOutputStream(socket.getOutputStream());
        mapper = new ObjectMapper();
    }

    @Override
    public void run() {
        while(true){
            try {
                // Legge dal flusso
                String str = reader.readLine();
                Message parsed = mapper.readValue(str,Message.class);
                System.out.println("RECEIVED: " + parsed.toString());

            } catch (IOException e) { 
                break;
            }
        }
    }
    
}
