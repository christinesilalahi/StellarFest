package controller;

import model.Vendor;
import model.Product;
import model.User;

import java.util.List;

public class VendorController {
	
	public static boolean ManageProduct(String vendor_id, String product_name, String product_description) {
		if(product_name.isEmpty() || product_description.length() < 200) {
			return false;
		}
		return Product.ManageProduct(vendor_id, product_name, product_description);
	}
}