package vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Tarjeta;
import repositorio.RepositorioTarjeta;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VentanaVerTarjetas extends JDialog implements ActionListener {
	
	private Cliente cliente;

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JButton btnCerrar;
    private JButton btnBloquear;
    private JTable tableTarjetas;
    private DefaultTableModel tableModel;
    private JLabel lblTarjetasBancarias;
    
    public VentanaVerTarjetas(Cliente cliente) {
    	this.cliente = cliente;
    	
    	setModal(true);
        setTitle("Ver tarjetas bancarias");
        setBounds(100, 100, 720, 530);
        getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setForeground(new Color(90, 90, 90));
        btnCerrar.setBackground(new Color(255, 255, 255));
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCerrar.addActionListener(this);
        btnCerrar.setBounds(500, 406, 150, 35);
        contentPanel.add(btnCerrar);

        btnBloquear = new JButton("Bloquear tarjeta");
        btnBloquear.setForeground(new Color(90, 90, 90));
        btnBloquear.setBackground(new Color(230, 230, 230));
        btnBloquear.setFont(new Font("Arial", Font.BOLD, 13));
        btnBloquear.addActionListener(this);
        btnBloquear.setBounds(50, 406, 150, 35);
        contentPanel.add(btnBloquear);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 120, 600, 250);
        contentPanel.add(scrollPane);

        String[] columnas = { "Número de tarjeta", "Tipo de tarjeta", "Fecha de Vencimiento", "Estado" };
        tableModel = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTarjetas = new JTable(tableModel);
        tableTarjetas.setFillsViewportHeight(true);
        tableTarjetas.setForeground(new Color(90, 90, 90));
        tableTarjetas.setBackground(new Color(255, 255, 255));
        tableTarjetas.setFont(new Font("Arial", Font.PLAIN, 13));
        tableTarjetas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableTarjetas);
        {
        	lblTarjetasBancarias = new JLabel("Tarjetas bancarias");
        	lblTarjetasBancarias.setForeground(new Color(238, 52, 37));
        	lblTarjetasBancarias.setHorizontalAlignment(SwingConstants.CENTER);
        	lblTarjetasBancarias.setFont(new Font("Arial", Font.BOLD, 25));
        	lblTarjetasBancarias.setBounds(242, 50, 220, 30);
        	contentPanel.add(lblTarjetasBancarias);
        }

        llenarTabla();
    }

    private void llenarTabla() {
    	ArrayList<Tarjeta> tarjetas = RepositorioTarjeta.consultarTarjeta(cliente.getIdPersona());
    	cliente.setTarjetas(tarjetas);
        tableModel.setRowCount(0);
        for (Tarjeta tarjeta : tarjetas) {
        	if(tarjeta.getEstado().equals("activa") && tarjeta.getFechaVencimiento().isEqual(LocalDate.now())) {
        		tarjeta.setEstado("vencida");
        		RepositorioTarjeta.actualizarTarjeta(tarjeta);
        	}
        	Object[] fila = new Object[4];
        	fila[0] = tarjeta.getNumeroTarjetaFormateado();
        	fila[1] = tarjeta.getTipoTarjeta();
        	fila[2] = tarjeta.getFechaVencimientoFormateada();
        	fila[3] = tarjeta.getEstado();
            tableModel.addRow(fila);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCerrar) {
            dispose();
        } else if (e.getSource() == btnBloquear) {
            bloquearTarjetaSeleccionada();
        }
    }

    private void bloquearTarjetaSeleccionada() {
        int posicionFilaSeleccionada = tableTarjetas.getSelectedRow();
        if(posicionFilaSeleccionada == -1) {
        	JOptionPane.showMessageDialog(this, "Selecciona una tarjeta.", "Información", JOptionPane.INFORMATION_MESSAGE);
        	return;
        }
        String numeroTarjeta = "";
        String numeroTarjetaFormateado = (String) tableModel.getValueAt(posicionFilaSeleccionada, 0);
        for (String numeros : numeroTarjetaFormateado.split(" ")) numeroTarjeta+=numeros;
        Tarjeta tarjeta = cliente.buscarTarjeta(numeroTarjeta);
        if(tarjeta == null) {
        	JOptionPane.showMessageDialog(this, "La tarjeta seleccionada no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
        	return;
        }
        else if (tarjeta.getEstado().equals("bloqueada")) {
            JOptionPane.showMessageDialog(this, "La tarjeta ya está bloqueada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea bloquear la tarjeta " + tarjeta.getNumeroTarjeta() + "?",
                "Confirmar bloqueo", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
        	VentanaAutenticar ventanaAutenticar = new VentanaAutenticar(cliente);
    		ventanaAutenticar.setVisible(true);
    		if(ventanaAutenticar.getEstadoAutenticacion()) {
    			tarjeta.setEstado("bloqueada");
    			RepositorioTarjeta.actualizarTarjeta(tarjeta);
    			llenarTabla();
    			JOptionPane.showMessageDialog(this, "La tarjeta ha sido bloqueada exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
    		} else {
    			JOptionPane.showMessageDialog(this, "La autenticación falló.", "Información", JOptionPane.INFORMATION_MESSAGE);
    		}
        }
    } 
}
