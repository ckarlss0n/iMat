package application;

import java.io.IOException;

import se.chalmers.ait.dat215.project.ShoppingItem;
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
	
	public HistoryCartItem(ShoppingItem shoppingItem) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyCartItem.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }	
    	
    	lblName.setText(shoppingItem.getProduct().getName());
    	lblAmount.setText(String.valueOf(shoppingItem.getAmount()) + shoppingItem.getProduct().getUnitSuffix());
    	lblPrice.setText(String.valueOf(shoppingItem.getTotal()) + "kr");
	}
	
	public void addToCart(ActionEvent evt){
		System.out.println("Add to cart.");
	}
}
