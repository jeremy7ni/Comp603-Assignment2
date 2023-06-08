package comp603.assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class Book extends JFrame {

    private String name;
//    private Room room;
    private String phone;
    private boolean isNameSubmitted = false;
    private boolean isPhoneSubmitted = false;
//    private String dateIn = "";
//    private String dateOut = "";
    protected String selectedRoom;
    protected final String[] roomTypes = {"Single Room", "Double Room", "Deluxe Room"};

    private final JLabel nameLabel = new JLabel("Please enter your name: ");
    private final JLabel phoneLabel = new JLabel("Please enter your phone number: ");
    private final JTextField nameTextField;
    private final JTextField phoneTextField;
    private final JLabel title = new JLabel("Make new Booking");
    private final JButton finishSelection;
    private final JTextArea textArea;
    private JComboBox<String> roomType;

    HomePage homePage;
    DatePicker datePicker;

    public Book(HomePage homepage) {

        this.homePage = homepage;

        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JPanel BookPanel = new JPanel();
        BookPanel.setLayout(null);

        // Get user info
        textArea = new JTextArea();
        nameTextField = new JTextField();
        nameTextField.setBounds(50, 100, 300, 30);
        phoneTextField = new JTextField();
        phoneTextField.setBounds(50, 200, 300, 30);
        nameLabel.setBounds(50, 50, 500, 30);
        phoneLabel.setBounds(50, 150, 500, 30);
        title.setBounds(250, 20, 700, 30);
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

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 350, 400, 250);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);

        roomType = new JComboBox();
        roomType.setModel(new DefaultComboBoxModel<>(roomTypes));
        roomType.setBounds(200, 270, 150, 35);
        showRoomInformation((String) roomType.getSelectedItem());
        roomType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRoomInformation((String) roomType.getSelectedItem());
            }
        });

        finishSelection = new JButton("Finished Selection");
        finishSelection.setBounds(480, 410, 150, 40);
        finishSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNameSubmitted && isPhoneSubmitted) {
                    int choice = showConfirmationDialog();
                    if (choice == JOptionPane.YES_OPTION) {
                        DatePicker(e);
                        selectedRoom = (String) roomType.getSelectedItem();
                        //setVisible(false);
                    } else {

                    }
                } else {
                    JOptionPane.showMessageDialog(Book.this, "Plese enter your name and phone number first.");
                }
            }
        });

        // Return to homePage
        JButton ReturnHome = new JButton("Return to Homepage");
        ReturnHome.setBounds(480, 480, 150, 40);
        ReturnHome.addActionListener((ActionEvent e) -> {
            dispose();
            HomeButton(e);
        });

        //Adding all the components to the panel
        BookPanel.add(scrollPane);
        BookPanel.add(roomType);
        BookPanel.add(title);
        BookPanel.add(nameLabel);
        BookPanel.add(phoneLabel);
        BookPanel.add(nameTextField);
        BookPanel.add(phoneTextField);
        BookPanel.add(finishSelection);
        BookPanel.add(confirmName);
        BookPanel.add(confirmPhone);
        BookPanel.add(ReturnHome);
        BookPanel.setBounds(0, 0, 700, 700);

        add(BookPanel);
        setVisible(true);
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    private void HomeButton(ActionEvent evt) {
        homePage.setVisible(true);
        dispose();
    }

    private void showRoomInformation(String roomType) {
        // Clear the text area        
        textArea.setText("");
        // Display room information based on the selected room type
        if (roomType.equals("Single Room")) {
            textArea.append("Single Room Information:\n");
            textArea.append("- Room size: ...\n");
            textArea.append("- Maximum occupancy: ...\n");
            textArea.setEditable(false);
            // Add more room information as needed
        } else if (roomType.equals("Double Room")) {
            textArea.append("Double Room Information:\n");
            textArea.append("- Room size: ...\n");
            textArea.append("- Maximum occupancy: ...\n");
            textArea.setEditable(false);

        } else if (roomType.equals("Deluxe Room")) {
            textArea.append("Deluxe Room Information:\n");
            textArea.append("- Room size: ...\n");
            textArea.append("- Maximum occupancy: ...\n");
            textArea.setEditable(false);
        }
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

    private void DatePicker(ActionEvent evt) {
        datePicker = new DatePicker(this, homePage);
        datePicker.setVisible(true);
        this.setVisible(false);
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

    private int showConfirmationDialog() {
        return JOptionPane.showConfirmDialog(this, "Are you sure you want to choose " + (String) roomType.getSelectedItem() + " ", "Confirmation", JOptionPane.YES_NO_OPTION);
    }
}
