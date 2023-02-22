
Docker DNS Server
============================================================

in the moment is support one entry in table DNS
//-------------------------------------------------
ID  	FORWARDDNS  	REVERSEDNS  	DNSZONE  	DNSTYPE  
1	217.160.255.254	null	demogitjava.de          A
//-------------------------------------------------
it also create the file 
named.conf.local 

and the domain file demogitjava.de 
//-------------------------------------------------
;namedconflocal

$TTL  86400   ; default TTL for this zone (this 1 day)
$ORIGIN demogitjava.de.
@        IN      SOA     demogitjava.de.(
                           2019011502
                               604800
                                86400
                              2419200
                                86400)
         IN      NS          demogitjava.de.
         IN      MX          mail.demogitjava.de.
www      IN      A           217.160.255.254
         IN      CNAME       demogitjava.de
//-------------------------------------------------

then restart the container or the server
with 
//-------------------------------------------------
sudo systemctl restart bind9
and
sudo ufw allow 53/tcp

sudo ufw allow 53/udp
//-------------------------------------------------




mirror 1:
https://drive.google.com/file/d/1vuvV8gIHtbfynC_ZJGpTXJx7UsMAFtak/view?usp=sharing

mirror 2:
https://www.dropbox.com/s/csoi6uvgfugh3t6/dockerdnsserver-0.0.1-SNAPSHOT.jar?dl=0



used operating system in Debain 11.6

The used IDE is
Apache NetBeans
https://netbeans.apache.org/download/index.html

required is docker on the machine
https://www.docker.com/

The used java jdk is
https://www.graalvm.org/downloads/
in this case i use graalvm jdk 11


Simple Interface in Java for 
Bind9 Server to add DNS Records over
Rest or the GUIServerPanel via epel-release



The Docker Container starts with name
--name=dockerdns
--dns=192.168.178.3

and expose port 53

forwarders are set with google
8.8.8.8
8.8.4.4
for edit the public dns look at 
DaoDnsEntry - createnamedconfoptions()






run options
============================================================

for bridge network -> 254 Containers

`docker network create --driver=bridge --subnet=192.168.178.0/24 --ip-range=192.168.178.0/24 --gateway=192.168.178.1 192.168.178.0`



