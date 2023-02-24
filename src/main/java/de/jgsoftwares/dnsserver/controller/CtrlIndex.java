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
    
    
    
    @Override
    public ModelAndView index() {
        
       
        mv = new ModelAndView("index");
       
       

        // long.class count forwarddns 
        Long forwarddnscount = indexservice.getDaodns().getForwarddnsCount();
        mv.addObject("forwarddnscount", forwarddnscount);
        
        // long.class count reversedns 
        Long reversednscount = indexservice.getDaodns().getReversednsCount();
        mv.addObject("reversednscount", reversednscount);

        return mv;
    }
}
