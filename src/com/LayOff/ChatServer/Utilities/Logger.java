package com.LayOff.ChatServer.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger
{
    //sets the time format for the filename and log output
    private static DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss");

    //sets the filename to be used for the session's log
    private static String fileName = "./log/" + fileFormat.format(LocalDateTime.now()) + ".log";

    //enum for the severity of what is being logged
    enum Level{
        DEBUG,
        FATAL,
        ERROR,
        WARN,
        INFO
    }

    //different logging methods for the different severities
    public static void logDebug(String msg) {output(Level.DEBUG, msg);}
    public static void logInfo(String msg) {output(Level.INFO, msg);}
    public static void logWarn(String msg) {output(Level.WARN, msg);}
    public static void logError(String msg) {output(Level.ERROR, msg);}
    public static void logFatal(String msg) {output(Level.FATAL, msg);}

    //used to intercept exceptions and output them to log file and console
    public static void logException(Exception e)
    {
        //ensures directory exists for logs to be output to
        File dir = new File("./log/");
        dir.mkdir();

        //sets the time format for the console
        DateTimeFormatter consoleFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        //gets the current time for the logging
        LocalDateTime ldt = LocalDateTime.now();

        try
        {
            //Readies file to be appended to
            FileWriter fw = new FileWriter(fileName, true);
            PrintWriter pw = new PrintWriter(fw);

            //outputs the exception to the file
            e.printStackTrace(pw);

            //closes file
            pw.close();
        }catch(Exception ex){
            e.printStackTrace();
        }

        //outputs the stacktrace to console
        e.printStackTrace();
    }


    private static void output(Level level, String msg)
    {
        //ensures directory exists for logs to be output to
        File dir = new File("./log/");
        dir.mkdir();

        //sets time format/gets current time
        DateTimeFormatter consoleFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();

        try
        {
            //readies the file to be appended to
            FileWriter fw = new FileWriter(fileName, true);
            PrintWriter pw = new PrintWriter(fw);

            //adds the logging to the file
            pw.println(String.format("[%s] [%s]: %s", fileFormat.format(ldt), level, msg));

            //closes file
            pw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //outputs the logging to the console
        System.out.println(String.format("[%s] [%s]: %s", consoleFormat.format(ldt), level, msg));
    }
}
