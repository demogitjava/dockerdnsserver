package de.jgsoftwares.dnsserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import de.jgsoftwares.dnsserver.model.MDNS;

public interface iCtrlDnsForward 
{
    
    
    @PostMapping("/forward")
    void addforwardrecord(MDNS mdns);
}
