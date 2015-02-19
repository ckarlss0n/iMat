package application;


import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MyController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private Button btnHej;
	
	@FXML
	private Label lblHej;
	
	int k = 1;
	
	public void changeText(ActionEvent evt){
		
		System.out.println(k++);
		lblHej.setText("halloj");
		
	}
	
	@FXML
	private Pane pnlColor;
	
	@FXML
	private StackPane stackPane;
	
	
	
	public void colorChange(ActionEvent evt){
		Random rand = new Random();
		
		int randNum = rand.nextInt(255);
		System.out.println(randNum);
		
		Pane smallPane1 = new Pane();
		smallPane1.setPrefSize(100, 360);
		smallPane1.setStyle("-fx-background-color: BLACK;");
		
		Pane smallPane2 = new Pane();
		smallPane2.setPrefSize(50, 50);
		smallPane2.setStyle("-fx-background-color: RED;");
		
		stackPane.getChildren().addAll(smallPane1, smallPane2);
		
		stackPane.setAlignment(smallPane1, Pos.TOP_CENTER);
		stackPane.setAlignment(smallPane2, Pos.BOTTOM_CENTER);
		
		//pnlColor.getChildren().addAll(smallPane1, smallPane2);
		
		//pnlColor.setStyle("-fx-background-color: DAE6F3;");
	}
	

}
