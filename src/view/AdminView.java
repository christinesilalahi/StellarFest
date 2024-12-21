package view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class AdminView extends GridPane{
	User user;
	Stage stage;
	MenuBar menuBar;
	Menu menu;
	MenuItem userItem, eventsItem, homeItem;
    
	public void initialize() {
        menuBar = new MenuBar();
        menu = new Menu("Options");
        eventsItem = new MenuItem("Events");
        userItem = new MenuItem("Users");
        homeItem = new MenuItem("Home");
        menu.getItems().add(userItem);
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
    	userItem.setOnAction(e -> {
    		new ViewUsers(stage, menuBar);
    		});
    	eventsItem.setOnAction(e -> {
    		new ViewEvents(stage, menuBar, user);
    		});
    	
    	homeItem.setOnAction(e -> {
    		new EventOrganizerView(stage, user);
    	});
    }
	
	public AdminView(Stage stage, User user ) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.user = user;
		initialize();
		setLayout();
		setButton();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Admin Home");
		stage.show();
	}
	

}
