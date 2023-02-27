package de.jgsoftwares.dnsserver.controller;

import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.jgsoftwares.dnsserver.service.iSIndex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.awt.List;


@Controller

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
