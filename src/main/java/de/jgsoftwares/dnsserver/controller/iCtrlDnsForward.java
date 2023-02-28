package de.jgsoftwares.dnsserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import de.jgsoftwares.dnsserver.model.MDNS;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface iCtrlDnsForward 
{
    
    
    @PostMapping("/forward")
    String addforwardrecord(MDNS mdns, HttpServletRequest request, HttpServletResponse response);
    
    
    @GetMapping("/createfile")
    String createFile(HttpServletRequest request, HttpServletResponse response);
    

    
   
  
}
