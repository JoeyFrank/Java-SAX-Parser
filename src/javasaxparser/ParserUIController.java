/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 *
 * @author Joey
 */
public class ParserUIController implements Initializable {
    @FXML
    private TextArea textArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        if (file != null) {
            try
            {
                XMLObject root = XMLLoader.load(file);
                textArea.clear();
                generateInterfaceFromRoot(root, 0);
                
            } catch (Exception ex) {
                System.out.println("Exception parsing XML file: " + ex.toString());
                
            }
        }
    }   
    
    private void generateInterfaceFromRoot(XMLObject root, int indent){
        textArea.appendText(root.getName());
        String content = "";
        content = content + (root.getContent());
        
        //Output cleanup
        content = content.trim().replace("\n", "").replace("\t", "").replaceAll("\\ +"," ");
        
        if(!content.isEmpty()){
            textArea.appendText(" = \"" + content + "\"");
        }
        
        textArea.appendText("\n");
        
        ArrayList<XMLObject> children = root.getChildren();
        if(!children.isEmpty()){
            for(XMLObject child: children){
                for(int i=0;i<=indent;i++){
                    textArea.appendText("     | ");
                }
                generateInterfaceFromRoot(child, (indent+1));
            }
        }
    }
}
