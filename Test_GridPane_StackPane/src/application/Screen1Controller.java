package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Screen1Controller implements Initializable, ControlledScreen {
	
	
	private ScreensController myController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@FXML
	private Button btnScreen2;
	
	
	public void goToScreen2(ActionEvent evt){
		myController.setScreen(Main.SCR2ID);
	}
	
	@FXML
	private Button btnScreen3;
	
	
	public void goToScreen3(ActionEvent evt){
		myController.setScreen(Main.SCR3ID);
	}
	
	@FXML
	private GridPane gridPane;
	
	FXMLLoader myLoader = null;
	Parent pane = null;
	
	public void addPane(ActionEvent evt){
		
		try {
			for(int i = 0; i < 3; i++){
				FXMLLoader myLoader = new FXMLLoader(getClass().getResource("SamplePane.fxml"));
				Parent pane = (Parent) myLoader.load();
			
				gridPane.add(pane, 0,i);
				//gridPane.getChildren().addAll(pane);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void removePanels(ActionEvent evt){
		/*
		for(int i = 0; i < 3; i++){
			gridPane.add(null, 0, i);;
		}*/
		
		gridPane.getChildren().clear();
		
	}
	

}
