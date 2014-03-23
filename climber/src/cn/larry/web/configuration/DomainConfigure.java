package cn.larry.web.configuration;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;

public class DomainConfigure {
	private static final String CONFIG_FILE_PATH = "\\config\\cfg.xml";
	public  String getCurrentDomain(){
		String path = this.getClass().getClassLoader().getResource("").getPath()+CONFIG_FILE_PATH;
		System.out.println(path);
		Document configDoc = XmlHandler.getDocument(path);
		return  XmlHandler.getElementStr(configDoc,"domain");	
	}
	public  void saveCurrentDomain(String value){
		String path = this.getClass().getClassLoader().getResource("").getPath()+CONFIG_FILE_PATH;
		Document configDoc = XmlHandler.getDocument(path);
		XmlHandler.saveDoc(XmlHandler.updateDoc(configDoc, "domain", value), path);
	}
//	public static void main(String args[]){
//		DomainConfigure dcf = new DomainConfigure();
//		//System.out.println(dcf.getCurrentDomain());
//		//dcf.saveCurrentDomain("http://www.csdn.net/");
//		//System.out.println(dcf.getCurrentDomain());
//		dcf.getCurrentDomain();
//	}
}
