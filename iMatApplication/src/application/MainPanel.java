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
	private CheckoutPanel checkoutPanel = new CheckoutPanel(this, onlinePanel);
	private ChoosePayment choosePayment = new ChoosePayment(this, checkoutPanel);
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

	public MainPanel() { // här kommer det behövas en if sats som kollar fiall man är inloggad och gör checkout, profil och andra panels baserat på den if satsen! ifall man även är i inte inloggad delen ska man kunna ta sig till inloggad delen.
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

					fillProductView(((CategoryTitledPane) mouseEvent.getSource()));
					categoryBtn.setText(((CategoryTitledPane) mouseEvent
							.getSource()).getText());
					System.out.println("hi");
					
				}
			};

			List<ShoppingItem> theCategoryList = new ArrayList<ShoppingItem>();
			
			for (Product p : productList) {

				if (p.getCategory() == c) {
					theCategoryList.add(new ShoppingItem(p));
				}
			}
			CategoryTitledPane ctp = new CategoryTitledPane(name,
					theCategoryList);

			ctp.setOnMouseClicked(mousehandler);
			categoryAccordation.getPanes().add(ctp);

		}
		
		shoppingCartRight = new ShoppingCartRight(this);
		bigBorder.setRight(shoppingCartRight);
		dataHandler.getShoppingCart().addShoppingCartListener(this);
	}

	public void fillProductView(CategoryTitledPane ctp) {
		List_Nx1_view l = ctp.getListView();
		changeScreen(l);
	}

	int i = 0;

	public void goToMyProfile(ActionEvent evt) {
		profilePanel = new ProfilePanel();
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
			return "Bär";
		case "BREAD":
			return "Bröd";
		case "POD":
			return "Baljväxter";
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
			return "Kål";
		case "MEAT":
			return "Kött";
		case "DAIRIES":
			return "Mejeri";
		case "MELONS":
			return "Melon";
		case "FLOUR_SUGAR_SALT":
			return "Mjöl, socker, salt";
		case "NUTS_AND_SEEDS":
			return "Nötter och frön";
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
			return "Örter";
		}
		return c.toString();
	}
	/*
	public void fillShoppingCart(ShoppingCartBig scb) {
		for (int k = 0; k < shoppingCartRight.getGridPane().getChildren().size(); k++) {
			ProductInShoppingCartBig piscb = new ProductInShoppingCartBig((ShoppingCartItem) shoppingCartRight.getGridPane().getChildren().get(k));
			scb.add(piscb, k);
		}
	}*/

	public void goToCheckout() { 
		shoppingCartBig = new ShoppingCartBig(this, pInf);
		shoppingCartBig.fillShoppingCart();
		//fillShoppingCart(shoppingCartBig);
		changeScreen(shoppingCartBig);
	}

	public void goToHome(ActionEvent evt) {
		changeScreen(onlinePanel);
	}

	public void changeScreen(Node node) {
		stackPane.getChildren().clear();
		stackPane.getChildren().add(node);
		if (node.equals(shoppingCartBig) || node.equals(pInf) || 
				node.equals(choosePayment) || node.equals(checkoutPanel)) {
			bigBorder.setRight(progressIndicator);
		} else {
			bigBorder.setRight(shoppingCartRight);
		}
	}
	
	//Bug if found product already in shoppingcart. Shows duplicates in list.
	public void searchForProducts(ActionEvent evt){
		if(!searchField.getText().isEmpty()){
			
			List<Product> foundProducts = dataHandler.findProducts(searchField.getText());
			List<ShoppingItem> foundItems = new ArrayList<ShoppingItem>();
			for(Product p: foundProducts){
				foundItems.add(new ShoppingItem(p));
				
			}
			List_Nx1_view productView = new List_Nx1_view(foundItems);
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
	/*
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
	}*/

	

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
