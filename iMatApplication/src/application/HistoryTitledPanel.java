package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class HistoryTitledPanel extends TitledPane{
	
	@FXML
	private Label dateLabel;
	@FXML
	private Label sumLabel;
	@FXML
	private Label smallSumLabel;
	
	public HistoryTitledPanel(HistoryCartItem hci) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyTitledPanel.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    	
    	this.getChildren().add(hci);
	}
	
	public void addAllProducts(ActionEvent evt){
		System.out.println("Add all products.");
	}
	
	public void showDetails(ActionEvent evt){
		System.out.println("Show details.");
	}
}
