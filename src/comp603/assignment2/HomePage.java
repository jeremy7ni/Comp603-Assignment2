package comp603.assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

/**
 *
 * @author Jeremy
 */
public class HomePage extends JFrame {
    protected DBManager DataBase;
    JPanel mainPanel;
    Book bookingMenu;
    Facilities fac;
    Cancelbooking cancel;
    
    public HomePage() {
        DataBase = new DBManager();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set the border
        int borderWidth = 10;
        Border border = BorderFactory.createLineBorder(Color.BLACK, borderWidth);

        setPanel();
        mainPanel.setPreferredSize(new Dimension(700, 700));
        mainPanel.setBorder(border);
        getContentPane().add(mainPanel);
        pack();

        // Set the frame to be visible
        setVisible(true);
    }

    private void setPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JButton button1 = new JButton("Make new booking");
        button1.setBounds(220, 150, 200, 35);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingButton(e);
            }

        });
        mainPanel.add(button1);

        JButton button2 = new JButton("Cancel your booking");
        button2.setBounds(220, 200, 200, 35);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cancelbooking(e);
            }

        });
        mainPanel.add(button2);

        JButton button3 = new JButton("Check my booking");
        button3.setBounds(220, 250, 200, 35);
        mainPanel.add(button3);

        JButton button4 = new JButton("Facilities");
        button4.setBounds(220, 300, 200, 35);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FacilitiesButton(e);
            }

        });
        mainPanel.add(button4);

        JButton button5 = new JButton("Exit");
        button5.setBounds(220, 350, 200, 35);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButton(e);
            }
        });
        mainPanel.add(button5);

    }
    
    private void bookingButton(ActionEvent eve) {
        bookingMenu = new Book(this);
        bookingMenu.setVisible(true);
        this.setVisible(false);
    }

    private void FacilitiesButton(ActionEvent evt) {
        fac = new Facilities(this);
        fac.setVisible(true);
        this.setVisible(false);
    }
    
    private void Cancelbooking(ActionEvent evt) {
        cancel = new Cancelbooking(this);
        cancel.setVisible(true);
        this.setVisible(false);
    }
    
    private void exitButton(ActionEvent evt) {
        JOptionPane.showMessageDialog(HomePage.this, "Thank you for using the system.");
        System.exit(0);
    }
}
