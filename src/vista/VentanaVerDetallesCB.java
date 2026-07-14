package vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import modelo.Cuenta;
import modelo.Transaccion;
import repositorio.RepositorioTransaccion;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class VentanaVerDetallesCB extends JDialog implements ActionListener {
	
	private Cuenta cuenta;

	private static final long serialVersionUID = 1L;
	private JLabel lblCuentaBancaria;
	private JLabel lblNewLabel;
	private JLabel lblSaldoDisponible;
	private JLabel lblSaldoContable;
	private JLabel lblFechaDeCreacin;
	private JLabel lblEstado;
	private JTextField txtNumeroCuenta;
	private JTextField txtSaldoDisponible;
	private JTextField txtSaldoContable;
	private JTextField txtFechaCreacion;
	private JTextField txtEstado;
	private JButton btnCerrar;
	private JScrollPane scrollPane;
	private JLabel lblHistorial;
	private JTable tableHistorial;
	private DefaultTableModel defaultTableModel;
	private JLabel lblMoneda;
	private JTextField txtMoneda;

	public VentanaVerDetallesCB(Cuenta cuenta) {
		this.cuenta = cuenta;
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setTitle("Ver detalles");
		setBounds(100, 100, 820, 630);
		getContentPane().setLayout(null);
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setBackground(new Color(255, 255, 255));
			btnCerrar.setForeground(new Color(90, 90, 90));
			btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(327, 505, 150, 35);
			getContentPane().add(btnCerrar);
		}
		{
			lblCuentaBancaria = new JLabel("Cuenta de " + cuenta.getTipoCuenta());
			lblCuentaBancaria.setForeground(new Color(238, 52, 37));
			lblCuentaBancaria.setFont(new Font("Arial", Font.BOLD, 25));
			lblCuentaBancaria.setHorizontalAlignment(SwingConstants.CENTER);
			lblCuentaBancaria.setBounds(274, 50, 256, 30);
			getContentPane().add(lblCuentaBancaria);
		}
		{
			lblNewLabel = new JLabel("Número de cuenta:");
			lblNewLabel.setForeground(new Color(90, 90, 90));
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
			lblNewLabel.setBounds(50, 120, 121, 16);
			getContentPane().add(lblNewLabel);
		}
		{
			lblSaldoDisponible = new JLabel("Saldo disponible:");
			lblSaldoDisponible.setForeground(new Color(90, 90, 90));
			lblSaldoDisponible.setFont(new Font("Arial", Font.BOLD, 13));
			lblSaldoDisponible.setBounds(50, 156, 111, 16);
			getContentPane().add(lblSaldoDisponible);
		}
		{
			lblSaldoContable = new JLabel("Saldo contable:");
			lblSaldoContable.setForeground(new Color(90, 90, 90));
			lblSaldoContable.setFont(new Font("Arial", Font.BOLD, 13));
			lblSaldoContable.setBounds(50, 192, 100, 16);
			getContentPane().add(lblSaldoContable);
		}
		{
			lblFechaDeCreacin = new JLabel("Fecha de creación:");
			lblFechaDeCreacin.setForeground(new Color(90, 90, 90));
			lblFechaDeCreacin.setFont(new Font("Arial", Font.BOLD, 13));
			lblFechaDeCreacin.setBounds(410, 120, 122, 16);
			getContentPane().add(lblFechaDeCreacin);
		}
		{
			lblEstado = new JLabel("Estado:");
			lblEstado.setForeground(new Color(90, 90, 90));
			lblEstado.setFont(new Font("Arial", Font.BOLD, 13));
			lblEstado.setBounds(410, 156, 46, 16);
			getContentPane().add(lblEstado);
		}
		{
			txtNumeroCuenta = new JTextField();
			txtNumeroCuenta.setForeground(new Color(90, 90, 90));
			txtNumeroCuenta.setBackground(new Color(255, 255, 255));
			txtNumeroCuenta.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNumeroCuenta.setEditable(false);
			txtNumeroCuenta.setBounds(190, 116, 200, 25);
			getContentPane().add(txtNumeroCuenta);
			txtNumeroCuenta.setColumns(10);
		}
		{
			txtSaldoDisponible = new JTextField();
			txtSaldoDisponible.setForeground(new Color(90, 90, 90));
			txtSaldoDisponible.setBackground(new Color(255, 255, 255));
			txtSaldoDisponible.setFont(new Font("Arial", Font.PLAIN, 13));
			txtSaldoDisponible.setEditable(false);
			txtSaldoDisponible.setColumns(10);
			txtSaldoDisponible.setBounds(190, 152, 200, 25);
			getContentPane().add(txtSaldoDisponible);
		}
		{
			txtSaldoContable = new JTextField();
			txtSaldoContable.setForeground(new Color(90, 90, 90));
			txtSaldoContable.setBackground(new Color(255, 255, 255));
			txtSaldoContable.setFont(new Font("Arial", Font.PLAIN, 13));
			txtSaldoContable.setEditable(false);
			txtSaldoContable.setColumns(10);
			txtSaldoContable.setBounds(190, 188, 200, 25);
			getContentPane().add(txtSaldoContable);
		}
		{
			txtFechaCreacion = new JTextField();
			txtFechaCreacion.setForeground(new Color(90, 90, 90));
			txtFechaCreacion.setBackground(new Color(255, 255, 255));
			txtFechaCreacion.setFont(new Font("Arial", Font.PLAIN, 13));
			txtFechaCreacion.setEditable(false);
			txtFechaCreacion.setColumns(10);
			txtFechaCreacion.setBounds(550, 116, 200, 25);
			getContentPane().add(txtFechaCreacion);
		}
		{
			txtEstado = new JTextField();
			txtEstado.setForeground(new Color(90, 90, 90));
			txtEstado.setBackground(new Color(255, 255, 255));
			txtEstado.setFont(new Font("Arial", Font.PLAIN, 13));
			txtEstado.setEditable(false);
			txtEstado.setColumns(10);
			txtEstado.setBounds(550, 152, 200, 25);
			getContentPane().add(txtEstado);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(50, 271, 700, 200);
			getContentPane().add(scrollPane);
			{
				tableHistorial = new JTable();
				tableHistorial.setFillsViewportHeight(true);
				tableHistorial.setForeground(new Color(90, 90, 90));
				tableHistorial.setFont(new Font("Arial", Font.PLAIN, 13));
				tableHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] columnas = new String[] {"Id", "Tipo", "Descripción", "Fecha y hora", "Monto"};
				defaultTableModel = new DefaultTableModel(columnas, 0) {
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {
		                return false;
		            }
				};
				tableHistorial.setModel(defaultTableModel);
				scrollPane.setViewportView(tableHistorial);
			}
		}
		{
			lblHistorial = new JLabel("Historial:");
			lblHistorial.setForeground(new Color(90, 90, 90));
			lblHistorial.setFont(new Font("Arial", Font.BOLD, 13));
			lblHistorial.setBounds(50, 237, 56, 16);
			getContentPane().add(lblHistorial);
		}
		{
			lblMoneda = new JLabel("Moneda:");
			lblMoneda.setForeground(new Color(90, 90, 90));
			lblMoneda.setFont(new Font("Arial", Font.BOLD, 13));
			lblMoneda.setBounds(410, 192, 55, 16);
			getContentPane().add(lblMoneda);
		}
		{
			txtMoneda = new JTextField();
			txtMoneda.setForeground(new Color(90, 90, 90));
			txtMoneda.setBackground(new Color(255, 255, 255));
			txtMoneda.setFont(new Font("Arial", Font.PLAIN, 13));
			txtMoneda.setText((String) null);
			txtMoneda.setEditable(false);
			txtMoneda.setColumns(10);
			txtMoneda.setBounds(550, 188, 200, 25);
			getContentPane().add(txtMoneda);
		}
		mostrarDatos();
		llenarTabla();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
	}
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
	private void mostrarDatos() {
		txtNumeroCuenta.setText(cuenta.getNumeroCuentaFormateado());
		txtSaldoDisponible.setText(cuenta.getSaldoDisponibleFormateado());
		txtSaldoContable.setText(cuenta.getSaldoContableFormateado());
		txtFechaCreacion.setText(cuenta.getFechaCreacionFormateada());
		txtEstado.setText(cuenta.getEstado());
		txtMoneda.setText(cuenta.getMoneda());
	}
	private void llenarTabla() {
		ArrayList<Transaccion> transacciones = RepositorioTransaccion.consultarTransaccionDescripcion(cuenta.getNumeroCuenta());
		cuenta.setTransacciones(transacciones);
		defaultTableModel.setRowCount(0);
		for (Transaccion transaccion : transacciones) {
			Object[] fila = new Object[5];
			fila[0] = transaccion.getIdTransaccion();
			fila[1] = transaccion.getTipoTransaccion();
			fila[2] = transaccion.getDescripcion();
			fila[3] = transaccion.getFechaHoraFormateada();
			fila[4] = transaccion.getMonto();
			defaultTableModel.addRow(fila);
		}
	}
}
