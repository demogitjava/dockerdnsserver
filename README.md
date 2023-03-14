
Docker DNS Server
============================================================  
for login on demodatabase  
path
http://192.168.178.3/h2-console

over remote connection like remmina

    username: admin 
    password: jj78mvpr52k1

![enter image description here](https://raw.githubusercontent.com/demogitjava/demodatabase/master/dockerdnspanel.png)


2023-03-08 created snapshot

mirror 1:  
https://drive.google.com/file/d/1ww0gId0cHH4eLv3gZ2Q5InvwnAwjndwS/view?usp=sharing

mirror 2:  
https://www.dropbox.com/s/agcep0wt6qbwfii/dockerdnsserver-0.0.1-SNAPSHOT.jar?dl=0

created file /etc/bind

**demogitjava**

    $TTL  86400   ; default TTL for this zone (this 1 day)
    $ORIGIN demogitjava.
    @        IN      SOA     demogitjava. root.demogitjava. (
                               2019011502
                                   604800
                                    86400
                                  2419200
                                    86400 )
    @        IN      NS          demogitjava.
    @        IN      MX          mail.demogitjava.
    @        IN      A           217.160.255.254
    www      IN      A           217.160.255.254
    @        IN      CNAME       demogitjava


**namedconflocal:**

          GNU nano 5.4                                                            named.conf.local                                                                     
    zone "demogitjava" {
        type master;
        file "/etc/bind/demogitjava";
        allow-query { none; };
        allow-tranfer { none; };
    };
    
    zone "reversedemogitjava.de" {
        type master;
        file "/etc/bind/reversedemogitjava.de";
        allow-query { none; };
        allow-tranfer { none; };
    };



**named.conf.options**
edit manuel:

    options {
        directory "/var/cache/bind";

        // If there is a firewall between you and nameservers you want
        // to talk to, you may need to fix the firewall to allow multiple
        // ports to talk.  See http://www.kb.cert.org/vuls/id/800113

        // If your ISP provided one or more IP addresses for stable
        // nameservers, you probably want to use them as forwarders.
        // Uncomment the following block, and insert the addresses replacing
        // the all-0's placeholder.

         forwarders {
                8.8.8.8;
                8.8.4.4; 
                };

        //========================================================================
        // If BIND logs error messages about the root key being expired,
        // you will need to update your keys.  See https://www.isc.org/bind-keys
        //========================================================================
        dnssec-validation auto;

        listen-on-v6 { none; };};










**reservedemogitjava.de**

    $TTL  86400   ; default TTL for this zone (this 1 day)@        IN      SOA     demogitjava.de. root.demogitjava.de. (
                           2019011502
                               604800
                                86400
                              2419200
                                86400 )
     @          IN      NS          demogitjava.de.
     100        IN      PTR         demogitjava.de.







in the moment is support **one entry** in table DNS  
it also create the file named.conf.local  
and the domain file demogitjava.de


then restart the container or the server  
with

    apt-get install bind8
    apt-get install ufw
    ufw allow 53/tcp 
    ufw allow 53/udp

**used operating system in Debain 11.6**

The used IDE is  
Apache NetBeans  
https://netbeans.apache.org/download/index.html

required is docker on the machine  
https://www.docker.com/

to install docker on centos  
[https://docs.docker.com/engine/install/centos/](https://docs.docker.com/engine/install/centos/)

manually install   
https://download.docker.com/linux/centos/



The used java jdk is  
openjdk-11-jre


Simple Interface in Java for  
Bind9 Server to add DNS Records over  
Rest or the GUIServerPanel via epel-release



The Docker Container run options:

    -p 217.160.255.254:53:53 --dns 8.8.8.8 --dns 8.8.4.4 --name dockerdns




forwarders are set with google

    8.8.8.8  
    8.8.4.4  

for edit the public dns look at  
DaoDnsEntry - createnamedconfoptions()






run options docker network
============================================================  

for bridge network -> 254 Containers

`docker network create --driver=bridge --subnet=192.168.178.0/24 --ip-range=192.168.178.0/24 --gateway=192.168.178.1 192.168.178.0`

