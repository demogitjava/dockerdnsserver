/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package de.jgsoftwares.dnsserver.controller;

import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.web.bind.annotation.PostMapping;
import de.jgsoftwares.dnsserver.service.iSBindReververse;
import org.springframework.beans.factory.annotation.Autowired;

public interface iCtrlDnsReverse {
    
   
    
    @PostMapping("/reverse")
    void addreverserecord(MDNS mdns);
}
