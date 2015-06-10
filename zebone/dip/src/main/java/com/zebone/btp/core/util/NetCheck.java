package com.zebone.btp.core.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NetCheck {

	/**
	 * @author caixl
	 * @date Apr 15, 2013
	 * @description TODO ping网络是否通畅
	 * @param args void
	 */
	public static void main(String[] args) {
		System.out.println(NetCheck.pingServer("192.168.1.154"));
	}

	public static boolean pingServer(String server){
		BufferedReader in = null;
		Runtime r = Runtime.getRuntime();
		String pingCommand = "ping " + server;
		try{
			Process p = r.exec(pingCommand);
			if(p == null){
				return false;
			}
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while((line = in.readLine()) != null){
				if (line.indexOf("TTL")>-1){
					return true;
				}
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
