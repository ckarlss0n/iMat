package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;

public class ProcessIndicator extends BorderPane {
	
	@FXML 
	ProgressIndicator progressOverview;
	@FXML 
	ProgressIndicator progressPersInfo;
	@FXML 
	ProgressIndicator progressChoosePayment;
	@FXML 
	ProgressIndicator progressFinished;
	
	public ProcessIndicator(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("progressIndicator.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }  
	}

}
