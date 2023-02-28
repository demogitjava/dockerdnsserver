package de.jgsoftwares.dnsserver.controller;

import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface iCtrlDnsReverse {
    
   
    
    @PostMapping("/reverse")
    String addreverserecord(MDNS mdns, HttpServletRequest request, HttpServletResponse response);
}
