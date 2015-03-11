package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;

public class ProcessIndicator extends BorderPane {
	
	@FXML 
	private ProgressIndicator progressOverview;
	@FXML 
	private ProgressIndicator progressPersInfo;
	@FXML 
	private ProgressIndicator progressChoosePayment;
	@FXML 
	private ProgressIndicator progressFinished;
	
	@FXML
	private Label lblOverview;
	@FXML
	private Label lblPersInfo;
	@FXML
	private Label lblChoosePayment;	
	@FXML
	private Label lblFinished;
	
	
	
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
	
	public void setOverview(double progress, double opacity){
		progressOverview.setProgress(progress);
		lblOverview.setOpacity(opacity);
	}
	public void setPersInfo(double progress, double opacity){
		progressPersInfo.setProgress(progress);
		lblPersInfo.setOpacity(opacity);
	}
	public void setChoosePayment(double progress, double opacity){
		progressChoosePayment.setProgress(progress);
		lblChoosePayment.setOpacity(opacity);
	}
	public void setFinished(double progress, double opacity){
		progressFinished.setProgress(progress);
		lblFinished.setOpacity(opacity);
	}
	

}
