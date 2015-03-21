package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;










import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ProfilePanel extends ScrollPane{
	
	@FXML
	private TabPane profileTabPane;
	
	@FXML
	private Tab infoTab;
	
	@FXML
	private Tab historyTab;
	
	@FXML
	private Accordion infoAccordion;
	
	@FXML
	private TitledPane infoPane;
	
	@FXML
	private TextField firstNameField;
	
	@FXML
	private TextField lastNameField;
	
	@FXML
	private TextField emailField;
	
	@FXML
	private TextField addressField;
	
	@FXML
	private TextField postalCodeField;
	
	@FXML
	private TextField cityField;
	
	@FXML
	private TextField phoneField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private ProgressIndicator progressIndicator;
	
	@FXML
	private Accordion historyAccordion;
	
	@FXML
	private Label bonusLevelLabel;
	
	@FXML
	private Button saveChangesBtn;
	@FXML
	private CheckBox nutBox;
	@FXML
	private CheckBox beanBox;
	@FXML
	private CheckBox wheatBox;
	@FXML
	private CheckBox shellBox;
	@FXML
	private CheckBox eggBox;
	
	List<ShoppingItem> currentCart;

	List<Product> ao;
	
	private Boolean firstName = true;
	private Boolean lastName = true;
	private Boolean email = true;
	private Boolean address = true;
	private Boolean postalCode = true;
	private Boolean city = true;
	private Boolean phone = true;
	private Boolean password = true;
	
	public ProfilePanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profilePanel.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        ao = new ArrayList<Product>();
        
        for(Order o : IMatDataHandler.getInstance().getOrders()){
        	if(o.getOrderNumber() == 1336){
        		for(ShoppingItem si : o.getItems()){
           			ao.add(si.getProduct());
           			}
           		}
           	}
		nutBox.setSelected(ao.contains(IMatDataHandler.getInstance().getProduct(97)));
		beanBox.setSelected(ao.contains(IMatDataHandler.getInstance().getProduct(1)));
		wheatBox.setSelected(ao.contains(IMatDataHandler.getInstance().getProduct(96)));
		shellBox.setSelected(ao.contains(IMatDataHandler.getInstance().getProduct(49)));
		eggBox.setSelected(ao.contains(IMatDataHandler.getInstance().getProduct(85)));

        
    	infoAccordion.setExpandedPane(infoPane);
    	firstNameField.setText(IMatDataHandler.getInstance().getCustomer().getFirstName());
    	lastNameField.setText(IMatDataHandler.getInstance().getCustomer().getLastName());
    	emailField.setText(IMatDataHandler.getInstance().getCustomer().getEmail());
    	addressField.setText(IMatDataHandler.getInstance().getCustomer().getAddress());
    	postalCodeField.setText(IMatDataHandler.getInstance().getCustomer().getPostCode());
    	cityField.setText(IMatDataHandler.getInstance().getCustomer().getPostAddress());
    	phoneField.setText(IMatDataHandler.getInstance().getCustomer().getPhoneNumber());
    	passwordField.setText(IMatDataHandler.getInstance().getUser().getPassword());
    	
    	
    	if(firstNameField.getText().matches("^[A-ZÅÄÖa-zåäöé+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?")){
			firstName = true;
			lastName = true;
			email = true;
			address = true;
			postalCode = true;
			city = true;
			phone = true;
			password = true;
        }else{
        	firstName = false;
			lastName = false;
			email = false;
			address = false;
			postalCode = false;
			city = false;
			phone = false;
			password = false;
        }
    	
    	saveChangesBtn.setDisable(true);
		setBtnVisible();
    	
    	
    	firstNameField.focusedProperty().addListener(new ChangeListener<Boolean>()
    			{
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    	    {
    	        if (newPropertyValue){
    	        	
    	        }
    	        else{
    	            if(firstNameField.getText().matches("^[A-ZÅÄÖa-zåäö+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?")){
    	            	firstNameField.setStyle("-fx-border-width: 0px ;");
    	            	firstName = true;
    	            	IMatDataHandler.getInstance().getCustomer().setFirstName(firstNameField.getText());
    	            	setBtnVisible();
    	            }else{
    	            	firstNameField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    	            	firstName = false;
    	            	saveChangesBtn.setDisable(true);
    	            }
    	        }
    	    }
    	});
    	
    	lastNameField.focusedProperty().addListener(new ChangeListener<Boolean>()
    			{
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    	    {
    	        if (newPropertyValue){
    	        	
    	        }
    	        else{
    	            if(lastNameField.getText().matches("^[A-ZÅÄÖa-zåäö+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?")){
    	            	lastNameField.setStyle("-fx-border-width: 0px ;");
    	            	IMatDataHandler.getInstance().getCustomer().setLastName(lastNameField.getText());
    	            	lastName = true;
    	            	setBtnVisible();
    	            }else{
    	            	lastNameField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    	            	lastName = false;
    	            	saveChangesBtn.setDisable(true);
    	            }
    	        }
    	    }
    	});
    	
    	emailField.focusedProperty().addListener(new ChangeListener<Boolean>()
    			{
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    	    {
    	        if (newPropertyValue){
    	        	
    	        }
    	        else{
    	            if(emailField.getText().matches("^[a-zA-Z0-9+_.-]+[@][a-zA-Z0-9_-]+\\.([a-zA-Z0-9+_.-]*)?[a-zA-Z0-9+_-]")){
    	            	emailField.setStyle("-fx-border-width: 0px ;");
    	            	IMatDataHandler.getInstance().getCustomer().setEmail(emailField.getText());
    	            	email = true;
    	            	setBtnVisible();
    	            }else{
    	            	emailField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    	            	email = false;
    	            	saveChangesBtn.setDisable(true);
    	            }
    	        }
    	    }
    	});
    	
    	addressField.focusedProperty().addListener(new ChangeListener<Boolean>()
    			{
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    	    {
    	        if (newPropertyValue){
    	        	
    	        }
    	        else{
    	            if(addressField.getText().matches("^[A-ZÅÄÖa-zåäö+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?(\\s\\d+$)*?")){
    	            	addressField.setStyle("-fx-border-width: 0px ;");
    	            	IMatDataHandler.getInstance().getCustomer().setAddress(addressField.getText());
    	            	address = true;
    	            	setBtnVisible();
    	            }else{
    	            	addressField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    	            	address = false;
    	            	saveChangesBtn.setDisable(true);
    	            }
    	        }
    	    }
    	});
    	
    	postalCodeField.focusedProperty().addListener(new ChangeListener<Boolean>()
    			{
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    	    {
    	        if (newPropertyValue){
    	        	
    	        }
    	        else{
    	            if( postalCodeField.getText().matches("[0-9]{5}")){
    	            	postalCodeField.setStyle("-fx-border-width: 0px ;");
    	            	IMatDataHandler.getInstance().getCustomer().setPostCode(postalCodeField.getText());
    	            	postalCode = true;
    	            	setBtnVisible();
    	            }else{
    	            	postalCodeField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    	            	postalCode = false;
    	            	saveChangesBtn.setDisable(true);
    	            }
    	        }
    	    }
    	});
    	
    	cityField.focusedProperty().addListener(new ChangeListener<Boolean>()
    			{
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    	    {
    	        if (newPropertyValue){
    	        	
    	        }
    	        else{
    	            if(cityField.getText().matches("^[A-ZÅÄÖa-zåäö+-]+(\\s[A-ZÅÄÖa-zåäö+-]*)*?")){
    	            	cityField.setStyle("-fx-border-width: 0px ;");
    	            	IMatDataHandler.getInstance().getCustomer().setPostAddress(cityField.getText());

    	            	city = true;
    	            	setBtnVisible();
    	            }else{
    	            	cityField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    	            	city = false;
    	            	saveChangesBtn.setDisable(true);
    	            }
    	        }
    	    }
    	});
    	
    	phoneField.focusedProperty().addListener(new ChangeListener<Boolean>()
    			{
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    	    {
    	        if (newPropertyValue){
    	        	
    	        }
    	        else{
    	            if(phoneField.getText().matches("[0-9]+")){
    	            	phoneField.setStyle("-fx-border-width: 0px ;");
    	            	phone = true;
    	            	IMatDataHandler.getInstance().getCustomer().setPhoneNumber(phoneField.getText());
    	            	setBtnVisible();
    	            }else{
    	            	phoneField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    	            	phone = false;
    	            	saveChangesBtn.setDisable(true);
    	            }
    	        }
    	    }
    	});
    	
    	passwordField.focusedProperty().addListener(new ChangeListener<Boolean>()
    			{
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    	    {
    	        if (newPropertyValue){
    	        	
    	        }
    	        else{
    	            if(passwordField.getText().matches(".+")){
    	            	passwordField.setStyle("-fx-border-width: 0px ;");
    	            	IMatDataHandler.getInstance().getUser().setPassword(passwordField.getText());
    	            	password = true;
    	            	setBtnVisible();
    	            }else{
    	            	passwordField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    	            	password = false;
    	            	saveChangesBtn.setDisable(true);
    	            }
    	        }
    	    }
    	});
    	
    	fillHistory();
    	
    	double userLevel = (IMatDataHandler.getInstance().getOrders().size()/100.0)%1;
    	bonusLevelLabel.setText("Bonusnivå: " + IMatDataHandler.getInstance().getOrders().size()/100);
    	progressIndicator.setProgress(userLevel);
    	
	}
	
	public void setBtnVisible(){
		if(firstName && lastName && email && address && postalCode && city && phone && password){
    		saveChangesBtn.setDisable(false);
    	}
	}
	
	@FXML
	private ImageView imgFname;
	@FXML
	private ImageView imgLname;
	@FXML
	private ImageView imgEmail;
	@FXML
	private ImageView imgAddress;
	@FXML
	private ImageView imgPostcode;
	@FXML
	private ImageView imgOrt;
	@FXML
	private ImageView imgPhone;
	@FXML
	private ImageView imgPassword;
	
	public void fillHistory(){	
		List<Order> orders = IMatDataHandler.getInstance().getOrders();
		
		
		Collections.sort(orders, new Comparator<Order>() {
		    public int compare(Order o1, Order o2) {
		        return -1*(o1.getDate().compareTo(o2.getDate())); //-1 for reverse order
		    }
		});
		
    	for(Order order : orders){
    		if(order.getItems().size()!=0){ //Only show valid orders
    			if(order.getOrderNumber() != 1336){
    				historyAccordion.getPanes().add(new HistoryTitledPanel(order));
    			}
    		}
    	}
	}
	
	public void doSomething(){
		System.out.println("HEJ");
	}
	
	public void saveProfile(ActionEvent evt){ 
		
			System.out.println("Save profile information.");
			System.out.println(IMatDataHandler.getInstance().getCustomer().getFirstName());
			
			final String content = ""
					+ "Ändringar sparade!";
			 
			 final Animation animation = new Transition() {
			     {
			         setCycleDuration(Duration.millis(1000));
			     }
			 
			     protected void interpolate(double frac) {
			         final int length = content.length();
			         final int n = Math.round(length * (float) frac);
			         saveChangesBtn.setText(content.substring(0, n));
			     }
			 
			 };
			 
			
			animation.play();
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), saveChangesBtn);
			fadeIn.setOnFinished(event -> saveChangesBtn.setText("Spara ändringar"));
			fadeIn.setFromValue(0.5);
			fadeIn.setToValue(1.0);
			
	
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), saveChangesBtn);
			fadeOut.setFromValue(1.0);
	        fadeOut.setToValue(0.5);
	        fadeOut.setOnFinished(event -> fadeIn.play());
			fadeOut.play();
		
		if(nutBox.isSelected() || beanBox.isSelected() || wheatBox.isSelected() || shellBox.isSelected() || eggBox.isSelected()){
			currentCart = new ArrayList();
			
			for(ShoppingItem sci : IMatDataHandler.getInstance().getShoppingCart().getItems()){
				currentCart.add(sci);
			}
			
			IMatDataHandler.getInstance().getShoppingCart().clear();
			
			fixAllergies();
		
		}
		
	}
	
	public void surnameAction(ActionEvent evt){
		//lastNameField.setText("Haha, de trodde du väll!");
	}
	
	public void fixAllergies(){

		if(nutBox.isSelected()){
			for(int i = 97; i <= 103; i++){
				IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(i));
			}
		}
		
		if(beanBox.isSelected()){
			for(int i = 1; i <= 7; i++){
				IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(i));
			}
		}
		
		if(wheatBox.isSelected()){
			
			IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(96));
			for(int i = 106; i <= 112; i++){
				IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(i));
			}
			for(int i = 8; i <= 14; i++){
				IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(i));
			}
			IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(134));
			IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(136));
			IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(137));
			IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(138));
		}
		
		if(shellBox.isSelected()){
			IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(49));
			IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(51));
		}
		
		if(eggBox.isSelected()){
			IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(85));
			IMatDataHandler.getInstance().getShoppingCart().addProduct(IMatDataHandler.getInstance().getProduct(136));
		}
		
		IMatDataHandler.getInstance().placeOrder(true).setOrderNumber(1336);
		
		for(ShoppingItem sci : currentCart){
			IMatDataHandler.getInstance().getShoppingCart().addItem(sci);
			IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(sci, false);
		}

		
		
	}
	
}
