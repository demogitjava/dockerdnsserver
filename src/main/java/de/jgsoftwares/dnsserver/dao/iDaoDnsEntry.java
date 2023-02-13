/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package de.jgsoftwares.dnsserver.dao;

import java.util.List;
import de.jgsoftwares.dnsserver.model.MDNS;

public interface iDaoDnsEntry 
{

    /**
     *
     * all dns entrys
     * from table DNS
     * @return 
     */
    public List<MDNS> getdnsentrys();
    
    
    /*
        write file
        named.conf.options
    
        to path
        /etc/bind/
    */
    public void createnamedconfoptions();
}
