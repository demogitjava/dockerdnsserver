@	IN	SOA	@  root.one.test.com (
			11 ; serial
			28800 ; refresh
			7200 ; retry
			604800 ; expire	
			86400 ; ttl
			)

	IN	A	192.168.1.2

	IN	NS	one.test.com.
	IN	NS	two.test.com.

	IN	MX	10	one.test.com.
	IN	MX	20	two.test.com.
	IN	CNAME	one

one	IN	TXT	"this is a test"

www	IN	A	192.168.1.1
	IN	A	192.168.1.2
	IN	AAAA	FEDC:BA98:7654:3210:FEDC:BA98:7654:3210
	IN	AAAA	1080:0:0:0:8:800:200C:417A
	IN	AAAA	1080::8:800:200C:417A
	IN	AAAA	FF01::43
	IN	AAAA	::1
	IN	AAAA	0:0:0:0:0:0:13.1.68.3
	IN	AAAA	0:0:0:0:0:FFFF:129.144.52.38
	IN	AAAA	::13.1.68.3
	IN	AAAA	::FFFF:129.144.52.38

mail	IN	CNAME	www

27.1.168.192.in-addr.arpa.	IN	PTR	www.test.com
0.1.2.3.4.5.6.7.8.9.0.1.2.3.4.5.6.7.8.9.0.1.2.3.4.5.6.7.8.9.0.1.ip6.int. IN PTR www.test.com
