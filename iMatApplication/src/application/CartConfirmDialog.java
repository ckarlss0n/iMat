package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class CartConfirmDialog extends BorderPane{
	
	@FXML
	private Button clearCartBtn;
	@FXML
	private Button cancelCartBtn;
	
	private Stage stage;
	
	public CartConfirmDialog() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cartConfirmDialog.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
		
		stage = new Stage();
		
		stage.setScene(new Scene(this));
		stage.setTitle("My modal window");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initStyle(StageStyle.UTILITY);
		stage.resizableProperty().set(false);
		ChangeSupport.getInstance().fireNewEvent("Blur", null);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>(){

			@Override
			public void handle(WindowEvent event) {
				ChangeSupport.getInstance().fireNewEvent("Unblur", null);
			}
			
		});
		stage.showAndWait();

	}
	
	public void clearCartCD(){
		System.out.println("Clear cart CD");
		ChangeSupport.getInstance().fireNewEvent("Clearcart", null);
		ChangeSupport.getInstance().fireNewEvent("Unblur", null);
		stage.close();
	}
	
	public void cancelCartClearCD(){
		System.out.println("Cancel cart CD");
		ChangeSupport.getInstance().fireNewEvent("Cancel", null);
		ChangeSupport.getInstance().fireNewEvent("Unblur", null);
		stage.close();	
	}
	
}
