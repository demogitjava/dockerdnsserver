/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.jgsoftwares.dnsserver.dao;

import de.jgsoftwares.dnsserver.model.MDNS;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.runner.Version.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DaoDnsEntry implements iDaoDnsEntry
{
 
     
    
      // demodb
    @Autowired
    @Qualifier(value = "defaultJdbcTemplate")
    JdbcTemplate jtm;
    
   
   
      
    public DaoDnsEntry()
    {
        
    }
    
    @Override
    public List<MDNS> getdnsentrys()
    {
        List<MDNS> ldnsentry = jtm.query("select * from dns", new BeanPropertyRowMapper(MDNS.class));
        return ldnsentry;
    }
    
    
    @Override
    public List<MDNS> getforwarddnsentry()
    {
        
        List<MDNS> forwardlistdns = jtm.query("select * from dns", new BeanPropertyRowMapper(MDNS.class));
        return forwardlistdns;
    }
    
    
    
    /*
        write File to 
        /etc/bind
    
        File name 
        named.conf.options
    */
    @Override
    public void createnamedconfoptions()
    {
       
        FileWriter fw;
        try {
            BufferedWriter bw = null;
            
            File file = new File("/etc/bind/named.conf.options");
        
        
            String stcommend = new String(";namedconfoptions" + "\n" + "\n");
            
            String stoptnamedconf = new String("options {\n" +
                "        directory \"/etc/bind\";\n" +
                "        recursion no;\n" +
                "        listen-on port 53 { localhost; 192.168.178.0/24; };\n" +
                "        allow-recursion { none; };\n" +
                "        allow-query { localhost; 192.168.178.0/24; };\n" +
                "        allow-query-cache { any; };\n" +
                "        \n" +
                "        forwarders {\n" +
                "                8.8.8.8;\n" +
                "                8.8.4.4;\n" +
                "        };\n" +
                "};");
        
            fw = new FileWriter(file);
            
            bw = new BufferedWriter(fw);
            
            bw.write (stcommend);  // check that file is gernated 
                                       // ;namedconfoptions
            bw.write(stoptnamedconf);  
            
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(DaoDnsEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
	
        
    }
    
    /**
     * named.conf.local
     */
    @Override
    public void createnamedconflocal()
    {
   
           List<MDNS> getdnsentry = null;
           if(getdnsentry == null)
           {
                getdnsentry = (List<MDNS>)getforwarddnsentry();
           }
           
          
            // static wan ip
            String stindernetip = null;
            if(stindernetip == null)
            {
                stindernetip = "127.0.0.1";
            }
            // fqdn of your email server
            // mail.demogitjava.de
            String stdomainmailserver = null;
            if(stdomainmailserver == null)
            {
                stdomainmailserver = "mail.localhost.de";
            }
            
            
            // fqdn of your internet address
            // demgitjava.de
            String stdomain = null;
            if(stdomain == null)
            {
                stdomain = "localhost";
            }
        
            // /etc/bind/named.conf.local
            FileWriter fw;
            try {
            BufferedWriter bw = null;
            
            File file = new File("/etc/bind/named.conf.local");
        
        
            String stnamedconflocal = new String(";namedconflocal" + "\n" + "\n");
            
            String sttl = new String("$TTL  86400   ; default TTL for this zone (this 1 day)" + "\n");  // TTL
            String storgin = new String("$ORIGIN " + stdomain +"." + "\n");  // base domain name
            
           
            String stsoa = new String(
                    "@        IN      SOA     " + stdomain + "." + "(" + "\n" +
                    "                           2019011502" + "\n" + // Serial number
                    "                               604800" + "\n" + // Refesh
                    "                                86400" + "\n" + // Retry
                    "                              2419200" + "\n" + // Expire
                    "                                86400" + ")" + "\n");
                 
            String stnameserver = new String(""
                    + "         IN      NS          stdoamin" + "." + "\n"); // nameserver
            
            String stmailserver = new String("         IN      MX          " + stdomainmailserver + "." + "\n");
            
            
            String starecords = new String(""
                    + "www      IN      A           " + stindernetip + "\n" +
                      "         IN      CNAME   " + "    demogitjava.de" + "\n");
            
            
            //  writer.append(' ');
            //
            String stoptnamedconf = new String("");
        
            fw = new FileWriter(file);
            
            bw = new BufferedWriter(fw);
             
            bw.write (stnamedconflocal);  // check that file is gernated 
            bw.write(sttl);
            bw.write(storgin);// ;namedconflocal
            bw.write(stsoa);
            bw.write(stnameserver);
            bw.write(stmailserver);
            bw.write(starecords);
           
                                              
                                              
            bw.write(stoptnamedconf);  
            
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(DaoDnsEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public void saveforward(MDNS mdns)
    {
      
       
      

        Integer dnscount = (Integer) getdnscount();
        if(dnscount == 0)
        {
             dnscount = Integer.valueOf(1);
             mdns.setId(dnscount);
            
        }
        else
        {
            dnscount = getdnscount() + 1;
            mdns.setId(dnscount);
        }
        
        
       
        jtm.update("insert into dns " +
                        "(id, forwarddns, reversedns, dnszone, dnstype) " +
                        "values (?, ?, ?, ?, ?)",
                mdns.getId(),
                mdns.getForwarddns(),
                mdns.getReversedns(),
                mdns.getDnszone(),
                mdns.getDnstype());
    
  
    }
    
    
    @Override
    public void savereverse(MDNS mdns)
    {
        Integer dnscount = (Integer) getdnscount();
        if(dnscount == 0)
        {
             dnscount = Integer.valueOf(1);
             mdns.setId(dnscount);
            
        }
        else
        {
            dnscount = getdnscount() + 1;
            mdns.setId(dnscount);
        }
        
        
       
        jtm.update("insert into dns " +
                        "(id, forwarddns, reversedns, dnszone, dnstype) " +
                        "values (?, ?, ?, ?, ?)",
                mdns.getId(),
                mdns.getForwarddns(),
                mdns.getReversedns(),
                mdns.getDnszone(),
                mdns.getDnstype());
    
  
    }
    
    @Override
    public Integer getdnscount()
    {

        Integer dnscount = jtm.queryForObject("SELECT COUNT(*) from dns", Integer.class);
        return dnscount;
    }

    
    
    
}
