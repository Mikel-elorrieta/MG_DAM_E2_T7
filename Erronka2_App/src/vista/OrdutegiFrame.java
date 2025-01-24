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

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\Erronka_2\\MG_DAM_E2_T7\\Erronka2_App\\img\\logo.jpg"));
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
		btnAtzera.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\Erronka_2\\MG_DAM_E2_T7\\Erronka2_App\\img\\backward (1).jpg"));
		btnAtzera.setBounds(10, 442, 57, 54);
		contentPane.add(btnAtzera);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(66, 161, 667, 283);
		contentPane.add(scrollPane);

		tableOrdu = new JTable();
		scrollPane.setViewportView(tableOrdu);

		// Cargar horarios en la tabla
		cargarHorariosEnTabla();
	}

	private void cargarHorariosEnTabla() {
	    // Columnas: Horas, Lunes, Martes, Miércoles, Jueves, Viernes
	    String[] columnNames = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

	    // Obtener horarios del controlador
	    List<Horarios> horarios = HorariosController.cargarHorariosPorProfesor(4);

	    // Organizar los horarios por hora y día
	    Map<String, Map<String, String>> horariosMap = new TreeMap<>();
	    for (Horarios horario : horarios) {
	        String hora = horario.getId().getHora();
	        String dia = horario.getId().getDia();
	        String asignatura = horario.getModulos().getNombre();

	        // Asegurarse de que no haya datos inválidos
	        if (hora != null && dia != null) {
	            horariosMap.putIfAbsent(hora, new TreeMap<>());
	            horariosMap.get(hora).put(dia, asignatura);
	        }
	    }

	    // Crear filas para la tabla
	    for (Map.Entry<String, Map<String, String>> entry : horariosMap.entrySet()) {
	        String hora = entry.getKey();
	        Map<String, String> diaAsignaturas = entry.getValue();

	        // Asegurar que se respeten celdas vacías por día
	        Object[] row = new Object[6];
	        row[0] = hora;
	        row[1] = diaAsignaturas.getOrDefault("L/A", "");
	        row[2] = diaAsignaturas.getOrDefault("M/A", "");
	        row[3] = diaAsignaturas.getOrDefault("X", "");
	        row[4] = diaAsignaturas.getOrDefault("J", "");
	        row[5] = diaAsignaturas.getOrDefault("V", "");
	        model.addRow(row);
	    }

	    tableOrdu.setModel(model);
	}


}
