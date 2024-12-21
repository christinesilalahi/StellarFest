package view;

import controller.VendorController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class ManageProductVendorView extends GridPane {

    Stage stage;
    User user;

    Label nameLabel, descLabel;
    TextField nameTF;
    TextArea descTA;
    Button submit, back;

    public void initialize() {
        nameLabel = new Label("Product Name: ");
        descLabel = new Label("Product Description: ");

        nameTF = new TextField();
        descTA = new TextArea();

        submit = new Button("Submit");
        back = new Button("Back");
    }
    
    public void setLayout() {
        this.setHgap(10); 
        this.setVgap(10);

        this.add(nameLabel, 0, 0);
        this.add(nameTF, 0, 1);
        this.add(descLabel, 0, 2);
        this.add(descTA, 0, 3);
        this.add(submit, 0, 4);
        this.add(back, 0, 5);
    }

    public void setButtonActions() {
        back.setOnAction(e -> {
            new VendorView(stage);
        });

        submit.setOnAction(e -> {
            String productName = nameTF.getText();
            String productDescription = descTA.getText();

            boolean result = VendorController.manageProduct(user, productName, productDescription);

            if (!result) {
                System.out.println("Something is wrong! Please ensure the description is at least 200 characters.");
            } else {
                System.out.println("Product added successfully!");
            }
        });
    }

    public ManageProductVendorView(Stage stage, User user) {
        this.stage = stage;
        this.user = user;
        initialize();
        setLayout();
        setButtonActions();
        Scene scene = new Scene(this, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Manage Product");
        stage.show();
    }
}