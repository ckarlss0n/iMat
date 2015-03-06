package application;

import java.util.EventObject;

import javafx.scene.Node;

public class TheEvent extends EventObject {
	
	private String nameOfEvent;
	
	private Node theScreen;
	
	public TheEvent(Object source) {
		super(source);
		this.nameOfEvent = "";
		this.theScreen = null;
	}
	
	public TheEvent(Object source, String nameOfEvent) {
		super(source);
		this.nameOfEvent = nameOfEvent;
		this.theScreen = null;
	}
	
	public TheEvent(Object source, Node theScreen) {
		super(source);
		this.nameOfEvent = "";
		this.theScreen = theScreen;
	}
	
	public String getNameOFEvent(){
		return nameOfEvent;
	}
	
	public Node getScreen(){
		return theScreen;
	}
}
