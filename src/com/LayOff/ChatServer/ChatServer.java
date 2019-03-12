package com.LayOff.ChatServer;

import com.LayOff.ChatServer.Game.World.ServerMessageSender;
import com.LayOff.ChatServer.Network.ServerListeners.ClientConnectionListener;
import com.LayOff.ChatServer.Network.ServerListeners.ClientDisconnectCheck;
import com.LayOff.ChatServer.Utilities.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer
{

    private static final ExecutorService serviceLoader = Executors.newFixedThreadPool(10);

    public static void main(String... args)
    {
        //MySQL.init(args[0], args[1]);
        try
        {
            serviceLoader.execute(() -> ClientConnectionListener.init());
            serviceLoader.execute(() -> ServerMessageSender.init());
            serviceLoader.execute(() -> ClientDisconnectCheck.init());

        }catch(Exception e){
            Logger.logError("Unable to start server");
            System.exit(1);
        }
    }
}