package application;
	
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			MainPanel mp = new MainPanel();
			mp.setPrefWidth(1366);
			mp.setPrefHeight(768);
			
			root.getChildren().addAll(mp);
			Scene scene = new Scene(root);
			
			
			
			scene.widthProperty().addListener(new ChangeListener<Number>() {
			    @Override public void changed(ObservableValue<? extends Number> observableValue, 
			    		Number oldSceneWidth, Number newSceneWidth) {
			       
			    	mp.setPrefWidth(newSceneWidth.doubleValue());
			    }
			});
			
			scene.heightProperty().addListener(new ChangeListener<Number>() {
			    @Override public void changed(ObservableValue<? extends Number> observableValue,
			    		Number oldSceneHeight, Number newSceneHeight) {
			    	mp.setPrefHeight(newSceneHeight.doubleValue());
			    }
			});
			
			
			Stage stage = new Stage();
			
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
			stage.setWidth(bounds.getWidth());
			stage.setHeight(bounds.getHeight());
			
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
