package view;

import controller.VendorController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Invitation;
import model.Vendor;

import java.util.List;

public class InvitationVendorView extends GridPane {
    private final Stage stage;
    private final Vendor vendor;
    private final ListView<Invitation> invitationListView;
    private final Button backButton;

    public InvitationVendorView(Stage stage, Vendor vendor) {
        this.stage = stage;
        this.vendor = vendor;
        this.invitationListView = new ListView<>();
        this.backButton = new Button("Back");

        initializeComponents();
        loadInvitations();
        setLayout();
        setButtonActions();

        Scene scene = new Scene(this, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Pending and Accepted Invitations");
        stage.show();
    }

    private void initializeComponents() {
        invitationListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Invitation> call(ListView<Invitation> param) {
                return new ListCell<>() {
                    private final Button acceptButton = new Button("Accept");
                    private final Button viewEventButton = new Button("View Event");

                    @Override
                    protected void updateItem(Invitation invitation, boolean empty) {
                        super.updateItem(invitation, empty);
                        if (empty || invitation == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText("Event: " + invitation.getEventName() +
                                    " | Status: " + invitation.getStatus());

                            GridPane buttonsPane = new GridPane();
                            buttonsPane.setHgap(10);

                            if ("pending".equalsIgnoreCase(invitation.getStatus())) {
                                acceptButton.setOnAction(e -> handleAcceptInvitation(invitation));
                                buttonsPane.add(acceptButton, 0, 0);
                            } else if ("accepted".equalsIgnoreCase(invitation.getStatus())) {
                                viewEventButton.setOnAction(e -> viewEventDetailVendorPage(invitation));
                                buttonsPane.add(viewEventButton, 0, 0);
                            }
                            setGraphic(buttonsPane);
                        }
                    }
                };
            }
        });
    }

    private void loadInvitations() {
        try {
            List<Invitation> invitations = VendorController.getVendorInvitations(vendor.getId());
            invitationListView.getItems().setAll(invitations);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading invitations: " + e.getMessage());
            alert.show();
        }
    }

    private void setLayout() {
        this.add(new Label("Pending and Accepted Invitations"), 0, 0);
        this.add(invitationListView, 0, 1);
        this.add(backButton, 0, 2);
    }

    private void setButtonActions() {
        backButton.setOnAction(e -> new VendorView(stage));
    }

    private void handleAcceptInvitation(Invitation invitation) {
        boolean success = VendorController.acceptInvitation(invitation.getId());
        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invitation accepted successfully!");
            alert.show();
            loadInvitations(); // Refresh the list
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to accept the invitation.");
            alert.show();
        }
    }

    private void viewEventDetailVendorPage(Invitation invitation) {
        new EventDetailVendorView(stage, invitation.getEventId());
    }
}
