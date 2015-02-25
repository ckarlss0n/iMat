package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class ProcessIndicator extends BorderPane {
	
	public ProcessIndicator(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("progressIndicator.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }  
	}

}
