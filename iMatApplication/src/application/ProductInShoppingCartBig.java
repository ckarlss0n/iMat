package application;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ProductInShoppingCartBig extends BorderPane {
	
	private Product theProduct;
	
	private ShoppingItem theItem;
	
	public ProductInShoppingCartBig(ShoppingItem item){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productInShoppingCartBig.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } 
        theItem = item;
        
        theProduct = item.getProduct();
        File image = new File(IMatDataHandler.getInstance().getImageIcon(theProduct).getDescription());
        
        setProductName(theProduct.getName());
        
        productImage.setImage(new Image(image.toURI().toString()));
        
        lblPrice.setText(twoDec.format(theProduct.getPrice() * item.getAmount()) + " kr");
        labelAmountAndPrice.setText(noDec.format(item.getAmount()) + " " + theProduct.getUnitSuffix() + " * " + twoDec.format(theProduct.getPrice()) + " kr");
        txtfAmount.setText(String.valueOf(noDec.format(item.getAmount())));
        //itemDesc.setText("Amount: " + item.getAmount() + " " + item.getProduct().getUnitSuffix());
        
        
	}
	DecimalFormat noDec = new DecimalFormat("#");
	DecimalFormat twoDec = new DecimalFormat("#.00");
	
	@FXML
	private Label labelAmountAndPrice;
	@FXML
	private TextField txtfAmount;
	
	@FXML 
	private ImageView productImage;
	
	@FXML 
	private Label lblProductName;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private Label itemDesc;
	
	public void decreaseAmount(ActionEvent evt){
		if(theItem.getAmount()-1>0){
			theItem.setAmount(theItem.getAmount()-1);
			update();
			IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(theItem, false);
		}
	}
	
	public void increaseAmount(ActionEvent evt){
		
	}
	
	public void removeProduct(ActionEvent evt){
		
	}
	
	
	
	public void update(){
		 lblPrice.setText(twoDec.format(theProduct.getPrice() * theItem.getAmount()) + " kr");
		 labelAmountAndPrice.setText(noDec.format(theItem.getAmount()) + " " + theProduct.getUnitSuffix() + " * " + 
				 								twoDec.format(theProduct.getPrice()) + " kr");
		 
		 txtfAmount.setText(String.valueOf(noDec.format(theItem.getAmount())));
		 
	}
	
	public void setProductImage(Image img){
		productImage.setImage(img);
	}
	
	public void setProductName(String name){
		lblProductName.setText(name);
	}
	
	public void setPrice(double price){
		lblPrice.setText(String.valueOf(price) + "0 kr");
	}
}
