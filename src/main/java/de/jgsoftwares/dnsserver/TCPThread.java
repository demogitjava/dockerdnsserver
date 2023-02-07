package de.jgsoftwares.dnsserver;

import java.net.*;
import java.io.*;

/**
 * The threads for getting and responding to TCP requests
 *
 * @author Steve Beaty
 */
public class TCPThread extends Thread
{   
    private Socket socket;
    // Java can't synchronize around base types.  sigh.
    private static Object mutex = new Object();
    private static int count = 0;

    /**
     * @param socket	the socket to talk to
     */
    public TCPThread (Socket socket) {
        this.socket = socket;

	// wait here if there are too many threads running
	synchronized (mutex)
	{
	     while (count > JDNSS.getThreads())
	     {
	         try { mutex.wait(); }
	         catch (Exception e) {}
	     }
	    count++;
	}
    }

    public void run()
    {
        InputStream is = null;
        OutputStream os = null;

	try
	{
	    is = socket.getInputStream();
	    os = socket.getOutputStream();
	}
	catch (IOException e) { e.printStackTrace(); }

	// in TCP, the first two bytes signify the length of the request
	byte buffer[] = new byte[2];

	try { is.read (buffer, 0, 2); }
	catch (IOException e) { e.printStackTrace(); }

	byte query[] = new byte [Utils.addThem (buffer[0], buffer[1])];

	try { is.read (query); }
	catch (IOException e) { e.printStackTrace(); }

	Query q = new Query (query);
	Response r = new Response (q, false);
	byte b[] = r.makeResponse (q);

	int count = b.length;
	buffer[0] = Utils.getByte (count, 2);
	buffer[1] = Utils.getByte (count, 1);

	try { os.write (Utils.combine (buffer, b)); }
	catch (IOException e) { e.printStackTrace(); }

	try { is.close (); os.close(); socket.close (); }
	catch (IOException e) { e.printStackTrace(); }

	synchronized (mutex)
	{
	    count--;
	    if (count > 0) mutex.notify();
	}
    }
}
