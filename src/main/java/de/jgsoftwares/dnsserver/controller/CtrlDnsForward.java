package de.jgsoftwares.dnsserver.controller;

import de.jgsoftwares.dnsserver.model.MDNS;
import de.jgsoftwares.dnsserver.service.iSBindForward;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CtrlDnsForward implements iCtrlDnsForward
{
    
    
   @Autowired
   iSBindForward ibindforward;
  
   @Autowired
   iCtrlIndex ictlindex;
   
   HttpServletRequest request;
    
   @Override
   public void addforwardrecord(MDNS mdns)
   {   
       ibindforward.getDaoentry().saveforward(mdns);
   }
   
    /**
     *
     * @param request
     * @param response
     * @return index MVC Controller
     */
    @Override
    public String createFile(HttpServletRequest request, HttpServletResponse response)
    {
        ibindforward.getDaoentry().createnamedconflocal();
        String redirectUrl = request.getPathInfo();
        if(redirectUrl == null)
        {
            redirectUrl = "index";
        }
        return "redirect:" + redirectUrl;
    }
    
    
    
}
