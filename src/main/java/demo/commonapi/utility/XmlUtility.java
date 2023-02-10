package demo.commonapi.utility;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlUtility
{
  public Document getXmlDocumentInstance(String data)
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    Document doc = null;
    try
    {
      DocumentBuilder builder = factory.newDocumentBuilder();
      doc = builder.parse(new InputSource(new StringReader(data)));
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
    }
    catch (SAXException localSAXException)
    {
    }
    catch (Exception localException)
    {
    }

    return doc;
  }

  public Document getXmlDocument(String xmlString)
  {
    System.out.println("XML Document >>>>>>>>>>>>>>>> :" + xmlString);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try
    {
      DocumentBuilder builder = factory.newDocumentBuilder();
      return builder.parse(new InputSource(new StringReader(xmlString)));
    }
    catch (ParserConfigurationException e)
    {
      e.printStackTrace();
    }
    catch (SAXException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Element getDocumentElement(String xmlString)
  {
    Document document = getXmlDocument(xmlString);

    if (document == null) {
      return null;
    }
    return document.getDocumentElement();
  }

  public String getNodeValue(String tagName, Element element)
  {
    NodeList list = element.getElementsByTagName(tagName);
    if ((list != null) && (list.getLength() > 0))
    {
      NodeList subList = list.item(0).getChildNodes();

      if ((subList != null) && (subList.getLength() > 0))
      {
        return subList.item(0).getNodeValue();
      }
    }
    return null;
  }

  public String getNodeValue(Element transData, String nodeName)
  {
    NodeList dataList = transData.getElementsByTagName(nodeName);

    if (dataList == null) {
      return null;
    }
    int k = 0; if (k < dataList.getLength())
    {
      Element condition = (Element)dataList.item(k);
      return condition.getFirstChild().getNodeValue();
    }

    return null;
  }

  public String getAttributeValue(NamedNodeMap attributes, String attributeName)
  {
    for (int g = 0; g < attributes.getLength(); g++)
    {
      Attr attribute = (Attr)attributes.item(g);
      if (attribute.getName().equalsIgnoreCase(attributeName))
      {
        return attribute.getValue();
      }
    }

    return null;
  }

  public String getXmlString(Document dom)
  {
    Transformer tf = null;
    try
    {
      tf = TransformerFactory.newInstance().newTransformer();
      tf.setOutputProperty("encoding", "UTF-8");
      tf.setOutputProperty("indent", "yes");
      Writer out = new StringWriter();
      tf.transform(new DOMSource(dom), new StreamResult(out));
      return out.toString().replaceAll("\n|\r", "");
    }
    catch (TransformerConfigurationException e)
    {
      e.printStackTrace();
    }
    catch (TransformerFactoryConfigurationError e)
    {
      e.printStackTrace();
    }
    catch (TransformerException e) {
      e.printStackTrace();
    }

    return null;
  }
}