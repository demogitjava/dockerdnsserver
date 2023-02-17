/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.jgsoftwares.dnsserver.service;

import de.jgsoftwares.dnsserver.dao.DaoDnsEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SBindReverse implements iSBindReververse {
    
    
     @Autowired
    de.jgsoftwares.dnsserver.dao.DaoDnsEntry daoentry;

    public DaoDnsEntry getDaoentry() {
        return daoentry;
    }

    public void setDaoentry(DaoDnsEntry daoentry) {
        this.daoentry = daoentry;
    }
     
     
}
