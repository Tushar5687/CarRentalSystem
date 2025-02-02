public class Car {
    private String carId;
    private String carName;
    private String model;
    private double rentalPricePerDay;
    private boolean available;

    public Car(String carId, String carName, String model, double rentalPricePerDay) {
        this.carId = carId;
        this.carName = carName;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
        this.available = true;
    }

    public void displayInfo() {
        System.out.println("Car ID: " + carId +
                           ", Name: " + carName +
                           ", Model: " + model +
                           ", Price/Day: " + rentalPricePerDay +
                           ", Available: " + available);
    }

    // Getters for carName and model
    public String getCarName() {
        return carName;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailability(boolean available) {
        this.available = available;
    }

    public String getCarId() {
        return carId;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }
}
