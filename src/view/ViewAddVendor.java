package view;

import java.util.ArrayList;
import java.util.List;

import controller.EventOrganizerController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;
import model.User;

public class ViewAddVendor extends GridPane {
    Stage stage;
    String type;
    MenuBar menuBar;
    Event event;
    TableView<User> tableView;
    Label messageLabel;
    TableColumn<User, String> idColumn, nameColumn, emailColumn;
    TableColumn<User, Boolean> selectColumn;
    Button inviteButton;
    ObservableList<User> candidateList;

    private void initialize() {
        candidateList = FXCollections.observableArrayList(EventOrganizerController.getGuest(event.getEvent_id(), type));
        messageLabel = new Label("");

        tableView = new TableView<>();

        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));

        nameColumn = new TableColumn<>("Username");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));

        emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        selectColumn = new TableColumn<>("Select");
        selectColumn.setCellValueFactory(cellData -> {
            User user = cellData.getValue();
            SimpleBooleanProperty selected = user.selectedProperty();
            return selected;
        });
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));

        inviteButton = new Button("Invite");

        tableView.getColumns().addAll(selectColumn, idColumn, nameColumn, emailColumn);
        tableView.setItems(candidateList);

        tableView.setEditable(true);
    }

    public void setLayout() {
        this.getChildren().clear();

        VBox topContainer = new VBox(menuBar);
        this.add(topContainer, 0, 0, 2, 1);

        this.add(tableView, 0, 1);
        this.add(inviteButton, 0, 2);
    }

    public void setButton() {
        inviteButton.setOnAction(e -> {
            for (User user : candidateList) {
                if (user.isSelected()) {
                	System.out.println(EventOrganizerController.sendInvitation(user, event));
                }
            }
        });
    }

    public ViewAddVendor(Stage stage, MenuBar menuBar, Event event, String type) {
        this.stage = stage;
        this.menuBar = menuBar;
        this.event = event;
        this.type = type;
        initialize();
        setLayout();
        setButton();
        Scene scene = new Scene(this, 500, 500);
        stage.setScene(scene);
        stage.setTitle("All Events");
        stage.show();
    }
}
