package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShoppingCartBig extends BorderPane {
	
	public ShoppingCartBig(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartBig.fxml"));
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
	@FXML
	private Button btnLoginCart;
	
	public void loginCartModal(ActionEvent evt){
		modalDialog();
	}
	
	public void modalDialog(){
		
		Stage loginDialog = new Stage();
        loginDialog.initModality(Modality.WINDOW_MODAL);
        Scene loginScene = new Scene(VBoxBuilder.create()
                .children()
                .alignment(Pos.CENTER)	
                .padding(new Insets(10))
                .build());

        loginDialog.setTitle("Logga in");
        loginDialog.setScene(loginScene);
        loginDialog.show();
      
  }
	
	
	public void add(Node node, int row){
		gridPane.add(node, 0, row);
	}
	
}
