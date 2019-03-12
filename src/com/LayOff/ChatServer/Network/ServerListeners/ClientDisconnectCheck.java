package com.LayOff.ChatServer.Network.ServerListeners;

import com.LayOff.ChatServer.Client.Client;
import com.LayOff.ChatServer.Game.World.WorldInfo;
import com.LayOff.ChatServer.Utilities.Logger;

import java.io.IOException;

public class ClientDisconnectCheck
{
    public static void init()
    {
        Client currentClient = null;
        while(true)
        {
            try
            {
                for (Client client : WorldInfo.getClients())
                {
                    if(client != null)
                    {
                        currentClient = client;
                        client.getOutput().write(1);
                    }
                }
                Thread.sleep(500);
            } catch (IOException e)
            {
                WorldInfo.removeClient(currentClient);
            } catch (InterruptedException e)
            {
                Logger.logException(e);
            }
        }
    }
}
