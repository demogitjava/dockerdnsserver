/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.jgsoftwares.dnsserver.model;
import javax.persistence.*;


@Entity
@Table(name = "DNS")
public class MDNS 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String forwarddns;
    private String reversedns;
    private String dnszone;

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
