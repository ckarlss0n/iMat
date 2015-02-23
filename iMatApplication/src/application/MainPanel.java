package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MainPanel extends AnchorPane {
	
	public MainPanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	@FXML
	private StackPane stackPane;
	
	
	
	
	public void buttonClicked(ActionEvent evt){
		OfflinePanel op = new OfflinePanel();
		ProfilePanel pp = new ProfilePanel();
		stackPane.getChildren().addAll(pp);
		System.out.println("Click");
	}
	
}
