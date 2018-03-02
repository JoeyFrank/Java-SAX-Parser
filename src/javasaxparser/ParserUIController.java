/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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
                generateInterfaceFromNode(root, 0);
                
            } catch (Exception ex) {
                displayExceptionAlert("Exception parsing XML file.", ex);   
            }
        }
    }   
    
    private void generateInterfaceFromNode(XMLObject node, int indent){
        textArea.appendText(node.getName());
        String content = "";
        content = content + (node.getContent());
        
        content = content.trim().replace("\n", "").replace("\t", "").replaceAll("\\ +"," ");
        
        //Output cleanup
        if(!content.isEmpty()){
            textArea.appendText(" = \"" + content + "\"");
        }
        textArea.appendText("\n");
        
        ArrayList<XMLObject> children = node.getChildren();
        if(!children.isEmpty()){
            for(XMLObject child: children){
                for(int i=0;i<=indent;i++){
                    textArea.appendText("     | ");
                }
                generateInterfaceFromNode(child, (indent+1));
            }
        }
    }
    
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(message);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
}
