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
    
    
    java.util.List<MDNS> forwardlistdnscount;
    
    @Override
    public ModelAndView index() {
        
       
        mv = new ModelAndView("index");
        // mv.addObject("webtextcomp", iServiceIndex.getDcontroller().getPageLanguageText());
        
      
        Long forwarddnscount = indexservice.getDaodns().getForwarddnsCount();
        // long.class count forwarddns 
        mv.addObject("forwarddnscount", forwarddnscount);
        
        Long reversednscount = indexservice.getDaodns().getReversednsCount();
        mv.addObject("reversednscount", reversednscount);

        return mv;
    }
}
