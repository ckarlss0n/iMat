package application;

import java.io.IOException;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import sun.awt.im.InputMethodAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
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
    	
    	progressIndicator.setProgress(0.1);
    	
    	System.out.println(IMatDataHandler.getInstance().getCustomer().getFirstName());
	}
	
	public void saveProfile(ActionEvent evt){ //de sparas ändå.... hmmm.... Rätt säker på att denna koden är rätt, fast kan iten kolla eftersom den ej utnytjas
    	
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
	}
	
}
