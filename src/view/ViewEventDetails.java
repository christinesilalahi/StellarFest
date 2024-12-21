package view;

import java.util.List;

import controller.AdminController;
import controller.EventController;
import controller.EventOrganizerController;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Vendor;
import model.Event;
import model.Guest;

public class ViewEventDetails extends GridPane{
	User user;
	Stage stage;
	MenuBar menuBar;
	Event event;
	Label guestTableLabel, vendorTableLabel, idLabel, nameLabel, dateLabel, locationLabel, descLabel, idEvent, nameEvent, dateEvent, locationEvent, descEvent, partition;
	Button editEventName, addVendor, addGuest;
	TableColumn<Guest, String> idGColumn, nameGColumn, emailGColumn;
	TableColumn<Vendor, String> idVColumn, nameVColumn, emailVColumn;
	TableView<Guest> guestView;
	TableView<Vendor> vendorView;
	
	public void initialize() {
        
		idLabel = new Label("Event id: ");
		nameLabel = new Label("Event name: ");
		dateLabel = new Label("Event date Y-M-D: ");
		locationLabel = new Label("Event location: ");
		descLabel = new Label("Event descriptions: ");
		
		idEvent = new Label(event.getEvent_id());
		nameEvent = new Label(event.getEvent_name());
		dateEvent = new Label(event.getEvent_date());
		locationEvent = new Label(event.getEvent_location());
		descEvent = new Label(event.getEvent_description());
		
        editEventName = new Button("Edit Event Name");
        addVendor = new Button("Add Vendor");
        addGuest = new Button("Add Guest");
        
        partition = new Label("=======================");

        guestTableLabel = new Label("Guest Table");
        vendorTableLabel = new Label("Vendor Table");
        
        
        List<Guest> guests = null;
        List<Vendor> vendors = null;
        if(user.getRole().equals("Event Organizer")) {
            guests = EventOrganizerController.getGuestByTransaction(event.getEvent_id());
            vendors = EventOrganizerController.getVendorByTransaction(event.getEvent_id());
        }else if(user.getRole().equals("Admin")) {
            guests = AdminController.getGuestByTransaction(event.getEvent_id());
            vendors = AdminController.getVendorByTransaction(event.getEvent_id());
        }
        
        guestView = new TableView<>();
        idGColumn = new TableColumn<>("ID");
        idGColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameGColumn = new TableColumn<>("Name");
        nameGColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        emailGColumn = new TableColumn<>("Email");
        emailGColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        vendorView = new TableView<>();
        idVColumn = new TableColumn<>("ID");
        idVColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameVColumn = new TableColumn<>("Name");
        nameVColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        emailVColumn = new TableColumn<>("Email");
        emailVColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        
        guestView.getColumns().addAll(idGColumn, nameGColumn, emailGColumn);
        guestView.getItems().setAll(guests);
        
        vendorView.getColumns().addAll(idVColumn, nameVColumn, emailVColumn);
        vendorView.getItems().setAll(vendors);
    }
	
	public void setLayout() {
		this.getChildren().clear();

        VBox topContainer = new VBox(menuBar);
        this.add(topContainer, 0, 0, 2, 1);
        
        this.add(idLabel, 0, 1);
        this.add(idEvent, 1, 1);
        this.add(nameLabel, 0, 2);
        this.add(nameEvent, 1, 2);
        this.add(dateLabel, 0, 3);
        this.add(dateEvent, 1, 3);
        this.add(locationLabel, 0, 4);
        this.add(locationEvent, 1, 4);
        this.add(descLabel, 0, 5);
        this.add(descEvent, 1, 5);
        
        this.add(guestTableLabel, 0, 11);
        this.add(vendorTableLabel, 1, 11);
        
        this.add(guestView, 0, 12);
        this.add(vendorView, 1, 12);
        
        if(user.getRole().equals("Event Organizer")) {

            this.add(editEventName, 0, 7);
            
            this.add(partition, 0, 9);
            this.add(addGuest, 0, 10);
            this.add(addVendor, 1, 10);
            
        }
	}
	
	public void setButton() {
		editEventName.setOnAction(e -> {
			new ViewEditEvent(stage, menuBar, event);
        });
		
		addVendor.setOnAction(e -> {
			new ViewAddVendor(stage, menuBar, event, "Vendor");
			
		});
		
		addGuest.setOnAction(e -> {

			new ViewAddVendor(stage, menuBar, event, "Guest");
			
		});
	}
	

	public ViewEventDetails(Stage stage, MenuBar menuBar, User user, Event event) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.user = user;
		this.menuBar = menuBar;
		this.event = event;
		initialize();
		setLayout();
		setButton();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Events Details");
		stage.show();
	}

}
