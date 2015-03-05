package application;

import java.util.EventObject;

public class TheEvent extends EventObject {
	
	private String nameOfEvent;
	
	public TheEvent(Object source) {
		super(source);
		this.nameOfEvent = "";
	}
	
	public TheEvent(Object source, String nameOfEvent) {
		super(source);
		this.nameOfEvent = nameOfEvent;
	}
	
	public String getNameOFEvent(){
		return nameOfEvent;
	}
	
}
