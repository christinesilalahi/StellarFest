package view;

import controller.VendorController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ManageProductVendorView extends GridPane {

    Stage stage;
    String vendor_id;

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
        this.add(nameTF, 1, 0);
        this.add(descLabel, 0, 1);
        this.add(descTA, 1, 1);
        this.add(submit, 0, 2);
        this.add(back, 1, 2);
    }

    public void setButtonActions() {
        back.setOnAction(e -> {
            new VendorView(stage);
        });

        submit.setOnAction(e -> {
            String productName = nameTF.getText();
            String productDescription = descTA.getText();

            boolean result = VendorController.ManageProduct(vendor_id, productName, productDescription);

            if (!result) {
                System.out.println("Something is wrong! Please ensure the description is at least 200 characters.");
            } else {
                System.out.println("Product added successfully!");
            }
        });
    }

    public ManageProductVendorView(Stage stage, String vendor_id) {
        this.stage = stage;
        this.vendor_id = vendor_id;
        initialize();
        setLayout();
        setButtonActions();
        Scene scene = new Scene(this, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Manage Product");
        stage.show();
    }
}