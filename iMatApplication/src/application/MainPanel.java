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
import se.chalmers.ait.dat215.project.ShoppingCartListener;
import se.chalmers.ait.dat215.project.ShoppingItem;
import se.chalmers.ait.dat215.project.User;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MainPanel extends BorderPane implements PropertyChangeListener {

	private List<Product> productList;
	private IMatDataHandler dataHandler;

	@FXML
	private Accordion categoryAccordation;

	public MainPanel() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"mainPanel.fxml"));
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

		for (ProductCategory c : ProductCategory.values()) {
			String name = getCategoryName(c);

			// System.out.println(ProductCategory.valueOf(c.toString()));
			// TitledPane t = new TitledPane(name, new AnchorPane());

			EventHandler<MouseEvent> mousehandler = new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {

					fillProductView(((CategoryTitledPane) mouseEvent
							.getSource()).getProducts());

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

	}

	@FXML
	private GridPane gridPane;

	@FXML
	private StackPane stackPane;

	@FXML
	private BorderPane borderPane;

	public void fillProductView(List<Product> productList) {
		List_Nx1_view l = new List_Nx1_view(this, productList);
		changeScreen(l);
	}

	int i = 0;

	public void goToMyProfile(ActionEvent evt) {

		ProfilePanel pp = new ProfilePanel();
		changeScreen(pp);

	}

	int s = 0;

	public void addToShoppingCart(Product p) {

		dataHandler.getShoppingCart().addProduct(p, 1);
		ShoppingCartItem sci = new ShoppingCartItem(p);

		gridPane.setPrefHeight((s + 1) * 36);
		gridPane.add(sci, 0, s);

		s++;
	}

	public String getCategoryName(ProductCategory c) {
		switch (c.toString()) {
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
			return "Mjöl socker salt";
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

	public void fillShoppingCart(ShoppingCartBig scb) {

		int k = 0;
		for (ShoppingItem i : dataHandler.getShoppingCart().getItems()) {
			Product p = i.getProduct();
			ProductInShoppingCartBig piscb = new ProductInShoppingCartBig(p);
			scb.add(piscb, k);
			k++;
		}

	}

	public void goToCheckOut(ActionEvent evt) {
		ShoppingCartBig scb = new ShoppingCartBig();

		fillShoppingCart(scb);

		ProcessIndicator pi = new ProcessIndicator();

		changeScreen(scb);

		borderPane.getChildren().clear();
		borderPane.setCenter(pi);
	}

	public void goToHome(ActionEvent evt) {
		borderPane.getChildren().clear();
		borderPane.setCenter(gridPane);
		OnlinePanel onp = new OnlinePanel();
		changeScreen(onp);

	}

	public void changeScreen(Node node) {
		stackPane.getChildren().clear();
		stackPane.getChildren().add(node);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		List<ShoppingItem> shoppingCartItems = IMatDataHandler.getInstance()
				.getShoppingCart().getItems();
		Product product = (Product) evt.getNewValue();

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
			ShoppingCartItem shoppingCartItem = (ShoppingCartItem) gridPane
					.getChildren().get(index);
			shoppingCartItem.increaseAmount();
		}

	}

}
