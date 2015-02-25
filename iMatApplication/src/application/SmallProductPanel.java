package application;

import java.io.IOException;

import javax.swing.ImageIcon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SmallProductPanel extends AnchorPane {
	
	public SmallProductPanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("smallProductPanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	@FXML
	private ImageView imgProduct;
	
	public void setProductImage(Image img){
		imgProduct.setImage(img);
	}
	
	@FXML
	private Label lblProductName;
	
	public void setProductName(String name){
		lblProductName.setText(name);
	}
	
}
