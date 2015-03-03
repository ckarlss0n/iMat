package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HistoryCartItem extends BorderPane{

	 @FXML
	 private Label lblName;
	 
	 @FXML
	 private Label lblPrice;
	 
	 @FXML
	 private Button btnAdd;
	 
	 @FXML
	 private Label lblAmount;
	
	public HistoryCartItem() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyCartItem.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }	
	}
	
	public void addToCart(ActionEvent evt){
		System.out.println("Add to cart.");
	}
}
