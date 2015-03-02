package application;

import java.io.IOException;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class PersonalInformationPanel extends ScrollPane {
	
	@FXML
	private TextField txtfSurname;
	@FXML
	private TextField txtfLastname;
	@FXML
	private TextField txtfEmail;
	@FXML
	private TextField txtfAdress;
	@FXML
	private TextField txtfPostCode;
	@FXML
	private TextField txtfCity;
	@FXML
	private TextField txtfPhone;
	String city;
	
	public PersonalInformationPanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("personalInformation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	public void register(ActionEvent evt){
		IMatDataHandler.getInstance().getCustomer().setFirstName(txtfSurname.getText());
		IMatDataHandler.getInstance().getCustomer().setLastName(txtfLastname.getText());
		IMatDataHandler.getInstance().getCustomer().setEmail(txtfEmail.getText());
		IMatDataHandler.getInstance().getCustomer().setAddress(txtfAdress.getText());
		IMatDataHandler.getInstance().getCustomer().setPostCode(txtfPostCode.getText());
		this.city = txtfCity.getText();
		IMatDataHandler.getInstance().getCustomer().setPhoneNumber(txtfPhone.getText());
	}
}
