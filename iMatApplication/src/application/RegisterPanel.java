package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class RegisterPanel extends ScrollPane {

	public RegisterPanel() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registrationPanel.fxml"));
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
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("user.txt", true));
			
			writer.write("#surname");
			writer.newLine();
			writer.write(txtfSurname.getText());
			writer.newLine();
			writer.write("#lastname");
			writer.newLine();
			writer.write(txtfLastname.getText());
			writer.newLine();
			writer.write("#email");
			writer.newLine();
			writer.write(txtfEmail.getText());
			writer.newLine();
			writer.write("#adress");
			writer.newLine();
			writer.write(txtfAdress.getText());
			writer.newLine();
			writer.write("#postcode");
			writer.newLine();
			writer.write(txtfPostcode.getText());
			writer.newLine();
			writer.write("#city");
			writer.newLine();
			writer.write(txtfCity.getText());
			writer.newLine();
			writer.write("#phone");
			writer.newLine();
			writer.write(txtfPhone.getText());
			writer.newLine();
			writer.write("#password");
			writer.newLine();
			writer.write(txtfPassword.getText());
			writer.newLine();
			
			writer.close();
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
