package de.jgsoftwares.dnsserver;

/* UDP.java */
import java.net.*;
import java.io.*;

/**
 * Receive request packets from a UDP port and create a thread for each
 *
 * @author Steve Beaty
 * @version $Id: UDP.java,v 1.2 2004/09/14 21:23:43 drb80 Exp $
 */
public class UDP extends Thread
{
    public void run()
    {
        DatagramSocket server = null;
        
        try { server = new DatagramSocket(JDNSS.getPort()); }
        catch ( SocketException e )
        {
            e.printStackTrace();
            System.out.println (getName() + " exiting");
            return;
        }

        while (true)
        {
            // make a new buffer for each query so this is thread-safe.
            // remember: arrays are passed by reference...

            byte[] buffer = new byte[512];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try { server.receive(packet); }
            catch ( IOException e ) { e.printStackTrace(); }
            
            // System.out.println ("----------");
            // System.out.println ("UDP");
            // System.out.println ("Packet length = " + packet.getLength());
            // System.out.println ("From address = " + packet.getAddress());
            // System.out.println ("From port = " + packet.getPort());
            // System.out.println ("----------");

            new UDPThread (server, packet).start();
        }
    }
}
