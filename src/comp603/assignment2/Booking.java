/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp603.assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Jeremy
 */
public class Booking extends JFrame {

    private String name;
    private Room room;
    private String phone;
    private JTextArea textArea;
    private JTextField inputTextField;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField nameField;
    private JTextField phoneField;

//    private java.util.Date date1 = null;
//    private java.util.Date date2 = null;
//    private SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
//    private String dateIn = "";
//    private String dateOut = "";
//    private long diffInDays;
//    private JTextArea textArea;
    public Booking() {
        // Set the size of the frame
        setSize(700, 700);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel for the main content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create a panel for the top section
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        // Create other components for the top section
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 25));
        phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(150, 25));
        button1 = new JButton("Single Room");
        button2 = new JButton("Double Room");
        button3 = new JButton("Deluxe Room");

        // Add the components to the top panel
        topPanel.add(new JLabel("Name:"));
        topPanel.add(nameField);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(new JLabel("Phone:"));
        topPanel.add(phoneField);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(button1);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(button2);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(button3);

        // Create a panel for the bottom section
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a text area
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setEditable(false);

        // Create a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the bottom panel
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the top and bottom panels to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        getContentPane().add(mainPanel);

        // Set the frame to be visible
        setVisible(true);

        // Add action listeners to the buttons
        button1.addActionListener(e -> showRoomInformation("Single Room"));
        button2.addActionListener(e -> showRoomInformation("Double Room"));
        button3.addActionListener(e -> showRoomInformation("Deluxe Room"));
    }

    private void showRoomInformation(String roomType) {
        // Clear the text area
        textArea.setText("");

        // Retrieve name and phone from the text fields
        name = nameField.getText();
        phone = phoneField.getText();

        // Display room information based on the selected room type
        if (roomType.equals("Single Room")) {
            textArea.append("Single Room Information:\n");
            textArea.append("- Room size: ...\n");
            textArea.append("- Maximum occupancy: ...\n");
            // Add more room information as needed
        } else if (roomType.equals("Double Room")) {
            textArea.append("Double Room Information:\n");
            textArea.append("- Room size: ...\n");
            textArea.append("- Maximum occupancy: ...\n");
            // Add more room information as needed
        } else if (roomType.equals("Deluxe Room")) {
            textArea.append("Deluxe Room Information:\n");
            textArea.append("- Room size: ...\n");
            textArea.append("- Maximum occupancy: ...\n");
            // Add more room information as needed
        }
    }

    public static void main(String[] args) {
        Booking bookingFrame = new Booking();
    }
}
