package application;

import java.io.File;
import java.io.IOException;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ProductInShoppingCartBig extends BorderPane {
	
	private Product theProduct;
	
	public ProductInShoppingCartBig(ShoppingCartItem item){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productInShoppingCartBig.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } 
        
        theProduct = item.getProduct();
        File image = new File(IMatDataHandler.getInstance().getImageIcon(theProduct).getDescription());
        
        setProductName(theProduct.getName());
        
        productImage.setImage(new Image(image.toURI().toString()));
        lblPrice.setText(theProduct.getPrice() + "0 kr");
        itemDesc.setText("Amount: " + item.getItem().getAmount() + " " + item.getItem().getProduct().getUnitSuffix());
        
        
	}
	
	@FXML 
	private ImageView productImage;
	
	@FXML 
	private Label lblProductName;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private Label itemDesc;
	
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
