import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CarRentalGUI {
    private JFrame frame;
    private JButton rentButton, returnButton, viewButton;
    private JTextArea textArea;
    private CarRentalSystem system;

    public CarRentalGUI() {
        system = new CarRentalSystem();

        // Add sample data
        system.addCar(new Car("C001", "Toyota Corolla", "Sedan", 50));
        system.addCar(new Car("C002", "Honda Civic", "Coupe", 55));
        system.addCar(new Car("C003", "Ford Mustang", "Sports", 80));
        system.addCustomer(new Customer("Cust01", "John Doe", "123456789"));
        system.addCustomer(new Customer("Cust02", "Jane Smith", "987654321"));

        // Create the frame and set its properties
        frame = new JFrame("Car Rental System");
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set frame background to blue (choose a shade you like)
        frame.getContentPane().setBackground(new Color(173, 216, 230)); // Light blue

        // Create buttons styled with blue background and black text
        rentButton = createStyledButton("Rent Car");
        returnButton = createStyledButton("Return Car");
        viewButton = createStyledButton("View Cars");

        // Create text area styled with blue background and black text
        textArea = new JTextArea(10, 35);
        textArea.setEditable(false);
        textArea.setBackground(new Color(173, 216, 230)); // same light blue background
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Add action listeners to buttons
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rentCarForm();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnCarForm();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCars();
            }
        });

        // Add components to the frame
        frame.add(viewButton);
        frame.add(rentButton);
        frame.add(returnButton);
        frame.add(new JScrollPane(textArea));

        // Make the frame visible
        frame.setVisible(true);
    }

    // Utility method to create a styled button with blue background and black text
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(173, 216, 230)); // light blue background
        button.setForeground(Color.BLACK); // black text
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(130, 35));
        // Optionally add a border to help the button stand out
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return button;
    }

    // Modified rentCarForm method that asks for Customer ID, Car ID, and Rental Days
    private void rentCarForm() {
        String customerId = JOptionPane.showInputDialog(frame, "Enter Customer ID:");
        if (customerId == null || customerId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Customer ID cannot be empty.");
            return;
        }

        String carId = JOptionPane.showInputDialog(frame, "Enter Car ID to Rent:");
        if (carId == null || carId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Car ID cannot be empty.");
            return;
        }

        String rentalDaysStr = JOptionPane.showInputDialog(frame, "Enter Rental Days:");
        int rentalDays;
        try {
            rentalDays = Integer.parseInt(rentalDaysStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid number for rental days.");
            return;
        }

        // Search for the customer by ID
        Customer selectedCustomer = null;
        for (Customer customer : system.getCustomerList()) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                selectedCustomer = customer;
                break;
            }
        }
        if (selectedCustomer == null) {
            JOptionPane.showMessageDialog(frame, "Customer not found!");
            return;
        }

        // Search for the car by ID
        Car selectedCar = null;
        for (Car car : system.getCarList()) {
            if (car.getCarId().equalsIgnoreCase(carId)) {
                selectedCar = car;
                break;
            }
        }
        if (selectedCar == null) {
            JOptionPane.showMessageDialog(frame, "Car not found!");
            return;
        }

        if (!selectedCar.isAvailable()) {
            JOptionPane.showMessageDialog(frame, "Car is not available for rent!");
            return;
        }

        system.rentCar(selectedCustomer, selectedCar, rentalDays);
        JOptionPane.showMessageDialog(frame, "Car rented successfully!");
        viewCars();
    }

    // Method to return a car based on Car ID input
    private void returnCarForm() {
        String carId = JOptionPane.showInputDialog(frame, "Enter Car ID to Return:");
        if (carId == null || carId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Car ID cannot be empty.");
            return;
        }

        boolean rentalFound = false;
        for (Rental rental : system.getRentalList()) {
            if (rental.getCar().getCarId().equalsIgnoreCase(carId)) {
                system.returnCar(rental);
                JOptionPane.showMessageDialog(frame, "Car returned successfully!");
                rentalFound = true;
                break;
            }
        }
        if (!rentalFound) {
            JOptionPane.showMessageDialog(frame, "No active rental found for this Car ID.");
        }
        viewCars();
    }

    // Updated viewCars method to display car details including name and model
    private void viewCars() {
        textArea.setText("");
        for (Car car : system.getCarList()) {
            String info = String.format("ID: %s | Name: %s | Model: %s | Price/Day: %.2f | Available: %s%n",
                    car.getCarId(),
                    car.getCarName(),
                    car.getModel(),
                    car.getRentalPricePerDay(),
                    car.isAvailable() ? "Yes" : "No");
            textArea.append(info);
        }
    }

    public static void main(String[] args) {
        // Set the look and feel to the system default for a cleaner UI (optional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new CarRentalGUI();
    }
}
