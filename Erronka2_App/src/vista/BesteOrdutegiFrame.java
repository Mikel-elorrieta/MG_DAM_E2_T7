
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

public class BesteOrdutegiFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxProfesores;
    private HorariosController utils;
    private JTable tableBeste;

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
        lblIcon.setIcon(new ImageIcon("img/logo.jpg"));
        lblIcon.setBounds(10, 0, 240, 75);
        contentPane.add(lblIcon);

        JButton btnLogout = new JButton("Log Out");
        btnLogout.addActionListener(event -> {
            controller.FrameMugimendu.loginJoan();
            dispose();
        });
        
                JSeparator separator = new JSeparator();
                separator.setForeground(new Color(0, 128, 128));
                separator.setBounds(0, 86, 816, 13);
                contentPane.add(separator);
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogout.setBackground(SystemColor.activeCaption);
        btnLogout.setBounds(717, 48, 89, 23);
        contentPane.add(btnLogout);

        JButton btnAtzera = new JButton("");
        btnAtzera.addActionListener(event -> {
            controller.FrameMugimendu.menuraJoan();
            dispose();
        });
        btnAtzera.setIcon(new ImageIcon("img/backward.jpg"));
        btnAtzera.setBackground(Color.WHITE);
        btnAtzera.setBounds(10, 447, 57, 54);
        contentPane.add(btnAtzera);

        comboBoxProfesores = new JComboBox<>();
        comboBoxProfesores.setBounds(234, 110, 200, 25);
        contentPane.add(comboBoxProfesores);
        cargarProfesores();

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(451, 110, 89, 25);
        btnFiltrar.addActionListener(e -> filtrarHorarios());
        contentPane.add(btnFiltrar);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(Color.BLACK);
        separator_1.setBounds(69, 185, 684, 2);
        contentPane.add(separator_1);

        tableBeste = new JTable();
        tableBeste.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        	}
        ));
        tableBeste.setBounds(69, 188, 684, 226);
        contentPane.add(tableBeste);
        
        JLabel lblNewLabel = new JLabel("ORDUA");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblNewLabel.setBounds(90, 164, 65, 23);
        contentPane.add(lblNewLabel);
        
        JLabel lblLa = new JLabel("L/A");
        lblLa.setHorizontalAlignment(SwingConstants.CENTER);
        lblLa.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblLa.setBounds(208, 164, 65, 23);
        contentPane.add(lblLa);
        
        JLabel lblMa = new JLabel("M/A");
        lblMa.setHorizontalAlignment(SwingConstants.CENTER);
        lblMa.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblMa.setBounds(314, 164, 65, 23);
        contentPane.add(lblMa);
        
        JLabel lblX = new JLabel("X");
        lblX.setHorizontalAlignment(SwingConstants.CENTER);
        lblX.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblX.setBounds(432, 164, 65, 23);
        contentPane.add(lblX);
        
        JLabel lblJo = new JLabel("J/O");
        lblJo.setHorizontalAlignment(SwingConstants.CENTER);
        lblJo.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblJo.setBounds(550, 164, 65, 23);
        contentPane.add(lblJo);
        
        JLabel lblVo = new JLabel("V/O");
        lblVo.setHorizontalAlignment(SwingConstants.CENTER);
        lblVo.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
        lblVo.setBounds(663, 164, 65, 23);
        contentPane.add(lblVo);
    }

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
            List<Horarios> horarios = HorariosController.cargarHorariosPorProfesor(profeId);
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
        String[] columnNames = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        Map<String, Map<String, String>> horariosMap = new TreeMap<>();
        for (Horarios horario : horarios) {
            String hora = horario.getId().getHora();
            String dia = horario.getId().getDia();
            String asignatura = horario.getModulos().getNombre();

            if (hora != null && dia != null) {
                horariosMap.putIfAbsent(hora, new TreeMap<>());
                horariosMap.get(hora).put(dia, asignatura);
            }
        }

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

        tableBeste.setModel(model);
    }
}
