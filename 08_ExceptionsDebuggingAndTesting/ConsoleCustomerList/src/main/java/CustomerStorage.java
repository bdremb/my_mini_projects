import org.apache.commons.validator.routines.EmailValidator;

import java.util.HashMap;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws InvalidEmailOrPhoneFormatException {
        String[] components = data.split("\\s+");
        String name = components[0] + " " + components[1];
        boolean emailValidate = EmailValidator.getInstance().isValid(components[2]);
        boolean numberValidate = components[3].matches("^((\\+7|7|8)+([0-9]){10})$"); //Russian phone numbers

        if (emailValidate && numberValidate) {
            storage.put(name, new Customer(name, components[3], components[2]));
        } else {
            throw new InvalidEmailOrPhoneFormatException("The email address or phone number is not in the correct format.");
        }

    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}