package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class CartConfirmDialog extends BorderPane{
	
	@FXML
	private Button clearCartBtn;
	@FXML
	private Button cancelCartBtn;
	
	public CartConfirmDialog() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cartConfirmDialog.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	public void clearCartCD(){
		System.out.println("Clear cart CD");
	}
	
	public void cancelCartClearCD(){
		System.out.println("Cancel cart CD");
	}
	
}
