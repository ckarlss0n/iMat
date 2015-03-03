package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class ShoppingCartItem extends BorderPane {

	private Product theProduct;
	private int panelId;
	private ShoppingItem theItem;

	public ShoppingCartItem(ShoppingItem i) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"shoppingCartSmallItem.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		theItem = i;
		theProduct = i.getProduct();
		panelId = i.getProduct().getProductId();
		
		setName(i.getProduct().getName());
		setAmount(i.getAmount());
		setPrice(i.getAmount() * i.getProduct().getPrice());
	}

	@FXML
	private Label lblName;

	@FXML
	private Label lblAmount;

	@FXML
	private Label lblPrice;

	DecimalFormat twoDec = new DecimalFormat("#.00");

	DecimalFormat noDec = new DecimalFormat("#");
	
	public void setName(String name){
		lblName.setText(name);
	}

	public void setPrice(double price) {
		lblPrice.setText(String.valueOf(twoDec.format(price) + "kr"));
	}
	
	public void setAmount(double amount){
		lblAmount.setText(String.valueOf(noDec.format(amount)) + theProduct.getUnitSuffix());
	}

	public Product getProduct() {
		return theProduct;
	}

	public int getPanelId() {
		return panelId;
	}

	public void decreaseAmount(ActionEvent evt) {
		decreaseAmount();
	}

	public void decreaseAmount(){
		if(theItem.getAmount()-1>0){
			theItem.setAmount(theItem.getAmount()-1);
			lblAmount.setText(String.valueOf(noDec.format(theItem.getAmount())) + theItem.getProduct().getUnitSuffix());
			IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(theItem, false);
		} else { //Remove the product from the shopping cart if amount is zero
			removeFromCart();
		}
	}
	
	public void increaseAmount(ActionEvent evt) {
		theItem.setAmount(theItem.getAmount()+1);
		lblAmount.setText(String.valueOf(noDec.format(theItem.getAmount())) + theItem.getProduct().getUnitSuffix());
		IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(theItem, false);
	}

	public void removeFromCart(ActionEvent evt) {
		removeFromCart();
	}
	
	public void removeFromCart(){
		System.out.println("Remove from cart!");
		IMatDataHandler.getInstance().getShoppingCart().removeItem(theItem);
		IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(theItem, false);
	}
	
	public ShoppingItem getItem(){
		return this.theItem;
	}
	
	public void update(ShoppingItem i){
		theItem = i;
		setAmount(theItem.getAmount());
		setPrice(theItem.getAmount() * theItem.getProduct().getPrice());
	}

}