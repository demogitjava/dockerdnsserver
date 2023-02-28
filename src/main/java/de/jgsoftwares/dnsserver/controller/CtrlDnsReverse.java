package de.jgsoftwares.dnsserver.controller;

import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.stereotype.Controller;

import de.jgsoftwares.dnsserver.service.SBindReverse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author hoscho
 */
@Controller
public class CtrlDnsReverse implements iCtrlDnsReverse
{
    @Autowired
    SBindReverse ibindreverse;
    
    
    HttpServletRequest request;
      
    /**
     *
     * @param mdns
     * @param request
     * @param response
     * @return 
     */
    @Override
   public String addreverserecord(MDNS mdns, HttpServletRequest request, HttpServletResponse response)
   {   
       ibindreverse.getDaoentry().savereverse(mdns);
       String redirectUrl = request.getPathInfo();
        if(redirectUrl == null)
        {
            redirectUrl = "index";
        }
        return "redirect:" + redirectUrl;
   }
    
}
