package Frames;

import Database.MillionaireConnection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final MillionaireConnection connection = new MillionaireConnection();

    private final JPanel panel_main = new JPanel(new BorderLayout(0, 10));

    private final JPanel panel_input = new JPanel(new GridLayout(2, 2));
    private final JLabel label_username = new JLabel("Username: ");
    private final JLabel label_password = new JLabel("Password: ");
    private final JTextField text_username = new JTextField(32);
    private final JPasswordField text_password = new JPasswordField(32);

    private final JTextArea area_msg = new JTextArea();

    private final JPanel panel_button = new JPanel(new GridLayout(1, 2, 10, 0));
    private final JButton button_confirm = new JButton("Login");
    private final JButton button_register = new JButton("Register");

    public LoginFrame() {
        super("Login");

        Dimension screenSize = toolkit.getScreenSize();
        int widthUnit = screenSize.width / 30;
        int heightUnit = screenSize.height / 30;
        setBounds(widthUnit * 12, heightUnit * 12, widthUnit * 6, heightUnit * 6);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponents();

        setListeners();
    }

    private void addComponents() {

        area_msg.setLineWrap(true);
        area_msg.setEditable(false);
        panel_main.setBorder(new EmptyBorder(20, 20, 20, 20));

        add(panel_main, BorderLayout.CENTER);
        panel_main.add(panel_input, BorderLayout.NORTH);
        panel_main.add(area_msg, BorderLayout.CENTER);
        panel_main.add(panel_button, BorderLayout.SOUTH);
        panel_input.add(label_username);
        panel_input.add(text_username);
        panel_input.add(label_password);
        panel_input.add(text_password);
        panel_button.add(button_confirm);
        panel_button.add(button_register);
    }

    private void setListeners() {
        button_confirm.addActionListener(event -> {
            String username = text_username.getText();
            String password = text_password.getText();

            if (connection.login(username, password)) {
                area_msg.setText("Login successfully! Welcome, " + username);
                closeAndStartGame();

            } else {
                area_msg.setText(
                        "Please check your username and password"
                        + " then try again.\n"
                        + "If you do not have an account, "
                        + "please register one\n");

            }
        });

        button_register.addActionListener(event -> {
            String username = text_username.getText();
            String password = text_password.getText();

            if (connection.register(username, password)) {
                area_msg.setText("Register successfully! Welcome, " + username);
                closeAndStartGame();

            } else {
                area_msg.setText(
                        "Username exsits!\n"
                        + "Please change your username then try again.\n");
            }
        });
    }

    private void closeAndStartGame() {
        EventQueue.invokeLater(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                setVisible(false);
                MainFrame.start();

            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
    
    // 500 2000 5000 10000 25000 75000 125000 250000 500000 10000000
}
