package application;

import java.io.IOException;
import java.text.DecimalFormat;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class ChoosePayment extends ScrollPane {

	@FXML
	private Button finalizeBtn;
	
	@FXML
	private ScrollPane scrlCard;
	
	MainPanel mainPanel;
	//CheckoutPanel checkoutPanel;
	DecimalFormat twoDec = new DecimalFormat("0.00");
	
	public ChoosePayment(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choosePayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		//this.checkoutPanel = checkoutPanel;
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
            
        }
		scrlCard.setContent(new CardPayment());
	}
	
	public void finalizeBuy(ActionEvent evt){
		
		IMatDataHandler.getInstance().placeOrder(true);
		CheckoutPanel checkoutPanel = new CheckoutPanel();
		System.out.println("Placing order");
		ChangeSupport.getInstance().fireNewEvent("", checkoutPanel);	
	}
	
	public void setFinalizeText(Double sum){
		finalizeBtn.setText("Slutför köp (" + String.valueOf(twoDec.format(sum)) + " kr)");
	}
}
