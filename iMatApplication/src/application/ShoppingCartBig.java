package application;

import java.io.IOException;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShoppingCartBig extends BorderPane {
	
	PersonalInformationPanel pInf;
	MainPanel mainPanel;
	
	public ShoppingCartBig(MainPanel mainPanel, PersonalInformationPanel pInf){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartBig.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.mainPanel = mainPanel;
        this.pInf = pInf;

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
	@FXML
	private Button shopWithoutBtn;
	@FXML
	private Label bigCartSum;
	
	public void shopWithout(ActionEvent evt){
		
		mainPanel.changeScreen(pInf);
		
	}
	
	public void loginCartModal(ActionEvent evt){
		System.out.println("Open login");
		shopWithout(evt); // ÄNDRA EFTER ONSDAG!!
	}

	public void goToRegisterCart(ActionEvent evt){
		System.out.println("Go to register cart");
		shopWithout(evt); // ÄNDRA EFTER ONSDAG!!
	}
	
	public void fillShoppingCart(){
		List<ShoppingItem> shoppingCart = IMatDataHandler.getInstance().getShoppingCart().getItems();
		int index = 0;
		for(ShoppingItem i: shoppingCart){
			ProductInShoppingCartBig pisc = new ProductInShoppingCartBig(i);
			gridPane.add(pisc, 0, index);
			index++;
		}
	}
	
	public void add(Node node, int row){
		gridPane.add(node, 0, row);
	}
	
}
