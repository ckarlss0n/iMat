package application;

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
    	
    	fillHistory();
    	
    	double userLevel = (IMatDataHandler.getInstance().getOrders().size()/100.0)%1;
    	bonusLevelLabel.setText("Bonusnivå: " + IMatDataHandler.getInstance().getOrders().size()/100);
    	progressIndicator.setProgress(userLevel);
    	
	}
	
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
	
	private Boolean checkTextFieldsBoolean = false;
	
	public void checkTextFields(){
		if(firstNameField.getText().matches("^[A-ZÅÄÖ]+[a-zåäö]*([-][A-ZÅÄÖ]*[a-zåäö]*)?(\\s[A-ZÅÄÖ]*[a-zåäö]*)?(\\s[A-ZÅÄÖ]*[a-zåäö]*)?") 
				&& lastNameField.getText().matches("^[A-ZÅÄÖ]+[a-zåäö]*([-][A-ZÅÄÖ]*[a-zåäö]*)?(\\s[A-ZÅÄÖ]*[a-zåäö]*)?") 
				&& emailField.getText().matches("^[a-zA-Z0-9+_.-]+[@][a-zA-Z0-9_-]+\\.([a-zA-Z0-9+_.-]*)?[a-zA-Z0-9+_-]") //kunna ta in "+" framför "@"?
				&& addressField.getText().matches("^[A-ZÅÄÖ]+[a-zåäö]*([-][A-ZÅÄÖ]*[a-zåäö]*)?(\\s[A-ZÅÄÖ]*[a-zåäö]*)?(\\s\\d+$)?") 
				&& postalCodeField.getText().matches("[0-9]{5}")
				&& cityField.getText().matches("^[A-ZÅÄÖ]+[a-zåäö]*([-][A-ZÅÄÖ]*[a-zåäö]*)?(\\s[A-ZÅÄÖ]*[a-zåäö]*)?") 
				&& phoneField.getText().matches("[0-9]+")
				&& passwordField.getText().matches(".+")){ 
			
			checkTextFieldsBoolean = true;
			
		}else{
			checkTextFieldsBoolean = false;
			
		}
	}
	
	public void saveProfile(ActionEvent evt){ 
		checkTextFields(); 
    	if(checkTextFieldsBoolean){
    		
    		IMatDataHandler.getInstance().getCustomer().setFirstName(firstNameField.getText());
        	IMatDataHandler.getInstance().getCustomer().setLastName(lastNameField.getText());
        	IMatDataHandler.getInstance().getCustomer().setEmail(emailField.getText());
        	IMatDataHandler.getInstance().getCustomer().setAddress(addressField.getText());
        	IMatDataHandler.getInstance().getCustomer().setPostCode(postalCodeField.getText());
        	IMatDataHandler.getInstance().getCustomer().setPostAddress(cityField.getText());
        	IMatDataHandler.getInstance().getCustomer().setPhoneNumber(phoneField.getText());
        	IMatDataHandler.getInstance().getUser().setPassword(passwordField.getText());
    	
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
		
    	}else{
    		System.out.println("sparades inte och gick inte igenom");
    		
    		final String content2 = ""
					+ "Ändringar sparades inte!";
			 
			 final Animation animation = new Transition() {
			     {
			         setCycleDuration(Duration.millis(1000));
			     }
			 
			     protected void interpolate(double frac) {
			         final int length = content2.length();
			         final int n = Math.round(length * (float) frac);
			         saveChangesBtn.setText(content2.substring(0, n));
			     }
			 
			 };
			 
			
			animation.play();
			FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(3), saveChangesBtn);
			fadeIn2.setOnFinished(event -> saveChangesBtn.setText("Spara ändringar"));
			fadeIn2.setFromValue(0.5);
			fadeIn2.setToValue(1.0);
			
	
			FadeTransition fadeOut2 = new FadeTransition(Duration.seconds(0.3), saveChangesBtn);
			fadeOut2.setFromValue(1.0);
	        fadeOut2.setToValue(0.5);
	        fadeOut2.setOnFinished(event -> fadeIn2.play());
			fadeOut2.play();
    	
    	}
		
		if(nutBox.isSelected() || beanBox.isSelected() || wheatBox.isSelected() || shellBox.isSelected() || eggBox.isSelected()){
			currentCart = new ArrayList();
			
			for(ShoppingItem sci : IMatDataHandler.getInstance().getShoppingCart().getItems()){
				currentCart.add(sci);
			}
			
			IMatDataHandler.getInstance().getShoppingCart().clear();
			
			fixAllergies();
		
		}
		
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
