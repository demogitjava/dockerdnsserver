package de.jgsoftwares.dnsserver.dao.interfaces;

import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author hoscho
 */
public interface JPACrudMDNS extends CrudRepository<MDNS, Integer>
{   
}
