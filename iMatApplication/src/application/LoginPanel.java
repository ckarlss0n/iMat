package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginPanel extends Pane{
	
	public LoginPanel(){
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginPanel.fxml"));
	        fxmlLoader.setRoot(this);
	        fxmlLoader.setController(this);

	        try {
	            fxmlLoader.load();
	            rememberMe();
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
	
	@FXML
	private CheckBox rememberMe;
	

	BufferedWriter writer;
	BufferedReader reader;
		
	private void rememberMe(){
		try {
			reader = new BufferedReader(new FileReader("loginSaved.txt"));
			txtfUsername.setText(reader.readLine());
			txtfPassword.setText(reader.readLine());
			if (reader.readLine().equals("true")){
				rememberMe.setSelected(true);
			}
			reader.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("notfound");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public boolean logIn(ActionEvent evt){
		String username = txtfUsername.getText();
		String password = txtfPassword.getText();
		String usernameRead = null;
		String passwordRead = null;
		String line;
		User user = IMatDataHandler.getInstance().getUser();
		
		try {
			
			reader = new BufferedReader(new FileReader("user.txt"));
			
			while((line = reader.readLine()) != null){
        		if(line.equals("#email")){
        			usernameRead = reader.readLine();
        		}
        		if(line.equals("#password")){
        			passwordRead = reader.readLine();
       			}    			
        		if (usernameRead.equals(username) && passwordRead.equals(password)){
        			reader.close();
        			user.setUserName(usernameRead);
        			user.setPassword(passwordRead);
    				writer = new BufferedWriter(new FileWriter("loginSaved.txt"));
        			if (rememberMe.isSelected()){
        				writer.write(username);
        				writer.newLine();
        				writer.write(password);
        				writer.newLine();
        				writer.write("true");
        			} 
        			writer.close();
        			return true;
        		}	
        	}
			reader.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("no matching user found");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch	(IOException e) {
			e.printStackTrace();
		} finally {
			return false;
		}
	}
	

}
