
package de.jgsoftwares.dnsserver.dao;

import de.jgsoftwares.dnsserver.model.MDNS;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface iJPADaoDNS  extends CrudRepository<MDNS, Long> 
{
  
     List<MDNS> findByLastName(String lastName);

    /**
     *
     * @param id
     * @return
     */
    MDNS findById(long id);
    
    
    @Override
    List<MDNS> findAll();
}
