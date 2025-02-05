package vista;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Reuniones;
import utils.ReunionesController;

public class BilerakFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableBilera;
    private JLabel lblSelectedRow;
    private JButton btnBaieztatu;
    private JButton btnEzeztatu;

    public BilerakFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 830, 550);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblIcon = new JLabel(new ImageIcon("img/logo.jpg"));
        lblIcon.setBounds(10, 0, 240, 75);
        contentPane.add(lblIcon);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 128, 128));
        separator.setBounds(0, 86, 816, 13);
        contentPane.add(separator);

        JButton btnLogout = new JButton("Log Out");
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogout.setBackground(SystemColor.activeCaption);
        btnLogout.setBounds(717, 48, 89, 23);
        btnLogout.addActionListener(e -> {
            controller.FrameMugimendu.loginJoan();
            dispose();
        });
        contentPane.add(btnLogout);

        JButton btnAtzera = new JButton(new ImageIcon("img/backward.jpg"));
        btnAtzera.setBackground(Color.WHITE);
        btnAtzera.setBounds(0, 457, 57, 54);
        btnAtzera.addActionListener(e -> {
            controller.FrameMugimendu.menuraJoan();
            dispose();
        });
        contentPane.add(btnAtzera);

        JLabel lblBilerak = new JLabel("Bilerak", SwingConstants.CENTER);
        lblBilerak.setFont(new Font("Times New Roman", Font.PLAIN, 37));
        lblBilerak.setBounds(222, 110, 404, 54);
        contentPane.add(lblBilerak);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(231, 159, 383, 5);
        contentPane.add(separator_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(75, 175, 688, 222);
        contentPane.add(scrollPane);

        tableBilera = new JTable();
        tableBilera.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableBilera);

        btnBaieztatu = new JButton("Onartu");
        btnBaieztatu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
				int selectedRow = tableBilera.getSelectedRow();
				if (selectedRow != -1) {
					String id = tableBilera.getValueAt(selectedRow, 0).toString();
					ReunionesController.actualizarEstado(id, "aceptada");
					cargarReunionesEnTabla();
				}	
        	}
        });
        btnBaieztatu.setEnabled(false);
        btnBaieztatu.setBounds(75, 141, 89, 23);
        contentPane.add(btnBaieztatu);

        btnEzeztatu = new JButton("Ezeztatu");
        btnEzeztatu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int selectedRow = tableBilera.getSelectedRow();
				if (selectedRow != -1) {
					String id = tableBilera.getValueAt(selectedRow, 0).toString();
					ReunionesController.actualizarEstado(id, "denegada");
					cargarReunionesEnTabla();
				}
        	}
        });
        btnEzeztatu.setEnabled(false);
        btnEzeztatu.setBounds(674, 141, 89, 23);
        contentPane.add(btnEzeztatu);

        lblSelectedRow = new JLabel("");
        lblSelectedRow.setBounds(75, 410, 688, 23);
        contentPane.add(lblSelectedRow);

        cargarReunionesEnTabla();

        tableBilera.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tableBilera.getSelectedRow() != -1) {
                lblSelectedRow.setText("Selected Row: " + (tableBilera.getSelectedRow() + 1));
                btnBaieztatu.setEnabled(true);
                btnEzeztatu.setEnabled(true);
            }
        });
    }

    private void cargarReunionesEnTabla() {
        String[] columnNames = {"ID", "Irakaslea", "Ikaslea", "Estado", "Data", "Gela"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Reuniones> reuniones = ReunionesController.cargarReunionesPorProfesor(MainFrame.usuario.getId());

        Map<String, Integer> dateOccurrences = calculateDateOccurrences(reuniones);

        // Actualizar el estado a "conflicto" si una fecha está repetida
        for (Reuniones reunion : reuniones) {
            String fecha = reunion.getFecha().toString();
            if (dateOccurrences.getOrDefault(fecha, 0) > 1) {
                reunion.setEstado("conflicto");
            }

            Object[] row = {
                reunion.getIdReunion(),
                reunion.getUsersByProfesorId().getNombre(),
                reunion.getUsersByAlumnoId().getNombre(),
                reunion.getEstado(),
                fecha,
                reunion.getAula()
            };
            model.addRow(row);
        }

        tableBilera.setModel(model);

        // Render personalizado para cambiar colores según el estado
        tableBilera.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String estado = table.getValueAt(row, 3).toString();

                // Cambiar el color de fondo según el estado
                switch (estado.toLowerCase()) {
                    case "pendiente":
                        c.setBackground(Color.ORANGE);
                        break;
                    case "aceptada":
                        c.setBackground(Color.GREEN);
                        break;
                    case "denegada":
                        c.setBackground(Color.RED);
                        break;
                    case "conflicto":
                        c.setBackground(Color.GRAY);
                        break;
                    default:
                        c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                        break;
                }
                c.setForeground(Color.BLACK);

                return c;
            }
        });
    }

    // Método para calcular las ocurrencias de cada fecha
    private Map<String, Integer> calculateDateOccurrences(List<Reuniones> reuniones) {
        Map<String, Integer> occurrences = new HashMap<>();
        for (Reuniones r : reuniones) {
            String fecha = r.getFecha().toString();
            occurrences.put(fecha, occurrences.getOrDefault(fecha, 0) + 1);
        }
        return occurrences;
    }
}
