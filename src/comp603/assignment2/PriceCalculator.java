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

/**
 *
 * @author JIUXIN NI
 */
public class PriceCalculator extends JFrame {

    private Double Cost;
    private final JLabel label;
    private final JRadioButton yes;
    private final JRadioButton no;
    private final ButtonGroup selection;
    private final JLabel Vip;
    private final JTextArea textArea;
    private final JButton confirm;
    private final JButton ReturnToDate;
    private final JButton ReturnHome;

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
                Cost = room.getPrice() * datePicker.diffInDays;
                showRoomInformation(room.isVip());
            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeRoom();
                room.setVip(false);
                room.calCost();
                Cost = room.getPrice() * datePicker.diffInDays;
                showRoomInformation(room.isVip());
            }
        });

        // new textArea to display the information and total cost.
        textArea = new JTextArea();
        textArea.setBounds(50, 200, 320, 250);
        textArea.setEditable(false);
        Font font = textArea.getFont();
       textArea.setFont(new Font("Arial", Font.PLAIN, 15));
        //Confirm Button
        confirm = new JButton("<html>Confirm <br>Booking</html>");
        confirm.setBounds(480, 270, 100, 50);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = showConfirmationDialog();
                if (choice == JOptionPane.YES_OPTION) {
                    booking = new Booking(homePage.bookingMenu.getName(), room, homePage.bookingMenu.getPhone(),
                            homePage.bookingMenu.datePicker.getDateIn(), homePage.bookingMenu.datePicker.getDateOut());
                    booking.toString();
                    System.out.println(booking);
                    homePage.DataBase.addBooking(booking);
                    JOptionPane.showMessageDialog(PriceCalculator.this, "Thank you ! Booking Confirmed");
                    JOptionPane.showMessageDialog(PriceCalculator.this, "You will return to the homepage now");
                    ReturnHomeButton(e);
                    dispose();
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

    //check if user confirm the choice
    private int showConfirmationDialog() {
        return JOptionPane.showConfirmDialog(this, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
    }

    // Made a Room depends on the Room Type
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

    //Show Room Info for the user again
    private void showRoomInformation(boolean vip) {
        // Clear the text area        
        textArea.setText("");
        if (vip) {
            textArea.append("The Room You booked is: "+room.getRoomType() +"\n");
            textArea.append("You can get discount price for 20% \n"
                    + "the price per day now is $ " + room.getPrice());
            textArea.append("\nThe total cost is $" + Cost);
            textArea.setEditable(false);

        } else if (!vip) {
            textArea.append("The Room You booked is: "+room.getRoomType() +"\n");
            textArea.append("the price per day now is $ " + room.getPrice());
            textArea.append("\nThe total cost is $" + Cost);
            textArea.setEditable(false);
        }
    }
}
