/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.jgsoftwares.dnsserver.controller;

import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.stereotype.Controller;

import de.jgsoftwares.dnsserver.service.SBindReverse;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class CtrlDnsReverse implements iCtrlDnsReverse
{
    @Autowired
    SBindReverse ibindreverse;
    
    /**
     *
     * @param mdns
     */
    @Override
   public void addreverserecord(MDNS mdns)
   {   
       ibindreverse.getDaoentry().savereverse(mdns);
   }
    
}
