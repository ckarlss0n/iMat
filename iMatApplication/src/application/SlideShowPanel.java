package application;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class SlideShowPanel extends BorderPane{
	@FXML
	private Button addToCartBtn;
	@FXML
	private Button forwardBtn;
	@FXML
	private ImageView slideImg;
	@FXML
	private Button backwardBtn;
	@FXML
	private Label productName;
	
	private List<ShoppingItem> shoppingItems;
	
	public SlideShowPanel(List<ShoppingItem> shoppingItems){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("slideShowPanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

		this.shoppingItems = shoppingItems;
		generateProducts();
	}
	
	public void generateProducts(){
		Product theProduct = shoppingItems.get(0).getProduct();
		File image = new File(IMatDataHandler.getInstance().getImageIcon(theProduct).getDescription());
        productName.setText(theProduct.getName());
        slideImg.setImage(new Image(image.toURI().toString()));
	}
	
	public void slideBackward(ActionEvent evt){
		System.out.println("Slide backward");
	}
	
	public void slideForward(ActionEvent evt){
		System.out.println("Slide forward");
	}
	
	public void addToCart(ActionEvent evt){
		System.out.println("Add to cart");
	}
	
}
