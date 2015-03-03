package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

public class ChoosePayment extends ScrollPane {

	public ChoosePayment(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choosePayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
}
