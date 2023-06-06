/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp603.assignment2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jeremy
 */
public class Facilities extends JFrame {

    HomePage homePage;

    public Facilities(HomePage homepage) {
        this.homePage = homepage;
        
        // Set the size of the frame
        setSize(700, 700);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the buttons
        JPanel facPanel = new JPanel();
        facPanel.setLayout(null); // Use absolute layout

        JButton button1 = new JButton("High-speed Full coverage WiFi");
        button1.setBounds(200, 100, 300, 30);
        button1.setFont(new Font("Arial", Font.BOLD, 15));
        facPanel.add(button1);
        button1.addActionListener((ActionEvent e) -> {
            String text = "High-speed Full coverage WiFi:\n\n"
                    + "*Our hotel provides high-speed WiFi access in all areas, ensuring a seamless internet experience for our guests."
                    + "\n*The WiFi name is PDC603Hotel"
                    + "\n*The WiFi password is HotelPDC603 ";
            JOptionPane.showMessageDialog(Facilities.this, text, "Facility Information", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton button2 = new JButton("Parking & Transportation");
        button2.setBounds(200, 150, 300, 30);
        button2.setFont(new Font("Arial", Font.BOLD, 15));
        facPanel.add(button2);
        button2.addActionListener((ActionEvent e) -> {
            String text = "Parking & Transportation:\n\n"
                    + "*We offer complimentary parking for hotel guests"
                    + "\n*Additionally, we provide shuttle services to popular attractions and the airpot"
                    + "\n*Valet Parking is also available ";
            JOptionPane.showMessageDialog(Facilities.this, text, "Facility Information", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton button3 = new JButton("Fitness Center");
        button3.setBounds(200, 200, 300, 30);
        button3.setFont(new Font("Arial", Font.BOLD, 15));
        facPanel.add(button3);
        button3.addActionListener((ActionEvent e) -> {
            String text = "Fitness Center:\n\n"
                    + "*Our state-of-the-art fitness center is equipped with a wide range of exercise machines and equipment,"
                    + "\n  ensuring a great workout experience."
                    + "\n*Elliptical Machines"
                    + "\n*Free Weights"
                    + "\n*Rower"
                    + "\n*24/7 No booking required";
            JOptionPane.showMessageDialog(Facilities.this, text, "Facility Information", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton button4 = new JButton("Meetings & Events");
        button4.setBounds(200, 250, 300, 30);
        button4.setFont(new Font("Arial", Font.BOLD, 15));
        facPanel.add(button4);
        button4.addActionListener((ActionEvent e) -> {
            String text = "Meetings & Events:\n\n"
                    + "*We offer meeting Room for hotel guests,"
                    + "\n  Over 20 meeting rooms avaliable"
                    + "\n*Largest room capacity: 460"
                    + "\n*Booking required";
            JOptionPane.showMessageDialog(Facilities.this, text, "Facility Information", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton button5 = new JButton("Business Center");
        button5.setBounds(200, 300, 300, 30);
        button5.setFont(new Font("Arial", Font.BOLD, 15));
        facPanel.add(button5);
        button5.addActionListener((ActionEvent e) -> {
            String text = "Business Center:\n\n"
                    + "*We offer Business Center for hotel guests,"
                    + "\n 24/7 avaliable"
                    + "\n Business Services are available"
                    + "\n*Equipment provide : Copying & Printer & Scanner";
            JOptionPane.showMessageDialog(Facilities.this, text, "Facility Information", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton button6 = new JButton("Return to Homepage");
        button6.setBounds(200, 350, 300, 30);
        button6.setFont(new Font("Arial", Font.BOLD, 15));
        facPanel.add(button6);
        button6.addActionListener((ActionEvent e) -> {
            dispose();
            HomeButton(e);
        });

        // Add the panel to the frame
        getContentPane().add(facPanel);

        // Set the frame to be visible
        setVisible(true);

    }

    private void HomeButton(ActionEvent evt) {
        homePage.setVisible(true);
        dispose();
    }
//    public static void main(String[] args) {
//        Facilities fac = new Facilities();
//    }
}
