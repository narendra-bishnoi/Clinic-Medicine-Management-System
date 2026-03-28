# 💊 Clinic Medicine Management System

A Java-based console application to manage medicine inventory in a clinic or medical store.
This system supports adding medicines, managing stock, handling sales, and storing data persistently.

---

## 🚀 Features

* Add, view, search, and delete medicines
* Buy (increase stock) and sell (decrease stock)
* Prevent selling when stock is insufficient
* Low stock alerts
* Expiry tracking
* File-based data storage (auto save/load)

---

## 🛠️ Tech Stack

* Java (Core + OOP)
* ArrayList (data storage)
* File Handling (BufferedReader, BufferedWriter)

---

## 📁 Project Structure

```text
ClinicMedicineManagement/
│── FileHandler.java
│── InventoryManager.java
│── Medicine.java
│── Main.java
│── *.class
│
│── data/
│   └── medicines.txt
```

---

## ⚠️ IMPORTANT (PACKAGE INFO)

This project uses the following package:

```java
package ClinicMedicineManagement;
```

So files must be compiled and run accordingly.

---

## ⚙️ How to Run (WITH PACKAGE)

### Step 1: Open terminal in project root folder

### Step 2: Compile

```bash
javac -d . *.java
```

### Step 3: Run

```bash
java ClinicMedicineManagement.Main
```

---

## 🖥️ Sample Menu

```
1. Add Medicine
2. View Medicines
3. Buy Stock
4. Sell Medicine
5. Search Medicine
6. Delete Medicine
7. Exit
```

---

## 💾 Data Storage

* Data is stored in: `data/medicines.txt`
* Automatically loads on startup
* Saves after each operation

---

## 🎯 Concepts Used

* Object-Oriented Programming (Encapsulation)
* File Handling (persistent storage)
* Modular design
* Error handling

---

## ⚠️ Limitations

* Console-based (no GUI)
* Single-user system
* No database integration

---

## 🔮 Future Enhancements

* GUI (Swing / JavaFX)
* Database (MySQL)
* Billing system
* Multi-user login

---

## 👨‍💻 Author

Narendra Bishnoi
B.Tech CSE (AI & ML)

---

## 📌 Note

If you face issues running the project, ensure:

* Java JDK is installed
* Correct package name is used in run command

---

# ⭐ Star this repo if you found it helpful!
