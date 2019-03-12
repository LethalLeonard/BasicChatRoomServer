package com.LayOff.ChatServer.Network.ServerListeners;

import com.LayOff.ChatServer.Client.Client;
import com.LayOff.ChatServer.Game.World.WorldInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMessageListener
{
    public static void init(Socket socket)
    {
        boolean exit = false;
        while(!exit)
        {
            String recievedMessage;
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                if((recievedMessage = in.readLine()) != null)
                {
                    System.out.println(recievedMessage);

                    for(Client others : WorldInfo.getClients())
                    {
                        if(others != null)
                        {
                            if (others.getOutput() != socket.getOutputStream())
                            {
                                PrintWriter repeatToOthers = new PrintWriter(others.getOutput(), true);
                                repeatToOthers.println(recievedMessage);
                            }
                        }
                    }
                }
            }catch(Exception e){
                exit = true;
            }
        }
    }
}
