package de.jgsoftwares.dnsserver;

import java.net.*;
import java.io.*;

/**
 * Listen to a TCP port for DNS requests...
 * @author Beaty
 * @version $Id: TCP.java,v 1.2 2004/09/14 21:23:43 drb80 Exp $
 */

// nslookup -port=5353 -vc www.mpcs.org localhost

public class TCP extends Thread
{   
   /**
    * listen on the given port and spawn a thread for each request.
    */    
    public void run()
    {
        ServerSocket server = null;

        try { server = new ServerSocket (JDNSS.getPort()); }
        catch (IOException e) { e.printStackTrace(); }

        while (true)
        {
            Socket socket = null;

            try
            {
                socket = server.accept();
                // System.out.println ("----------");
                // System.out.println ("TCP");
                // System.out.println ("From address = " + socket.getInetAddress());
                // System.out.println ("From port = " + socket.getPort());
                // System.out.println ("----------");

		new TCPThread (socket).start();
            }
            catch (IOException e) { e.printStackTrace(); }
        }
    }
}
