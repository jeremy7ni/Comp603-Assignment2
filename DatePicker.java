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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatePicker extends JFrame {

    private JTextField checkInField;
    private JTextField checkOutField;
    private JLabel checkIn;
    private JLabel checkOut;
    private java.util.Date date1 = null;
    private java.util.Date date2 = null;
    private SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    private String dateIn = "";
    private String dateOut = "";
    private boolean isValidDate1 = false;
    private boolean isValidDate2 = false;
    private long diffInDays;

    public DatePicker() {

        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JPanel DatePanel = new JPanel();
        DatePanel.setLayout(null);

        checkInField = new JTextField();
        checkInField.setBounds(100, 100, 300, 35);
        checkIn = new JLabel("Enter the date you want to check in (DD/MM/YYYY):");
        checkIn.setBounds(100, 50, 300, 35);

        checkOutField = new JTextField();
        checkOutField.setBounds(100, 200, 300, 35);
        checkOut = new JLabel("Enter the date you want to check out (DD/MM/YYYY):");
        checkOut.setBounds(100, 150, 300, 35);

        JButton submit = new JButton("Submit");
        submit.setBounds(300, 400, 100, 35);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateIn = checkInField.getText();
                dateOut = checkOutField.getText();
                boolean isDateInValid = isDateValid(dateIn, "dd/MM/yyyy");
                boolean isDateOutValid = isDateValid(dateIn, "dd/MM/yyyy");
                if (!dateIn.isEmpty() && !dateOut.isEmpty()) {
                    if (isDateInValid && isDateOutValid) {
                        String dateStr1 = dateIn;
                        String dateStr2 = dateOut;
                        try {
                            date1 = date.parse(dateStr1);
                        } catch (ParseException ex) {
                            Logger.getLogger(DatePicker.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        // Validate that the year is within 1 year from current year
                        Calendar c1 = Calendar.getInstance();
                        Calendar c2 = Calendar.getInstance();
                        c1.setTime(new java.util.Date());
                        c2.setTime(date1);
                        int diff = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
                        int month = c2.get(Calendar.MONTH) + 1;
                        int day = c2.get(Calendar.DAY_OF_MONTH);
                        if (diff > 1 || diff < 0) {
                            JOptionPane.showMessageDialog(DatePicker.this, "Sorry you can only book the date within 1 year from the current year. "
                                    + "Please Enter again");
                        } 
                        if (date1.before(new java.util.Date()) ) {
                            JOptionPane.showMessageDialog(DatePicker.this, "Error: The date entered is before the current date. Please enter a valid date.");
                        }
                        isValidDate1 = true;
                        dateIn = date.format(date1);
                        //checkInField.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(DatePicker.this, "1.Invalid date format. Please Enter again");
                    }
                } else {
                    JOptionPane.showMessageDialog(DatePicker.this, "2.Please Enter the Date");
                }
                
                
            
            }
        });
        
        DatePanel.setBounds(0, 0, 700, 700);
        DatePanel.add(checkInField);
        DatePanel.add(checkIn);
        DatePanel.add(checkOutField);
        DatePanel.add(checkOut);
        DatePanel.add(submit);
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

    public static void main(String[] args) {
        DatePicker datePicker = new DatePicker();
    }
}
