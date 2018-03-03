/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.util.ArrayList;
import org.xml.sax.Attributes;

/**
 *
 * @author Joey
 */
public class XMLObject {
    private ArrayList<XMLObject> children;
    private String name;
    private String content = null;
    
    public XMLObject(String name) {
        this.name = name;
        children = new ArrayList<>();
    }
    
    public ArrayList<XMLObject> getChildren() {
        return children;
    }
    
    public void addChild(XMLObject child) {
        children.add(child);
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setContent(String content){
        if(this.content == null){
            this.content = content;
        } else {
            this.content = this.content.concat(content);
        }
    }
    
    public String getContent(){
        return content;
    }
}
