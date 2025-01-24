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
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBilerak = new JLabel("BILERAK");
		lblBilerak.setBackground(Color.WHITE);
		lblBilerak.setHorizontalAlignment(SwingConstants.CENTER);
		lblBilerak.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		lblBilerak.setBounds(541, 428, 255, 56);
		contentPane.add(lblBilerak);
		
		JLabel lblBesteOrdutegia = new JLabel("BESTE ORDUTEGIAK");
		lblBesteOrdutegia.setBackground(Color.WHITE);
		lblBesteOrdutegia.setHorizontalAlignment(SwingConstants.CENTER);
		lblBesteOrdutegia.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		lblBesteOrdutegia.setBounds(275, 428, 255, 56);
		contentPane.add(lblBesteOrdutegia);
		
		JLabel lblOrdutegi = new JLabel("ORDUTEGIAK");
		lblOrdutegi.setBackground(Color.WHITE);
		lblOrdutegi.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		lblOrdutegi.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdutegi.setBounds(10, 428, 255, 56);
		contentPane.add(lblOrdutegi);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.FrameMugimendu.loginJoan();
				dispose();
			}
		});
		btnLogout.setBackground(SystemColor.activeCaption);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(717, 79, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnOrdutegi = new JButton("");
		btnOrdutegi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					OrdutegiFrame frame = new OrdutegiFrame();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnOrdutegi.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\Erronka_2\\MG_DAM_E2_T7\\Erronka2_App\\img\\BesteHorario.jpg"));
		btnOrdutegi.setBackground(Color.WHITE);
		btnOrdutegi.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnOrdutegi.setVerticalAlignment(SwingConstants.TOP);
		btnOrdutegi.setBounds(10, 166, 255, 268);
		contentPane.add(btnOrdutegi);
		
		JButton btnBesteOrdutegi = new JButton("");
		btnBesteOrdutegi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.FrameMugimendu.besteOrdutegiJoan();
				dispose();
				
			}
		});
		btnBesteOrdutegi.setVerticalAlignment(SwingConstants.TOP);
		btnBesteOrdutegi.setBackground(Color.WHITE);
		btnBesteOrdutegi.setSelectedIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\WorkSpace\\Erronka2_App\\img\\fp1 (2).png"));
		btnBesteOrdutegi.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\Erronka_2\\MG_DAM_E2_T7\\Erronka2_App\\img\\horario.jpg"));
		btnBesteOrdutegi.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnBesteOrdutegi.setBounds(275, 166, 255, 268);
		contentPane.add(btnBesteOrdutegi);
		
		JButton btnBilera = new JButton("");
		btnBilera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.FrameMugimendu.bileraJoan();
				dispose();			
			}
		});
		btnBilera.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\Erronka_2\\MG_DAM_E2_T7\\Erronka2_App\\img\\bilera.jpg"));
		btnBilera.setVerticalAlignment(SwingConstants.TOP);
		btnBilera.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnBilera.setBackground(Color.WHITE);
		btnBilera.setBounds(541, 166, 255, 268);
		contentPane.add(btnBilera);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\Erronka_2\\MG_DAM_E2_T7\\Erronka2_App\\img\\logo.jpg"));
		lblIcon.setBounds(10, 31, 240, 75);
		contentPane.add(lblIcon);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 128, 128));
		separator.setBounds(0, 117, 816, 13);
		contentPane.add(separator);
	}
}
