package it.parisio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Handler implements Runnable {

    Socket socket;
    BufferedReader reader;
    DataOutputStream writer;
    ObjectMapper mapper;

    public Handler(Socket s) throws IOException{
        socket = s;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new DataOutputStream(socket.getOutputStream());
        mapper = new ObjectMapper();
    }

    @Override
    public void run() {
        while(true){
            String str = "";
            Message msg = null;
            try {
                // Input socket
                str = reader.readLine();
                System.out.println("Read " + str + ".");

                // JSONMapper prende l'oggetto JSON dalla Stringa
                msg = mapper.readValue(str, Message.class);

                // Manipolazione del messaggio
                msg.setContent(msg.getContent().toUpperCase());

                //Rimanda il dato indietro
                String toSend = mapper.writeValueAsString(msg);
                writer.writeBytes(toSend + "\n");
            } catch ( Exception e ) {
                System.out.println(e.getStackTrace());
            }
            
        }
    }
    
}
