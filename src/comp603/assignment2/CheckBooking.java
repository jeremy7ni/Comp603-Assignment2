package comp603.assignment2;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
 * @author JIUXIN NI
 */
public class CheckBooking extends JFrame {

    private String name;
    private String phone;
    private boolean isNameSubmitted = false;
    private boolean isPhoneSubmitted = false;
    protected int index = 1;
    private final JLabel nameLabel;
    private final JLabel phoneLabel;
    private final JTextField nameTextField;
    private final JTextField phoneTextField;
    private final JTextArea textArea;
    private final JButton findBooking;
    private final JButton ReturnHome;
    JPanel checkPanel;

    HomePage homePage;

    //Check the Booking
    public CheckBooking(HomePage homePage) {

        this.homePage = homePage;
        //SetUp Panel
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        checkPanel = new JPanel();
        checkPanel.setLayout(null);
        checkPanel.setBounds(0, 0, 700, 700);

        //SetUp TextField to get user input
        nameTextField = new JTextField();
        nameTextField.setBounds(50, 100, 300, 30);
        phoneTextField = new JTextField();
        phoneTextField.setBounds(50, 200, 300, 30);
        nameLabel = new JLabel("Please enter your name: ");
        phoneLabel = new JLabel("Please enter your phone number: ");
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

        //find booking by calling getResult()
        findBooking = new JButton("Search for Booking");
        findBooking.setBounds(480, 320, 150, 40);
        findBooking.addActionListener((ActionEvent e) -> {
            getResult();
        });

        ReturnHome = new JButton("Return to Homepage");
        ReturnHome.setBounds(480, 480, 150, 40);
        ReturnHome.addActionListener((ActionEvent e) -> {
            //dispose();
            HomeButton(e);
        });

        //Adding all the components to the panel
        checkPanel.add(nameLabel);
        checkPanel.add(phoneLabel);
        checkPanel.add(nameTextField);
        checkPanel.add(phoneTextField);
        checkPanel.add(confirmName);
        checkPanel.add(confirmPhone);
        checkPanel.add(scrollPane);
        checkPanel.add(findBooking);
        checkPanel.add(ReturnHome);
        add(checkPanel);
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

    private void HomeButton(ActionEvent evt) {
        homePage.setVisible(true);
        dispose();
    }

    // search booking record using name and phone on the DataBase
    private void getResult() {
        if (isNameSubmitted && isPhoneSubmitted && index == 1) {
            ArrayList<Booking> result = homePage.DataBase.checkForBooking(name, phone);
            String results = "";
            if (!result.isEmpty()) {
                for (Booking token : result) {
                    results += index + "\n" + token.toString();
                    index++;
                }
                textArea.setText(results);
            } else {
                textArea.setText("No record found");
                JOptionPane.showMessageDialog(this, "Sorry we couldn't find your booking.");
            }
        }

    }
}
