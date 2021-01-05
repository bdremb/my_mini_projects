import javax.swing.*;

public class Main {
    private static CollapseForm collapseForm;
    private static JButton collapseButton;
    private static JTextField surnameField;
    private static JTextField nameField;
    private static JTextField fathersNameField;
    private static JPanel panel1;

    private static ExpandForm expandForm;
    private static JPanel panel2;
    private static JTextField expandTextField;
    private static JButton expandButton;
    private static JFrame frame1;
    private static JFrame frame2;

    public static void main(String[] args) {
        collapseForm = getAndInitialCollapseForm();
        expandForm = getAndInitialExpandForm();

        frame1 = getNewJFrame(430, 350, panel1, true);
        frame2 = getNewJFrame(430, 350, panel2, false);

        collapseButton.addActionListener(actionEvent -> {
            String name = collapseForm.getNameField().getText().trim();
            String surname = collapseForm.getSurnameField().getText().trim();
            String fathersName = collapseForm.getFathersNameField().getText().trim();
            if (name.length() == 0 || surname.length() == 0) {
                JOptionPane.showMessageDialog(
                        panel1,
                        "Введите имя и фамилию",
                        "Недостаточно данных",
                        JOptionPane.PLAIN_MESSAGE
                );
            } else {
                expandTextField.setText(surname.toUpperCase() + " "
                        + name.toUpperCase() + " " + fathersName.toUpperCase());
                frame1.setVisible(false);
                frame2.setVisible(true);
            }
        });

        expandButton.addActionListener(actionEvent -> {
            String[] fullName = expandForm.getTextField1().getText().trim().split("\\s+");
            if (fullName.length < 2 || fullName.length > 3) {
                JOptionPane.showMessageDialog(
                        panel2,
                        "Введите имя, фамилию и, если есть, отчество",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE
                );
            }
            setFullName(fullName);
        });
    }

    private static CollapseForm getAndInitialCollapseForm() {
        CollapseForm collapseForm = new CollapseForm();
        collapseButton = collapseForm.getCollapse();
        surnameField = collapseForm.getSurnameField();
        nameField = collapseForm.getNameField();
        fathersNameField = collapseForm.getFathersNameField();
        panel1 = collapseForm.getPanel1();
        return collapseForm;
    }

    private static ExpandForm getAndInitialExpandForm() {
        ExpandForm expandForm = new ExpandForm();
        expandButton = expandForm.getExpand();
        expandTextField = expandForm.getTextField1();
        panel2 = expandForm.getPanel2();
        return expandForm;
    }

    private static JFrame getNewJFrame(int width, int height, JPanel panel, boolean isVisible) {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(isVisible);
        return frame;
    }

    private static void setFullName(String[] fullName) {
        nameField.setText(fullName[1].trim().toUpperCase());
        surnameField.setText(fullName[0].trim().toUpperCase());
        fathersNameField.setText("");
        if (fullName.length == 3) {
            fathersNameField.setText(fullName[2].trim().toUpperCase());
        }
        frame1.setVisible(true);
        frame2.setVisible(false);
    }
}