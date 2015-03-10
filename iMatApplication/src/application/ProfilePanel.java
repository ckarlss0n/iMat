package application;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.Timer;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import sun.awt.im.InputMethodAdapter;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
	
	public ProfilePanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profilePanel.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    	
    	
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
    	bonusLevelLabel.setText("Bonusniv�: " + IMatDataHandler.getInstance().getOrders().size()/100);
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
    			historyAccordion.getPanes().add(new HistoryTitledPanel(order));
    		}
    	}
	}
	
	public void doSomething(){
		System.out.println("HEJ");
	}
	
	public void saveProfile(ActionEvent evt){ 
    	
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
		
		
		
		
	}
	
}
