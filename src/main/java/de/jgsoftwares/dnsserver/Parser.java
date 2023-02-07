package de.jgsoftwares.dnsserver;

/**
* The parser for zone files
*
* @author Steve Beaty
* @version $Id: Parser.java,v 1.3 2004/09/14 20:59:55 drb80 Exp $
*/

import java.util.*;
import java.io.*;

// IPV6 addresses: http://www.faqs.org/rfcs/rfc1884.html
// IPV6 DNS: http://www.faqs.org/rfcs/rfc1886.html

public class Parser
{
    /*
    ** the range from 0 to 255 are used for query/response codes
    */

    private static final int EOF	= -1;
    private static final int NOTOK	= 256;
    private static final int IPV4ADDR	= 257;
    private static final int IPV6ADDR	= 258;
    private static final int AT		= 259;
    private static final int LCURLY	= 260;
    private static final int RCURLY	= 261;
    private static final int LPAREN	= 262;
    private static final int RPAREN	= 263;
    private static final int STRING	= 264;
    private static final int IN		= 265;
    private static final int FQDN	= 266;
    private static final int PQDN	= 267;
    private static final int INT	= 268;
    private static final int SEMI	= 269;
    private static final int INADDR	= 270;

    private int intValue;
    private String StringValue;

    private StreamTokenizer in;
    private Hashtable tokens;

    private Zone z;

    /**
     * The main parsing routine.
     *
     * @param in	where the information is coming from
     */
    public Parser (InputStream in)
    {
        /*
        ** set up the tokenizer
        */
        this.in = new StreamTokenizer (new InputStreamReader (in));
        this.in.commentChar (';');

        /*
        ** putting 0-9 into wordChars doesn't work unless one
        ** first makes them ordinary.  weird.
        */
        this.in.ordinaryChars ('0', '9');
        this.in.wordChars ('0', '9');
        this.in.wordChars ('.', '.');
        this.in.wordChars (':', ':');

        this.in.quoteChar ('"');

        this.in.slashSlashComments (true);
        this.in.slashStarComments (true);

        tokens = new Hashtable();
        tokens.put ("SOA", new Integer (Utils.SOA));
        tokens.put ("IN", new Integer (IN));
        tokens.put ("MX", new Integer (Utils.MX));
        tokens.put ("NS", new Integer (Utils.NS));
        tokens.put ("A", new Integer (Utils.A));
        tokens.put ("AAAA", new Integer (Utils.AAAA));
        tokens.put ("CNAME", new Integer (Utils.CNAME));
        tokens.put ("PTR", new Integer (Utils.PTR));
        tokens.put ("TXT", new Integer (Utils.TXT));
        tokens.put ("HINFO", new Integer (Utils.HINFO));
    }

    private int getNextToken ()
    {
        int t = NOTOK;

        try { t = in.nextToken (); }
        catch (IOException e)
        {
            System.err.println ("Error while reading token: " + e);
            System.exit (1);
        }

        // System.out.println ("t = " + t);

        switch (t)
        {
	    case '"' :
		StringValue = in.sval;
		return (STRING);
            case StreamTokenizer.TT_EOF:
                return (EOF);
            case StreamTokenizer.TT_NUMBER:
                Utils.Assert (false);
                return (NOTOK);
            case StreamTokenizer.TT_WORD:
	    {
		// System.out.println ("TT_WORD");
		String a = in.sval;
		// System.out.println ("a = " + a);

		/*
		** is it in the tokens hash?
		*/
		Integer i = (Integer) tokens.get (a);
		if (i != null)
		    return (i.intValue());

		if (a.matches ("(\\d+\\.){3}+\\d+"))
		{
		    StringValue = a;
		    return (IPV4ADDR);
		}

		/*
		** any number of hex digits separated by colons or
		** any number of hex digits separated by colons that
		** end in an IPv4 address
		*/
		if (a.matches ("(\\p{XDigit}*\\:)+\\p{XDigit}+") ||
		    a.matches ("(\\p{XDigit}*\\:)+(\\d+\\.){3}+\\d+"))
		{
		    StringValue = a;
		    return (IPV6ADDR);
		}

		if (a.matches ("\\d+"))
		{
		    intValue = Integer.parseInt (a);
		    return (INT);
		}

		// FQDN's end with a dot
		if (a.matches ("(\\w+\\.)+"))
		{
		    StringValue = a;
		    return (FQDN);
		}

		// PQDN's don't
		if (a.matches ("\\w+(\\.\\w+)*"))
		{
		    StringValue = a;
		    return (PQDN);
		}

		String b = a.toLowerCase();
		if (b.matches ("(\\d+\\.){4}+in-addr\\.arpa\\."))
		{
		    StringValue = b;
		    return (INADDR);
		}

		System.out.println ("a = " + a);
		Utils.Assert (false);
	    }
            case ';': return (SEMI);
            case '@': return (AT);
            case '{': return (LCURLY);
            case '}': return (RCURLY);
            case '(': return (LPAREN);
            case ')': return (RPAREN);
            default:
                System.out.println ("t = " + t);
                Utils.Assert (false);
                return (NOTOK);
        }
    }

