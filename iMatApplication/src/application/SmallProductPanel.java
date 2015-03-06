package application;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SmallProductPanel extends AnchorPane {
	
	@FXML 
	private Label lblProductName;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private ComboBox<String> productAmount;
	
	@FXML
	private ImageView starImage;
	
	@FXML
	private Label unitSuffix;
	
	@FXML
	private ImageView imgProduct;
	
	private ShoppingItem sci;
	private Product theProduct;
	
	public SmallProductPanel(ShoppingItem sci){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("smallProductPanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
		
		 for(int i=0; i<10; i++){
	        	productAmount.getItems().add(i, i+1+"");
	      }
	      
		 productAmount.setValue(productAmount.getItems().get(0));
	     
		 this.sci = sci;
		 theProduct = sci.getProduct();
		 
		 File image = new File(IMatDataHandler.getInstance().getImageIcon(theProduct).getDescription());
	     setProductName(theProduct.getName());
	        
	     imgProduct.setImage(new Image(image.toURI().toString()));
	     lblPrice.setText(String.valueOf(twoDec.format(theProduct.getPrice())));
	     unitSuffix.setText(theProduct.getUnit());
	     
	     setStar();
	}
	
	DecimalFormat noDec = new DecimalFormat("#");
	DecimalFormat twoDec = new DecimalFormat("#.00");
	
	public void setStar(){
		File file;
		if(IMatDataHandler.getInstance().isFavorite(sci.getProduct())){
			file = new File("icon32/star-full.png");
		} else {
			file = new File("icon32/star-empty.png");
		}
		Image icon = new Image(file.toURI().toString());
	    starImage.setImage(icon);
	}
	
	
	public void setProductImage(Image img){
		imgProduct.setImage(img);
	}
	
	
	
	public void setProductName(String name){
		lblProductName.setText(name);
	}
	
	public void setPrice(double price){
		lblPrice.setText(twoDec.format(price));
	}
	
	public void addToFavorite(MouseEvent evt) {
		File file;
		if(!IMatDataHandler.getInstance().isFavorite(sci.getProduct())){
			IMatDataHandler.getInstance().addFavorite(sci.getProduct());
			file = new File("icon32/star-full.png");
		} else {
			IMatDataHandler.getInstance().removeFavorite(sci.getProduct());
			file = new File("icon32/star-empty.png");
		}
		Image image = new Image(file.toURI().toString());
	    starImage.setImage(image);
	}
	
	public void setToHalfStar(MouseEvent evt){
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), starImage);
		fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.3);
		fadeOut.play();
	}
	
	public void setToNormal(MouseEvent evt){
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), starImage);
		fadeIn.setFromValue(0.3);
		fadeIn.setToValue(1.0);
		fadeIn.play();
		setStar();
	}
	
	public void addToCart(ActionEvent evt){
		int selectedValue;
		try{
			selectedValue = Integer.parseInt(productAmount.getValue());
		} catch (NumberFormatException e){
			selectedValue = 1;
		}
		
		try{
			for(ShoppingItem i: IMatDataHandler.getInstance().getShoppingCart().getItems()){
				if(i.getProduct().getProductId() == sci.getProduct().getProductId()){
					System.out.println("same");
					sci = i;
				}
			}
			
			if(IMatDataHandler.getInstance().getShoppingCart().getItems().contains(sci)){ //Already in cart
			
				
				if(selectedValue>0){
					sci.setAmount(sci.getAmount() + selectedValue+0.0);
				} else {
					sci.setAmount(sci.getAmount() + 1);
				}
		
				
			IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(sci, false);
				
			}else { //Not in cart
				if(selectedValue>0){
					sci.setAmount(selectedValue+0.0);
				} else {
					sci.setAmount(1);
				}
				
				IMatDataHandler.getInstance().getShoppingCart().addItem(sci);
			}
		
		} catch(IllegalArgumentException r){
			System.out.println("Error");
		}
		
		
	}
	
	
	
}
