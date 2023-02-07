echo www.test.com
nslookup -silent -retry=1 -port=5353 www.test.com localhost
echo test.com
nslookup -silent -retry=1 -port=5353 test.com localhost
echo MX
nslookup -silent -retry=1 -port=5353 -type=MX test.com localhost
echo NS
nslookup -silent -retry=1 -port=5353 -type=NS test.com localhost
echo CNAME
nslookup -silent -retry=1 -port=5353 -type=CNAME mail.test.com localhost
echo PTR
nslookup -silent -retry=1 -port=5353 192.168.1.27 localhost
echo AAAA
nslookup -silent -retry=1 -port=5353 -type=AAAA www.test.com localhost
echo TCP
nslookup -silent -retry=1 -port=5353 -vc www.test.com localhost
echo truncation
nslookup -silent -retry=1 -port=5353 test.trunc.org localhost
echo TXT
nslookup -silent -retry=1 -port=5353 -type=TXT one.test.com localhost
echo SOA
nslookup -silent -retry=1 -port=5353 -type=SOA test.com localhost
