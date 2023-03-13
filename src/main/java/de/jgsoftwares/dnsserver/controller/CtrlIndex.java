package de.jgsoftwares.dnsserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.jgsoftwares.dnsserver.service.iSIndex;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author hoscho
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CtrlIndex implements iCtrlIndex
{
    
    @Autowired
    iSIndex indexservice;
    
    ModelAndView mv;
    
    Long forwarddnscount;
    Long reversednscount;
    
    @Override
    public ModelAndView index() {
        
       
        mv = new ModelAndView("index");
       
        if(forwarddnscount == null)
        {
            forwarddnscount = Long.valueOf(0);
        }

        // long.class count forwarddns 
        forwarddnscount = indexservice.getDaodns().getForwarddnsCount();
       
        mv.addObject("forwarddnscount", forwarddnscount);
        
     
        // long.class count reversedns
        reversednscount = indexservice.getDaodns().getReversednsCount();
        
        mv.addObject("reversednscount", reversednscount);

        
        
        /*
        
            get all entrys form 
            table dns in h2 database
        
        */
        mv.addObject("mdnsentrys", indexservice.getDaodns().findAllEntry());
        
       
        return mv;
    }
}
