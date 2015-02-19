package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Screen2Controller implements Initializable, ControlledScreen {
	
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
	private Button btnScreen3;
	
	
	public void goToScreen1(ActionEvent evt){
		myController.setScreen(Main.SCR1ID);
	}
	
	public void goToScreen3(ActionEvent evt){
		myController.setScreen(Main.SCR3ID);
	}

	

}
