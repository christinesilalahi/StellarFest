package view;

import controller.VendorController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.InvitationDetails;
import model.Vendor;

import java.util.List;

public class InvitationVendorView extends GridPane {
    private Stage stage;
    private Vendor vendor;
    private ListView<String> invitationListView;
    private Button backButton;

    public InvitationVendorView(Stage stage) {
        this.stage = stage;
        initialize();
        setLayout();
        setButtonActions();
        Scene scene = new Scene(this, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Pending Invitations");
        stage.show();
    }

    private void initialize() {
        invitationListView = new ListView<>();
        backButton = new Button("Back");
        
        // Fetch pending invitations
        List<InvitationDetails> invitations = VendorController.fetchPendingInvitations(vendor);
        for (InvitationDetails details : invitations) {
            String invitationInfo = "Event: " + details.getEventName() + 
                                    ", Date: " + details.getEventDate() + 
                                    ", Location: " + details.getEventLocation() + 
                                    ", Description: " + details.getEventDescription() + 
                                    ", Organizer: " + details.getOrganizerUsername();
            invitationListView.getItems().add(invitationInfo);
        }
    }

    private void setLayout() {
        this.add(new Label("Pending Invitations"), 0, 0);
        this.add(invitationListView, 0, 1);
        this.add(backButton, 0, 2);
    }

    private void setButtonActions() {
    	backButton.setOnAction(e -> {
            // Logic to return to the previous view
            new VendorView(stage);
        });
    }
}