import javax.swing.*;
import java.awt.event.*;

public class MenuExample extends JFrame implements ActionListener {

    JMenu fileMenu, helpMenu;
    JMenuItem newItem, openItem, exitItem, aboutItem;

    public MenuExample() {

        // Frame setup
        setTitle("Menu Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // MenuBar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        exitItem = new JMenuItem("Exit");

        // Adding menu items to File
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(exitItem);

        // Help Menu
        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");

        // Add item to Help menu
        helpMenu.add(aboutItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Add menu bar to frame
        setJMenuBar(menuBar);

        // Action listeners
        newItem.addActionListener(this);
        openItem.addActionListener(this);
        exitItem.addActionListener(this);
        aboutItem.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newItem) {
            JOptionPane.showMessageDialog(this, "New File Created!");
        } 
        else if (e.getSource() == openItem) {
            JOptionPane.showMessageDialog(this, "Open File Dialog!");
        } 
        else if (e.getSource() == exitItem) {
            System.exit(0);
        } 
        else if (e.getSource() == aboutItem) {
            JOptionPane.showMessageDialog(this, "This is a Simple Menu Application");
        }
    }

    public static void main(String[] args) {
        new MenuExample();
    }
}
