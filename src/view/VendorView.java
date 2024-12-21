package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//import controller.VendorController;
import model.Event;
import model.Invitation;
import model.User;
import model.Vendor;

import java.util.List;

public class VendorView extends GridPane {

    private Stage stage;
    private User user;
    private Vendor vendor;
    private Label greetings;
    private Button changeProfile, invitation, manageProducts;

    private void initialize() {
        greetings = new Label("Hello Vendor!");
        changeProfile = new Button("Change Profile");
        invitation = new Button("Invitation");
        manageProducts = new Button("Manage Products");
    }

    private void setLayout() {
        this.add(greetings, 0, 1);
        this.add(changeProfile, 0, 2);
        this.add(invitation, 0, 3);
        this.add(manageProducts, 0, 5);
    }

    private void setButtonActions() {
        changeProfile.setOnAction(e -> {
            new ChangeProfileView(stage);
        });
        
        invitation.setOnAction(e -> {
        	new InvitationVendorView(stage, vendor);
        });
        
        manageProducts.setOnAction(e -> {
        	new ManageProductVendorView(stage, user);
        });
    }
    
    public VendorView(Stage stage) {
        this.stage = stage;
        initialize();
        setLayout();
        setButtonActions();
        Scene scene = new Scene(this, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Vendor");
        stage.show();
    }
}
