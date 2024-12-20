package view;

import java.util.List;

import controller.EventController;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.User;
import model.Event;

public class ViewEvents extends GridPane{
	User user;
	Stage stage;
	MenuBar menuBar;
	TableView<Event> tableView;
	
	private void initialize() {
        List<Event> events = Event.viewOrganizedEvents(user.getId()); 
        
        tableView = new TableView<>();
        
        TableColumn<Event, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_id()));
        TableColumn<Event, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_name()));
        TableColumn<Event, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_date()));
        TableColumn<Event, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_location()));
        TableColumn<Event, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvent_description()));
        
        TableColumn<Event, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(new Callback<TableColumn<Event, Void>, TableCell<Event, Void>>() {
            @Override
            public TableCell<Event, Void> call(TableColumn<Event, Void> param) {
                return new TableCell<Event, Void>() {
                    private final Button editButton = new Button("Edit");

                    {
                        editButton.setOnAction(event -> {
                            Event currentEvent = getTableView().getItems().get(getIndex());
                            editEventName(currentEvent);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(editButton);
                        }
                    }
                };
            }
        });
        
        tableView.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn, editColumn);
        tableView.getItems().setAll(events);
       
    }

    private void editEventName(Event event) {
        TextInputDialog dialog = new TextInputDialog(event.getEvent_name());
        dialog.setTitle("Edit Event Name");
        dialog.setHeaderText("Edit the name of the event");
        dialog.showAndWait().ifPresent(newName -> {
            event.setEvent_name(newName);
            // You can update the database here if needed
        });
    }

    public void setLayout() {
    	this.getChildren().clear();

        VBox topContainer = new VBox(menuBar);
        this.add(topContainer, 0, 0, 2, 1);
        
        VBox container = new VBox(tableView);
        this.getChildren().add(container);
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
		stage.setTitle("Home");
		stage.show();
	}

}
