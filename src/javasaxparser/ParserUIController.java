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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Joey
 */
public class ParserUIController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(scrollPane.getScene().getWindow());
        if (file != null) {
            try
            {
                XMLObject root = XMLLoader.load(file);
                ArrayList<XMLObject> nodes = root.getChildren();
//                for (Student student : students) {
//                    textArea.appendText(Integer.toString(student.getId()) + "\n");
//                    textArea.appendText(student.getPawprint() + "\n");
//                    textArea.appendText(student.getFirstName() + "\n");
//                    textArea.appendText(student.getLastName() + "\n");
//                    textArea.appendText(Double.toString(student.getGrade()) + "\n");
//                    textArea.appendText("-------------------------\n");
//                }
                
                
            } catch (Exception ex) {
                System.out.println("Exception parsing XML file: " + ex.toString());
            }
        }
    }
    
}
