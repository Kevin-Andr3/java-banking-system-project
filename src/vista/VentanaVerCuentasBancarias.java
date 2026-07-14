package vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Cuenta;
import repositorio.RepositorioCuenta;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Color;

public class VentanaVerCuentasBancarias extends JDialog implements ActionListener {

	private Cliente cliente;
	
	private static final long serialVersionUID = 1L;
	private JButton btnCerrar;
	private JScrollPane scrollPane;
	private JTable tableCuentasBancarias;
	private DefaultTableModel defaultTableModel;
	private JButton btnVerDetalles;
	private JButton btnCancelar;

	public VentanaVerCuentasBancarias(Cliente cliente) {
		getContentPane().setBackground(new Color(255, 255, 255));
		this.cliente = cliente;
		
		setTitle("Ver cuentas bancarias");
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 820, 530);
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Cuentas bancarias");
			lblNewLabel.setForeground(new Color(238, 52, 37));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
			lblNewLabel.setBounds(291, 50, 222, 30);
			getContentPane().add(lblNewLabel);
		}
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setForeground(new Color(90, 90, 90));
			btnCerrar.setBackground(new Color(255, 255, 255));
			btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(600, 406, 150, 35);
			getContentPane().add(btnCerrar);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(50, 120, 700, 250);
			getContentPane().add(scrollPane);
			{
				tableCuentasBancarias = new JTable();
				tableCuentasBancarias.setFillsViewportHeight(true);
				tableCuentasBancarias.setForeground(new Color(90, 90, 90));
				tableCuentasBancarias.setFont(new Font("Arial", Font.PLAIN, 13));
				tableCuentasBancarias.setBackground(new Color(255, 255, 255));
				tableCuentasBancarias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] columnas = new String[] {"Número de cuenta", "Tipo de cuenta", "Saldo disponible", "Saldo contable", 
						"Estado", "Moneda"};
				defaultTableModel = new DefaultTableModel(columnas, 0) {
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {
		                return false;
		            }
				};
				tableCuentasBancarias.setModel(defaultTableModel);
				scrollPane.setViewportView(tableCuentasBancarias);
			}
		}
		{
			btnVerDetalles = new JButton("Ver detalles");
			btnVerDetalles.setBackground(new Color(238, 52, 37));
			btnVerDetalles.setForeground(new Color(255, 255, 255));
			btnVerDetalles.setFont(new Font("Arial", Font.BOLD, 13));
			btnVerDetalles.addActionListener(this);
			btnVerDetalles.setBounds(50, 406, 150, 35);
			getContentPane().add(btnVerDetalles);
		}
		{
			btnCancelar = new JButton("Cancelar cuenta");
			btnCancelar.setBackground(new Color(230, 230, 230));
			btnCancelar.setForeground(new Color(90, 90, 90));
			btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCancelar.addActionListener(this);
			btnCancelar.setBounds(230, 406, 150, 35);
			getContentPane().add(btnCancelar);
		}
		llenarTabla();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
		if (e.getSource() == btnVerDetalles) {
			do_btnVerDetalles_actionPerformed(e);
		}
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
	}
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
	public void llenarTabla() {
		ArrayList<Cuenta> cuentas = RepositorioCuenta.consultarCuenta(cliente.getIdPersona());
		cliente.setCuentas(cuentas);
		defaultTableModel.setRowCount(0);
		for (Cuenta cuenta : cuentas) {
			Object[] fila = new Object[6];
			fila[0] = cuenta.getNumeroCuentaFormateado();
			fila[1] = cuenta.getTipoCuenta();
			fila[2] = cuenta.getSaldoDisponibleFormateado();
			fila[3] = cuenta.getSaldoContableFormateado();
			fila[4] = cuenta.getEstado();
			fila[5] = cuenta.getMoneda();
			defaultTableModel.addRow(fila);
		}
	}
	protected void do_btnVerDetalles_actionPerformed(ActionEvent e) {
		Cuenta cuenta = obtenerCuentaBancaria();
		if(cuenta == null) return;
		VentanaVerDetallesCB ventanaVerDetallesCB = new VentanaVerDetallesCB(cuenta);
		ventanaVerDetallesCB.setVisible(true);
	}
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		Cuenta cuenta = obtenerCuentaBancaria();
		if(cuenta == null) return;
		else if (cuenta.getEstado().equals("cancelada")) {
            JOptionPane.showMessageDialog(this, "La cuenta ya está cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea cancelar la cuenta " + cuenta.getNumeroCuentaFormateado() + "?",
                "Confirmar cancelación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
        	VentanaAutenticar ventanaAutenticar = new VentanaAutenticar(cliente);
    		ventanaAutenticar.setVisible(true);
    		if(ventanaAutenticar.getEstadoAutenticacion()) {
    			cuenta.setEstado("cancelada");
    			RepositorioCuenta.actualizarCuenta(cuenta);
    			llenarTabla();
    			JOptionPane.showMessageDialog(this, "La cuenta ha sido cancelada exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
    		} else {
    			JOptionPane.showMessageDialog(this, "La autenticación falló.", "Información", JOptionPane.INFORMATION_MESSAGE);
    		}
        }
	}
	private Cuenta obtenerCuentaBancaria() {
		int posicionFilaSeleccionada = tableCuentasBancarias.getSelectedRow();
		if(posicionFilaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona una cuenta.", "Información", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		String numeroCuentaFormateada = (String) tableCuentasBancarias.getValueAt(posicionFilaSeleccionada, 0);
		Cuenta cuenta = cliente.buscarCuenta(numeroCuentaFormateada.substring(0, 3) + numeroCuentaFormateada.substring(4));
		if(cuenta == null) {
			JOptionPane.showMessageDialog(this, "La cuenta seleccionada no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		return cuenta;
	}
}
