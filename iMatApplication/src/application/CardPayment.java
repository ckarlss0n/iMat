package application;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class CardPayment extends BorderPane {
	
	@FXML
	private ChoiceBox chbCardType;
	
	@FXML
	private ChoiceBox chbMonth;
	
	@FXML
	private ChoiceBox chbYear;
	
	@FXML
	private TextField txtfCardHolderName;
	
	@FXML
	private TextField txtfCardNumber;
	
	@FXML
	private TextField txtfCVC;
	
	
	
	
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
		
		chbCardType.setItems(FXCollections.observableArrayList("Välj korttyp","Visa", "MasterCard"));
		
		chbMonth.setItems(FXCollections.observableArrayList("Månad", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12"));
		
		chbYear.setItems(FXCollections.observableArrayList("År"));
		
		for(int i = 0; i < 15; i++){
			chbYear.getItems().add("20" + (15+i));
		}
		
		chbMonth.setValue(chbMonth.getItems().get(0));
		chbYear.setValue(chbYear.getItems().get(0));
		
		
	}
	
	
}
