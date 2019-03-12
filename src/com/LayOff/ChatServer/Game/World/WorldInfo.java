package com.LayOff.ChatServer.Game.World;

import com.LayOff.ChatServer.Client.Client;
import com.LayOff.ChatServer.Utilities.Logger;

import java.io.PrintWriter;
import java.net.Socket;

public class WorldInfo
{
    public static Client[] getClients()
    {
        return clients;
    }

    private static Client[] clients = new Client[1000];

    public static boolean newClient(Socket socket)
    {
        Client newClient = new Client(socket);
        Logger.logInfo("New connection requested.");
        for (int i = 0; i < clients.length; i++)
        {
            if (clients[i] == null)
            {
                clients[i] = newClient;
                Logger.logInfo("New connection successful.");
                return true;
            }
        }
        return false;
    }

    public static void removeClient(Client toRemove)
    {
        if(toRemove != null)
        {
            for (int i = 0; i < clients.length; i++)
            {
                if (toRemove == clients[i])
                {
                    clients[i] = null;
                    Logger.logDebug("Client closed.");
                }
            }
        }else{
            Logger.logWarn("Client that was attempted to be removed is null.");
        }
    }

    public static void sendServerMessage(String message)
    {
        for (Client client : clients)
        {
            if (client != null)
            {
                Logger.logDebug("NOT NULL");
                PrintWriter out = new PrintWriter(client.getOutput(), true);
                out.println(message);
            }
        }
    }
}
