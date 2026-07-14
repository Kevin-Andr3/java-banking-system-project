package vista;

import javax.swing.JDialog;

import modelo.Cliente;
import modelo.Cuenta;
import modelo.Tarjeta;
import repositorio.RepositorioCuenta;
import repositorio.RepositorioTarjeta;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaVerDetallesCliente extends JDialog implements ActionListener {

	private Cliente cliente;
	
	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JTextField txtDni;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JLabel lblCorreoElectrnico;
	private JLabel lblDireccin;
	private JLabel lblTelfono;
	private JLabel lblApellidos;
	private JLabel lblNombres;
	private JLabel lblDni;
	private JButton btnCerrar;
	private JScrollPane scrollPane;
	private JTable tableCuentasCliente;
	private JScrollPane scrollPane_1;
	private JLabel lblCuentas;
	private JLabel lblTarjetas;
	private JTable tableTarjetasCliente;
	private DefaultTableModel defaultTableModelCuentas;
	private DefaultTableModel defaultTableModelTarjetas;

	public VentanaVerDetallesCliente(Cliente cliente) {
		getContentPane().setBackground(new Color(255, 255, 255));
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.cliente = cliente;
		setTitle("Ver detalles");
		setBounds(100, 100, 820, 610);
		getContentPane().setLayout(null);
		{
			lblNewLabel = new JLabel("Cliente");
			lblNewLabel.setForeground(new Color(238, 52, 37));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
			lblNewLabel.setBounds(367, 50, 83, 30);
			getContentPane().add(lblNewLabel);
		}
		{
			txtDni = new JTextField();
			txtDni.setBackground(new Color(255, 255, 255));
			txtDni.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDni.setForeground(new Color(90, 90, 90));
			txtDni.setText((String) null);
			txtDni.setEditable(false);
			txtDni.setColumns(10);
			txtDni.setBounds(150, 116, 200, 25);
			getContentPane().add(txtDni);
		}
		{
			txtNombres = new JTextField();
			txtNombres.setBackground(new Color(255, 255, 255));
			txtNombres.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNombres.setForeground(new Color(90, 90, 90));
			txtNombres.setText((String) null);
			txtNombres.setEditable(false);
			txtNombres.setColumns(10);
			txtNombres.setBounds(150, 152, 200, 25);
			getContentPane().add(txtNombres);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.setBackground(new Color(255, 255, 255));
			txtApellidos.setFont(new Font("Arial", Font.PLAIN, 13));
			txtApellidos.setForeground(new Color(90, 90, 90));
			txtApellidos.setText((String) null);
			txtApellidos.setEditable(false);
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(150, 188, 200, 25);
			getContentPane().add(txtApellidos);
		}
		{
			txtTelefono = new JTextField();
			txtTelefono.setBackground(new Color(255, 255, 255));
			txtTelefono.setFont(new Font("Arial", Font.PLAIN, 13));
			txtTelefono.setForeground(new Color(90, 90, 90));
			txtTelefono.setText((String) null);
			txtTelefono.setEditable(false);
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(548, 116, 200, 25);
			getContentPane().add(txtTelefono);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.setBackground(new Color(255, 255, 255));
			txtDireccion.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDireccion.setForeground(new Color(90, 90, 90));
			txtDireccion.setText((String) null);
			txtDireccion.setEditable(false);
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(548, 152, 200, 25);
			getContentPane().add(txtDireccion);
		}
		{
			txtCorreo = new JTextField();
			txtCorreo.setBackground(new Color(255, 255, 255));
			txtCorreo.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCorreo.setForeground(new Color(90, 90, 90));
			txtCorreo.setText((String) null);
			txtCorreo.setEditable(false);
			txtCorreo.setColumns(10);
			txtCorreo.setBounds(548, 188, 200, 25);
			getContentPane().add(txtCorreo);
		}
		{
			lblCorreoElectrnico = new JLabel("Correo electrónico:");
			lblCorreoElectrnico.setBackground(new Color(255, 255, 255));
			lblCorreoElectrnico.setFont(new Font("Arial", Font.BOLD, 13));
			lblCorreoElectrnico.setForeground(new Color(90, 90, 90));
			lblCorreoElectrnico.setBounds(396, 192, 122, 16);
			getContentPane().add(lblCorreoElectrnico);
		}
		{
			lblDireccin = new JLabel("Dirección:");
			lblDireccin.setBackground(new Color(255, 255, 255));
			lblDireccin.setFont(new Font("Arial", Font.BOLD, 13));
			lblDireccin.setForeground(new Color(90, 90, 90));
			lblDireccin.setBounds(396, 156, 64, 16);
			getContentPane().add(lblDireccin);
		}
		{
			lblTelfono = new JLabel("Teléfono:");
			lblTelfono.setBackground(new Color(255, 255, 255));
			lblTelfono.setFont(new Font("Arial", Font.BOLD, 13));
			lblTelfono.setForeground(new Color(90, 90, 90));
			lblTelfono.setBounds(396, 120, 60, 16);
			getContentPane().add(lblTelfono);
		}
		{
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBackground(new Color(255, 255, 255));
			lblApellidos.setFont(new Font("Arial", Font.BOLD, 13));
			lblApellidos.setForeground(new Color(90, 90, 90));
			lblApellidos.setBounds(50, 192, 63, 16);
			getContentPane().add(lblApellidos);
		}
		{
			lblNombres = new JLabel("Nombres:");
			lblNombres.setBackground(new Color(255, 255, 255));
			lblNombres.setFont(new Font("Arial", Font.BOLD, 13));
			lblNombres.setForeground(new Color(90, 90, 90));
			lblNombres.setBounds(50, 156, 60, 16);
			getContentPane().add(lblNombres);
		}
		{
			lblDni = new JLabel("DNI:");
			lblDni.setBackground(new Color(255, 255, 255));
			lblDni.setFont(new Font("Arial", Font.BOLD, 13));
			lblDni.setForeground(new Color(90, 90, 90));
			lblDni.setBounds(50, 120, 26, 16);
			getContentPane().add(lblDni);
		}
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setBackground(new Color(255, 255, 255));
			btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCerrar.setForeground(new Color(90, 90, 90));
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(327, 487, 150, 35);
			getContentPane().add(btnCerrar);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(50, 265, 325, 200);
			getContentPane().add(scrollPane);
			{
				tableCuentasCliente = new JTable();
				tableCuentasCliente.setFillsViewportHeight(true);
				tableCuentasCliente.setForeground(new Color(90, 90, 90));
				tableCuentasCliente.setFont(new Font("Arial", Font.PLAIN, 13));
				tableCuentasCliente.setBackground(new Color(255, 255, 255));
				tableCuentasCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] columnas = new String[] {"Número de cuenta", "Tipo de cuenta", "Estado"};
				defaultTableModelCuentas = new DefaultTableModel(columnas, 0) {
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {
		                return false;
		            }
				};
				tableCuentasCliente.setModel(defaultTableModelCuentas);
				scrollPane.setViewportView(tableCuentasCliente);
			}
		}
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(423, 265, 325, 200);
			getContentPane().add(scrollPane_1);
			{
				tableTarjetasCliente = new JTable();
				tableTarjetasCliente.setFillsViewportHeight(true);
				tableTarjetasCliente.setForeground(new Color(90, 90, 90));
				tableTarjetasCliente.setFont(new Font("Arial", Font.PLAIN, 13));
				tableTarjetasCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] columnas = { "Número de tarjeta", "Tipo de tarjeta", "Estado" };
				defaultTableModelTarjetas = new DefaultTableModel(columnas, 0) {
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {
		                return false;
		            }
		        };
				tableTarjetasCliente.setModel(defaultTableModelTarjetas);
				scrollPane_1.setViewportView(tableTarjetasCliente);
			}
		}
		{
			lblCuentas = new JLabel("Cuentas:");
			lblCuentas.setBackground(new Color(255, 255, 255));
			lblCuentas.setFont(new Font("Arial", Font.BOLD, 13));
			lblCuentas.setForeground(new Color(90, 90, 90));
			lblCuentas.setBounds(50, 238, 55, 16);
			getContentPane().add(lblCuentas);
		}
		{
			lblTarjetas = new JLabel("Tarjetas:");
			lblTarjetas.setBackground(new Color(255, 255, 255));
			lblTarjetas.setFont(new Font("Arial", Font.BOLD, 13));
			lblTarjetas.setForeground(new Color(90, 90, 90));
			lblTarjetas.setBounds(423, 238, 55, 16);
			getContentPane().add(lblTarjetas);
		}
		mostrarDatos();
		llenarTablaCuentas();
		llenarTablaTarjetas();
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
		txtDni.setText(cliente.getDni());
		txtNombres.setText(cliente.getNombres());
		txtApellidos.setText(cliente.getApellidos());
		txtTelefono.setText(cliente.getTelefono());
		txtDireccion.setText(cliente.getDireccion());
		txtCorreo.setText(cliente.getCorreo());		
	}
	private void llenarTablaCuentas() {
		ArrayList<Cuenta> cuentas = RepositorioCuenta.consultarCuenta(cliente.getIdPersona());
		cliente.setCuentas(cuentas);
		defaultTableModelCuentas.setRowCount(0);
		for (Cuenta cuenta : cuentas) {
			Object[] fila = new Object[3];
			fila[0] = cuenta.getNumeroCuenta();
			fila[1] = cuenta.getTipoCuenta();
			fila[2] = cuenta.getEstado();
			defaultTableModelCuentas.addRow(fila);
		}
	}
	private void llenarTablaTarjetas() {
		ArrayList<Tarjeta> tarjetas = RepositorioTarjeta.consultarTarjeta(cliente.getIdPersona());
		cliente.setTarjetas(tarjetas);
        defaultTableModelTarjetas.setRowCount(0);
        for (Tarjeta tarjeta : tarjetas) {
        	Object[] fila = new Object[3];
        	fila[0] = tarjeta.getNumeroTarjeta();
        	fila[1] = tarjeta.getTipoTarjeta();
        	fila[2] = tarjeta.getEstado();
        	defaultTableModelTarjetas.addRow(fila);
        }
	}
}
