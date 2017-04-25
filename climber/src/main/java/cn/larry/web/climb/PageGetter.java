package cn.larry.web.climb;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class PageGetter {
	public String getPage(String urlStr){
		//System.out.println("getPage-------------"+urlStr);
		if(urlStr.startsWith("http")){
			return getHttpPage(urlStr);
		}else if(urlStr.startsWith("https")){
			return getHttpsPage(urlStr);
		}else{
			return null;
		}
		
	}
	
	private String getHttpPage(String urlStr){
		StringBuilder str= new StringBuilder();
		try{
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();

			conn.connect();
	        Scanner in = new Scanner(url.openStream());

	        while (in.hasNext()) {
	        	String temp = in.nextLine();
	        	str.append(temp);
	        }
	        in.close();
		}catch(Exception e){
			e.printStackTrace();
			return new String(str);
		}
		return new String(str);
	}
	
private String getHttpsPage(String urlStr){
	System.setProperty("sun.net.client.defaultConnectTimeout", "1000"); 
	System.setProperty("sun.net.client.defaultReadTimeout", "1000");
	StringBuilder str= new StringBuilder();
	try{
		
		URL url = new URL(urlStr);
		HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
		httpsConn.setRequestProperty("Content-Type", 
				"application/x-www-form-urlencoded;charset=utf-8"
				 );
		httpsConn.connect();
        Scanner in = new Scanner(httpsConn.getInputStream(),"utf-8");
        // ��ȡ����������Ӧ���ݲ���
        while (in.hasNext()) {
        	String temp = in.nextLine();
        	str.append(temp); 
        }
        
	}catch(Exception e){
		e.printStackTrace();
		return new String(str);
	}
	return new String(str);
	}
}
	

