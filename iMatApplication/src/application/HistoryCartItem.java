package application;

import java.io.IOException;

import se.chalmers.ait.dat215.project.IMatDataHandler;
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
	 
	 private ShoppingItem sci;
	
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
    	double amount = shoppingItem.getAmount();
    	sci = new ShoppingItem(shoppingItem.getProduct());
    	sci.setAmount(amount);
	}
	
	public void addToCart(ActionEvent evt){
		try{
			for(ShoppingItem i: IMatDataHandler.getInstance().getShoppingCart().getItems()){
				if(i.getProduct().getProductId() == sci.getProduct().getProductId()){
					i.setAmount(sci.getAmount());
					sci = i;
				}
			}
			
			if(IMatDataHandler.getInstance().getShoppingCart().getItems().contains(sci)){
				
				sci.setAmount(sci.getAmount()+1);
				System.out.println(sci.getAmount());
				IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(sci, false);
			}else {
				sci.setAmount(sci.getAmount());
				System.out.println(sci.toString());
				IMatDataHandler.getInstance().getShoppingCart().addItem(sci);
			}
		
		} catch(IllegalArgumentException r){
			System.out.println("Error");
		}
	}
}
