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
import se.chalmers.ait.dat215.project.User;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
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
		
		for (ProductCategory c : ProductCategory.values()){
			String name = getCategoryName(c); 
			System.out.println(ProductCategory.valueOf(c.toString()));
			TitledPane t = new TitledPane(name, new AnchorPane());
			categoryAccordation.getPanes().add(t);
		}
		
	}
	
	@FXML
	private StackPane stackPane;
	
	@FXML 
	private BorderPane borderPane;
	
	
	int i = 0;
	public void buttonClicked(ActionEvent evt){
		
		System.out.println(productList.size());
		List<Product> categoryList = new ArrayList<Product>();
		
		
		for (int i = 0; i<productList.size(); i ++){
			if(productList.get(i).getCategory() == ProductCategory.BERRY){
				categoryList.add(productList.get(i));
			}
		}
		
		if(i == 0){
			List_Nx1_view l = new List_Nx1_view(this, categoryList);
			
			ProfilePanel pp = new ProfilePanel();
			stackPane.getChildren().clear();
			stackPane.getChildren().addAll(l);
			i++;
		/*}else if(i == 1){
			
			RegisterPanel rp = new RegisterPanel();
			
			stackPane.getChildren().clear();
			LoginPanel lp = new LoginPanel();
			stackPane.getChildren().add(rp);
		*/
			
		}else{
			
			stackPane.getChildren().clear();
			
			ShoppingCartBig scb = new ShoppingCartBig();
			ProcessIndicator pi = new ProcessIndicator();
			
			
			
			borderPane.getChildren().clear();
			borderPane.setCenter(pi);
			
			/*
			OfflinePanel op = new OfflinePanel();
			OnlinePanel onp = new OnlinePanel();
			
			onp.setWidth(5 * 190);
			for(int i = 0; i < 5; i++){
				SmallProductPanel smpp = new SmallProductPanel();
				
				Product p = productList.get(i);
				File fimg = new File(dataHandler.getImageIcon(p).getDescription());
				smpp.setProductImage(new Image(fimg.toURI().toString()));
				smpp.setProductName(p.getName());
				
				onp.add(smpp, i);
			}*/
			stackPane.getChildren().addAll(scb);
		}
		
		System.out.println("Click");
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
		System.out.println("Hej");
		
	}
	
	
}
