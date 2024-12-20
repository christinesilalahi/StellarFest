package view;

import java.nio.file.attribute.UserDefinedFileAttributeView;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class EventOrganizerView extends GridPane{
	User user;
	Stage stage;
	MenuBar menuBar;
	Menu menu;
	MenuItem createEventItem, eventItem, homeItem;
    
	public void initialize() {
        menuBar = new MenuBar();
        menu = new Menu("Options");
        createEventItem = new MenuItem("Create Event");
        eventItem = new MenuItem("Event");
        homeItem = new MenuItem("Home");
        menu.getItems().add(createEventItem);
        menu.getItems().add(eventItem);
        menu.getItems().add(homeItem);
        menuBar.getMenus().add(menu);
    }

    public void setLayout() {
        this.setVgap(10);
        this.setHgap(10);

        VBox topContainer = new VBox(menuBar);
        this.add(topContainer, 0, 0, 2, 1);
        
    }

    public void setButton() {
    	createEventItem.setOnAction(e -> {
    		new CreateEventView(stage, menuBar, user);
    		});
    	eventItem.setOnAction(e -> {
//    		stuff
    		});
    	
    	homeItem.setOnAction(e -> {
    		new EventOrganizerView(stage, user);
    	});
    }
	
	public EventOrganizerView(Stage stage, User user ) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.user = user;
		initialize();
		setLayout();
		setButton();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Home");
		stage.show();
	}
	

}
