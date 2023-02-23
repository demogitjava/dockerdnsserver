/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package de.jgsoftwares.dnsserver.service;

import de.jgsoftwares.dnsserver.dao.DaoDnsEntry;

/**
 *
 * @author root
 */
public interface iSIndex {
    public DaoDnsEntry getDaodns();
    public void setDaodns(DaoDnsEntry daodns);
}
