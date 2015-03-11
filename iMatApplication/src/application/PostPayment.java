package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PostPayment extends BorderPane {
	
	public PostPayment(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("postPayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
			
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
