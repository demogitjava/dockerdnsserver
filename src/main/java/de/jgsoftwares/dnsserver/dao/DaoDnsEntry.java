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
 
    List<MDNS> ldnsentry;  
    
      // demodb
    @Autowired
    @Qualifier(value = "defaultJdbcTemplate")
    JdbcTemplate jtm;
    
   
      
    
    @Override
    public List<MDNS> getdnsentrys()
    {
        ldnsentry = jtm.query("select * from dns", new BeanPropertyRowMapper(MDNS.class));

        return ldnsentry;
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
   
            // get all dns entrys
            if(ldnsentry == null)
            {
                getdnsentrys();
            }
            
            
            
        
        
            // /etc/bind/named.conf.local
            FileWriter fw;
            try {
            BufferedWriter bw = null;
            
            File file = new File("/etc/bind/named.conf.local");
        
        
            String stnamedconflocal = new String(";namedconflocal" + "\n" + "\n");
            
            
            //  writer.append(' ');
            //
            String stoptnamedconf = new String("");
        
            fw = new FileWriter(file);
            
            bw = new BufferedWriter(fw);
            
            bw.write (stnamedconflocal);  // check that file is gernated 
                                              // ;namedconflocal
            bw.write(stoptnamedconf);  
            
            bw.close();
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
    public Integer getdnscount()
    {

        Integer dnscount = jtm.queryForObject("SELECT COUNT(*) from dns", Integer.class);
        return dnscount;
    }

    
    
    
}
