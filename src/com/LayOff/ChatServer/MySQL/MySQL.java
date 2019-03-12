package com.LayOff.ChatServer.MySQL;

import com.LayOff.ChatServer.MySQL.Connection.MySQLConnection;

public class MySQL
{
    public static void init(String user, String pass)
    {
        new MySQLConnection(user,pass);
    }

}
