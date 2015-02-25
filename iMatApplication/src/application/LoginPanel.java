package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginPanel extends Pane{
	
	public LoginPanel(){
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginPanel.fxml"));
	        fxmlLoader.setRoot(this);
	        fxmlLoader.setController(this);

	        try {
	            fxmlLoader.load();
	        } catch (IOException exception) {
	            throw new RuntimeException(exception);
	        }
	       
	}
	
	@FXML
	private Button btnLogIn;
	
	@FXML
	private TextField txtfUsername;
	
	@FXML
	private TextField txtfPassword;
	
	BufferedWriter writer = null;
	public boolean logIn(ActionEvent evt){
		String username = txtfUsername.getText();
		String password = txtfPassword.getText();
		
		User user = IMatDataHandler.getInstance().getUser();
		if(username.equals(user.getUserName()) && password.equals(user.getPassword())){
			System.out.println("True");
			return true;
		}else{
			System.out.println("False");
			return false;
		}
	}
	

}
