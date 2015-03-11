package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class NoSearchResultPanel extends ScrollPane {

		@FXML
		private Button searchToStartBtn;
		@FXML
		private Label noSearchResultLbl;
		
		private MainPanel mainPanel;
		private OnlinePanel onlinePanel;
		public NoSearchResultPanel(){
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("noSearchResultPanel.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);
			
			
			try {
	    		fxmlLoader.load();
	        } catch (IOException exception) {
	            throw new RuntimeException(exception);
	        }
		}
		
		public void searchToStart(ActionEvent evt){
			ChangeSupport.getInstance().fireNewEvent("Home", null);
			System.out.println("Home");
			//mainPanel.changeScreen(onlinePanel);
			
		}
		
		public void setNoSearchResultLbl(String s){
			noSearchResultLbl.setText(s);
		}
	
}
