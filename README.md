
Docker DNS Server
============================================================  
for login on demodatabase  
path /h2-console

username: admin password: jj78mvpr52k1

![enter image description here](https://raw.githubusercontent.com/demogitjava/demodatabase/master/dockerdnspanel.png)


2023-03-08 created snapshot

mirror 1:  
https://drive.google.com/file/d/1aVWNU3m30F55f2_89kFQlgFR1d8UlTni/view?usp=sharing

mirror 2:  
https://www.dropbox.com/s/lfrjwdq4bciyv01/dockerdnsserver-0.0.1-SNAPSHOT.jar?dl=0

created file /etc/bind  
demogitjava.de

    ;namedconflocal  
      
    $TTL  86400   ; default TTL for this zone (this 1 day)  
    $ORIGIN demogitjava.  
    @        IN      SOA     demogitjava.(  
                               2019011502  
                                   604800  
                                    86400  
                                  2419200  
                                    86400)  
             IN      NS          demogitjava.  
             IN      MX          mail.demogitjava.  
    		www      IN      A           217.160.255.254  
             IN      CNAME       demogitjava





in the moment is support **one entry** in table DNS  
it also create the file named.conf.local  
and the domain file demogitjava.de


then restart the container or the server  
with

sudo systemctl restart bind9 sudo ufw allow 53/tcp sudo ufw allow 53/udp

used operating system in Debain 11.6

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



The Docker Container starts with name

    --name=dnsdemogitjava  
    --dns=192.168.178.3  




forwarders are set with google

    8.8.8.8  
    8.8.4.4  

for edit the public dns look at  
DaoDnsEntry - createnamedconfoptions()






run options docker network
============================================================  

for bridge network -> 254 Containers

`docker network create --driver=bridge --subnet=192.168.178.0/24 --ip-range=192.168.178.0/24 --gateway=192.168.178.1 192.168.178.0`
