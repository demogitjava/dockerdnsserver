/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package de.jgsoftwares.dnsserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.stereotype.Repository;



@Repository
public interface JPADNS extends JpaRepository<MDNS, Integer>  {
    
}
