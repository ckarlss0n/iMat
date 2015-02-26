package application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingItem;
import se.chalmers.ait.dat215.project.User;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;



public class MainPanel extends BorderPane implements PropertyChangeListener{
	
	private List<Product> productList;
	private IMatDataHandler dataHandler;
	
	
	@FXML 
	private Accordion categoryAccordation;
	
	public MainPanel(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
	    fxmlLoader.setRoot(this);
    	fxmlLoader.setController(this);

    	try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    	
    	
		dataHandler = IMatDataHandler.getInstance();
		productList = dataHandler.getProducts();
		
		User theUser = dataHandler.getUser();
		
		theUser.setUserName("Emil");
		theUser.setPassword("123");
		OnlinePanel onp = new OnlinePanel();
		stackPane.getChildren().add(onp);
		
		for (ProductCategory c : ProductCategory.values()){
			String name = getCategoryName(c);
			
			//System.out.println(ProductCategory.valueOf(c.toString()));
			//TitledPane t = new TitledPane(name, new AnchorPane());
			
			
			EventHandler<MouseEvent> mousehandler = new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent mouseEvent) {
			    	
			    	fillProductView(((CategoryTitledPane)mouseEvent.getSource()).getProducts());
			    	
			        System.out.println("hi");
			    }
			};
			
			List<Product> theCategoryList = new ArrayList<Product>();
			for(Product p: productList){
	    		
				if(p.getCategory() == c){
	    			theCategoryList.add(p);
	    		}
			}
			CategoryTitledPane ctp = new CategoryTitledPane(name, theCategoryList);
			
			
			ctp.setOnMouseClicked(mousehandler);
			categoryAccordation.getPanes().add(ctp);
			
		}
		
	}
	
	@FXML
	private StackPane stackPane;
	
	@FXML 
	private BorderPane borderPane;
	
	public void fillProductView(List<Product> productList){
		stackPane.getChildren().clear();
		List_Nx1_view l = new List_Nx1_view(this, productList);
		stackPane.getChildren().add(l);
	}
	
	
	int i = 0;
	public void goToMyProfile(ActionEvent evt){
		
		stackPane.getChildren().clear();
		ProfilePanel pp = new ProfilePanel();
		stackPane.getChildren().add(pp);
			
	}
	@FXML
	private Accordion shoppingCart;

	public void changeShoppingCart(Product p){
		System.out.println("Emil");
		
		ShoppingCartItem sci = new ShoppingCartItem();
		sci.setTitle(p.getName(), p.getPrice());
		
		System.out.println(shoppingCart.toString());

	    
		
		shoppingCart.getPanes().add(sci);
	}
	
	public String getCategoryName(ProductCategory c){
		switch(c.toString()){
		case "BERRY":
			return "Bär";
		case "BREAD":
			return "Bröd";
		case "POD":
			return "Baljväxter";
		case "CITRUS_FRUIT":
			return "Citrus frukter";
		case "HOT_DRINKS":
			return "Varma drycker";
		}
		return c.toString();
	}
	
	
	public void goToCheckOut(){
		ShoppingCartBig scb = new ShoppingCartBig();
		stackPane.getChildren().clear();
		stackPane.getChildren().add(scb);
		ProcessIndicator pi = new ProcessIndicator();
		
		
		
		borderPane.getChildren().clear();
		borderPane.setCenter(pi);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		List<ShoppingItem> list = IMatDataHandler.getInstance().getShoppingCart().getItems();
		
		Boolean trueFal = true;
 		
		for(ShoppingItem i : list){
			if(((Product)evt.getNewValue()).getProductId() == i.getProduct().getProductId()){
				trueFal = false;
			}
		}
 		
 		if(trueFal){
 			changeShoppingCart((Product)evt.getNewValue());
 		}
		
		
		System.out.println("Hej");
		
	}
	
	
}
