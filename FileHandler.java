package ClinicMedicineManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "medicines.csv";

    public static void saveData(List<Medicine> medicines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Medicine medicine : medicines) {
                bw.write(medicine.toCsvString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    public static List<Medicine> loadData() {
        List<Medicine> medicines = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return medicines; // Return empty list if no file exists yet
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Medicine med = Medicine.fromCsvString(line);
                    if (med != null) {
                        medicines.add(med);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + e.getMessage());
        }
        return medicines;
    }
}
