package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.Database;

public class Product {

    private String product_name;
    private String product_description;

    public Product(String product_name, String product_description) {
        this.product_name = product_name;
        this.product_description = product_description;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public static boolean ManageProduct(User user, String product_name, String product_description) {
        Database db = Database.getInstance();
        String vendor_id = user.getId();
        String query = "INSERT INTO products(vendor_id, product_name, product_description) VALUES(?, ?, ?)";

        try (PreparedStatement ps = db.preparedStatement(query)) {
        	ps.setString(1, vendor_id);
            ps.setString(2, product_name);
            ps.setString(3, product_description);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}