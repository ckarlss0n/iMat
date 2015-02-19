package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Screen3Controller implements Initializable, ControlledScreen {
	
	private ScreensController myController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	@FXML
	private Button btnScreen1;
	@FXML
	private Button btnScreen2;
	
	public void goToScreen1(ActionEvent evt){
		myController.setScreen(Main.SCR1ID);
	}
	
	
	
	public void goToScreen2(ActionEvent evt){
		myController.setScreen(Main.SCR2ID);
	}
	
	


}
