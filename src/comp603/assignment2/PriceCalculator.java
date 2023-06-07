
package comp603.assignment2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
    DatePicker datePicker;
    public PriceCalculator(DatePicker datePicker) {

        this.datePicker = datePicker;
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JPanel PricePanel = new JPanel();
        PricePanel.setLayout(null);

        label = new JLabel("Calculator your Cost");
        label.setBounds(220, 30, 500, 30);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        Vip = new JLabel("Are you VIP customer");
        Vip.setBounds(80, 150, 500, 30);
        Vip.setFont(new Font("Arial", 0, 20));

        // two buttons to check if customer is VIP
        yes = new JRadioButton("YES");
        no = new JRadioButton("NO");
        yes.setFont(new Font("Arial", 0, 15));
        no.setFont(new Font("Arial", 0, 15));
        selection = new ButtonGroup();
        selection.add(yes);
        selection.add(no);
        yes.setBounds(350, 150, 60, 35);
        no.setBounds(440, 150, 60, 35);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vip = true;
            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vip = false;
            }
        });
        

        PricePanel.add(label);
        PricePanel.add(yes);
        PricePanel.add(no);
        PricePanel.add(Vip);
        PricePanel.setBounds(0, 0, 700, 700);
        add(PricePanel);
        setVisible(true);
    }

    private void DateButton(ActionEvent evt) {
        datePicker.setVisible(true);
        dispose();
    }

//    public static void main(String[] args) {
//        PriceCalculator price = new PriceCalculator();
//    }
}
