package com.LayOff.ChatServer.Network.ServerListeners;

import com.LayOff.ChatServer.Game.World.WorldInfo;
import com.LayOff.ChatServer.Utilities.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnectionListener
{
    public static void init()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(25565);
            while(true)
            {
                Socket clientSocket = serverSocket.accept();
                WorldInfo.newClient(clientSocket);


            }

        }catch(IOException e){
            e.printStackTrace();
            Logger.logException(e);
            Logger.logFatal("Unable to open socket listener.");
        }
    }
}
