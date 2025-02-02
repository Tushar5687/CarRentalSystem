import java.util.ArrayList;

public class CarRentalSystem {
    ArrayList<Car> carList = new ArrayList<>();
    ArrayList<Customer> customerList = new ArrayList<>();
    ArrayList<Rental> rentalList = new ArrayList<>();

    // Add a car to the list
    public void addCar(Car car) {
        carList.add(car);
    }

    // Add a customer to the list
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    // Rent a car
    public void rentCar(Customer customer, Car car, int rentalDays) {
        if (car.isAvailable()) {
            Rental rental = new Rental(customer, car, rentalDays);
            rentalList.add(rental);
            car.setAvailability(false); // Mark the car as rented
            System.out.println("Rental successful!");
        } else {
            System.out.println("Car is not available!");
        }
    }

    // Return a car
    public void returnCar(Rental rental) {
        rental.getCar().setAvailability(true); // Mark the car as available
        rentalList.remove(rental);
        System.out.println("Car returned successfully!");
    }

    // View available cars
    public void viewAvailableCars() {
        for (Car car : carList) {
            if (car.isAvailable()) {
                car.displayInfo();
            }
        }
    }

    public ArrayList<Car> getCarList() {
        return carList;
    }

    // **New getter method for customer list**
    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    // Optional: getter for rental list if needed elsewhere
    public ArrayList<Rental> getRentalList() {
        return rentalList;
    }
}
