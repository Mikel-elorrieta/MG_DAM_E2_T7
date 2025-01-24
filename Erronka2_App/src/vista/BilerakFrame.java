package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BilerakFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BilerakFrame frame = new BilerakFrame();
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
	public BilerakFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\Erronka_2\\MG_DAM_E2_T7\\Erronka2_App\\img\\logo.jpg"));
		lblIcon.setBounds(10, 0, 240, 75);
		contentPane.add(lblIcon);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 128, 128));
		separator.setBounds(0, 86, 816, 13);
		contentPane.add(separator);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.FrameMugimendu.loginJoan();
				dispose();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBackground(SystemColor.activeCaption);
		btnLogout.setBounds(717, 48, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnAtzera = new JButton("");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.FrameMugimendu.menuraJoan();
				dispose();
			}
		});
		btnAtzera.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\Erronka_2\\MG_DAM_E2_T7\\Erronka2_App\\img\\backward (1).jpg"));
		btnAtzera.setBackground(Color.WHITE);
		btnAtzera.setBounds(0, 457, 57, 54);
		contentPane.add(btnAtzera);
	}
}
