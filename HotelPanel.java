package comp603.assignment2;

/**
 *
 * @author Jeremy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelPanel extends JPanel {

    Booking bookingMenu;
    Facilities fac;

    public HotelPanel(HomePage homepage) {
        setLayout(null);

        JButton button1 = new JButton("Make new booking");
        button1.setBounds(200, 150, 200, 30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingButton(e);
            }

        });
        add(button1);

        JButton button2 = new JButton("Cancel your booking");
        button2.setBounds(200, 200, 200, 30);
        add(button2);

        JButton button3 = new JButton("Check my booking");
        button3.setBounds(200, 250, 200, 30);
        add(button3);

        JButton button4 = new JButton("Check Booking list");
        button4.setBounds(200, 300, 200, 30);
        add(button4);

        JButton button5 = new JButton("Facilities");
        button5.setBounds(200, 350, 200, 30);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FacilitiesButton(e);
            }

        });
        add(button5);

        JButton button6 = new JButton("Exit");
        button6.setBounds(200, 400, 200, 30);
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButton(e);
            }
        });
        add(button6);

        button2.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "click2");
        });
    }

    private void bookingButton(ActionEvent evt) {
        bookingMenu = new Booking();
        bookingMenu.setVisible(true);
        this.setVisible(false);
    }

    private void FacilitiesButton(ActionEvent evt) {
        fac = new Facilities(homepage);
        fac.setVisible(true);
        this.setVisible(false);
    }

    private void exitButton(ActionEvent evt) {

        System.exit(0);
    }

}
