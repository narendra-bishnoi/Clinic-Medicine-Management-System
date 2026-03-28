package ClinicMedicineManagement;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static InventoryManager manager;
    private static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("Loading Clinic Medicine Management System...");
        manager = new InventoryManager();
        scanner = new Scanner(System.in);
        
        manager.checkAlerts();

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        addNewMedicine();
                        break;
                    case 2:
                        viewAllMedicines();
                        break;
                    case 3:
                        searchMedicine();
                        break;
                    case 4:
                        addStockPurchase();
                        break;
                    case 5:
                        sellMedicineStockOut();
                        break;
                    case 6:
                        deleteMedicine();
                        break;
                    case 7:
                        manager.checkAlerts();
                        break;
                    case 8:
                        running = false;
                        System.out.println("Exiting system. Data has been saved automatically. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=============================================");
        System.out.println("    CLINIC MEDICINE MANAGEMENT SYSTEM    ");
        System.out.println("=============================================");
        System.out.println("1. Add New Medicine");
        System.out.println("2. View All Medicines");
        System.out.println("3. Search Medicine");
        System.out.println("4. Purchase Medicine (Add Stock)");
        System.out.println("5. Sell Medicine (Stock Out)");
        System.out.println("6. Delete Medicine");
        System.out.println("7. View Alerts (Low Stock / Expired)");
        System.out.println("8. Exit");
        System.out.println("=============================================");
    }

    private static void addNewMedicine() {
        System.out.println("\n--- Add New Medicine ---");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        
        if (manager.searchMedicine(id) != null) {
            System.out.println("Error: Medicine with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        try {
            System.out.print("Enter Price: $");
            double price = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Enter Initial Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
            LocalDate expiryDate = LocalDate.parse(scanner.nextLine());
            
            Medicine med = new Medicine(id, name, price, quantity, expiryDate);
            if (manager.addMedicine(med)) {
                System.out.println("Success: Medicine added to inventory.");
            } else {
                System.out.println("Error: Could not add medicine.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Price and Quantity must be valid numbers.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private static void printTableHeader() {
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-8s | %-8s | %-12s |\n", "ID", "Name", "Price", "Quantity", "Expiry Date");
        System.out.println("----------------------------------------------------------------------------------");
    }

    private static void viewAllMedicines() {
        List<Medicine> all = manager.getAllMedicines();
        if (all.isEmpty()) {
            System.out.println("\nNo medicines found in inventory.");
            return;
        }
        System.out.println("\n--- All Medicines in Stock ---");
        printTableHeader();
        for (Medicine m : all) {
            System.out.println(m);
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    private static void searchMedicine() {
        System.out.print("\nEnter Medicine ID or Name to search: ");
        String query = scanner.nextLine();
        Medicine med = manager.searchMedicine(query);
        
        if (med != null) {
            System.out.println("\nMedicine Found:");
            printTableHeader();
            System.out.println(med);
            System.out.println("----------------------------------------------------------------------------------");
        } else {
            System.out.println("Medicine not found.");
        }
    }

    private static void addStockPurchase() {
        System.out.print("\nEnter Medicine ID or Name to add stock: ");
        String query = scanner.nextLine();
        
        if (manager.searchMedicine(query) == null) {
            System.out.println("Error: Medicine not found. Add it as a new medicine first.");
            return;
        }
        
        try {
            System.out.print("Enter quantity to add: ");
            int amt = Integer.parseInt(scanner.nextLine());
            if (amt <= 0) {
                System.out.println("Quantity must be positive.");
                return;
            }
            if (manager.updateStock(query, amt)) {
                System.out.println("Stock updated successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        }
    }

    private static void sellMedicineStockOut() {
        System.out.print("\nEnter Medicine ID or Name to sell: ");
        String query = scanner.nextLine();
        
        try {
            System.out.print("Enter quantity to sell: ");
            int amt = Integer.parseInt(scanner.nextLine());
            if (amt <= 0) {
                System.out.println("Quantity must be positive.");
                return;
            }
            
            double total = manager.sellMedicine(query, amt);
            if (total >= 0) {
                 System.out.printf("Sale successful! Total bill amount: $%.2f\n", total);
            } else if (total == -2.0) {
                 System.out.println("Error: Insufficient stock for this sale.");
            } else {
                 System.out.println("Error: Medicine not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        }
    }

    private static void deleteMedicine() {
         System.out.print("\nEnter Medicine ID to delete: ");
         String id = scanner.nextLine();
         
         if (manager.deleteMedicine(id)) {
             System.out.println("Medicine deleted successfully.");
         } else {
             System.out.println("Error: Medicine with ID " + id + " not found.");
         }
    }
}
