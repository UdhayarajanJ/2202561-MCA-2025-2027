import javax.swing.*;
import java.awt.*;

public class LoginPage {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Spacing
        gbc.anchor = GridBagConstraints.WEST; // Left-align labels

        // Title
        JLabel title = new JLabel("Login Form");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(title, gbc);

        // Reset anchor for left alignment
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JLabel("Username : "), gbc);

        // Username Field
        JTextField txtUser = new JTextField(18);
        gbc.gridx = 1;
        frame.add(txtUser, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(new JLabel("Password : "), gbc);

        // Password Field
        JPasswordField txtPass = new JPasswordField(18);
        gbc.gridx = 1;
        frame.add(txtPass, gbc);

        // Login Button (same width as text fields)
        JButton btnLogin = new JButton("Login");

        // Force button width equal to textfield
        btnLogin.setPreferredSize(txtUser.getPreferredSize());

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(btnLogin, gbc);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            if (user.equals("admin") && pass.equals("123")) {
                JOptionPane.showMessageDialog(frame, "Login Successful !", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Login Failed !", "Message", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.setVisible(true);
    }
}
