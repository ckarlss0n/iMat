package application;
import java.io.IOException;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class CheckoutPanel extends ScrollPane {

	@FXML
	private Button toStartBtn;
	
	private MainPanel mainPanel;
	private OnlinePanel onlinePanel;
	public CheckoutPanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkoutPanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	public void backToStart(ActionEvent evt){
		ChangeSupport.getInstance().fireNewEvent("Home", null);
		System.out.println("Home");
		//mainPanel.changeScreen(onlinePanel);
		
	}
}