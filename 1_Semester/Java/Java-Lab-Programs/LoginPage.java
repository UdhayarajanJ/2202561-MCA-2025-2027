import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {

    JLabel lblUser, lblPass;
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin;

    public LoginPage() {

        setTitle("Login Page");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        lblUser = new JLabel("Username:");
        lblPass = new JLabel("Password:");

        txtUser = new JTextField();
        txtPass = new JPasswordField();

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);

        add(lblUser);
        add(txtUser);
        add(lblPass);
        add(txtPass);
        add(new JLabel());   // empty cell
        add(btnLogin);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        // Simple validation (You can change)
        if (user.equals("admin") && pass.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
