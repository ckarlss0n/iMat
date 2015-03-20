package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class FPayment extends BorderPane {
	
	@FXML
	private Label paymentFlabel1;
	@FXML
	private Label paymentFlabel2;
	@FXML
	private TextField txtfFName;
	@FXML
	private TextField txtfFLastName;
	@FXML
	private TextField txtfFAdress;
	@FXML
	private TextField txtfFPostCode;
	@FXML
	private TextField txtfFCity;
	
	
	public FPayment(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fPaymentNew.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
			txtfFName.setText(IMatDataHandler.getInstance().getCustomer().getFirstName() + " " + IMatDataHandler.getInstance().getCustomer().getLastName());
			//txtfFSurName.setText(IMatDataHandler.getInstance().getCustomer().getFirstName());
			//txtfFLastName.setText(IMatDataHandler.getInstance().getCustomer().getLastName());
			txtfFAdress.setText(IMatDataHandler.getInstance().getCustomer().getAddress());
			txtfFPostCode.setText(IMatDataHandler.getInstance().getCustomer().getPostCode());
			txtfFCity.setText(IMatDataHandler.getInstance().getCustomer().getPostAddress());
			
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
    

		

		
	}
}
