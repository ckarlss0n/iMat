package application;

import java.io.IOException;
import java.text.DecimalFormat;
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
	@FXML
	private Label bigCartSum;
	
	DecimalFormat twoDec = new DecimalFormat("0.00");
	
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
	private Button goToPersInfoBtn;
	
	public void setGoBtn(){
		goToPersInfoBtn.setDisable(IMatDataHandler.getInstance().getShoppingCart().getItems().isEmpty());
	}

	public void goToPersInfo(ActionEvent evt){
		pInf.pInfSetText();// denna metod hämtar info från profilen till betalnings-personuppgifterna 

		mainPanel.changeScreen(pInf);
	}

	
	public void clear(){
		gridPane.getChildren().clear();
		index = 0;
	}
	
	
	
	public void refresh(List<ShoppingItem> list){
		clear();
		int i = 0;
		double sum = 0;

		setGoBtn();
		for(ShoppingItem si: list){
			addToShoppingCart(si);
			System.out.println(i);
			i++;
			sum += si.getAmount() * si.getProduct().getPrice();
		}
		
		setTotalSum(sum);
	}
	
	int index = 0;
	public void addToShoppingCart(ShoppingItem i){
		
		ProductInShoppingCartBig pisc = new ProductInShoppingCartBig(i);
		gridPane.setPrefHeight(140*index);
		gridPane.add(pisc, 0, index);
		index++;
		
	}
	
	public void fillShoppingCart(){
		
		gridPane.getChildren().clear();
		
		List<ShoppingItem> shoppingCart = IMatDataHandler.getInstance().getShoppingCart().getItems();
		
		double sum = 0;
		for(ShoppingItem i: shoppingCart){
			ProductInShoppingCartBig pisc = new ProductInShoppingCartBig(i);
			gridPane.add(pisc, 0, index);
			index++;
			sum += i.getAmount() * i.getProduct().getPrice();
		}
		
		setTotalSum(sum);
		
		
		
	}
	
	
	public void setTotalSum(double sum){
		bigCartSum.setText(String.valueOf((twoDec.format(sum))));
	}
	
	public void addToShoppingCart(Node node, int row){
		gridPane.add(node, 0, row);
	}
	
}
