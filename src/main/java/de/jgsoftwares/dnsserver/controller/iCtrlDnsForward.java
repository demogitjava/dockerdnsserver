package de.jgsoftwares.dnsserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import de.jgsoftwares.dnsserver.model.MDNS;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public interface iCtrlDnsForward 
{
    
    
    @PostMapping("/forward")
    void addforwardrecord(MDNS mdns);
    
    
    @GetMapping("/createfile")
    String createFile(HttpServletRequest request, HttpServletResponse response);
    

    
   
  
}
