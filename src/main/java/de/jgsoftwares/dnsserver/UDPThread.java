package de.jgsoftwares.dnsserver;

import java.net.*;
import java.io.*;

/**
 * The threads for responding to UDP requests
 *
 * @author Steve Beaty
 * @version $Id: UDPThread.java,v 1.3 2004/09/14 21:23:43 drb80 Exp $
 */
public class UDPThread extends Thread
{
    /*
    ** is responding through the original socket thread safe, or should we
    ** create another one here?
    */
    private DatagramSocket socket;
    private DatagramPacket packet;
    // Java can't synchronize around base types.  sigh.
    private static Object mutex = new Object();
    private static int count = 0;

    /**
     * @param socket	the socket to respond through
     * @param packet	the query
     */
    public UDPThread (DatagramSocket socket, DatagramPacket packet)
    {
        this.socket = socket;
	this.packet = packet;

        // System.out.println ("P: count = " + count);

	// wait here if there are too many threads running
	synchronized (mutex)
	{
	     while (count > JDNSS.getThreads())
	     {
                 // System.out.println ("thread blocked: " + getName());

	         try { mutex.wait(); }
	         catch (Exception e) {}
	     }
             // System.out.println ("thread unblocked: " + getName());
	     count++;
	}
    }

    /**
     * make the response
     */
    public void run()
    {
	byte buf[] = new byte[packet.getLength()];
	System.arraycopy (packet.getData(), 0, buf, 0, buf.length);

	// sleep for 10 secs for testing of threads
	// System.out.println ("before sleep");
	// try { Thread.sleep (10000); }
	// catch (InterruptedException e) { e.printStackTrace(); }
	// System.out.println ("after sleep");

	Query q = new Query (buf);	// System.out.println (q);
	Response r = new Response (q);	// System.out.println (r);
	byte b[] = r.makeResponse(q);

	if (b != null)
	{
	    DatagramPacket reply = new DatagramPacket(b, b.length,
		packet.getAddress(), packet.getPort());

	    try { socket.send(reply); }
	    catch ( IOException e ) { e.printStackTrace(); }

	    synchronized (mutex)
	    {
	        count--;
                // System.out.println ("V: count = " + count);
	        if (count > 0) mutex.notify();
	    }
	}
    }
}
