package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class RegisterPanel extends ScrollPane {

	public RegisterPanel() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"registrationPanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

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
	private TextField txtfPassword;

	public void register(ActionEvent evt) {

		Customer customer = IMatDataHandler.getInstance().getCustomer();
		User user = IMatDataHandler.getInstance().getUser();
		customer.setFirstName(txtfSurname.getText());
		customer.setLastName(txtfSurname.getText());
		customer.setEmail(txtfEmail.getText());
		customer.setAddress(txtfAdress.getText());
		customer.setMobilePhoneNumber(txtfPhone.getText());
		customer.setPostAddress(txtfAdress.getText());
		customer.setPostCode(txtfPostcode.getText());
		user.setPassword(txtfPassword.getText());
		user.setUserName(txtfEmail.getText());

	}
}
