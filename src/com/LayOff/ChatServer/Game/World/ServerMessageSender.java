package com.LayOff.ChatServer.Game.World;

import com.LayOff.ChatServer.Utilities.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerMessageSender
{
    public static void init()
    {
        try
        {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            while (true)
            {
                String toSend = stdIn.readLine();
                WorldInfo.sendServerMessage(2, "Server: " + toSend);
            }
        }catch(IOException e)
        {
            Logger.logError("CANT SEND NO MESSAGES");
        }
    }
}
