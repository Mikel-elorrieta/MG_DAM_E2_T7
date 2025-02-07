package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Horarios;
import utils.HorariosController;

public class OrdutegiFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableOrdu;

	public OrdutegiFrame() {
		 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 546);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ORDUA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		lblNewLabel.setBounds(82, 225, 65, 23);
		contentPane.add(lblNewLabel);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBounds(64, 246, 684, 2);
		contentPane.add(separator_1_1);
		
		JLabel lblVo = new JLabel("V/O");
		lblVo.setHorizontalAlignment(SwingConstants.CENTER);
		lblVo.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		lblVo.setBounds(663, 225, 65, 23);
		contentPane.add(lblVo);
		
		JLabel lblLa = new JLabel("L/A");
		lblLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblLa.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		lblLa.setBounds(200, 225, 65, 23);
		contentPane.add(lblLa);
		
		JLabel lblMa = new JLabel("M/A");
		lblMa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMa.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		lblMa.setBounds(309, 225, 65, 23);
		contentPane.add(lblMa);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		lblX.setBounds(419, 225, 65, 23);
		contentPane.add(lblX);
		
		JLabel lblJo = new JLabel("J/O");
		lblJo.setHorizontalAlignment(SwingConstants.CENTER);
		lblJo.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		lblJo.setBounds(539, 225, 65, 23);
		contentPane.add(lblJo);

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("img/logo.jpg"));
		lblIcon.setBounds(10, 11, 240, 75);
		contentPane.add(lblIcon);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 128, 128));
		separator.setBounds(0, 97, 810, 13);
		contentPane.add(separator);

		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(e -> {
			controller.FrameMugimendu.loginJoan();
			dispose();
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBackground(SystemColor.activeCaption);
		btnLogout.setBounds(708, 63, 89, 23);
		contentPane.add(btnLogout);

		JButton btnAtzera = new JButton("");
		btnAtzera.addActionListener(e -> {
			controller.FrameMugimendu.menuraJoan();
			dispose();
		});
		btnAtzera.setBackground(Color.WHITE);
		btnAtzera.setIcon(new ImageIcon("img/backward.jpg"));
		btnAtzera.setBounds(10, 442, 57, 54);
		contentPane.add(btnAtzera);
		
		JLabel lblTitle = new JLabel("Zure Ordutegia");
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(169, 121, 461, 54);
		contentPane.add(lblTitle);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(190, 170, 386, 2);
		contentPane.add(separator_1);
		
				tableOrdu = new JTable();
				tableOrdu.setBounds(64, 246, 683, 176);
				contentPane.add(tableOrdu);

		cargarHorariosEnTabla();
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

	    tableOrdu.setModel(model);
	}
}
