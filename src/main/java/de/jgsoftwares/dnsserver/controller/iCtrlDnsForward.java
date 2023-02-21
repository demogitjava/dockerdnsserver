package de.jgsoftwares.dnsserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import de.jgsoftwares.dnsserver.model.MDNS;
import java.util.List;

public interface iCtrlDnsForward 
{
    
    
    @PostMapping("/forward")
    void addforwardrecord(MDNS mdns);
    
    
    @GetMapping("/createfile")
    void createFile();
  
}
