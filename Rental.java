public class Rental {
    private Customer customer;
    private Car car;
    private int rentalDays;
    private double totalCost;

    public Rental(Customer customer, Car car, int rentalDays) {
        this.customer = customer;
        this.car = car;
        this.rentalDays = rentalDays;
        this.totalCost = car.getRentalPricePerDay() * rentalDays;
    }

    public void displayRentalInfo() {
        System.out.println("Customer: " + customer.getName() + ", Car: " + car.getCarId() + ", Days: " + rentalDays + ", Total Cost: " + totalCost);
    }

    public Car getCar() {
        return car;
    }
}
