package de.jgsoftwares.dnsserver.controller;

import de.jgsoftwares.dnsserver.model.MDNS;
import de.jgsoftwares.dnsserver.service.iSBindForward;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hoscho
 */
@Controller
public class CtrlDnsForward implements iCtrlDnsForward
{
    
    
   @Autowired
   iSBindForward ibindforward;
  
   @Autowired
   iCtrlIndex ictlindex;
   
   HttpServletRequest request;
    
     /**
      *
      * @param mdns
      * @param request
      * @param response
      * @return 
     */
     @Override
     public String addforwardrecord(MDNS mdns, HttpServletRequest request, HttpServletResponse response)
     {   
       ibindforward.getDaoentry().saveforward(mdns);
       String redirectUrl = request.getPathInfo();
        if(redirectUrl == null)
        {
            redirectUrl = "index";
        }
        return "redirect:" + redirectUrl;
     }
   
    /**
     *
     * @param request  redirect to index.html
     * @param response
     * @return index MVC Controller
     */
    @Override
    public String createFile(HttpServletRequest request, HttpServletResponse response)
    {
        ibindforward.getDaoentry().createreversefile(); // create reverse file
        ibindforward.getDaoentry().createnamedconflocal(); // create forward file on path /etc/bind
        ibindforward.getDaoentry().createnamedconfoptions(); 
        String redirectUrl = request.getPathInfo();
        if(redirectUrl == null)
        {
            redirectUrl = "index";
        }
        return "redirect:" + redirectUrl;
    }
    
  
    
}
