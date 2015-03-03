package application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
import se.chalmers.ait.dat215.project.ShoppingItem;
import se.chalmers.ait.dat215.project.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MainPanel extends BorderPane implements PropertyChangeListener, ShoppingCartListener {

	private List<Product> productList;
	private IMatDataHandler dataHandler;
	private ShoppingCartBig shoppingCartBig;
	private ProcessIndicator progressIndicator = new ProcessIndicator();
	private OnlinePanel onlinePanel = new OnlinePanel();
	private ProfilePanel profilePanel;
	private ShoppingCartRight shoppingCartRight;
	private ChoosePayment choosePayment = new ChoosePayment();
	private PersonalInformationPanel pInf = new PersonalInformationPanel(this, choosePayment);

	@FXML
	private Accordion categoryAccordation;
	@FXML
	private StackPane stackPane;
	@FXML
	private BorderPane bigBorder;
	@FXML
	private TextField searchField;
	@FXML
	private Button categoryBtn;

	public MainPanel() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//dataHandler.getShoppingCart().

		dataHandler = IMatDataHandler.getInstance();
		productList = dataHandler.getProducts();

		User theUser = dataHandler.getUser();

		theUser.setUserName("Emil");
		theUser.setPassword("123");

		Customer theCustomer = dataHandler.getCustomer();
		theCustomer.setFirstName("John");
		theCustomer.setLastName("Doe");
		theCustomer.setEmail("john.doe@example.com");
		theCustomer.setAddress("Ringv�gen 239");
		theCustomer.setPostCode("41280");
		theCustomer.setPostAddress("G�teborg");
		theCustomer.setPhoneNumber("0705326742");

		stackPane.getChildren().add(onlinePanel);

		for (ProductCategory c : ProductCategory.values()) {
			String name = getCategoryName(c);

			// System.out.println(ProductCategory.valueOf(c.toString()));
			// TitledPane t = new TitledPane(name, new AnchorPane());

			EventHandler<MouseEvent> mousehandler = new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {

					fillProductView(((CategoryTitledPane) mouseEvent
							.getSource()).getProducts());
					categoryBtn.setText(((CategoryTitledPane) mouseEvent
							.getSource()).getText());
					System.out.println("hi");
				}
			};

			List<Product> theCategoryList = new ArrayList<Product>();
			
			for (Product p : productList) {

				if (p.getCategory() == c) {
					theCategoryList.add(p);
				}
			}
			CategoryTitledPane ctp = new CategoryTitledPane(name,
					theCategoryList);

			ctp.setOnMouseClicked(mousehandler);
			categoryAccordation.getPanes().add(ctp);

		}
		profilePanel = new ProfilePanel();
		shoppingCartRight = new ShoppingCartRight(this);
		bigBorder.setRight(shoppingCartRight);
		dataHandler.getShoppingCart().addShoppingCartListener(this);
	}

	public void fillProductView(List<Product> productList) {
		List_Nx1_view l = new List_Nx1_view(this, productList);
		changeScreen(l);
	}

	int i = 0;

	public void goToMyProfile(ActionEvent evt) {
		changeScreen(profilePanel);
	}

	int s = 0;

	public void addToShoppingCart(ShoppingItem i) {
		//dataHandler.getShoppingCart().addProduct(p, 1);
		ShoppingCartItem sci = new ShoppingCartItem(i);

		shoppingCartRight.getGridPane().setPrefHeight((s + 1) * 36);
		shoppingCartRight.getGridPane().add(sci, 0, s);

		s++;
	}

	public String getCategoryName(ProductCategory c){
		switch(c.toString()){
		case "BERRY":
			return "B�r";
		case "BREAD":
			return "Br�d";
		case "POD":
			return "Baljv�xter";
		case "CITRUS_FRUIT":
			return "Citrus frukter";
		case "HOT_DRINKS":
			return "Varma drycker";
		case "COLD_DRINKS":
			return "Kalla drycker";
		case "EXOTIC_FRUIT":
			return "Exotiska frukter";
		case "FISH":
			return "Fisk";
		case "VEGETABLE_FRUIT":
			return "Gr�nsaksfrukt";
		case "CABBAGE":
			return "K�l";
		case "MEAT":
			return "K�tt";
		case "DAIRIES":
			return "Mejeri";
		case "MELONS":
			return "Melon";
		case "FLOUR_SUGAR_SALT":
			return "Mj�l, socker, salt";
		case "NUTS_AND_SEEDS":
			return "N�tter och fr�n";
		case "PASTA":
			return "Pasta";
		case "POTATO_RICE":
			return "Potatis ris";
		case "ROOT_VEGETABLE":
			return "Rotfrukt";
		case "FRUIT":
			return "Frukt";
		case "SWEET":
			return "Godis";
		case "HERB":
			return "�rter";
		}
		return c.toString();
	}

	public void fillShoppingCart(ShoppingCartBig scb) {
		for (int k = 0; k < shoppingCartRight.getGridPane().getChildren()
				.size(); k++) {
			ProductInShoppingCartBig piscb = new ProductInShoppingCartBig(
					(ShoppingCartItem) shoppingCartRight.getGridPane()
							.getChildren().get(k));
			scb.add(piscb, k);
		}
	}

	public void goToCheckout() {
		shoppingCartBig = new ShoppingCartBig(this, pInf);
		fillShoppingCart(shoppingCartBig);
		changeScreen(shoppingCartBig);
	}

	public void goToHome(ActionEvent evt) {
		changeScreen(onlinePanel);
	}

	public void changeScreen(Node node) {
		stackPane.getChildren().clear();
		stackPane.getChildren().add(node);
		if (node.equals(shoppingCartBig) || node.equals(pInf) || node.equals(choosePayment)) {
			bigBorder.setRight(progressIndicator);
		} else {
			bigBorder.setRight(shoppingCartRight);
		}
	}

	//Bug if found product already in shoppingcart. Shows duplicates in list.
	public void searchForProducts(ActionEvent evt){
		if(!searchField.getText().isEmpty()){
			List<Product> foundProducts = dataHandler.findProducts(searchField.getText());
			List_Nx1_view productView = new List_Nx1_view(this, foundProducts);
			changeScreen(productView);
		}
	}

	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		/*
		List<ShoppingItem> shoppingCartItems = IMatDataHandler.getInstance()
				.getShoppingCart().getItems();
		Product product = (Product) evt.getNewValue();
		System.out.println(IMatDataHandler.getInstance().getShoppingCart()
				.getTotal());
		shoppingCartRight.setShoppingCartSum(shoppingCartRight
				.getShoppingCartSum() + product.getPrice());
		boolean productAlreadyInCart = false;
		int iteration = 0;
		int index = 0;
		for (ShoppingItem cartItem : shoppingCartItems) {

			if (product.getProductId() == cartItem.getProduct().getProductId()) {
				productAlreadyInCart = true;
				index = iteration;
			}
			iteration++;
		}

		if (!productAlreadyInCart) {
			addToShoppingCart((Product) evt.getNewValue());

		} else {
			ShoppingCartItem shoppingCartItem = (ShoppingCartItem) shoppingCartRight
					.getGridPane().getChildren().get(index);
			shoppingCartItem.increaseAmount();
		}*/

	}
	
	int k = 0;
	public void updateShoppingCart(ShoppingItem i){
		boolean alreadyInCart = false;
		
		if(shoppingCartRight.getGridPane().getChildren().size() < 1){
			ShoppingCartItem sci = new ShoppingCartItem(i);
			
			shoppingCartRight.getGridPane().setPrefHeight((k + 1) * 36);
			shoppingCartRight.getGridPane().add(sci, 0, k);
			k++;
		} else{
			for(Node sci: shoppingCartRight.getGridPane().getChildren()){
				//((ShoppingCartItem)sci);
				System.out.println("loop");
				
				if(((ShoppingCartItem)sci).getItem().getProduct().getProductId() == i.getProduct().getProductId()){
					((ShoppingCartItem)sci).setAmount(i.getAmount());
					System.out.println(i.getAmount());
					System.out.println(dataHandler.getShoppingCart().getItems().size());
					System.out.println("Update");
				}
			}
			
			
		}
		
	}
	

	@Override
	public void shoppingCartChanged(CartEvent evt) {
		System.out.println("ShoppingCartListner");
		
		ShoppingItem theItem = (ShoppingItem) evt.getShoppingItem();
		//System.out.println(shoppingCartItems.size());
		if(evt.isAddEvent()){
			addToShoppingCart(theItem);
			System.out.println(theItem.getAmount());
		}
		
		shoppingCartRight.getShoppingCartItem(theItem).update(theItem);
		
		double sum = 0;
		
		for(Node sci: shoppingCartRight.getGridPane().getChildren()){
			sum += ((ShoppingCartItem)sci).getItem().getAmount()*((ShoppingCartItem)sci).getItem().getProduct().getPrice();
		}
		
		shoppingCartRight.setShoppingCartSum(sum);
	
	}
}
