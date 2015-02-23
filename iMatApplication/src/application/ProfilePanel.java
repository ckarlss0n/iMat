package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProfilePanel extends AnchorPane{
	
	public ProfilePanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profilePanel1.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
}
