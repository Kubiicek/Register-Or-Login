package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.JRegisterCryptography;

public class JRegister extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private boolean mostrarSenha = false;

    // Launch the application.
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JRegister frame = new JRegister();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Create the frame.
    public JRegister() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 519, 305);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(49, 62, 64));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(126, 11, 248, 225);
        panel.setBackground(new Color(204, 207, 206));
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon eyeIcon = new ImageIcon(getClass().getResource("/images/eye.png"));

        JButton btnOlhoPassword = new JButton("");
        btnOlhoPassword.setBackground(new Color(204, 207, 206));
        btnOlhoPassword.setEnabled(true);
        btnOlhoPassword.setBounds(193, 130, 46, 16);
        btnOlhoPassword.setIcon(eyeIcon);
        btnOlhoPassword.setBorder(null);
        panel.add(btnOlhoPassword);

        btnOlhoPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mostrarSenha) {
                    passwordField.setEchoChar('\u2022');
                } else {
                    passwordField.setEchoChar((char) 0);
                }
                mostrarSenha = !mostrarSenha;
            }
        });

        JLabel lblNewLabel = new JLabel("User");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel.setBounds(23, 52, 46, 14);
        panel.add(lblNewLabel);

        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(23, 68, 160, 20);
        panel.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1.setBounds(23, 116, 70, 14);
        panel.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Continue");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textFieldUsuario.getText();
                char[] password = passwordField.getPassword();
                
                if (JRegisterCryptography.registerUser(username, password) == 1) {
                    JOptionPane.showMessageDialog(btnNewButton, "Successfully registered.");
                    
                } else if (JRegisterCryptography.registerUser(username, password) == 3) {
                    JOptionPane.showMessageDialog(btnNewButton, "User already exists with this username.", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    
                } else {
                	JOptionPane.showMessageDialog(btnNewButton, "Check the information.", "Warning",
                			JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnNewButton.setBackground(new Color(49, 62, 64));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBounds(149, 191, 89, 23);
        panel.add(btnNewButton);

        JLabel lblNewLabel_2 = new JLabel("Register");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_2.setBounds(84, 11, 121, 23);
        panel.add(lblNewLabel_2);

        passwordField = new JPasswordField();
        passwordField.setBounds(23, 130, 160, 20);
        panel.add(passwordField);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                JRegisterOrLogin jRL = new JRegisterOrLogin();
                jRL.setLocationRelativeTo(jRL);
                jRL.setVisible(true);
            }
        });
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(49, 62, 64));
        btnBack.setBounds(23, 191, 89, 23);
        panel.add(btnBack);

        btnOlhoPassword.revalidate();
        btnOlhoPassword.repaint();
    }
}
