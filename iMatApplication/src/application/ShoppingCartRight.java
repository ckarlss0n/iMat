package application;

import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ShoppingCartRight extends BorderPane{
	@FXML
	private GridPane gridPane;
	@FXML
	private BorderPane borderPane;
	
	private MainPanel mp;
	
	public void goToCheckOutv(ActionEvent evt){
		mp.goToCheckout();
	}
	
	public ShoppingCartRight(MainPanel mp){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartRight.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        this.mp = mp;
	}
	
	public GridPane getGridPane(){
		return gridPane;
	}
	
	public BorderPane getBorderPane(){
		return borderPane;
	}

}
