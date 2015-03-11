package application;

import java.io.IOException;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class CardPayment extends BorderPane {
	
	@FXML
	private ChoiceBox<String> chbCardType;
	
	@FXML
	private ChoiceBox<String> chbMonth;
	
	@FXML
	private ChoiceBox<String> chbYear;
	
	@FXML
	private TextField txtfCardHolderName;
	
	@FXML
	private TextField txtfCardNumber;
	
	@FXML
	private TextField txtfCVC;
	
	@FXML
	private Label lblErrorType;
	@FXML
	private Label lblErrorName;
	@FXML
	private Label lblErrorCardNbr;
	@FXML
	private Label lblErrorDate;
	@FXML
	private Label lblErrorYear;
	@FXML
	private Label lblErrorCVC;
	
	private boolean isFilledCorrect;
	
	private ChoosePayment cp;
	public CardPayment(ChoosePayment cp){
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardPayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		this.cp = cp;
    
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
		
		chbCardType.setValue(chbCardType.getItems().get(0));
		chbMonth.setValue(chbMonth.getItems().get(0));
		chbYear.setValue(chbYear.getItems().get(0));
		
		
		BooleanBinding bb = new BooleanBinding() {
		    {
		       super.bind(txtfCardNumber.textProperty(),txtfCVC.textProperty(),
		    		   chbCardType.getSelectionModel().selectedItemProperty(),chbMonth.getSelectionModel().selectedItemProperty(),
		    		   chbYear.getSelectionModel().selectedItemProperty(), txtfCardHolderName.textProperty());
		    }
		    @Override
		    protected boolean computeValue() {
		    	
		      return (!(txtfCardNumber.getText().length() > 0 && txtfCVC.getText().length() >0
		            && txtfCardHolderName.getText().length() > 0));
		    }
		    
		};
		cp.setBinding(bb);
		
		
	}
	
	public void fillCardInfo(){
		int cardNbr = 0;
		int CVC = 0;
		String type = "";
		String cardHolder = "";
		int validMonth = 0;
		int validYear = 0;
		try{
			if(txtfCardHolderName.getText().equals("")){
				throw new IllegalArgumentException();
			}
			cardHolder = txtfCardHolderName.getText();
			lblErrorName.setOpacity(0);
			isFilledCorrect = true;
		} catch(Exception e){
			lblErrorName.setOpacity(1);
			
			isFilledCorrect = false;
		}
		try{
			cardNbr = Integer.parseInt(txtfCardNumber.getText());
			lblErrorCardNbr.setOpacity(0);
			isFilledCorrect = true;
		} catch(Exception e){
			lblErrorCardNbr.setOpacity(1);
			if(isFilledCorrect = true){
				isFilledCorrect = false;
			}
			
		}
		
		try{
			CVC = Integer.parseInt(txtfCVC.getText());
			lblErrorCVC.setOpacity(0);
			isFilledCorrect = true;
		} catch(Exception e){
			lblErrorCVC.setOpacity(1);
			if(isFilledCorrect = true){
				isFilledCorrect = false;
			}
			//isFilledCorrect = false;
		}
		
		try{
			type = chbCardType.getSelectionModel().getSelectedItem();
			lblErrorType.setOpacity(0);
			if(type.equals("Välj korttyp")){
				throw new IllegalArgumentException();
			}
			isFilledCorrect = true;
		} catch(Exception e){
			lblErrorType.setOpacity(1);
			if(isFilledCorrect = true){
				isFilledCorrect = false;
			}
			//isFilledCorrect = false;
		}
		
		try{
			
			validMonth = Integer.parseInt(chbMonth.getSelectionModel().getSelectedItem());
			isFilledCorrect = true;
			lblErrorDate.setOpacity(0);
		} catch(Exception e){
			lblErrorDate.setOpacity(1);
			if(isFilledCorrect = true){
				isFilledCorrect = false;
			}
			//isFilledCorrect = false;
		}
		
		try{
			validYear = Integer.parseInt(chbYear.getSelectionModel().getSelectedItem());
			lblErrorYear.setOpacity(0);
			isFilledCorrect = true;
			
		} catch(Exception e){
			if(isFilledCorrect = true){
				isFilledCorrect = false;
			}
			//isFilledCorrect = false;
			lblErrorYear.setOpacity(1);
		}
		
		System.out.println(isFilledCorrect);
		if(isFilledCorrect){
			IMatDataHandler.getInstance().getCreditCard().setHoldersName(txtfCardHolderName.getText());
			IMatDataHandler.getInstance().getCreditCard().setCardNumber(txtfCardNumber.getText());
			IMatDataHandler.getInstance().getCreditCard().setVerificationCode(CVC);
			IMatDataHandler.getInstance().getCreditCard().setCardType(type);
			IMatDataHandler.getInstance().getCreditCard().setValidMonth(validMonth);
			IMatDataHandler.getInstance().getCreditCard().setValidMonth(validYear);
			
		}
		
			
	}
	
	public boolean getIsCorrect(){
		return isFilledCorrect;
	}
	
	
	
}
