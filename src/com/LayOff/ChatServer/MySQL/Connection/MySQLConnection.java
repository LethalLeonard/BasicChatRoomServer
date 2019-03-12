package com.LayOff.ChatServer.MySQL.Connection;

import com.LayOff.ChatServer.Utilities.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection
{
    private static Connection connection;

    public MySQLConnection(String username, String password)
    {
        connection = connect(username, password);
    }

    //Used to start a connection with the server
    private static Connection connect(String user, String pass)
    {
        Connection con = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/somegamedb?useSSL=false", user, pass);
            Logger.logInfo("Connection successful.");

        }catch(Exception e){

            Logger.logException(e);
            Logger.logError("Failure connecting.");
        }

        return con;
    }

    public static Connection getConnection()
    {
        return connection;
    }
}
