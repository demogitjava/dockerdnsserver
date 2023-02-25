package de.jgsoftwares.dnsserver.service;

import de.jgsoftwares.dnsserver.dao.DaoDnsEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jgsoftwares.dnsserver.dao.iJPADaoDNS;



@Service
public class SIndex implements iSIndex
{
    @Autowired
    DaoDnsEntry daodns;

    
    @Autowired
    iJPADaoDNS jpacrudao;

    
    @Override
    public iJPADaoDNS getJpacrudao() {
        return jpacrudao;
    }

    @Override
    public void setJpacrudao(iJPADaoDNS jpacrudao) {
        this.jpacrudao = jpacrudao;
    }
    
    
    
    
    /**
     *
     * @return
     */
    @Override
    public DaoDnsEntry getDaodns() {
        return daodns;
    }

    /**
     *
     * @param daodns
     */
    @Override
    public void setDaodns(DaoDnsEntry daodns) {
        this.daodns = daodns;
    }

    
    
}
