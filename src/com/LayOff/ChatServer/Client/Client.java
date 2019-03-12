package com.LayOff.ChatServer.Client;

import com.LayOff.ChatServer.Network.ServerListeners.ClientMessageListener;
import com.LayOff.ChatServer.Utilities.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client
{
    private static final ExecutorService serviceLoader = Executors.newFixedThreadPool(2);

    private static String IP;
    private static String playerName;
    private static int privilege;

    private InputStream input;
    private OutputStream output;
    private Socket socket;

    public Socket getSocket()
    {
        return socket;
    }

    public InputStream getInput()
    {
        return input;
    }

    public OutputStream getOutput()
    {
        return output;
    }

    public Client (Socket socket)
    {
        try
        {
            this.input = socket.getInputStream();
            this.output = socket.getOutputStream();
            this.socket = socket;
            serviceLoader.execute(() -> ClientMessageListener.init(socket));
        }catch(IOException e){
            Logger.logException(e);
            Logger.logError("Error creating client object.");
        }
    }
}
