package comp603.assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    private Room room;
    private String phone;
    private JLabel nameLabel = new JLabel("Please enter your name: ");
    private JLabel phoneLabel = new JLabel("Please enter your phone number: ");
    private JTextField TextField;
    private JLabel title = new JLabel("Make new Booking");
    private boolean isNameSubmitted = false;
    private boolean isPhoneSubmitted = false;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JLabel phoneInvalid;
    private JButton finishSelection;
    private JTextArea textArea;
    DatePicker datePicker;
    private ButtonGroup selection;
    public Book() {

        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JPanel BookPanel = new JPanel();
        BookPanel.setLayout(null);
        textArea = new JTextArea();
        // Get user info
        TextField = new JTextField();
        TextField.setBounds(50, 100, 300, 30);
        nameLabel.setBounds(50, 50, 700, 30);
        phoneLabel.setBounds(50, 50, 700, 30);
        title.setBounds(250, 20, 700, 30);
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isNameSubmitted) {
                    name = TextField.getText();
                    if (!name.isEmpty()) {
                        System.out.println(name);
                        BookPanel.remove(nameLabel);
                        BookPanel.add(phoneLabel);
                        BookPanel.repaint();
                        BookPanel.revalidate();
                        isNameSubmitted = true;
                        TextField.setText("");
                    }
                } else {
                    phone = TextField.getText();
                    if (phoneisValid(phone)) {
                        isPhoneSubmitted = true;
                        System.out.println(phone);
                        TextField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(Book.this, "Invalid phone number. Please enter a valid phone number(9-11 digits).");
                    }
                }
            }
        });
        submit.setBounds(400, 100, 80, 30);

        // Room info area
//        JTextArea option1Text = new JTextArea("Option 1 Text");
//        JTextArea option2Text = new JTextArea("Option 2 Text");
//        JTextArea option3Text = new JTextArea("Option 3 Text");
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 300, 400, 250);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);
        //scrollPane.setViewportView(option1Text);

//        JButton option1Button = new JButton("Single Room");
//        option1Button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                scrollPane.setViewportView(option1Text);
//            }
//        });
//
//        JButton option2Button = new JButton("Double Room");
//        option2Button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                scrollPane.setViewportView(option2Text);
//            }
//        });
//
//        JButton option3Button = new JButton("Deluxe Room");
//        option3Button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                scrollPane.setViewportView(option3Text);
//            }
//        });
        option1 = new JRadioButton("Single Room");
        option2 = new JRadioButton("Double Room");
        option3 = new JRadioButton("Deluxe Room");
        selection = new ButtonGroup();
        selection.add(option1);
        selection.add(option2);
        selection.add(option3);
        option1.addActionListener(e -> showRoomInformation("Single Room"));
        option2.addActionListener(e -> showRoomInformation("Double Room"));
        option3.addActionListener(e -> showRoomInformation("Deluxe Room"));
        option1.setBounds(50, 200, 120, 35);
        option2.setBounds(200, 200, 120, 35);
        option3.setBounds(350, 200, 120, 35);

        finishSelection = new JButton("Finished Selection");
        finishSelection.setBounds(480, 400, 170, 40);
        finishSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNameSubmitted && isPhoneSubmitted) {
                    if (selection.getSelection()!= null) {
                        datePicker = new DatePicker();
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(Book.this, "Plese Selected your Room Type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(Book.this, "Plese enter your name and phone number first.");
                }
            }

        });

        BookPanel.add(scrollPane);
        BookPanel.add(option1);
        BookPanel.add(option2);
        BookPanel.add(option3);
        BookPanel.add(title);
        BookPanel.add(nameLabel);
        BookPanel.add(TextField);
        BookPanel.add(finishSelection);
        BookPanel.add(submit);
        BookPanel.setBounds(0, 0, 700, 700);

        add(BookPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Book book = new Book();
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
            // Add more room information as needed
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
}
