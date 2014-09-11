package ywork.study.xml;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DomDemo {

	public static void main(String[] args) {
		String a = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<xml> "
				+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
				+ " <CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA[this is a test]]></Content>"
				+ "<MsgId>1234567890123456</MsgId>" + "</xml>";
		System.out.println(a);
		Document doc = loadXML(a);
		System.out.println(getNodeValue(doc, "ToUserName"));
		System.out.println(getNodeValue(doc, "CreateTime"));
		System.out.println(getNodeValue(doc, "MsgId"));
		System.out.println(getNodeValue(doc, "b"));
		
	}

	/**
	 * 得到doc中只有单个节点的值
	 */
	public static String getNodeValue(Document doc, String tagName) {
		NodeList nodes = doc.getElementsByTagName(tagName);
		if (nodes.getLength() > 0) {
			Element e = (Element)nodes.item(0);
			return getCharacterDataFromElement(e);
		}
		return null;
	}
	
	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	/**
	 * 加载XML
	 * 
	 * @param xml
	 * @return
	 */
	public static Document loadXML(String xml) {
		DocumentBuilder db;
		Document doc = null;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			doc = db.parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return doc;

	}

}
