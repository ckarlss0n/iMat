package application;

import java.io.IOException;
import java.text.DecimalFormat;

import se.chalmers.ait.dat215.project.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class ShoppingCartItem extends BorderPane{
	
	private Product theProduct;
	private int panelId;
	
	public ShoppingCartItem(Product p){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartSmallItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }  
        
        theProduct = p;
        panelId = p.getProductId();
        
        setNameAmountNPrice(p.getName(), 1, p.getPrice());
        
	}
	
	@FXML
	private Label lblName;
	
	@FXML
	private Label lblAmount;
	
	@FXML
	private Label lblPrice;
	
	DecimalFormat twoDec = new DecimalFormat("#.00");
	
	DecimalFormat noDec = new DecimalFormat("#");
	public void setNameAmountNPrice(String name, double amount, double price){
		lblName.setText(name);
		
		lblAmount.setText(String.valueOf(noDec.format(amount)) + " st");
		
		lblPrice.setText(String.valueOf(twoDec.format(price)) + " kr");
	}
	
	public void setPrice(double price){
		
		lblPrice.setText(String.valueOf(twoDec.format(price) + " kr"));
	}
	
	public Product getProduct(Product p){
		return theProduct;
	}
	
	public int getAmount(){
		return Integer.parseInt(lblAmount.getText());
	}
	
	public int getPanelId(){
		return panelId;
	}
	
	public void setAmount(double amount){
		
		lblAmount.setText(String.valueOf(noDec.format(amount)) + " st");
		
		setPrice(this.theProduct.getPrice() * amount);
		
	}
	
	
	
	
	
	

}
