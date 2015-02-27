package application;

import java.io.IOException;

import se.chalmers.ait.dat215.project.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class ShoppingCartItem extends BorderPane{
	
	
	public ShoppingCartItem(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartSmallItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }  
	}
	
	public void setTitle(String name, double price){
		String sPrice = String.valueOf(price);
		//this.setText(name+ " " + sPrice + " kr"  );
	}

}
