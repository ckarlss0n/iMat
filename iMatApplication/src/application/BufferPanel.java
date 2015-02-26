package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

public class BufferPanel extends AnchorPane {
	
	public BufferPanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buffringPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }  
	}
	
	@FXML
	private ProgressIndicator buffer;
	
	public void setValueOnBuffer(int i){
		
		buffer.setProgress(i);
		
	}
}
