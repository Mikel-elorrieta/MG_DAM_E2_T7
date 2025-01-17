package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.Users;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import utils.Login;
import utils.UserParser;
import java.awt.SystemColor;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUser;
    private JPasswordField textPass;
    private Login login;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Object serverResponse = DAO.konexioa.ask("getAllUsers/");
                    System.out.println(serverResponse);

                    List<Users> users = UserParser.parseUsers(serverResponse);

                    Login login = new Login(users);

                    MainFrame frame = new MainFrame(login);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainFrame(Login login) {
        this.login = login;

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 823, 546);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblIcon = new JLabel("");
        lblIcon.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\WorkSpace\\Erronka2_App\\img\\logo.jpg"));
        lblIcon.setBounds(10, 11, 240, 75);
        contentPane.add(lblIcon);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 128, 128));
        separator.setBounds(0, 97, 810, 13);
        contentPane.add(separator);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.text);
        panel.setBounds(10, 122, 786, 382);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblUser = new JLabel("Erabiltzailea");
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblUser.setBounds(32, 58, 310, 21);
        panel.add(lblUser);

        JLabel lblPass = new JLabel("Pasahitza");
        lblPass.setHorizontalAlignment(SwingConstants.CENTER);
        lblPass.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblPass.setBounds(32, 166, 310, 21);
        panel.add(lblPass);

        textUser = new JTextField();
        textUser.setHorizontalAlignment(SwingConstants.CENTER);
        textUser.setFont(new Font("Verdana", Font.PLAIN, 15));
        textUser.setBounds(32, 90, 310, 40);
        panel.add(textUser);
        textUser.setColumns(10);

        textPass = new JPasswordField();
        textPass.setHorizontalAlignment(SwingConstants.CENTER);
        textPass.setFont(new Font("Verdana", Font.PLAIN, 15));
        textPass.setBounds(32, 193, 310, 40);
        panel.add(textPass);

        JButton btnLogin = new JButton("Log In");
        btnLogin.setBackground(SystemColor.activeCaption);
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogin.setBounds(32, 284, 310, 23);
        panel.add(btnLogin);
        
                JLabel lblIcon1 = new JLabel("");
                lblIcon1.setBounds(387, 11, 389, 363);
                panel.add(lblIcon1);
                lblIcon1.setForeground(new Color(0, 128, 128));
                lblIcon1.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\WorkSpace\\Erronka2_App\\img\\fp1.png"));
                lblIcon1.setHorizontalAlignment(SwingConstants.CENTER);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textUser.getText();
                String password = new String(textPass.getPassword());

                Object usuario = null;

                if (login.balidatu(username, password)) {
                    usuario = DAO.konexioa.ask("getUserByName/" + username);
                    System.out.println("Usuario guardado: " + usuario);

                    JOptionPane.showMessageDialog(contentPane, "Kaixo " + username + ", barruan zaude", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    
                    utils.FrameMugimendua.menuraJoan();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