    /**
     * Parse the zone
     *
     * @param name	the name of the zone to be created
     */
    public Zone parseIt (String name)
    {
        int serial, refresh, retry, expire, ttl;

        int t = getNextToken ();
        Utils.Assert (t == AT || t == PQDN || t == FQDN);

        t = getNextToken ();

        if (t == INT)
        {
            // System.out.println ("ttl =" + intValue);
            t = getNextToken ();
        }

        Utils.Assert (t == IN);

        t = getNextToken (); Utils.Assert (t == Utils.SOA);
        t = getNextToken (); Utils.Assert (t == AT || t == FQDN || t == PQDN);
	if (t == FQDN || t == PQDN) name = StringValue;
        t = getNextToken (); Utils.Assert (t == PQDN || t == FQDN);
	String contact = StringValue;
        t = getNextToken (); Utils.Assert (t == LPAREN);

        t = getNextToken (); Utils.Assert (t == INT); serial = intValue;
        t = getNextToken (); Utils.Assert (t == INT); refresh = intValue;
        t = getNextToken (); Utils.Assert (t == INT); retry = intValue;
        t = getNextToken (); Utils.Assert (t == INT); expire = intValue;
        t = getNextToken (); Utils.Assert (t == INT); ttl = intValue;

        t = getNextToken (); Utils.Assert (t == RPAREN);

        z = new Zone (name, contact, serial, refresh, retry, expire, ttl);

        RRs();

        return z;
    }

    private void RRs()
    {
        String current = "";

        int t = getNextToken();

        while (t != EOF)
        {
            // System.out.println ("t = " + t);
            if (t != IN)
            {
                Utils.Assert (t == FQDN || t == PQDN || t == INADDR);
                current = StringValue;
                t = getNextToken ();
            }

            Utils.Assert (t == IN);

            t = getNextToken ();
            switch (t)
            {
                case Utils.A:
                case Utils.AAAA:
                case Utils.CNAME:
                case Utils.NS:
                case Utils.TXT:
                    getNextToken ();
                    z.add (t, current, StringValue);
                    break;
                case Utils.HINFO:
		{
                    getNextToken ();
		    String s = StringValue;
                    getNextToken ();
		    // use a separator that can't occur...
		    s += "\u1FFF" + StringValue;
                    z.add (t, current, s);
                    break;
		}
                case Utils.MX:
                    getNextToken ();
                    getNextToken ();
                    String s = StringValue + " " + intValue;
                    z.add (Utils.MX, current, s);
                    break;
                case Utils.PTR:
                    getNextToken ();
                    Zone y = null;
                    if (current.endsWith ("in-addr.arpa."))
                        y = JDNSS.getZone ("in-addr.arpa");
                    else if (current.endsWith ("ip6.int."))
                        y = JDNSS.getZone ("ip6.int");
                    else
                        System.out.println ("unrecognized: " + current);
                    y.add (Utils.PTR, current, StringValue);
                    break;
                default:
                    System.out.println ("didn't recognize " + t);
                    break;
            }

            t = getNextToken ();
        }
    }

    /**
     * For testing -- creates an instance of Parser and
     * parses for the domain "mpcs.org"
     */
    public static void main (String args[])
    {
        Parser parse = new Parser (System.in);

        System.out.println (parse.parseIt ("mpcs.org"));
    }
}
