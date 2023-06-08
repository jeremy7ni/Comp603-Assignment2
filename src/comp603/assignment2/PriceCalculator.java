package comp603.assignment2;

import java.awt.Font;
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
public class PriceCalculator extends JFrame {

    private Double Cost;
    private final JLabel label;
    private JRadioButton yes;
    private JRadioButton no;
    private ButtonGroup selection;
    private JLabel Vip;
    private JTextArea textArea;
    private JButton confirm;
    private JButton ReturnToDate;
    private JButton ReturnHome;
    private JScrollPane scrollPane;

    DatePicker datePicker;
    HomePage homePage;
    Room room;
    Booking booking;
    
    public PriceCalculator(HomePage homePage, DatePicker datePicker) {
        this.homePage = homePage;
        this.datePicker = datePicker;
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JPanel PricePanel = new JPanel();
        PricePanel.setLayout(null);

        label = new JLabel("Calculator your Cost");
        label.setBounds(220, 30, 500, 30);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        Vip = new JLabel("Are you our VIP customer");
        Vip.setBounds(80, 130, 500, 30);
        Vip.setFont(new Font("Arial", 0, 20));

        // two buttons to check if customer is VIP
        yes = new JRadioButton("YES");
        no = new JRadioButton("NO");
        yes.setFont(new Font("Arial", 0, 15));
        no.setFont(new Font("Arial", 0, 15));
        selection = new ButtonGroup();
        selection.add(yes);
        selection.add(no);
        yes.setBounds(350, 130, 60, 35);
        no.setBounds(440, 130, 60, 35);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeRoom();
                room.setVip(true);
                room.calCost();
                Cost = room.getPrice()*datePicker.diffInDays;
                scrollPane.setVisible(true);
            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 makeRoom();
                room.setVip(false);
                room.calCost();
                Cost = room.getPrice()*datePicker.diffInDays;
                scrollPane.setVisible(true);
            }
        });

        // new textArea to display the information and total cost.
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 200, 300, 250);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);
        scrollPane.setVisible(false);
        Font font = textArea.getFont();
        textArea.setFont(font.deriveFont(14f));

        //Confirm Button
        confirm = new JButton("<html>Confirm <br>Booking</html>");
        confirm.setBounds(480, 270, 100, 50);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = showConfirmationDialog();
                if (choice == JOptionPane.YES_OPTION) {                    
                    booking = new Booking(homePage.bookingMenu.getName(),room,homePage.bookingMenu.getPhone(),
                            homePage.bookingMenu.datePicker.getDateIn(),homePage.bookingMenu.datePicker.getDateOut());
                    booking.toString();
                    System.out.println(booking);
                    homePage.DataBase.addBooking(booking);                    
                    JOptionPane.showMessageDialog(PriceCalculator.this, "Thank you ! Booking Confirmed");
                    JOptionPane.showMessageDialog(PriceCalculator.this, "You will return to the homepage now");
                    // after booking finished,go back to the homepage

                } else {
                    JOptionPane.showMessageDialog(PriceCalculator.this, "failed to confirm booking");
                }
            }
        });

        //Return to Home Button
        ReturnHome = new JButton("Return to HomePage");
        ReturnHome.setBounds(250, 530, 180, 35);
        ReturnHome.addActionListener((ActionEvent e) -> {
            dispose();
            ReturnHomeButton(e);
        });
        ReturnToDate = new JButton("Return to Booking");
        ReturnToDate.setBounds(250, 480, 180, 35);
        ReturnToDate.addActionListener((ActionEvent e) -> {
            dispose();
            DateButton(e);
        });

        PricePanel.add(label);
        PricePanel.add(yes);
        PricePanel.add(no);
        PricePanel.add(Vip);
        PricePanel.add(textArea);
        PricePanel.add(confirm);
        PricePanel.add(scrollPane);
        PricePanel.add(ReturnHome);
        PricePanel.add(ReturnToDate);
        PricePanel.setBounds(0, 0, 700, 700);
        add(PricePanel);
        setVisible(true);
    }

    private void DateButton(ActionEvent evt) {
        datePicker.setVisible(true);
        dispose();
    }

    private void ReturnHomeButton(ActionEvent evt) {
        homePage.setVisible(true);
        dispose();
    }

    private int showConfirmationDialog() {
        return JOptionPane.showConfirmDialog(this, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
    }

    private void makeRoom() {
        if (homePage.bookingMenu.selectedRoom.contains("Single")) {
            room = new SingleRoom();
        }
        if (homePage.bookingMenu.selectedRoom.contains("Double")) {
            room = new DoubleRoom();
        }
        if (homePage.bookingMenu.selectedRoom.contains("Deluxe")) {
            room = new DeluxeRoom();
        }
    }

//    public static void main(String[] args) {
//        PriceCalculator price = new PriceCalculator();
//    }
}
