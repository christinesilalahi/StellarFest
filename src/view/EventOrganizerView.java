package view;

import javafx.scene.Scene;
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
	MenuItem createEventItem, eventsItem, homeItem;
    
	public void initialize() {
        menuBar = new MenuBar();
        menu = new Menu("Options");
        createEventItem = new MenuItem("Create Event");
        eventsItem = new MenuItem("Event");
        homeItem = new MenuItem("Home");
        menu.getItems().add(createEventItem);
        menu.getItems().add(eventsItem);
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
    		new ViewCreateEvent(stage, menuBar, user);
    		});
    	eventsItem.setOnAction(e -> {
    		new ViewEvents(stage, menuBar, user);
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
		stage.setTitle("Event Organizer Home");
		stage.show();
	}
	

}
