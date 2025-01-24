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

import utils.HorariosController;
import modelo.Horarios;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrdutegiFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JComboBox<String> comboBoxProfesores;
    private HorariosController utils;

    public OrdutegiFrame() {
    	utils = new HorariosController();
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
        btnAtzera.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.FrameMugimendu.menuraJoan();
        		dispose();
        	}
        });
        btnAtzera.setBackground(Color.WHITE);
        btnAtzera.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\Erronka_2\\MG_DAM_E2_T7\\Erronka2_App\\img\\backward (1).jpg"));
        btnAtzera.setBounds(10, 442, 57, 54);
        contentPane.add(btnAtzera);

        comboBoxProfesores = new JComboBox<>();
        comboBoxProfesores.setBounds(66, 121, 200, 25);
        contentPane.add(comboBoxProfesores);
        cargarProfesores();

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(280, 121, 89, 25);
        btnFiltrar.addActionListener(e -> filtrarHorarios());
        contentPane.add(btnFiltrar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(66, 161, 667, 283);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel());
        scrollPane.setViewportView(table);
    }

    private void cargarProfesores() {
        Map<String, Integer> profesores = utils.cargarProfesores();
        for (String nombre : profesores.keySet()) { // KEYSET = NOMBRE DEL PROFESOR
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

        table.setModel(model);
    }


}
