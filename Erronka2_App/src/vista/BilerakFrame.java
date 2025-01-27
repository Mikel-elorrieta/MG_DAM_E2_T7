package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Horarios;
import utils.HorariosController;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class BilerakFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableBilera;

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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 192, 690, 242);
		contentPane.add(scrollPane);
		
		tableBilera = new JTable();
		scrollPane.setViewportView(tableBilera);
		
		JLabel lblBilerak = new JLabel("Bilerak");
		lblBilerak.setHorizontalAlignment(SwingConstants.CENTER);
		lblBilerak.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		lblBilerak.setBounds(193, 110, 461, 54);
		contentPane.add(lblBilerak);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(214, 159, 386, 2);
		contentPane.add(separator_1);
	}
	private void cargarHorariosEnTabla() {
	    String[] columnNames = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

	    List<Horarios> horarios = HorariosController.cargarHorariosPorProfesor(MainFrame.usuario.getId());

	    // Ordutegiak orduaren eta egunaren arabera antolatu
	    Map<String, Map<String, String>> horariosMap = new TreeMap<>();
	    for (Horarios horario : horarios) {
	        String hora = horario.getId().getHora();
	        String dia = horario.getId().getDia();
	        String asignatura = horario.getModulos().getNombre();

	        if (hora != null && dia != null) {
	            horariosMap.putIfAbsent(hora, new TreeMap<>());

	            // Irakasgaia dagokion egunari lotu
	            horariosMap.get(hora).put(dia, asignatura);
	        }
	    }

	    // Sortu taula errenkadak
	    for (Map.Entry<String, Map<String, String>> entry : horariosMap.entrySet()) {
	        String hora = entry.getKey();
	        Map<String, String> diaAsignaturas = entry.getValue();

	        Object[] row = new Object[6];
	        row[0] = hora;
	        row[1] = diaAsignaturas.getOrDefault("L/A", "");
	        row[2] = diaAsignaturas.getOrDefault("M/A", "");
	        row[3] = diaAsignaturas.getOrDefault("X", "");
	        row[4] = diaAsignaturas.getOrDefault("J/O", "");
	        row[5] = diaAsignaturas.getOrDefault("V/O", "");

	        model.addRow(row);
	    }

	    tableBilera.setModel(model);
	}
}
