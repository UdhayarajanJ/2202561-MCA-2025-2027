import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {

    JTextField t1, t2, result;
    JButton add, sub, mul, div;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Number 1 label
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Number 1:"), gbc);

        // Number 1 text
        t1 = new JTextField(15);
        gbc.gridx = 1;
        add(t1, gbc);

        // Number 2 label
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Number 2:"), gbc);

        // Number 2 text
        t2 = new JTextField(15);
        gbc.gridx = 1;
        add(t2, gbc);

        // Result label
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Result:"), gbc);

        // Result field
        result = new JTextField(15);
        result.setEditable(false);
        gbc.gridx = 1;
        add(result, gbc);

        // Panel for buttons (one row)
        JPanel btnPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        add = new JButton("Add");
        sub = new JButton("Subtract");
        mul = new JButton("Multiply");
        div = new JButton("Divide");

        btnPanel.add(add);
        btnPanel.add(sub);
        btnPanel.add(mul);
        btnPanel.add(div);

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);

        // Add button panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnPanel, gbc);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double n1 = Double.parseDouble(t1.getText());
            double n2 = Double.parseDouble(t2.getText());
            double res = 0;

            if (e.getSource() == add)
                res = n1 + n2;
            else if (e.getSource() == sub)
                res = n1 - n2;
            else if (e.getSource() == mul)
                res = n1 * n2;
            else if (e.getSource() == div)
                res = n1 / n2;

            result.setText(String.valueOf(res));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
