package comp603.assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Jeremy
 */
public class Cancelbooking extends JFrame {

    private String name;
    private String phone;
    private boolean isNameSubmitted = false;
    private boolean isPhoneSubmitted = false;
    private final JLabel nameLabel = new JLabel("Please enter your name: ");
    private final JLabel phoneLabel = new JLabel("Please enter your phone number: ");
    private final JTextField nameTextField;
    private final JTextField phoneTextField;
    private final JTextArea textArea;
    private final JButton confirmCancel;
    private final JButton ReturnHome;
    JPanel cancelPanel;

    HomePage homePage;

    public Cancelbooking(HomePage homePage) {

        this.homePage = homePage;
        //SetUp Panel
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        cancelPanel = new JPanel();
        cancelPanel.setLayout(null);
        cancelPanel.setBounds(0, 0, 700, 700);

        //SetUp TextField to get user input
        nameTextField = new JTextField();
        nameTextField.setBounds(50, 100, 300, 30);
        phoneTextField = new JTextField();
        phoneTextField.setBounds(50, 200, 300, 30);
        nameLabel.setBounds(50, 50, 500, 30);
        phoneLabel.setBounds(50, 150, 500, 30);
        JButton confirmName = new JButton("Confirm Name");
        JButton confirmPhone = new JButton("Confirm Phone");
        confirmName.addActionListener(e -> {
            if (!isNameSubmitted) {
                submitName();
            }
        });
        confirmPhone.addActionListener(e -> {
            if (!isPhoneSubmitted) {
                submitPhone();
            }
        });
        confirmName.setBounds(400, 100, 150, 30);
        confirmPhone.setBounds(400, 200, 150, 30);

        //TextArea to show user's information
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 300, 400, 250);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);

        //Confirm cancel Booking
        confirmCancel = new JButton("Cancel The Booking");
        confirmCancel.setBounds(480, 400, 150, 40);
        confirmCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNameSubmitted && isPhoneSubmitted) {
                    int choice = showConfirmationDialog();
                    if (choice == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(Cancelbooking.this, "Thank you ! Booking Cancelled");
                        JOptionPane.showMessageDialog(Cancelbooking.this, "You will return to the homepage now");
                        System.out.println("User confirmed the selection.");
                        // after cancel finished,go back to the homepage
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(Cancelbooking.this, "failed to cocancel booking");
                    }
                } else {
                    JOptionPane.showMessageDialog(Cancelbooking.this, "Plese enter your name and phone number first.");
                }
            }
        });
        ReturnHome = new JButton("Return to Homepage");
        ReturnHome.setBounds(480, 480, 150, 40);
        ReturnHome.addActionListener((ActionEvent e) -> {
            //dispose();
            HomeButton(e);
        });
        
        //Adding all the components to the panel
        cancelPanel.add(nameLabel);
        cancelPanel.add(phoneLabel);
        cancelPanel.add(nameTextField);
        cancelPanel.add(phoneTextField);
        cancelPanel.add(confirmName);
        cancelPanel.add(confirmPhone);
        cancelPanel.add(textArea);
        cancelPanel.add(scrollPane);
        cancelPanel.add(confirmCancel);
        cancelPanel.add(ReturnHome);
        add(cancelPanel);
        setVisible(true);
    }

    private void submitName() {
        name = nameTextField.getText();
        if (!name.isEmpty()) {
            System.out.println(name);
            repaint();
            revalidate();
            isNameSubmitted = true;
            nameTextField.setEnabled(false);

        } else {
            JOptionPane.showMessageDialog(this, "Please enter your name.");
        }

    }

    private void submitPhone() {
        phone = phoneTextField.getText();
        if (phoneisValid(phone)) {
            isPhoneSubmitted = true;
            System.out.println(phone);
            phoneTextField.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid phone number. Please enter a valid phone number (9-11 digits).");
        }
    }

    public boolean checkName(String name) {
        return true;
    }

    // check if the phone is valid or not
    public boolean phoneisValid(String phone) {

        if (!phone.matches("\\d+")) {
            return false;
        }
        int length = phone.length();
        if (length < 9 || length > 11) {
            return false;
        }
        return true;
    }

    private int showConfirmationDialog() {
        return JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this Booking?", "Confirmation", JOptionPane.YES_NO_OPTION);
    }

    private void HomeButton(ActionEvent evt) {
        homePage.setVisible(true);
        dispose();
    }

}
