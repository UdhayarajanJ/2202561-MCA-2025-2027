import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeBackground extends JFrame implements ActionListener {

    JButton btnRed, btnGreen, btnBlue;
    JPanel panel;

    public ChangeBackground() {

        // Frame settings
        setTitle("Color Change Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Buttons
        btnRed = new JButton("Red");
        btnGreen = new JButton("Green");
        btnBlue = new JButton("Blue");

        // Add ActionListener
        btnRed.addActionListener(this);
        btnGreen.addActionListener(this);
        btnBlue.addActionListener(this);

        // Add buttons to panel
        panel.add(btnRed);
        panel.add(btnGreen);
        panel.add(btnBlue);

        // Add panel to frame
        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnRed) {
            panel.setBackground(Color.RED);
        } else if (e.getSource() == btnGreen) {
            panel.setBackground(Color.GREEN);
        } else if (e.getSource() == btnBlue) {
            panel.setBackground(Color.BLUE);
        }
    }

    public static void main(String[] args) {
        new ChangeBackground();
    }
}
