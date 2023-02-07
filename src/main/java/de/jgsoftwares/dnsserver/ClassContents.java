package de.jgsoftwares.dnsserver;

import java.io.*;
import java.lang.reflect.*;

public class ClassContents
{
	public static void main (String[] args)
	{
		try
		{
			Class c = Class.forName (args[0]);
			System.out.println (c);
			System.out.println ("----- Fields -----");
			printMembers (c.getFields());
			System.out.println ("----- Constructors -----");
			printMembers (c.getConstructors());
			System.out.println ("----- Methods -----");
			printMembers (c.getMethods());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println ("unknown class: " + args[0]);
		}
	}

	private static void printMembers (Member[] mems)
	{
		for (int i=0; i < mems.length; i++)
		{
			if (mems[i].getDeclaringClass() == Object.class)
				continue;
			String decl = mems[i].toString();
			System.out.println ("    " + decl);
		}
	}
}

