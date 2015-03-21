package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class PersonalInformationPanel extends BorderPane {
	
	@FXML
	private TextField txtfSurname;
	@FXML
	private TextField txtfLastname;
	@FXML
	private TextField txtfEmail;
	@FXML
	private TextField txtfAdress;
	@FXML
	private TextField txtfPostcode;
	@FXML
	private TextField txtfCity;
	@FXML
	private TextField txtfPhone;
	@FXML
	private Button goToPaymentBtn;
	@FXML
	private RadioButton rBtnProfile;
	@FXML
	private RadioButton rBtnOther;
	@FXML
	private ChoiceBox choiceDay;
	@FXML
	private ChoiceBox choiceTime;
	
	ToggleGroup tg;
	Customer customer = IMatDataHandler.getInstance().getCustomer();
	
	private Boolean pInfSurname = true;
	private Boolean pInfLastname = true;
	private Boolean pInfEmail = true; 
	private Boolean pInfAdress = true;
	private Boolean pInfPostcode = true;
	private Boolean pInfCity = true;
	private Boolean pInfPhone = true;
	
	
	public PersonalInformationPanel(Customer customer){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("personalInformation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		//this.choosePayment = choosePayment;
		
		

		
		try {

			
			
			fxmlLoader.load();
    		tg = new ToggleGroup();
    		rBtnProfile.setToggleGroup(tg);
    		rBtnOther.setToggleGroup(tg);
    		
    		rBtnProfile.setSelected(true);
    		
    		if(txtfLastname.getText().matches("^[A-ZÅÄÖa-zåäöé+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?")){
				pInfSurname = true;
				pInfLastname = true;
				pInfEmail = true;
				pInfAdress = true;
				pInfPostcode = true;
				pInfCity = true;
				pInfPhone = true;
	        }else{
	        	pInfSurname = false;
				pInfLastname = false;
				pInfEmail = false;
				pInfAdress = false;
				pInfPostcode = false;
				pInfCity = false;
				pInfPhone = false;
	        } 
    		
    		//goToPaymentBtn.setDisable(true);
    		if(txtfSurname.getText().length() == 0 || txtfLastname.getText().length() == 0 || txtfEmail.getText().length() == 0 ||
    				txtfAdress.getText().length() == 0 || txtfPostcode.getText().length() == 0	|| txtfCity.getText().length() == 0 ||
    				txtfPhone.getText().length() == 0){
    			
    			goToPaymentBtn.setDisable(true);
    		}
    		pInfSetBtnVisible();
    		
    		txtfSurname.focusedProperty().addListener(new ChangeListener<Boolean>()
        			{
        	    @Override
        	   public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        	    {
        	        if (newPropertyValue){
        	        	
        	        }
        	        else{
        	            if(txtfSurname.getText().matches("^[A-ZÅÄÖa-zåäöé+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?")){
        	            	txtfSurname.setStyle("-fx-border-width: 0px ;");
        	            	pInfSurname = true;
        	            	pInfSetBtnVisible();
        	            }else{
        	            	txtfSurname.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        	            	pInfSurname = false;
        	            	goToPaymentBtn.setDisable(true);
        	            }
        	        }
        	    }
        	});
        	
    		txtfLastname.focusedProperty().addListener(new ChangeListener<Boolean>()
        			{
        	    @Override
        	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        	    {
        	        if (newPropertyValue){
        	        	
        	        }
        	        else{
        	            if(txtfLastname.getText().matches("^[A-ZÅÄÖa-zåäö+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?")){
        	            	txtfLastname.setStyle("-fx-border-width: 0px ;");
        	            	pInfLastname = true;
        	            	pInfSetBtnVisible();
        	            }else{
        	            	txtfLastname.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        	            	pInfLastname = false;
        	            	goToPaymentBtn.setDisable(true);
        	            }
        	        }
        	    }
        	});
        	
    		txtfEmail.focusedProperty().addListener(new ChangeListener<Boolean>()
        			{
        	    @Override
        	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        	    {
        	        if (newPropertyValue){
        	        	
        	        }
        	        else{
        	            if(txtfEmail.getText().matches("^[a-zA-Z0-9+_.-]+[@][a-zA-Z0-9_-]+\\.([a-zA-Z0-9+_.-]*)?[a-zA-Z0-9+_-]")){
        	            	txtfEmail.setStyle("-fx-border-width: 0px ;");
        	            	pInfEmail = true;
        	            	pInfSetBtnVisible();
        	            }else{
        	            	txtfEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        	            	pInfEmail = false;
        	            	goToPaymentBtn.setDisable(true);
        	            }
        	        }
        	    }
        	});
        	
    		txtfAdress.focusedProperty().addListener(new ChangeListener<Boolean>()
        			{
        	    @Override
        	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        	    {
        	        if (newPropertyValue){
        	        	
        	        }
        	        else{
        	            if(txtfAdress.getText().matches("^[A-ZÅÄÖa-zåäö+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?(\\s\\d+$)*?")){
        	            	txtfAdress.setStyle("-fx-border-width: 0px ;");
        	            	pInfAdress = true;
        	            	pInfSetBtnVisible();
        	            }else{
        	            	txtfAdress.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        	            	pInfAdress = false;
        	            	goToPaymentBtn.setDisable(true);
        	            }
        	        }
        	    }
        	});
        	
    		txtfPostcode.focusedProperty().addListener(new ChangeListener<Boolean>()
        			{
        	    @Override
        	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        	    {
        	        if (newPropertyValue){
        	        	
        	        }
        	        else{
        	            if( txtfPostcode.getText().matches("[0-9]{5}")){
        	            	txtfPostcode.setStyle("-fx-border-width: 0px ;");
        	            	pInfPostcode = true;
        	            	pInfSetBtnVisible();
        	            }else{
        	            	txtfPostcode.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        	            	pInfPostcode = true;
        	            	goToPaymentBtn.setDisable(true);
        	            }
        	        }
        	    }
        	});
        	
    		txtfCity.focusedProperty().addListener(new ChangeListener<Boolean>()
        			{
        	    @Override
        	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        	    {
        	        if (newPropertyValue){
        	        	
        	        }
        	        else{
        	            if(txtfCity.getText().matches("^[A-ZÅÄÖa-zåäö+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?")){
        	            	txtfCity.setStyle("-fx-border-width: 0px ;");
        	            	pInfCity = true;
        	            	pInfSetBtnVisible();
        	            }else{
        	            	txtfCity.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        	            	pInfCity = false;
        	            	goToPaymentBtn.setDisable(true);
        	            }
        	        }
        	    }
        	});
        	
    		txtfPhone.focusedProperty().addListener(new ChangeListener<Boolean>()
        			{
        	    @Override
        	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        	    {
        	        if (newPropertyValue){
        	        	
        	        }
        	        else{
        	            if(txtfPhone.getText().matches("[0-9]+")){
        	            	txtfPhone.setStyle("-fx-border-width: 0px ;");
        	            	pInfPhone = true;
        	            	pInfSetBtnVisible();
        	            }else{
        	            	txtfPhone.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        	            	pInfPhone = false;
        	            	goToPaymentBtn.setDisable(true);
        	            }
        	        }
        	    }
        	});

    		    Date date = new Date();
    		    List<String> dateSet = new ArrayList<String>();
    		    int todaysDay = date.getDay();
    		    for(int i = 0; i <= 5; i++){
    		    	String weekday;
    		    	if(todaysDay == 0){
    		    		weekday = "Söndag";
    		    		todaysDay = todaysDay + 1;
    		    	} else if (todaysDay == 1){
    		    		weekday = "Måndag";
    		    		todaysDay = todaysDay + 1;
    		    	} else if (todaysDay == 2){
    		    		weekday = "Tisdag";
    		    		todaysDay = todaysDay + 1;
    		    	} else if (todaysDay == 3){
    		    		weekday = "Onsdag";
    		    		todaysDay = todaysDay + 1;
    		    	} else if (todaysDay == 4){
    		    		weekday = "Torsdag";
    		    		todaysDay = todaysDay + 1;
    		    	}else if (todaysDay == 5){
    		    		weekday = "Fredag";
    		    		todaysDay = todaysDay + 1;
    		    	}else {
    		    		weekday = "Lördag";
    		    		todaysDay = todaysDay - 6;
    		    	}
    		    	dateSet.add(i, weekday);
    		    }
    		    
    		    
    		 
    		    String today = "Idag " + date.getDate() + ": " + dateSet.get(0);
    		    String tomorrow = "Imorgon " + (date.getDate() + 1) + ": " + dateSet.get(1);
    		    String three = (date.getDate() + 2) + ": " + dateSet.get(2);
    		    String four = (date.getDate() + 3) + ": " + dateSet.get(3);
    		    String five = (date.getDate() + 4) + ": " + dateSet.get(4);
    		    choiceDay.setItems(FXCollections.observableArrayList("Datum", today, tomorrow, three, four, five));


    		    
    		    String timeOne = "8:00 - 12:00";
    		    String timeTwo = "12:00 - 14:00";
    		    String timeThree = "14:00 - 16:00";
    		    String timeFour = "16:00 - 18:00";

    		    /*
    		    int hours = date.getHours();
    		    String timeOne = (hours + 1) + ":00 - " + (hours + 2) + ":00";
    		    String timeTwo = (hours + 2) + ":00 - " + (hours + 3) + ":00";
    		    String timeThree = (hours + 3) + ":00 - " + (hours + 4) + ":00";
    		    String timeFour = (hours + 1) + ":00 - " + (hours + 2) + ":00";
    		     */
    		    choiceTime.setItems(FXCollections.observableArrayList("Tid", timeOne, timeTwo, timeThree, timeFour));

    		    choiceTime.setValue(choiceTime.getItems().get(0));

    			choiceDay.setValue(choiceDay.getItems().get(0));			
    		    
		} catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	public void pInfSetBtnVisible(){
		if(pInfSurname && pInfLastname && pInfEmail && pInfAdress && pInfPostcode && pInfCity && pInfPhone){
			goToPaymentBtn.setDisable(false);
    	}
	}
	
	public void pInfSetText(){
		txtfSurname.setText(customer.getFirstName());
		txtfLastname.setText(customer.getLastName());
		txtfEmail.setText(customer.getEmail());
		txtfAdress.setText(customer.getAddress());
		txtfPostcode.setText(customer.getPostCode());
		txtfCity.setText(customer.getPostAddress());
		txtfPhone.setText(customer.getPhoneNumber());
	}
	
	//private Boolean goToChoosePayment = true; 
	public void register(ActionEvent evt){
		
		ChoosePayment choosePayment = new ChoosePayment();
		ChangeSupport.getInstance().fireNewEvent("", choosePayment);

	
	}
	
	public void useProfile(MouseEvent evt){
		txtfSurname.setText(customer.getFirstName());
		txtfLastname.setText(customer.getLastName());
		txtfEmail.setText(customer.getEmail());
		txtfAdress.setText(customer.getAddress());
		txtfPostcode.setText(customer.getPostCode());
		txtfCity.setText(customer.getPostAddress());
		txtfPhone.setText(customer.getPhoneNumber());

	}
	
	public void useOther(MouseEvent evt){
		txtfSurname.clear();
		txtfLastname.clear();
		txtfEmail.clear();
		txtfAdress.clear();
		txtfPostcode.clear();
		txtfCity.clear();
		txtfPhone.clear();
			
		pInfSurname = false;
		pInfLastname = false;
		pInfEmail = false;
		pInfAdress = false;
		pInfPostcode = false;
		pInfCity = false;
		pInfPhone = false;
		
	}
	


}
