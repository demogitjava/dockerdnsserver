package de.jgsoftwares.dnsserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
public interface iCtrlIndex 
{
 
    
    

    /**
     *
     * load default landingpage
     * de
     * @return 
     */
    @GetMapping({"/", "index"})
    public ModelAndView index();
    
    
}
