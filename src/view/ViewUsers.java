package view;

import java.util.List;

import controller.AdminController;
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

public class ViewUsers extends GridPane{
	Stage stage;
	MenuBar menuBar;
	TableView<User> tableView;
	Label messageLabel;
	TableColumn<User, String> idColumn, nameColumn, emailColumn;
	TableColumn<User, Void> detailsColumn;
	
	
	private void initialize() {
		List<User> users = AdminController.viewAllUser();
		
        messageLabel = new Label("");
        
        tableView = new TableView<>();
        
        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameColumn = new TableColumn<>("username");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        emailColumn = new TableColumn<>("Date");
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        
        
        detailsColumn = new TableColumn<>("Details");
        detailsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button detailsButton = new Button("Delete");

            {
                detailsButton.setOnAction(event -> {
                    
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
        
        tableView.getColumns().addAll(idColumn, nameColumn, emailColumn, detailsColumn);
        tableView.getItems().setAll(users);
        
       
    }

    public void setLayout() {
    	this.getChildren().clear();

        VBox topContainer = new VBox(menuBar);
        this.add(topContainer, 0, 0, 2, 1);
        
        this.add(tableView, 0, 1);
    }

	public ViewUsers(Stage stage, MenuBar menuBar) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.menuBar = menuBar;
		initialize();
		setLayout();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("All Users");
		stage.show();
	}

}
