package ClinicMedicineManagement;

import java.time.LocalDate;

public class Medicine {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private LocalDate expiryDate;

    public Medicine(String id, String name, double price, int quantity, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    // Convert to CSV line
    public String toCsvString() {
        return id + "," + name + "," + price + "," + quantity + "," + expiryDate.toString();
    }

    // Parse from CSV line
    public static Medicine fromCsvString(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 5) return null;
        return new Medicine(
            parts[0],
            parts[1],
            Double.parseDouble(parts[2]),
            Integer.parseInt(parts[3]),
            LocalDate.parse(parts[4])
        );
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-20s | $%7.2f | %-8d | %-12s |", 
            id, name, price, quantity, expiryDate.toString());
    }
}
