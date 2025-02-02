public class Customer {
    private String customerId;
    private String name;
    private String contact;

    public Customer(String customerId, String name, String contact) {
        this.customerId = customerId;
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getContact() {
        return contact;
    }
}
