package de.jgsoftwares.dnsserver.controller;

import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.web.bind.annotation.PostMapping;
import de.jgsoftwares.dnsserver.service.iSBindReververse;
import org.springframework.beans.factory.annotation.Autowired;

public interface iCtrlDnsReverse {
    
   
    
    @PostMapping("/reverse")
    void addreverserecord(MDNS mdns);
}
