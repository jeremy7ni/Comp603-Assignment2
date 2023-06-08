/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp603.assignment2;

/**
 *
 * @author Jeremy
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatePicker extends JFrame {

    private Date CheckIndate = null;
    private Date CheckOutdate = null;
    private SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    private String dateIn;
    private String dateOut;
    private boolean isValidDate1 = false;
    private boolean isValidDate2 = false;
    protected long diffInDays;

    private JTextField checkInField;
    private JTextField checkOutField;
    private final JLabel checkIn;
    private final JLabel checkOut;
    private final JLabel info;
    private final JButton ReturnHome;
    private final JButton ReturnBooking;
    HomePage homePage;
    Book booking;
    PriceCalculator priceCal;

    public DatePicker(Book booking, HomePage homePage) {

        this.homePage = homePage;
        this.booking = booking;
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JPanel DatePanel = new JPanel();
        DatePanel.setLayout(null);

        info = new JLabel("Pick the Date of Check In & Check Out");
        info.setBounds(100, 50, 300, 35);

        checkInField = new JTextField();
        checkInField.setBounds(100, 150, 300, 35);
        checkIn = new JLabel("Enter the date you want to check in (DD/MM/YYYY):");
        checkIn.setBounds(100, 100, 300, 35);

        JButton confirmIn = new JButton("Confirm Check In");
        confirmIn.setBounds(440, 150, 150, 35);

        checkOutField = new JTextField();
        checkOutField.setBounds(100, 300, 300, 35);
        checkOut = new JLabel("Enter the date you want to check out (DD/MM/YYYY):");
        checkOut.setBounds(100, 250, 300, 35);
        checkOutField.setEnabled(false);
        JButton confirmOut = new JButton("Confirm Check Out");
        confirmOut.setBounds(440, 300, 150, 35);

        JButton submit = new JButton("Submit");
        submit.setBounds(300, 400, 100, 35);

        confirmIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateIn = checkInField.getText();
                dateOut = checkOutField.getText();
                boolean isDateInValid = isDateValid(dateIn, "dd/MM/yyyy");
                boolean isDateOutValid = isDateValid(dateIn, "dd/MM/yyyy");
                if (!dateIn.isEmpty()) {
                    if (isDateInValid) {
                        String dateStr1 = dateIn;
                        try {
                            CheckIndate = date.parse(dateStr1);
                        } catch (ParseException ex) {
                            Logger.getLogger(DatePicker.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        // Validate that the year is within 1 year from current year
                        Calendar c1 = Calendar.getInstance();
                        Calendar c2 = Calendar.getInstance();
                        c1.setTime(new java.util.Date());
                        c2.setTime(CheckIndate);
                        int diff = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
                        int month = c2.get(Calendar.MONTH) + 1;
                        int day = c2.get(Calendar.DAY_OF_MONTH);
                        if (CheckIndate.before(new java.util.Date())) {
                            JOptionPane.showMessageDialog(DatePicker.this, "Error: The date entered is before the current date. Please enter a valid date.");
                        } else if (diff > 1 || diff < 0) {
                            JOptionPane.showMessageDialog(DatePicker.this, "Sorry you can only book the date within 1 year from the current year. "
                                    + "Please Enter again");
                        } else {
                            isValidDate1 = true;
                            dateIn = date.format(CheckIndate);
                            System.out.println(dateIn);
                            checkInField.setEnabled(false);
                            checkOutField.setEnabled(true);
                        }

                    } else {
                        JOptionPane.showMessageDialog(DatePicker.this, "Invalid date format. Please Enter again");
                    }
                } else {
                    JOptionPane.showMessageDialog(DatePicker.this, "Please Enter the Date");
                }
            }
        });

        confirmOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateOut = checkOutField.getText();
                boolean isDateOutValid = isDateValid(dateOut, "dd/MM/yyyy");
                if (!dateOut.isEmpty()) {
                    if (isDateOutValid) {
                        String dateStr2 = dateOut;
                        try {
                            CheckOutdate = date.parse(dateStr2);
                        } catch (ParseException ex) {
                            Logger.getLogger(DatePicker.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Calendar c3 = Calendar.getInstance();
                        c3.setTime(CheckIndate);
                        c3.add(Calendar.DAY_OF_MONTH, 90);
                        if (CheckOutdate.before(CheckIndate) || CheckOutdate.after(c3.getTime())) {
                            JOptionPane.showMessageDialog(DatePicker.this, "The check out date must be after the check in date "
                                    + "and within 90 days. Please enter again");
                        } else {
                            isValidDate2 = true;
                            dateOut = date.format(CheckOutdate);
                            System.out.println(dateOut);
                            checkOutField.setEnabled(false);
                        }

                    } else {
                        JOptionPane.showMessageDialog(DatePicker.this, "Invalid date format. Please Enter again");
                    }
                } else {
                    JOptionPane.showMessageDialog(DatePicker.this, "Please Enter the Date");
                }
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidDate1 = true && isValidDate2 == true) {
                    int choice = showConfirmationDialog();
                    if (choice == JOptionPane.YES_OPTION) {
                        diffInDays = ChronoUnit.DAYS.between(CheckIndate.toInstant(), CheckOutdate.toInstant());
                        if (diffInDays == 0) {
                            diffInDays = 1;
                        }
                        System.out.println(diffInDays);
                        dispose();
                        PriceCalculator(e);
                        System.out.println("User confirmed the selection.");

                    } else {
                        JOptionPane.showMessageDialog(DatePicker.this, "You can re-select the date now");
                        checkInField.setEnabled(true);
                        checkOutField.setEnabled(true);
                        isValidDate1 = false;
                        isValidDate2 = false;

                    }
                } else {
                    JOptionPane.showMessageDialog(DatePicker.this, "Please confirm the Date first");
                }
            }

        });

        ReturnHome = new JButton("Return to HomePage");
        ReturnHome.setBounds(270, 530, 180, 35);
        ReturnHome.addActionListener((ActionEvent e) -> {
            dispose();
            ReturnHomeButton(e);
        });
        ReturnBooking = new JButton("Return to Booking");
        ReturnBooking.setBounds(270, 480, 180, 35);
        ReturnBooking.addActionListener((ActionEvent e) -> {
            dispose();
            ReturnBookingButton(e);
        });

        //Adding all the components to the panel
        DatePanel.setBounds(0, 0, 700, 700);
        DatePanel.add(info);
        DatePanel.add(checkInField);
        DatePanel.add(checkIn);
        DatePanel.add(confirmIn);
        DatePanel.add(checkOutField);
        DatePanel.add(checkOut);
        DatePanel.add(confirmOut);
        DatePanel.add(submit);
        DatePanel.add(ReturnBooking);
        DatePanel.add(ReturnHome);
        add(DatePanel);

        setVisible(true);
    }

    public boolean isDateValid(String inputDate, String dateFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(inputDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private int showConfirmationDialog() {
        return JOptionPane.showConfirmDialog(this, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
    }

    private void ReturnBookingButton(ActionEvent evt) {
        booking.setVisible(true);
        dispose();
    }

    private void ReturnHomeButton(ActionEvent evt) {
        homePage.setVisible(true);
        dispose();
    }

    private void PriceCalculator(ActionEvent evt) {
        priceCal = new PriceCalculator(homePage, this);
        priceCal.setVisible(true);
        this.setVisible(false);
    }

    public java.util.Date getDate1() {
        return CheckIndate;
    }

    public java.util.Date getDate2() {
        return CheckOutdate;
    }

    public String getDateIn() {
        return dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }
    
    

}
