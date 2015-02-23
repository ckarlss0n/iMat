package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class OfflinePanel extends ScrollPane {
	
	public OfflinePanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homeOfflinePanel.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	
}
