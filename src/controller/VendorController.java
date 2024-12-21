package controller;

import model.Vendor;
import model.Invitation;
import model.Product;
import model.User;

import java.util.List;

public class VendorController {

    public static boolean manageProduct(User user, String productName, String productDescription) {
        if (productName.isEmpty() || productDescription.length() < 200) {
            return false;
        }
        Vendor vendor = (Vendor) user;
        return vendor.manageProduct(productName, productDescription);
    }

    public static List<Invitation> getVendorInvitations(String vendorId) {
        return Vendor.fetchInvitationsByVendorId(vendorId);
    }

    public static boolean acceptInvitation(String invitationId) {
        return Vendor.updateInvitationStatus(invitationId, "accepted");
    }
}