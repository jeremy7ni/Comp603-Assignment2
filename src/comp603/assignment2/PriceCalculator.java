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

    private final JLabel label;
    private JRadioButton yes;
    private JRadioButton no;
    private ButtonGroup selection;
    private JLabel Vip;
    private boolean vip;
    private JTextArea textArea;
    private JButton confirm;
    private JScrollPane scrollPane;
    DatePicker datePicker;
    HomePage homePage;
    
    public PriceCalculator(HomePage homePage,DatePicker datePicker) {
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
                vip = true;
                scrollPane.setVisible(true); 
            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vip = false;
                scrollPane.setVisible(true);
            }
        });

        // new textArea to display the information and total cost.
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 300, 400, 250);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);
        scrollPane.setVisible(false);
        Font font = textArea.getFont();
        textArea.setFont(font.deriveFont(14f));
        
        //Confirm Button
        confirm = new JButton("Confirm");
        confirm.setBounds(500, 350, 80, 35);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int choice = showConfirmationDialog();
                    if (choice == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(PriceCalculator.this, "Thank you ! Booking Confirmed");
                        JOptionPane.showMessageDialog(PriceCalculator.this, "You will return to the homepage now");
                        // after booking finished,go back to the homepage

                    } else {
                        JOptionPane.showMessageDialog(PriceCalculator.this, "failed to confirm booking");
                    }
            }
        }); 

        PricePanel.add(label);
        PricePanel.add(yes);
        PricePanel.add(no);
        PricePanel.add(Vip);
        PricePanel.add(textArea);
        PricePanel.add(confirm);
        PricePanel.add(scrollPane);
        PricePanel.setBounds(0, 0, 700, 700);
        add(PricePanel);
        setVisible(true);
    }

    private void DateButton(ActionEvent evt) {
        datePicker.setVisible(true);
        dispose();
    }

    private int showConfirmationDialog() {
        return JOptionPane.showConfirmDialog(this, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
    }
    
    public static void main(String[] args) {
        PriceCalculator price = new PriceCalculator();
    }
}
