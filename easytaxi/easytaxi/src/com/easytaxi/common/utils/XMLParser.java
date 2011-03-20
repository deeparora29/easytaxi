package com.easytaxi.common.utils;

import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLParser {
	/**
	 * xml文件全路径
	 * 
	 * @param xmlFullPath
	 * @return
	 */
	public static Element parseXml2RootElement(String xmlFullPath) {
		InputStream in = XMLParser.class.getClassLoader().getResourceAsStream(
				xmlFullPath);
		SAXReader saxReader = new SAXReader();
		Element el = null;
		try {
			Document doc = saxReader.read(in);
			el = doc.getRootElement();
		} catch (DocumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return el;
	}

	/**
	 * 根据根的key对应的value找到其下的子Elements
	 * 
	 * @param xmlFullPath
	 * @param rootKey
	 * @param rootValue
	 * @return
	 */
	public static Element getChildsByRootValue(String xmlFullPath, String rootKey, String rootValue){
		Element root = parseXml2RootElement(xmlFullPath);
		Element el = null;
		for (Iterator it = root.elementIterator(); it.hasNext();) {
			Element childel = (Element) it.next();
			if(childel.attributeValue(rootKey).trim().equals(rootValue)){
				el = childel;
				//el.addAttribute("pname", childel.attributeValue("pname"));
				break;
			}
			
		}
		return el;
	}
	
	public static void main(String[] args) {
		String xml = "xml/CfgMenuTab.xml";
		System.out.println(xml);
		Element root = parseXml2RootElement(xml);
		for (Iterator it = root.elementIterator(); it.hasNext();) {
			Element el = (Element) it.next();
			System.out.println("MenuPid****" + el.attributeValue("pid"));
			System.out.println("MenuPname****" + el.attributeValue("pname"));
			for(int i = 0; i < el.elements().size(); i++){
				Element child = (Element)el.elements().get(i);
				child.addAttribute("pname", el.attributeValue("pname"));
				System.out.println("Menu Name****" + child.attributeValue("name"));
				System.out.println("Menu Url****" + child.attributeValue("url"));
				System.out.println("Menu pname****" + child.attributeValue("pname"));
			}
			

		}
	}
}
