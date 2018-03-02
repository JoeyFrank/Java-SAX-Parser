/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Joey
 */
public class XMLLoader {
    public static XMLObject load(File xmlCourseFile) throws Exception {
        XMLObject root = new XMLObject();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                ArrayList<XMLObject> stack = new ArrayList<>();
                XMLObject currentNode;
                boolean isRootSet = false;
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if(!isRootSet){
                        root.setName(qName);
                        stack.add(0, root);
                        isRootSet = true;
                        return;     
                    } 
                    
                    currentNode = new XMLObject();
                    currentNode.setName(qName);
                    stack.add(0, currentNode);                                       
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    XMLObject poppedNode = stack.remove(0);                    
                    if(currentNode.equals(poppedNode) && currentNode.getName() == qName){                   
                        currentNode = stack.get(0);
                        currentNode.addChild(poppedNode);
                    } else {
                        throw new SAXException("Popped item does not equal current item");
                    }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    currentNode.setContent(new String(ch, start, length));
                }
            };
            
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
            
        } catch (Exception e) {
            throw e;
        }
        
      return root; 
    }
}
