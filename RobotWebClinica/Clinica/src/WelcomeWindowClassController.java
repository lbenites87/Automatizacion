/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class WelcomeWindowClassController {
    
    @FXML StackPane introStack;
    /**
     * Initializes the controller class.
     */
    //@Override
    public void initialize(String URL) {
        // TODO
        ImageView imgV = new ImageView();
        Image image = new Image(URL);
        
        imgV.setImage(image);
        introStack.getChildren().add(imgV);
        
    }
    
}
