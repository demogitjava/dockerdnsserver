package de.jgsoftwares.dnsserver;

import java.net.*;
import java.io.*;
import java.util.Hashtable;

/**
 * The main driver for JDNSS 
 *
 * @author Steve Beaty 
 * @version $Id: JDNSS.java,v 1.6 2004/07/22 05:11:16 drb80 Exp $
 */

public class JDNSS
{   
    private static Hashtable Zones;
    private static int port = 53;
    private static int threads = 10;
    private static String version = "1.0";

    /** 
     * Finds the Zone associated with the domain name passed in
     *
     * @param name	the name of the domain to find
     * @return		the associated Zone
     * @see Zone
     */
    public static Zone getZone (String name)
    { return ((Zone)Zones.get (name)); }

    /** 
     * Returns the number of threads to allow.
     *
     * @return		the number of threads
     */
    public static int getThreads() { return (threads); }

    /** 
     * Returns the port number to listen to.
     *
     * @return		the port number
     */
    public static int getPort() { return (port); }

    /** 
     * Returns the JDNSS version number
     *
     * @return		the version number
     */
    public static String getVersion() { return (version); }

    /**
     * The main driver for the server; creates threads for TCP and UDP.
     *
     * @param args	-p #, -t #, -v
     */
    public static void main(String[] args) // throws IOException
    {   
        Zones = new Hashtable();

	/*
        ** this is a guess; i don't know if this is a good way to implement
        ** the PTR records, and i don't know if these are good default
        ** values...
	*/
        Zones.put ("in-addr.arpa",
                new Zone ("in-addr.arpa", "", 1, 28800, 7200, 604800, 86400));
        Zones.put ("ip6.int",
                new Zone ("ip6.int", "", 1, 28800, 7200, 604800, 86400));

        for (int i = 0; i < args.length; i++)
        {
	    if (args[i].charAt(0) == '-')
	    {
		switch (args[i].charAt(1))
		{
		    case 'p' : port = Integer.parseInt (args[++i]); break;
		    case 't' : threads = Integer.parseInt (args[++i]); break;
		    case 'v' :
			System.out.println ("JDNSS version " + getVersion());
			break;
		    default :
			System.out.println ("Ignoring flag: " + args[i]);
			break;
		}
		continue;
	    }

	    try
	    {
                String name = new File (args[i]).getName();
                Parser parse = new Parser (new FileInputStream (args[i]));
                Zone z = parse.parseIt (name);	// System.out.println (z);
                Zones.put (name, z);
	    }
	    catch (FileNotFoundException e)
	    {
		System.err.print ("Couldn't open file " + args[i]);
		System.err.println (" : " + e);
	    }
        }

	if (Zones.size() == 2)
	{
	    System.err.println ("No zone files");
	    System.exit (1);
	}

        new TCP().start();
        new UDP().start();
        // new MC().start();
    }
}
