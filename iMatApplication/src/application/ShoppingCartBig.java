package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

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
	@FXML
	private GridPane gridPane;
	
	public void add(Node node, int row){
		gridPane.add(node, 0, row);
	}
	
}
