
Docker DNS Server 
============================================================

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




run options
============================================================

for bridge network -> 254 Containers

`docker network create --driver=bridge --subnet=192.168.178.0/24 --ip-range=192.168.178.0/24 --gateway=192.168.178.1 192.168.178.0`



