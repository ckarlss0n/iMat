package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class ShoppingCartBig extends BorderPane {
	
	public ShoppingCartBig(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartBig.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }  
	}
	
}
