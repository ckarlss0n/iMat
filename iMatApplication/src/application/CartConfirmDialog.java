package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
		
		Stage stage = new Stage();
		
		stage.setScene(new Scene(this));
		stage.setTitle("My modal window");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initStyle(StageStyle.UTILITY);
		stage.showAndWait();

	}
	
	public void clearCartCD(){
		System.out.println("Clear cart CD");
	}
	
	public void cancelCartClearCD(){
		System.out.println("Cancel cart CD");
	}
	
}
