package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class OnlinePanel extends ScrollPane{
	
	public OnlinePanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homeOnlinePanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	@FXML
	private GridPane gridPane;
	
	public void add(Node node, int col){
		gridPane.setConstraints(node, col, 0); 
		gridPane.getChildren().addAll(node);
		//gridPane.setPrefWidth(col*180);
	}
	
	public void setWidth(int i){
		gridPane.setPrefWidth(i);
	}
}
