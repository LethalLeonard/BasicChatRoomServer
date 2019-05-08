package com.LayOff.ChatServer.Network.ServerListeners;

import com.LayOff.ChatServer.Client.Client;
import com.LayOff.ChatServer.Game.World.WorldInfo;
import com.LayOff.ChatServer.Utilities.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMessageListener
{
    public static void init(Client client)
    {
        boolean exit = false;
        while(!exit)
        {
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInput()));
                int opcode = in.read();
                switch(opcode){
                    case 1: //used for setting a client name after connection
                        client.setPlayerName(in.readLine());
                        WorldInfo.sendServerMessage(2,"----- User "+ client.getPlayerName()+ " has joined -----");
                        break;
                    case 2: //used for receiving client messages
                        handleMessageFromClient(in.readLine(), client);
                        break;
                    default:
                        Logger.logError("Unknown OP Code " + opcode + " from " + client.getPlayerName());
                }
            }catch(Exception e){
                exit = true;
            }
        }
    }

    private static void handleMessageFromClient(String message, Client client) {

        if(message != null)
        {
            System.out.println(client.getPlayerName() + ": " + message);

            for(Client others : WorldInfo.getClients())
            {
                if(others != null)
                {
                    if (others.getOutput() != client.getOutput())
                    {
                        WorldInfo.sendMessage(others, client.getPlayerName() + ": " + message);
                    }
                }
            }
        }
    }
}
