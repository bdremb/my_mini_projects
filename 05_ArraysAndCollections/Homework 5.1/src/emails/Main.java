package emails;

public class Main {
    private final static String COMMAND_ADD = "ADD";
    private final static String COMMAND_LIST = "LIST";
    private final static String COMMAND_EXIT = "EXIT";

    public static void main(String[] args) {

        EmailList emailList = new EmailList();

        for (; ; ) {
            String userInput = UserInput.getLine();

            if (userInput.startsWith(COMMAND_ADD)) {
                emailList.add(userInput.replaceFirst(COMMAND_ADD, "").trim());
            } else if (userInput.equals(COMMAND_LIST)) {
                emailList.printList();
            } else if (userInput.equals(COMMAND_EXIT)) {
                break;

            } else {
                System.out.println("Неверная команда.");
            }
        }
    }
}
