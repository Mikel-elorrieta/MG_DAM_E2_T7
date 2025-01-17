package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Horarios;
import modelo.HorariosId;
import modelo.Modulos;

public class OrdutegiFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Create the frame.
     */
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
        lblIcon.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\WorkSpace\\Erronka2_App\\img\\logo.jpg"));
        lblIcon.setBounds(10, 11, 240, 75);
        contentPane.add(lblIcon);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 128, 128));
        separator.setBounds(0, 97, 810, 13);
        contentPane.add(separator);

        JButton btnLogout = new JButton("Log Out");
        btnLogout.addActionListener(e -> {
            utils.FrameMugimendua.loginJoan();
            dispose();
        });
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogout.setBackground(SystemColor.activeCaption);
        btnLogout.setBounds(708, 63, 89, 23);
        contentPane.add(btnLogout);

        JButton btnAtzera = new JButton("");
        btnAtzera.setBackground(Color.WHITE);
        btnAtzera.setIcon(new ImageIcon("C:\\Users\\in2dm3-d\\Desktop\\WorkSpace\\Erronka2_App\\img\\backward (1).jpg"));
        btnAtzera.setBounds(10, 442, 57, 54);
        contentPane.add(btnAtzera);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(66, 121, 667, 313);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "", "ASTELEHENA", "ASTEARTEA", "ASTEAZKENA", "OSTEGUNA", "OSTIRALA"
                }
        ));
        scrollPane.setViewportView(table);

        cargarHorarios();
    }

    private void cargarHorarios() {
        Object response = DAO.konexioa.ask("getHorariosByUserId/4");

        if (response instanceof List<?>) {
            List<?> lista = (List<?>) response;

            if (!lista.isEmpty() && lista.get(0) instanceof Horarios) {
                List<Horarios> horarios = (List<Horarios>) lista;

                cargarDatosEnTabla(horarios);
            } else {
                System.out.println("La lista no contiene objetos de tipo Horarios.");
            }
        } else {
            System.out.println("La respuesta no es una lista.");
        }
    }

    private void cargarDatosEnTabla(List<Horarios> horarios) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        for (Horarios horario : horarios) {
            HorariosId id = horario.getId();
            Modulos modulo = horario.getModulos();

            Object[] fila = new Object[]{
                    id.getDia(),
                    id.getHora(),
                    modulo != null ? modulo.getNombre() : "Sin módulo",
                    id.getProfeId()
            };

            model.addRow(fila);
        }
    }

    private int obtenerIndiceDia(String dia) {
        switch (dia.toUpperCase()) {
            case "ASTELEHENA":
                return 1;
            case "ASTEARTEA":
                return 2;
            case "ASTEAZKENA":
                return 3;
            case "OSTEGUNA":
                return 4;
            case "OSTIRALA":
                return 5;
            default:
                return -1;
        }
    }
}
