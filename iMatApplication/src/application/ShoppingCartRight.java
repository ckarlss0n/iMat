package application;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ShoppingCartRight extends BorderPane{
	@FXML
	private GridPane gridPane;
	@FXML
	private BorderPane borderPane;
	@FXML
	private Label shoppingCartSum;
	
	private MainPanel mp;
	
	public void goToCheckOutv(ActionEvent evt){
		mp.goToCheckout();
	}
	
	public ShoppingCartRight(MainPanel mp){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartRight.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        this.mp = mp;
	}
	
	public GridPane getGridPane(){
		return gridPane;
	}
	
	public BorderPane getBorderPane(){
		return borderPane;
	}
	
	public void clearShoppingCart(){
		
		gridPane.getChildren().clear();
		shoppingCartSum.setText("0.00");
		s = 0;
	}
	

	public ShoppingCartItem getShoppingCartItem(ShoppingItem i){
		
		for(Node n : this.getGridPane().getChildren()){
			if(((ShoppingCartItem)n).getItem().equals(i)){
				System.out.println("Equals");
				return (ShoppingCartItem)n;
			}
		}
		
		return null;
	}
	DecimalFormat twoDec = new DecimalFormat("#.00");
	
	public void refreshCart(List<ShoppingItem> list){
		clear();
		for(ShoppingItem si: list){
			addToShoppingCart(si);
		}
	}
	
	public void clear(){
		gridPane.getChildren().clear();
		s = 0;
	}
	public void setShoppingCartSum(double amount){
		shoppingCartSum.setText(String.valueOf((twoDec.format(amount))));
	}
	
	public double getShoppingCartSum(){
		return Double.valueOf(shoppingCartSum.getText());
	}

	int s = 0;
	public void addToShoppingCart(ShoppingItem i) {
		//dataHandler.getShoppingCart().addProduct(p, 1);
		ShoppingCartItem sci = new ShoppingCartItem(i);
		
		gridPane.setPrefHeight((s + 1) * 36);
		gridPane.add(sci, 0, s);

		s++;
		
	}

}
