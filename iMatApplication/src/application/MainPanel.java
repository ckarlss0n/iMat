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
	
	
	
	int i = 0;
	public void buttonClicked(ActionEvent evt){
		
		
		if(i == 0){
			
			ProfilePanel pp = new ProfilePanel();
			stackPane.getChildren().addAll(pp);
			i++;
		} else{
			stackPane.getChildren().clear();
			OfflinePanel op = new OfflinePanel();
			stackPane.getChildren().addAll(op);
		}
		
		System.out.println("Click");
	}
	
}
