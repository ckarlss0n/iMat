package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class CardPayment extends BorderPane {
	
	public CardPayment(){
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardPayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
    
		int sum = 0;
		for(ShoppingItem i: IMatDataHandler.getInstance().getShoppingCart().getItems()){
			sum += i.getProduct().getPrice() * i.getAmount();
		}
	}
	
	
}
