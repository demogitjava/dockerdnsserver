package de.jgsoftwares.dnsserver.service;

import de.jgsoftwares.dnsserver.dao.DaoDnsEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class SIndex implements iSIndex
{
    @Autowired
    DaoDnsEntry daodns;

    
     
    
    
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
