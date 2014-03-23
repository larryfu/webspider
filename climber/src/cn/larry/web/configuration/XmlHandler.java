package cn.larry.web.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlHandler {
	public static Document getDocument(String path){
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			File file = new File(path);
			try {
				Scanner in = new Scanner(file);
				while(in.hasNext()){
					System.out.print(in.next());
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("file.getAbsolutePath = " + file.getAbsolutePath());
			doc = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static String getElementStr(Document doc,String elementTag){
		Element root = doc.getRootElement();
		Element element = root.element(elementTag);
		return element.getText();
	}
	
	public static Document updateDoc(Document doc,String elementTag,String value){
		Document document = doc;
		Element root = document.getRootElement();
		Element element = root.element(elementTag);
		element.setText(value);
		return document;
	}
	public static void saveDoc(Document doc,String path){
		try{
		  OutputFormat format = OutputFormat.createPrettyPrint();
		  XMLWriter writer = new XMLWriter(new FileOutputStream(path),format);
		  writer.write(doc);
		  writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
