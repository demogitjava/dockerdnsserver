/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package de.jgsoftwares.dnsserver.dao;

import java.util.List;
import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


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
    
    public void createnamedconflocal();
    
    public Integer getdnscount();
    public void saveforward(MDNS mdns);
    public void savereverse(MDNS mdns);
    
}
