package application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private OnlinePanel onlinePanel;
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
	@FXML
	private Label lblSort;
	@FXML
	private Label lblView;
	
	
	public MainPanel() { // hï¿½r kommer det behï¿½vas en if sats som kollar fiall man ï¿½r inloggad och gï¿½r checkout, profil och andra panels baserat pï¿½ den if satsen! ifall man ï¿½ven ï¿½r i inte inloggad delen ska man kunna ta sig till inloggad delen.
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
		onlinePanel =  new OnlinePanel(productList);
		
		Order o = new Order();
		o.setItems(productList);
		o.setOrderNumber(1);
		
		
		Customer theCustomer = dataHandler.getCustomer();
		pInf = new PersonalInformationPanel(theCustomer);
		changeScreen(onlinePanel);
		
		
		fixCategories();
		
		fillChoiceboxes();
		
	
	
		shoppingCartRight = new ShoppingCartRight(this);
		bigBorder.setRight(shoppingCartRight);
		dataHandler.getShoppingCart().addShoppingCartListener(this);
		shoppingCartRight.refreshCart(dataHandler.getShoppingCart().getItems()); 
	}
	
	public List<String> getSortByNames(List<ShoppingItem> list){
		List<String> names = new ArrayList<String>();
		for(ShoppingItem i : list){
			names.add(i.getProduct().getName());
		}
		
		Collections.sort(names);
		return names;
		
		
	}
	
	public List<ShoppingItem> getShoppingItems(){
		return productList;
	}
	
	public List<ItemInList> getSortedItemList(List<ItemInList> itemList){

		
		
		if(chbSort.getSelectionModel().getSelectedItem().equals("A-Ö")){
			
			Collections.sort(itemList, new Comparator<ItemInList>() {
			    public int compare(ItemInList il1, ItemInList il2) {
			        return il1.getShoppingItem().getProduct().getName()
			        		.compareTo(il2.getShoppingItem().getProduct().getName());
			        		
			    }
			});
			
		}else if(chbSort.getSelectionModel().getSelectedItem().equals("Ö-A")){
			
			Collections.sort(itemList, new Comparator<ItemInList>() {
			    public int compare(ItemInList il1, ItemInList il2) {
			        return -1*(il1.getShoppingItem().getProduct().getName()
			        		.compareTo(il2.getShoppingItem().getProduct().getName()));
			        		
			    }
			});
			
		}else if(chbSort.getSelectionModel().getSelectedItem().equals("Pris stigande")){
			
			Collections.sort(itemList, new Comparator<ItemInList>() {
			    public int compare(ItemInList il1, ItemInList il2) {
			        return Double.compare(il1.getShoppingItem().getProduct().getPrice(), 
			        		il2.getShoppingItem().getProduct().getPrice());
			    }
			});
			
		}else if(chbSort.getSelectionModel().getSelectedItem().equals("Pris fallande")){
			Collections.sort(itemList, new Comparator<ItemInList>() {
			    public int compare(ItemInList il1, ItemInList il2) {
			        return -1*(Double.compare(il1.getShoppingItem().getProduct().getPrice(), 
			        		il2.getShoppingItem().getProduct().getPrice()));
			    }
			});
			
		}
		
		
		return itemList;
	}
	
	public List<ShoppingItem> getSortedShoppingItemList(List<ShoppingItem> itemList){
		
		
		if(chbSort.getSelectionModel().getSelectedItem().equals("A-Ö")){
			
			Collections.sort(itemList, new Comparator<ShoppingItem>() {
			    public int compare(ShoppingItem il1, ShoppingItem il2) {
			        return il1.getProduct().getName()
			        		.compareTo(il2.getProduct().getName());
			        		
			    }
			});
			
		}else if(chbSort.getSelectionModel().getSelectedItem().equals("Ö-A")){
			
			Collections.sort(itemList, new Comparator<ShoppingItem>() {
			    public int compare(ShoppingItem il1, ShoppingItem il2) {
			        return -1*(il1.getProduct().getName()
			        		.compareTo(il2.getProduct().getName()));
			        		
			    }
			});
			
		}else if(chbSort.getSelectionModel().getSelectedItem().equals("Pris stigande")){
			
			Collections.sort(itemList, new Comparator<ShoppingItem>() {
			    public int compare(ShoppingItem il1, ShoppingItem il2) {
			        return Double.compare(il1.getProduct().getPrice(), 
			        		il2.getProduct().getPrice());
			    }
			});
			
		}else if(chbSort.getSelectionModel().getSelectedItem().equals("Pris fallande")){
			Collections.sort(itemList, new Comparator<ShoppingItem>() {
			    public int compare(ShoppingItem il1, ShoppingItem il2) {
			        return -1*(Double.compare(il1.getProduct().getPrice(), 
			        		il2.getProduct().getPrice()));
			    }
			});
			
		}
		
		
		return itemList;
	}
	
	public void fillView(List<ItemInList> itemList){
		
		itemList = getSortedItemList(itemList);
		
		if( chbView.getSelectionModel().getSelectedItem().equals("Standardvy") ){
			
			List_Nx1_view li = new List_Nx1_view(itemList, 1);
			changeScreen(li);
		
		} else if(chbView.getSelectionModel().getSelectedItem().equals("Rutnätsvy")){
			List<ShoppingItem> theItems = new ArrayList<ShoppingItem>();
			
			for(ItemInList iil : itemList){
				theItems.add(iil.getShoppingItem());
			}
			
		fillProductView(getCurrentList());
			//sqmv.fillList(theItems);
		}
	}
	
	public void fillProductView(List<ShoppingItem> productList) {
		productList = getSortedShoppingItemList(productList);
		if(chbView.getSelectionModel().getSelectedItem().equals("Standardvy") ){
			
			changeScreen(lnv);
			lnv.fillList(productList);
		} else if(chbView.getSelectionModel().getSelectedItem().equals("Rutnätsvy")){
			
			System.out.println(productList.size());
			sqmv.fillList(productList);
			changeScreen(sqmv);
		}
		System.out.println(chbView.getSelectionModel().getSelectedItem());
		
		
	}
	
	EventHandler<MouseEvent> categoryClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent mouseEvent) {
			
			if(mouseEvent.getSource() instanceof CategoryTitledPane){
				
				currentItemList = (((CategoryTitledPane) mouseEvent
						.getSource()).getItemInList());
				
				currentList = ((CategoryTitledPane) mouseEvent
						.getSource()).getItemsInCategory();
				fillView(currentItemList);
				//fillProductView(currentList);
				categoryBtn.setText(((CategoryTitledPane) mouseEvent
						.getSource()).getText());
			} else if(mouseEvent.getSource() instanceof SubcategoryButton){
				
				currentItemList = ((SubcategoryButton) mouseEvent
						.getSource()).getItemList();
				
				currentList = ((SubcategoryButton) mouseEvent
						.getSource()).getList();
				
				fillProductView(currentList);
				//fillView(currentItemList);
				
				
				categoryBtn.setText(((SubcategoryButton) mouseEvent
						.getSource()).getText());
			}
			
		}
	};
	private List<ItemInList> currentItemList;
	public List<ItemInList> getCurrentItemList(){
		return currentItemList;
	}
	
	
	public List<ShoppingItem> getCurrentList(){
		return currentList;
	}
	
	SquareModeView smv;
	public void fillChoiceboxes(){
		
		
		chbView.setItems(FXCollections.observableArrayList("Standardvy", "Rutnätsvy"));
		chbView.setValue(chbView.getItems().get(0));;
		
		chbView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				
				fillView(getCurrentItemList());
			}
			
			});
		
		chbSort.setItems(FXCollections.observableArrayList("Populära","A-Ö", "Ö-A","Pris stigande" , "Pris fallande"));
		chbSort.setValue(chbSort.getItems().get(1));
		
		chbSort.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				
				fillView(getCurrentItemList());
				
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

	
	public String getMainCategoryName(ProductCategory c){
		switch(c.toString()){
			case "BERRY":
				return "Frukter och bär";
			case "BREAD":
				return "Bröd";
			case "POD":
				return "Bönor och linser";
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


	
	public String getCategoryName(ProductCategory c){
		switch(c.toString()){
		case "BERRY":
			return "Bär";
		case "BREAD":
			return "Bröd";
		case "POD":
			return "Bönor och linser";
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
			return "Nötter och frön";
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
		categoryBtn.setOpacity(100);
		categoryBtn.setText("Tillbaka");
		categoryBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Handle!!");
				
				changeScreen(listOfNodes.get(listOfNodes.size()-2));
				listOfNodes.remove(listOfNodes.size()-1);
				listOfNodes.remove(listOfNodes.size()-1);

			}
			
		});
	}


	public void goToHome(ActionEvent evt) {
		changeScreen(getHomeScreen());
	}
	
	public void setHideViewEtc(Node node){
		
		if(node instanceof OnlinePanel || node instanceof CheckoutPanel){
			//listOfNodes.clear();
			categoryBtn.setOnAction(null);
			categoryBtn.setOpacity(0);
			categoryBtn.setDisable(true);
			
			chbView.setOpacity(0);
			chbView.setDisable(true);
			
			chbSort.setOpacity(0);
			chbSort.setDisable(true);
			
			lblSort.setVisible(false);
			lblView.setVisible(false);
		
		}else if(node instanceof ShoppingCartBig || node instanceof ChoosePayment
			|| node instanceof PersonalInformationPanel || node instanceof ProfilePanel){
			
			categoryBtn.setOpacity(100);
			categoryBtn.setText("Tillbaka");
			categoryBtn.setDisable(false);
			
			chbView.setOpacity(0);
			chbView.setDisable(false);
			
			chbSort.setOpacity(0);
			chbSort.setDisable(false);
			
			lblSort.setVisible(false);
			lblView.setVisible(false);
		}else{
			categoryBtn.setOnAction(null);
			categoryBtn.setOpacity(100);
			categoryBtn.setDisable(false);
			
			chbView.setOpacity(100);
			chbView.setDisable(false);
			
			chbSort.setOpacity(100);
			chbSort.setDisable(false);
			
			lblSort.setVisible(true);
			lblView.setVisible(true);
		}
	}
	
	public void changeScreen(Node node) {
		stackPane.getChildren().clear();
		stackPane.getChildren().add(node);
		
		setHideViewEtc(node);
	
		if (node instanceof ShoppingCartBig || node instanceof PersonalInformationPanel || 
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

			progressIndicator.setOverview(0, 1);
			progressIndicator.setPersInfo(0, 0.5);
			progressIndicator.setChoosePayment(0, 0.5);
			progressIndicator.setFinished(0, 0.5);
			
		} else if(node.equals(pInf)){

			progressIndicator.setOverview(1, 1);
			progressIndicator.setPersInfo(0, 1);
			progressIndicator.setChoosePayment(0, 0.5);
			progressIndicator.setFinished(0, 0.5);

		} else if(node instanceof ChoosePayment){
			
			((ChoosePayment)getCurrentScreen()).setFinalizeText(dataHandler.getShoppingCart().getTotal());
			
			progressIndicator.setOverview(1, 1);
			progressIndicator.setPersInfo(1, 1);
			progressIndicator.setChoosePayment(0, 1);
			progressIndicator.setFinished(0, 0.5);
			//progressIndicator.progressPersInfo.setProgress(1);
			//progressIndicator.progressChoosePayment.setProgress(0);
		} else if(node instanceof CheckoutPanel){
			
			progressIndicator.setOverview(1, 1);
			progressIndicator.setPersInfo(1, 1);
			progressIndicator.setChoosePayment(1, 1);
			progressIndicator.setFinished(1, 1);
			//progressIndicator.progressChoosePayment.setProgress(1);
			//progressIndicator.progressFinished.setProgress(1);
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
			categoryBtn.setText("Sökresultat: " + searchField.getText());
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
			return new OnlinePanel(productList);
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

