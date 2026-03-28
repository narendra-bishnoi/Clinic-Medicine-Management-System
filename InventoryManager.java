package ClinicMedicineManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Medicine> medicines;

    public InventoryManager() {
        medicines = FileHandler.loadData();
    }

    private void save() {
        FileHandler.saveData(medicines);
    }

    public boolean addMedicine(Medicine med) {
        // Prevent duplicate ID
        for (Medicine m : medicines) {
            if (m.getId().equalsIgnoreCase(med.getId())) {
                return false;
            }
        }
        medicines.add(med);
        save();
        return true;
    }

    public List<Medicine> getAllMedicines() {
        return medicines;
    }

    public Medicine searchMedicine(String query) {
        for (Medicine m : medicines) {
            if (m.getId().equalsIgnoreCase(query) || m.getName().equalsIgnoreCase(query)) {
                return m;
            }
        }
        return null;
    }

    public boolean updateStock(String query, int addedQuantity) {
        Medicine med = searchMedicine(query);
        if (med != null) {
            med.setQuantity(med.getQuantity() + addedQuantity);
            save();
            return true;
        }
        return false;
    }

    public boolean deleteMedicine(String id) {
        boolean removed = medicines.removeIf(m -> m.getId().equalsIgnoreCase(id));
        if (removed) {
            save();
        }
        return removed;
    }

    // Returns total bill amount if successful, or -1.0 if not found, or -2.0 if insufficient stock
    public double sellMedicine(String query, int sellQuantity) {
        Medicine med = searchMedicine(query);
        if (med != null) {
            if (med.getQuantity() >= sellQuantity) {
                med.setQuantity(med.getQuantity() - sellQuantity);
                double total = med.getPrice() * sellQuantity;
                save();
                return total;
            } else {
                return -2.0; // Insufficient stock
            }
        }
        return -1.0; // Not found
    }

    public void checkAlerts() {
        LocalDate now = LocalDate.now();
        boolean issuesFound = false;
        System.out.println("--- ALERT SYSTEM ---");
        for (Medicine m : medicines) {
            if (m.getQuantity() < 10) {
                System.out.println("LOW STOCK ALERT: " + m.getName() + " (ID: " + m.getId() + ") has only " + m.getQuantity() + " left!");
                issuesFound = true;
            }
            if (m.getExpiryDate().isBefore(now) || m.getExpiryDate().isEqual(now)) {
                System.out.println("EXPIRY ALERT: " + m.getName() + " (ID: " + m.getId() + ") has EXPIRED on " + m.getExpiryDate() + "!");
                issuesFound = true;
            }
        }
        if (!issuesFound) {
            System.out.println("No alerts. Inventory is healthy.");
        }
        System.out.println("--------------------");
    }
}
