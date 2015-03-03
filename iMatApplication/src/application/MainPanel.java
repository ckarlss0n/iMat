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

	private List<ShoppingItem> productList;
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

	public MainPanel() { // h�r kommer det beh�vas en if sats som kollar fiall man �r inloggad och g�r checkout, profil och andra panels baserat p� den if satsen! ifall man �ven �r i inte inloggad delen ska man kunna ta sig till inloggad delen.
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//dataHandler.getShoppingCart().
		productList = new ArrayList<ShoppingItem>();
		
		dataHandler = IMatDataHandler.getInstance();
		System.out.println(dataHandler.getProducts().size());
		for(Product p: dataHandler.getProducts()){
			productList.add(new ShoppingItem(p));
		}
		//productList = dataHandler.getProducts();
		
		//gör till shoppingitems

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
							.getSource()).getItemsInCategory());
					categoryBtn.setText(((CategoryTitledPane) mouseEvent
							.getSource()).getText());
					System.out.println("hi");
				}
			};

			List<ShoppingItem> theCategoryList = new ArrayList<ShoppingItem>();
			
			for (ShoppingItem p : productList) {

				if (p.getProduct().getCategory() == c) {
					theCategoryList.add(p);
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

	public void fillProductView(List<ShoppingItem> productList) {
		List_Nx1_view l = new List_Nx1_view(productList);
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
			return "Grönsaksfrukt";
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
	

	public void goToCheckout() {
		shoppingCartBig = new ShoppingCartBig(this, pInf);
		shoppingCartBig.fillShoppingCart();
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
			setIndicator(node);
		} else {
			bigBorder.setRight(shoppingCartRight);
		}
	}
	
	public void setIndicator(Node node){
		if(node.equals(shoppingCartBig)){
			progressIndicator.progressOverview.setProgress(-1);
			progressIndicator.progressPersInfo.setProgress(0);
			progressIndicator.progressChoosePayment.setProgress(0);
			progressIndicator.progressFinished.setProgress(0);
		} else if(node.equals(pInf)){
			progressIndicator.progressOverview.setProgress(1);
			progressIndicator.progressPersInfo.setProgress(-1);
		} else if(node.equals(choosePayment)){
			progressIndicator.progressPersInfo.setProgress(1);
			progressIndicator.progressChoosePayment.setProgress(-1);
		} else if(node.equals(checkoutPanel)){
			progressIndicator.progressChoosePayment.setProgress(1);
			progressIndicator.progressFinished.setProgress(1);
		} 
	}

	//Bug if found product already in shoppingcart. Shows duplicates in list.
	public void searchForProducts(ActionEvent evt){
		if(!searchField.getText().isEmpty()){
			List<Product> foundProducts = dataHandler.findProducts(searchField.getText());
			List<ShoppingItem> foundItems = new ArrayList<ShoppingItem>();
			for(Product p: foundProducts){
				for(ShoppingItem i: productList){
					if(p.getProductId() == i.getProduct().getProductId()){
						System.out.println("Hej");
						foundItems.add(i);
					}
				}
			}
			List_Nx1_view productView = new List_Nx1_view(foundItems);

			changeScreen(productView);
		}
	}

	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
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

