package application;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ShoppingCartRight extends BorderPane implements ChangeScreenListener {
	@FXML
	private GridPane gridPane;
	@FXML
	private BorderPane borderPane;
	@FXML
	private Label shoppingCartSum;
	@FXML
	private Button btnSave;
	@FXML
	private Button goToCheckoutBtn;
	
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
			setGoToCheckBtn();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        int sum = 0;
        for(ShoppingItem i: IMatDataHandler.getInstance().getShoppingCart().getItems()){
        	sum += i.getProduct().getPrice() * i.getAmount();
        }
        
        setShoppingCartSum(sum);
        
        ChangeSupport.getInstance().addListner(this);
        
        this.mp = mp;
	}
	
	public GridPane getGridPane(){
		return gridPane;
	}
	
	public BorderPane getBorderPane(){
		return borderPane;
	}
	
	public void setGoToCheckBtn(){
		goToCheckoutBtn.setDisable(IMatDataHandler.getInstance().getShoppingCart().getItems().isEmpty());
	}
	
	public void clearShoppingCart(){
		gridPane.getChildren().clear();
		shoppingCartSum.setText("0.00");
		s = 0;
		setGoToCheckBtn();
	}
	
	public void fullyClearShoppingCart(){ //When clear button clicked
		clearCart = false;
	
		System.out.println("OPEN CONFIRMATION DIALOG HERE!");
		CartConfirmDialog ccd = new CartConfirmDialog();
		
		if(clearCart){
			IMatDataHandler.getInstance().getShoppingCart().clear();
			clearShoppingCart();
		}
		
	}
	
	boolean clearCart = false;
	
	public ShoppingCartItem getShoppingCartItem(ShoppingItem i){
		
		for(Node n : this.getGridPane().getChildren()){
			if(((ShoppingCartItem)n).getItem().equals(i)){
				System.out.println("Equals");
				return (ShoppingCartItem)n;
			}
		}
		
		return null;
	}
	DecimalFormat twoDec = new DecimalFormat("0.00");
	
	public void refreshCart(List<ShoppingItem> list){
		clear();
		
		setGoToCheckBtn();
		int sum = 0;
		for(ShoppingItem si: list){
			addToShoppingCart(si);
			sum += si.getProduct().getPrice() * si.getAmount();
		}
		
		setShoppingCartSum(sum);
		
	}
	
	public void clear(){
		gridPane.getChildren().clear();
		s = 0;
	}
	
	public void setShoppingCartSum(double amount){
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.8), shoppingCartSum);
		fadeIn.setFromValue(0.6);
		fadeIn.setToValue(1.0);
		fadeIn.play();
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
	
	public void saveShoppingCart(ActionEvent evt){
		int index = 0;
		int minValue = 0;
		
		for(Order o :IMatDataHandler.getInstance().getOrders()){
			
			if(o.getOrderNumber() < minValue){
				minValue = o.getOrderNumber();
			}
			
		}
		
		if(minValue < 0){
				index = minValue-1;
		} else{
			index = -1;
		}
		System.out.println(index);
		
		IMatDataHandler.getInstance().placeOrder(false).setOrderNumber(index);
	}

	@Override
	public void eventRecieved(TheEvent evt) {
		
		if(evt.getScreen() == null){
			if(evt.getNameOFEvent().equals("Clearcart")){
				clearCart = true;
			}
			if(evt.getNameOFEvent().equals("Cancel")){
				clearCart = false;
			}
		}
	}

}
