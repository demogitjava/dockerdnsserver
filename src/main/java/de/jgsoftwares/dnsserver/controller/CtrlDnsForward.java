/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.jgsoftwares.dnsserver.controller;

import de.jgsoftwares.dnsserver.model.MDNS;
import de.jgsoftwares.dnsserver.service.iSBindForward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlDnsForward implements iCtrlDnsForward
{
    
    
   @Autowired
   iSBindForward ibindforward;
  
  
    
   @Override
   public void addforwardrecord(MDNS mdns)
   {
     
       
       ibindforward.getDaoentry().saveforward(mdns);
       
   }
}
