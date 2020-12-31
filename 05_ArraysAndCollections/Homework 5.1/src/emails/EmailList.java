package emails;


import org.apache.commons.validator.routines.EmailValidator;

import java.util.TreeSet;

public class EmailList {
    private final TreeSet<String> emails = new TreeSet<>();


    public void add(String mail) {
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(mail)) {
            if (emails.contains(mail)) {
                System.out.println("Такой адрес уже существует в базе. Введите другой.");
            } else {
                emails.add(mail);
                System.out.println("Новый адрес успешно добавлен.");
            }
        } else {
            System.out.println("Проверьте правильность введенного адреса.");
        }
    }

    public void printList() {
        if (emails.isEmpty()) {
            System.out.println("Список адресов пуст. Добавьте новый адрес.");

        } else {
            for (String email : emails) {
                System.out.println(email);
            }
        }
    }

}
