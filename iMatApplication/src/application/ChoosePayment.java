package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class ChoosePayment extends ScrollPane {

	@FXML
	private Button finalizeBtn;
	
	MainPanel mainPanel		;
	CheckoutPanel checkoutPanel;
	
	public ChoosePayment(MainPanel mainPanel, CheckoutPanel checkoutPanel){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choosePayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		this.mainPanel = mainPanel;
		this.checkoutPanel = checkoutPanel;
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	public void finalizeBuy(ActionEvent evt){
		mainPanel.changeScreen(checkoutPanel);	
	}
}
