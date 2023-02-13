/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.jgsoftwares.dnsserver.dao;

import de.jgsoftwares.dnsserver.model.MDNS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoDnsEntry implements iDaoDnsEntry 
{
    
    
    @Autowired
    @Qualifier(value = "defaultJdbcTemplate")
    JdbcTemplate jtm;
    
    @Override
    public List<MDNS> getdnsentrys()
    {
        List<MDNS> ldnsentry = jtm.query("select * from dns", new BeanPropertyRowMapper(MDNS.class));

        return ldnsentry;
    }
}
