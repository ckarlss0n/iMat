package application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
import se.chalmers.ait.dat215.project.ShoppingItem;
import se.chalmers.ait.dat215.project.User;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainPanel extends BorderPane implements ChangeListener, ShoppingCartListener, ChangeScreenListener {

	private List<ShoppingItem> productList;
	private IMatDataHandler dataHandler;
	private ShoppingCartBig shoppingCartBig;
	private ProcessIndicator progressIndicator = new ProcessIndicator();
	private OnlinePanel onlinePanel = new OnlinePanel();
	private ProfilePanel profilePanel;
	private ShoppingCartRight shoppingCartRight;
	//private CheckoutPanel checkoutPanel = new CheckoutPanel();
	//private ChoosePayment choosePayment = new ChoosePayment();
	private ChangeSupport changeSupport;
	private PersonalInformationPanel pInf;
	private boolean isOnline = true;
	private List<ShoppingItem> currentList;
	private List<Node> listOfNodes = new ArrayList<Node>();
	private List_Nx1_view lnv = new List_Nx1_view();
	private SquareModeView sqmv = new SquareModeView();
	
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
	@FXML
	private TitledPane favorites;
	@FXML
	private ChoiceBox<String> chbView;
	@FXML
	private ChoiceBox<String> chbSort;
	@FXML
	private TitledPane savedTitledPane;
	
	
	public MainPanel() { // h�r kommer det beh�vas en if sats som kollar fiall man �r inloggad och g�r checkout, profil och andra panels baserat p� den if satsen! ifall man �ven �r i inte inloggad delen ska man kunna ta sig till inloggad delen.
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		changeSupport = ChangeSupport.getInstance();
		changeSupport.addListner(this);
		
		//dataHandler.getShoppingCart().
		productList = new ArrayList<ShoppingItem>();
		
		dataHandler = IMatDataHandler.getInstance();
		
		
		
		for(Product p: dataHandler.getProducts()){
			productList.add(new ShoppingItem(p));
		}
		
		Order o = new Order();
		o.setItems(productList);
		o.setOrderNumber(1);
		
		
		Customer theCustomer = dataHandler.getCustomer();
		pInf = new PersonalInformationPanel(theCustomer);
		changeScreen(onlinePanel);
		
		
		fixCategories();
		
		fillChoiceboxes();
		
		for(Order ord : dataHandler.getOrders()){
			System.out.println(ord.getOrderNumber());
		}
	
		shoppingCartRight = new ShoppingCartRight(this);
		bigBorder.setRight(shoppingCartRight);
		dataHandler.getShoppingCart().addShoppingCartListener(this);
		shoppingCartRight.refreshCart(dataHandler.getShoppingCart().getItems()); 
	}
	
	public void fillView(List<ItemInList> itemList){
		List_Nx1_view li = new List_Nx1_view(itemList, 1);
		changeScreen(li);
	}
	
	
	EventHandler<MouseEvent> categoryClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent mouseEvent) {
			
			if(mouseEvent.getSource() instanceof CategoryTitledPane){
				System.out.println("in mouse event");
				//fillProductView(((CategoryTitledPane) mouseEvent
					//	.getSource()).getItemsInCategory());
				
				fillView(((CategoryTitledPane) mouseEvent
							.getSource()).getItemInList());
				System.out.println(((CategoryTitledPane) mouseEvent
							.getSource()).getItemInList().size());
				
				currentList = ((CategoryTitledPane) mouseEvent
						.getSource()).getItemsInCategory();
				System.out.println(currentList.size());
				
				categoryBtn.setText(((CategoryTitledPane) mouseEvent
						.getSource()).getText());
			} else if(mouseEvent.getSource() instanceof SubcategoryButton){
				System.out.println("Click button");
				fillProductView(((SubcategoryButton) mouseEvent
						.getSource()).getList());
				
				System.out.println(((SubcategoryButton) mouseEvent
						.getSource()).getList().size());
				
				
				currentList = ((SubcategoryButton) mouseEvent
						.getSource()).getList();
				
				categoryBtn.setText(((SubcategoryButton) mouseEvent
						.getSource()).getText());
			}
			
		}
	};
	
	
	public List<ShoppingItem> getCurrentList(){
		return currentList;
	}
	
	SquareModeView smv;
	public void fillChoiceboxes(){
		
		AnchorPane ap = new AnchorPane();
		Label lb = new Label("List vy");
		ap.getChildren().add(lb);
		chbView.setItems(FXCollections.observableArrayList("Välj vy", "Standard vy", "Fyrkants vy"));
		chbView.setValue(chbView.getItems().get(0));;
		
		chbView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				
				fillProductView(getCurrentList());
				
			}
			
			});
	}
	
	public void fixCategories(){
		List<String> names = new ArrayList<String>();
		for (ProductCategory c : ProductCategory.values()) {
			String mainName = getMainCategoryName(c);
			
			if(!names.contains(mainName)){
				names.add(mainName);
				
				List<SubcategoryButton> thebuttons = new ArrayList<SubcategoryButton>();
				List<ShoppingItem> allProductsInCategory = new ArrayList<ShoppingItem>();
					for (ProductCategory pc : ProductCategory.values()) {
						
						if(getMainCategoryName(pc).equals(mainName)){
							
							List<ShoppingItem> theCategoryList = new ArrayList<ShoppingItem>();
							
							for (ShoppingItem p : productList) {

								if (p.getProduct().getCategory() == pc) {
									theCategoryList.add(p);
								}
							}
							
							allProductsInCategory.addAll(theCategoryList);
							SubcategoryButton scb = new SubcategoryButton(getCategoryName(pc), theCategoryList);
							scb.setOnMouseClicked(categoryClick);
							thebuttons.add(scb);
						}
					}
					
					CategoryTitledPane ctp = new CategoryTitledPane(mainName, thebuttons, allProductsInCategory);
					ctp.setOnMouseClicked(categoryClick);
					categoryAccordation.getPanes().add(ctp);
			}
		}
	}
	
	
	
	public void fillProductView(List<ShoppingItem> productList) {
		if(chbView.getSelectionModel().getSelectedItem().equals("Välj vy") || 
				chbView.getSelectionModel().getSelectedItem().equals("Standard vy") ){
			System.out.println("if-state");
			//theView = new List_Nx1_view(productList);
			
			lnv.fillList(productList);
			System.out.println("Fill!");
			changeScreen(lnv);
			
			
		} else if(chbView.getSelectionModel().getSelectedItem().equals("Fyrkants vy")){
			
			sqmv.fillList(productList);
			changeScreen(sqmv);
		}
		System.out.println(chbView.getSelectionModel().getSelectedItem());
		
		
	}


	public void goToMyProfile(ActionEvent evt) {
		profilePanel = new ProfilePanel();
		changeScreen(profilePanel);
	}
	
	
	public ProfilePanel getProfilePanel(){
		return profilePanel;
	}

	
	int s = 0;
	public void addToShoppingCart(ShoppingItem i) {
		
		ShoppingCartItem sci = new ShoppingCartItem(i);
		
		shoppingCartRight.getGridPane().setPrefHeight((s + 1) * 36);
		shoppingCartRight.getGridPane().add(sci, 0, s);
		s++;
		
	}
	
	public void goToFavorites(MouseEvent evt){
		
		List<ShoppingItem> theList = new ArrayList<ShoppingItem>();
		
		for(Product p: dataHandler.favorites()){
			ShoppingItem sci = new ShoppingItem(p);
			
			for(ShoppingItem i: productList){
				if(i.getProduct().getProductId() == sci.getProduct().getProductId()){
					sci = i;
					theList.add(sci);
				}
			}
		}
		
		fillProductView(theList);
	}
	
	
	public void fillLists(){
		for (ProductCategory c : ProductCategory.values()) {

			List<ShoppingItem> theCategoryList = new ArrayList<ShoppingItem>();
			
			for (ShoppingItem p : productList) {
				if (p.getProduct().getCategory().toString().equals(c.toString())) {
					theCategoryList.add(p);
				}
			}
			if(c == ProductCategory.BREAD){
				System.out.println("BrödBröd!");
			}
			System.out.println(c.toString());
			
			
			addToList(c, theCategoryList);
		}	
	}
	
	
	private List<List<ShoppingItem>> bread = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> berryNfruits = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> pod = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> beverage = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> vegtables = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> fish = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> meat = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> dairies = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> flourSugarSalt = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> potatoRicePasta = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> nutsSeeds = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> sweets = new ArrayList<List<ShoppingItem>>();
	private List<List<ShoppingItem>> herbs = new ArrayList<List<ShoppingItem>>();
	
	
	public List<List<ShoppingItem>> getListInList(ProductCategory p){
		switch(p.toString()){
			case "BERRY":
				return berryNfruits;
			case "BREAD":
				return bread;
			case "POD":
				return pod;
			case "CITRUS_FRUIT":
				return berryNfruits;
			case "HOT_DRINKS":
				return beverage;
			case "COLD_DRINKS":
				return beverage;
			case "EXOTIC_FRUIT":
				return berryNfruits;
			case "FISH":
				return fish;
			case "VEGETABLE_FRUIT":
				return vegtables;
			case "CABBAGE":
				return vegtables;
			case "MEAT":
				return meat;
			case "DAIRIES":
				return dairies;
			case "MELONS":
				return berryNfruits;
			case "FLOUR_SUGAR_SALT":
				return flourSugarSalt;
			case "NUTS_AND_SEEDS":
				return nutsSeeds;
			case "PASTA":
				return potatoRicePasta;
			case "POTATO_RICE":
				return potatoRicePasta;
			case "ROOT_VEGETABLE":
				return vegtables;
			case "FRUIT":
				return berryNfruits;
			case "SWEET":
				return sweets;
			case "HERB":
				return herbs;
		}
		return null;
	}
	
	public String getMainCategoryName(ProductCategory c){
		switch(c.toString()){
			case "BERRY":
				return "Frukter och bär";
			case "BREAD":
				return "Bröd";
			case "POD":
				return "Baljväxter";
			case "CITRUS_FRUIT":
				return "Frukter och bär";
			case "HOT_DRINKS":
				return "Drycker";
			case "COLD_DRINKS":
				return "Drycker";
			case "EXOTIC_FRUIT":
				return "Frukter och bär";
			case "FISH":
				return "Fisk";
			case "VEGETABLE_FRUIT":
				return "Grönsaker";
			case "CABBAGE":
				return "Grönsaker";
			case "MEAT":
				return "Kött";
			case "DAIRIES":
				return "Mejeri";
			case "MELONS":
				return "Frukter och bär";
			case "FLOUR_SUGAR_SALT":
				return "Mjöl, socker, salt";
			case "NUTS_AND_SEEDS":
				return "Nötter och frön";
			case "PASTA":
				return "Potatis, ris, pasta";
			case "POTATO_RICE":
				return "Potatis, ris, pasta";
			case "ROOT_VEGETABLE":
				return "Grönsaker";
			case "FRUIT":
				return "Frukter och bär";
			case "SWEET":
				return "Godis";
			case "HERB":
				return "Örter";
		}
		return c.toString();
	}
	
	public void addSavedList(){
		List<Label> labelList = new ArrayList<Label>();
		for(Order o: dataHandler.getOrders()){
			if(o.getOrderNumber()<0){
				
				labelList.add(new Label("-1"));
			}
		}
		
		
		
		((AnchorPane)savedTitledPane.getContent()).getChildren().addAll(labelList);
	}

	
	public void addToList(ProductCategory c, List<ShoppingItem> theProductList){
			switch(c.toString()){
				case "BERRY":
					berryNfruits.add(theProductList);
					break;
				case "BREAD":
					bread.add(theProductList);
					break;
				case "POD":
					pod.add(theProductList);
					break;
				case "CITRUS_FRUIT":
					berryNfruits.add(theProductList);
					break;
				case "HOT_DRINKS":
					beverage.add(theProductList);
					break;
				case "COLD_DRINKS":
					beverage.add(theProductList);
					break;
				case "EXOTIC_FRUIT":
					berryNfruits.add(theProductList);
					break;
				case "FISH":
					fish.add(theProductList);
					break;
				case "VEGETABLE_FRUIT":
					vegtables.add(theProductList);
					break;
				case "CABBAGE":
					vegtables.add(theProductList);
					break;
				case "MEAT":
					meat.add(theProductList);
					break;
				case "DAIRIES":
					dairies.add(theProductList);
					break;
				case "MELONS":
					berryNfruits.add(theProductList);
					break;
				case "FLOUR_SUGAR_SALT":
					flourSugarSalt.add(theProductList);
					break;
				case "NUTS_AND_SEEDS":
					nutsSeeds.add(theProductList);
					break;
				case "PASTA":
					potatoRicePasta.add(theProductList);
					break;
				case "POTATO_RICE":
					potatoRicePasta.add(theProductList);
					break;
				case "ROOT_VEGETABLE":
					vegtables.add(theProductList);
					break;
				case "FRUIT":
					berryNfruits.add(theProductList);
					break;
				case "SWEET":
					sweets.add(theProductList);
					break;
				case "HERB":
					herbs.add(theProductList);
					break;
			}
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
			return "Citrusfrukter";
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
			return "Nötter och fr�n";
		case "PASTA":
			return "Pasta";
		case "POTATO_RICE":
			return "Potatis, ris";
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
	

	public void goToCheckout() {
		shoppingCartBig = new ShoppingCartBig(this, pInf);
		shoppingCartBig.fillShoppingCart();
		changeScreen(shoppingCartBig);
		categoryBtn.setText("Tillbaka");
		
		categoryBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        changeScreen(listOfNodes.get(listOfNodes.size()-2));
		        categoryBtn.setOnAction(null);
		        
		    }
		});
	}


	public void goToHome(ActionEvent evt) {
		changeScreen(getHomeScreen());
	}

	public void changeScreen(Node node) {
		stackPane.getChildren().clear();
		stackPane.getChildren().add(node);
		
		if(node instanceof OnlinePanel || node instanceof OfflinePanel || node.equals(shoppingCartBig) || node.equals(pInf) || 
				node instanceof ChoosePayment|| node instanceof CheckoutPanel){
			categoryBtn.setOpacity(0);
			categoryBtn.setDisable(true);
			
			chbView.setOpacity(0);
			chbView.setDisable(true);
			
			chbSort.setOpacity(0);
			chbSort.setDisable(true);
		} else{
			categoryBtn.setOpacity(100);
			categoryBtn.setDisable(false);
			
			chbView.setOpacity(100);
			chbView.setDisable(false);
			
			chbSort.setOpacity(100);
			chbSort.setDisable(false);
		}
		
		if (node.equals(shoppingCartBig) || node.equals(pInf) || 
				node instanceof ChoosePayment|| node instanceof CheckoutPanel) {
			bigBorder.setRight(progressIndicator);
			setIndicator(node);
		}else {
			bigBorder.setRight(shoppingCartRight);
		}
		
		listOfNodes.add(node);
	}
	
	public void setIndicator(Node node){
		if(node.equals(shoppingCartBig)){
			progressIndicator.progressOverview.setProgress(-1);
			progressIndicator.progressPersInfo.setProgress(0);
			progressIndicator.progressChoosePayment.setProgress(0);
			progressIndicator.progressFinished.setProgress(0);
		} else if(node.equals(pInf)){
			
			//pInf.pInfSetText(); 
			
			progressIndicator.progressOverview.setProgress(1);
			progressIndicator.progressPersInfo.setProgress(-1);

		} else if(node instanceof ChoosePayment){
			
			((ChoosePayment)getCurrentScreen()).setFinalizeText(dataHandler.getShoppingCart().getTotal());
			
			progressIndicator.progressPersInfo.setProgress(1);
			progressIndicator.progressChoosePayment.setProgress(-1);
		} else if(node instanceof CheckoutPanel){
			progressIndicator.progressChoosePayment.setProgress(1);
			progressIndicator.progressFinished.setProgress(1);
		} 
	}

	//Bug if found product already in shoppingcart. Shows duplicates in list.
	public void searchForProducts(ActionEvent evt){
		if(!searchField.getText().isEmpty()){
			List<Product> foundProducts = dataHandler.findProducts(searchField.getText());
			
			List<ShoppingItem> foundItems = new ArrayList<ShoppingItem>();
			
			// To avoid to create a new ShoppingItem, makes it easier in shoppingCart
			for(Product p: foundProducts){
				for(ShoppingItem i: productList){
					if(p.getProductId() == i.getProduct().getProductId()){
						System.out.println("Hej");
						foundItems.add(i);
					}
				}
			}
			List_Nx1_view productView = new List_Nx1_view(foundItems);
			categoryBtn.setText("S�kresultat: " + searchField.getText());
			
			changeScreen(productView);
		}
	}
	
	public Node getCurrentScreen(){
		return stackPane.getChildren().get(0);
	}

	
	

	@Override
	public void shoppingCartChanged(CartEvent evt) {
		
		if(getCurrentScreen() instanceof ShoppingCartBig){
			shoppingCartBig.refresh(dataHandler.getShoppingCart().getItems());
		}
		
		if(dataHandler.getShoppingCart().getItems().size() > 0){
			ShoppingItem theItem = (ShoppingItem) evt.getShoppingItem();
			shoppingCartRight.refreshCart(dataHandler.getShoppingCart().getItems());
			
			//double sum = 0;
			//for(Node sci: shoppingCartRight.getGridPane().getChildren()){
				//sum += ((ShoppingCartItem)sci).getItem().getAmount()*((ShoppingCartItem)sci).getItem().getProduct().getPrice();
			//}
			//shoppingCartRight.setShoppingCartSum(sum);
			
		} else{	
			shoppingCartRight.clearShoppingCart();
		}
		
		
	
	}
	
	public Node getHomeScreen(){
		if(isOnline){
			return new OnlinePanel();
		}else{
			return new OfflinePanel();
		}
	}



	@Override
	public void eventRecieved(TheEvent evt) {
		if(evt.getScreen() == null){
			if(evt.getNameOFEvent().equals("Home")){
				changeScreen(getHomeScreen());
			}
			
		}else{
			System.out.println("Panel change!");
			changeScreen(evt.getScreen());
		}
	}

	@Override
	public void changed(ObservableValue observable, Object oldValue,
			Object newValue) {
		// TODO Auto-generated method stub
		
	}

}

