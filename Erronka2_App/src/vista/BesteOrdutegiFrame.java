package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Horarios;
import utils.HorariosController;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BesteOrdutegiFrame extends JFrame {

	 private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private JComboBox<String> comboBoxProfesores;
	    private HorariosController utils;
	    private JTable tableBeste;

	
	/**
	 * Create the frame.
	 */
	public BesteOrdutegiFrame() {
    	utils = new HorariosController();

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 551);
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
		btnAtzera.setBounds(10, 447, 57, 54);
		contentPane.add(btnAtzera);
		
		comboBoxProfesores = new JComboBox<String>();
		comboBoxProfesores.setBounds(234, 110, 200, 25);
		contentPane.add(comboBoxProfesores);
        cargarProfesores();

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(451, 110, 89, 25);
        btnFiltrar.addActionListener(e -> filtrarHorarios());
        contentPane.add(btnFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 146, 667, 283);
		contentPane.add(scrollPane);
		
		tableBeste = new JTable();
		scrollPane.setViewportView(tableBeste);
		
		
	}		
	
	// Funtzioak
		private void cargarProfesores() {
	        Map<String, Integer> profesores = utils.cargarProfesores();
	        for (String nombre : profesores.keySet()) {
	            comboBoxProfesores.addItem(nombre);
	        }
		}
	        private void filtrarHorarios() {
	            String profesorSeleccionado = (String) comboBoxProfesores.getSelectedItem();
	            if (profesorSeleccionado != null) {
	                int profeId = utils.cargarProfesores().get(profesorSeleccionado);
	                List<Horarios> horarios = utils.cargarHorariosPorProfesor(profeId);
	                System.err.println(profeId);
	                System.err.println(horarios);

	                if (horarios != null) {
	                    cargarDatosEnTabla(horarios);
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se encontraron horarios para el profesor seleccionado.");
	                }
	            }
	        }
	        private void cargarDatosEnTabla(List<Horarios> horarios) {
	            String[] dias = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
	            DefaultTableModel model = new DefaultTableModel(dias, 0) {
	                @Override
	                public boolean isCellEditable(int row, int column) {
	                    return false;
	                }
	            };

	            // Mapa para normalizar los nombres de los días
	            Map<String, String> mapaDias = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	            mapaDias.put("lunes", "Lunes");
	            mapaDias.put("martes", "Martes");
	            mapaDias.put("miércoles", "Miércoles");
	            mapaDias.put("jueves", "Jueves");
	            mapaDias.put("viernes", "Viernes");

	            // Organizar los horarios por hora y día
	            Map<String, Map<String, String>> horarioPorHora = new TreeMap<>();

	            for (Horarios horario : horarios) {
	                String hora = horario.getId().getHora();
	                String diaOriginal = horario.getId().getDia(); // Día original del objeto Horarios
	                String dia = mapaDias.getOrDefault(diaOriginal.toLowerCase(), null); // Normaliza el día
	                String modulo = horario.getModulos() != null ? horario.getModulos().getNombre() : "Sin módulo";

	                if (dia != null) {
	                    horarioPorHora.putIfAbsent(hora, new TreeMap<>());
	                    horarioPorHora.get(hora).put(dia, modulo);
	                }
	            }

	            // Crear las filas basadas en las horas
	            for (Map.Entry<String, Map<String, String>> entrada : horarioPorHora.entrySet()) {
	                String hora = entrada.getKey();
	                Map<String, String> modulosPorDia = entrada.getValue();

	                Object[] fila = new Object[dias.length];
	                fila[0] = hora;

	                for (int i = 1; i < dias.length; i++) {
	                    String dia = dias[i];
	                    fila[i] = modulosPorDia.getOrDefault(dia, "");
	                }

	                model.addRow(fila);
	            }

	            tableBeste.setModel(model);
	        }

}
