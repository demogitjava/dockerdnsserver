package de.jgsoftwares.dnsserver;

/* MC.java */
import java.net.*;
import java.io.*;

/** This class has an open socket on port 5353 to listen for Multicast DNS packets.
 * @author Beaty
 * @version $Id: MC.java,v 1.1.1.1 2004/06/30 18:42:22 drb80 Exp $
 */
public class MC extends Thread
{
    /** This method uses the MutliCastSocket class.  Upon receipt makes a new 
     * thread for each Query. 
     */    
    public void run()
    {
        MulticastSocket server = null;
        try
        {
            server = new MulticastSocket(5353);
        }
        catch ( IOException e ) {}

        InetAddress group = null;
        try
        {
            group = InetAddress.getByName ("224.0.0.251");
        }
        catch (UnknownHostException e) {}
        
        try
        {
            server.joinGroup (group);
        }
        catch ( IOException e ) {}
        
        while (true)
        {
            // make a new buffer for each query so this is thread-safe.
            // remember: arrays are passed by reference...

            byte[] buffer = new byte[512];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try
            {
                server.receive(packet);
            }
            catch ( IOException e ) {}

            System.out.println ("----------");
            System.out.println ("MC");
            System.out.println ("Packet length = " + packet.getLength());
            System.out.println ("From address = " + packet.getAddress());
            System.out.println ("From port = " + packet.getPort());

            Query q = new Query (packet.getData());
            System.out.print (q);

            System.out.println ("----------");
        }

        // server.leaveGroup (group);
        // server.close ();
    }
}
