package de.jgsoftwares.dnsserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.jgsoftwares.dnsserver.service.iSIndex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class CtrlIndex implements iCtrlIndex
{
    @Autowired
    iSIndex indexservice;
    
    ModelAndView mv;
    
    @Override
    public ModelAndView index() {
        
       
        mv = new ModelAndView("index");
        
        
        return mv;
    }
}
