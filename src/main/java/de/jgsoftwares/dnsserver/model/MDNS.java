
package de.jgsoftwares.dnsserver.model;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "DNS")
public class MDNS implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String forwarddns;
    private String reversedns;
    private String dnszone;
    
    private String dnstype;

    public String getDnstype() {
        return dnstype;
    }

    public void setDnstype(String dnstype) {
        this.dnstype = dnstype;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForwarddns() {
        return forwarddns;
    }

    public void setForwarddns(String forwarddns) {
        this.forwarddns = forwarddns;
    }

    public String getReversedns() {
        return reversedns;
    }

    public void setReversedns(String reversedns) {
        this.reversedns = reversedns;
    }

    public String getDnszone() {
        return dnszone;
    }

    public void setDnszone(String dnszone) {
        this.dnszone = dnszone;
    }
    
    
}
