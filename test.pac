function FindProxyForURL(url, host)
{
	ip = myIpAddress();
	alert(ip);
	return "PROXY 192.168.0.107:8888; DIRECT";
}
