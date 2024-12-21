package view;

import java.util.List;

import controller.EventOrganizerController;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Event;

public class ViewEvents extends GridPane{
	User user;
	Stage stage;
	MenuBar menuBar;
	TableView<Event> tableView;
	Label messageLabel;
	TableColumn<Event, String> idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn;
	TableColumn<Event, Void> detailsColumn;
	
	private void initialize() {
        List<Event> events = EventOrganizerController.viewOrganizedEvents(user.getId()); 
        messageLabel = new Label("");
        
        tableView = new TableView<>();
        
        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_id()));
        nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_name()));
        dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_date()));
        locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_location()));
        descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_description()));
        
        detailsColumn = new TableColumn<>("Details");
        detailsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button detailsButton = new Button("Details");

            {
                detailsButton.setOnAction(event -> {
                    Event selectedEvent = getTableView().getItems().get(getIndex());
                    if (selectedEvent != null) {
                        redirectToEventDetails(selectedEvent);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(detailsButton);
                }
            }
        });
        
        tableView.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn, detailsColumn);
        tableView.getItems().setAll(events);
       
    }

	 private void redirectToEventDetails(Event event) {
	        new ViewEventDetails(stage, menuBar, user, event); 
	    }

    public void setLayout() {
    	this.getChildren().clear();

        VBox topContainer = new VBox(menuBar);
        this.add(topContainer, 0, 0, 2, 1);
        
        VBox container = new VBox(tableView);
        this.getChildren().add(container);
        
        this.add(messageLabel, 0, 2);
    }

	public ViewEvents(Stage stage, MenuBar menuBar, User user) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.user = user;
		this.menuBar = menuBar;
		initialize();
		setLayout();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("All Events");
		stage.show();
	}

}
