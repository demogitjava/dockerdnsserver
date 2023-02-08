/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.jgsoftwares.dnsserver;

import org.h2.tools.Server;

/**
 *
 * @author root
 */
public class startH2Server 
{
    
     // start h2 database server
    public static void startH2Server()
    {
        try
        {
            org.h2.tools.Server h2Server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
            //org.h2.tools.Server h2Server = Server.createTcpServer().start();
            if (h2Server.isRunning(true))
            {
                System.out.print("H2 server was started and is running." + "\n");
            } else
            {
                h2Server = Server.createTcpServer().start();
                throw new RuntimeException("Could not start H2 server." + "\n");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to start H2 server: " + e + "\n");
        }

    }
    
}
