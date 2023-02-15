package de.jgsoftwares.dnsserver.service;

import de.jgsoftwares.dnsserver.dao.DaoDnsEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jgsoftwares.dnsserver.dao.DaoDnsEntry;

@Service
public class SBindForward implements iSBindForward
{
    @Autowired
    de.jgsoftwares.dnsserver.dao.DaoDnsEntry daoentry;
    
    
    public SBindForward()
    {
        
    }

    @Override
    public DaoDnsEntry getDaoentry() {
        return daoentry;
    }

    @Override
    public void setDaoentry(DaoDnsEntry daoentry) {
        this.daoentry = daoentry;
    }
    
    
    
    
    
}
