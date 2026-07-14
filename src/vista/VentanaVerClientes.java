package vista;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import repositorio.RepositorioCliente;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class VentanaVerClientes extends JDialog implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private JLabel lblClientes;
	private JScrollPane scrollPane;
	private JButton btnCerrar;
	private JTable tableClientes;
	private DefaultTableModel defaultTableModel;
	private JLabel lblDni;
	private JTextField txtDni;
	private JLabel lblNombres;
	private JTextField txtNombres;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JButton btnVerDetalles;

	public VentanaVerClientes() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setTitle("Ver clientes");
		setBounds(100, 100, 820, 610);
		getContentPane().setLayout(null);
		{
			lblClientes = new JLabel("Clientes");
			lblClientes.setForeground(new Color(238, 52, 37));
			lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
			lblClientes.setFont(new Font("Arial", Font.BOLD, 25));
			lblClientes.setBounds(353, 50, 97, 30);
			getContentPane().add(lblClientes);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(50, 210, 700, 250);
			getContentPane().add(scrollPane);
			{
				tableClientes = new JTable();
				tableClientes.setFillsViewportHeight(true);
				tableClientes.setBackground(new Color(255, 255, 255));
				tableClientes.setForeground(new Color(90, 90, 90));
				tableClientes.setFont(new Font("Arial", Font.PLAIN, 13));
				tableClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] columnas = new String[] {"DNI", "Nombres", "Apellidos", "Teléfono", "Dirección", "Correo electrónico"};
				defaultTableModel = new DefaultTableModel(columnas, 0) {
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {
		                return false;
		            }
				};
				tableClientes.setModel(defaultTableModel);
				scrollPane.setViewportView(tableClientes);
			}
		}
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setBackground(new Color(255, 255, 255));
			btnCerrar.setForeground(new Color(90, 90, 90));
			btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(600, 491, 150, 35);
			getContentPane().add(btnCerrar);
		}
		{
			lblDni = new JLabel("DNI:");
			lblDni.setForeground(new Color(90, 90, 90));
			lblDni.setFont(new Font("Arial", Font.BOLD, 13));
			lblDni.setBounds(50, 120, 26, 16);
			getContentPane().add(lblDni);
		}
		{
			txtDni = new JTextField();
			txtDni.addKeyListener(this);
			txtDni.setForeground(new Color(90, 90, 90));
			txtDni.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDni.setColumns(10);
			txtDni.setBounds(160, 116, 200, 25);
			getContentPane().add(txtDni);
		}
		{
			lblNombres = new JLabel("Nombres:");
			lblNombres.setForeground(new Color(90, 90, 90));
			lblNombres.setFont(new Font("Arial", Font.BOLD, 13));
			lblNombres.setBounds(440, 120, 60, 16);
			getContentPane().add(lblNombres);
		}
		{
			txtNombres = new JTextField();
			txtNombres.addKeyListener(this);
			txtNombres.setForeground(new Color(90, 90, 90));
			txtNombres.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNombres.setColumns(10);
			txtNombres.setBounds(550, 116, 200, 25);
			getContentPane().add(txtNombres);
		}
		{
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setForeground(new Color(90, 90, 90));
			lblApellidos.setFont(new Font("Arial", Font.BOLD, 13));
			lblApellidos.setBounds(50, 159, 63, 16);
			getContentPane().add(lblApellidos);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.addKeyListener(this);
			txtApellidos.setForeground(new Color(90, 90, 90));
			txtApellidos.setFont(new Font("Arial", Font.PLAIN, 13));
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(160, 155, 200, 25);
			getContentPane().add(txtApellidos);
		}
		{
			btnVerDetalles = new JButton("Ver detalles");
			btnVerDetalles.setBackground(new Color(238, 52, 37));
			btnVerDetalles.setForeground(new Color(255, 255, 255));
			btnVerDetalles.setFont(new Font("Arial", Font.BOLD, 13));
			btnVerDetalles.addActionListener(this);
			btnVerDetalles.setBounds(50, 491, 150, 35);
			getContentPane().add(btnVerDetalles);
		}
		llenarTabla("","","");
	}
	public void actionPerformed(ActionEvent e) {
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
	public void llenarTabla(String dni, String nombres, String apellidos) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		if(dni.isEmpty() && nombres.isEmpty() && apellidos.isEmpty()) clientes = RepositorioCliente.listarCliente();
		else clientes = RepositorioCliente.consultarCliente(dni, nombres, apellidos);
		defaultTableModel.setRowCount(0);
		for (Cliente cliente : clientes) {
			Object[] fila = new Object[6];
			fila[0] = cliente.getDni();
			fila[1] = cliente.getNombres();
			fila[2] = cliente.getApellidos();
			fila[3] = cliente.getTelefono();
			fila[4] = cliente.getDireccion();
			fila[5] = cliente.getCorreo();
			defaultTableModel.addRow(fila);
		}
	}
	protected void do_btnVerDetalles_actionPerformed(ActionEvent e) {
		int posicionFilaSeleccionada = tableClientes.getSelectedRow();
		if(posicionFilaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona un cliente.", "Información", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String correo = tableClientes.getValueAt(posicionFilaSeleccionada, 5).toString();
		Cliente cliente = RepositorioCliente.consultarCliente(correo);
		if(cliente == null) {
			JOptionPane.showMessageDialog(this, "El cliente seleccionado no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		VentanaVerDetallesCliente ventanaVerDetallesCliente = new VentanaVerDetallesCliente(cliente);
		ventanaVerDetallesCliente.setVisible(true);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtApellidos) {
			do_txtApellidos_keyReleased(e);
		}
		if (e.getSource() == txtNombres) {
			do_txtNombres_keyReleased(e);
		}
		if (e.getSource() == txtDni) {
			do_txtDni_keyReleased(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtApellidos) {
			do_txtApellidos_keyTyped(e);
		}
		if (e.getSource() == txtNombres) {
			do_txtNombres_keyTyped(e);
		}
		if (e.getSource() == txtDni) {
			do_txtDni_keyTyped(e);
		}
	}
	protected void do_txtDni_keyTyped(KeyEvent e) {
		char caracteres = e.getKeyChar();
		if(Character.isAlphabetic(caracteres)) {
			e.consume();
			JOptionPane.showMessageDialog(this, "El DNI debe tener números.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} else if (txtDni.getText().length() >= 8) {
	        e.consume();
	        JOptionPane.showMessageDialog(this, "El DNI debe tener 8 dígitos.", "Información", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	protected void do_txtNombres_keyTyped(KeyEvent e) {
		char caracteres = e.getKeyChar();
		if(Character.isDigit(caracteres)) {
			e.consume();
			JOptionPane.showMessageDialog(this, "Los nombres deben tener letras.", "Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	protected void do_txtApellidos_keyTyped(KeyEvent e) {
		char caracteres = e.getKeyChar();
		if(Character.isDigit(caracteres)) {
			e.consume();
			JOptionPane.showMessageDialog(this, "Los apellidos deben tener letras.", "Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	protected void do_txtDni_keyReleased(KeyEvent e) {
		String dni = txtDni.getText().trim();
		String nombres = txtNombres.getText().trim();
		String apellidos = txtApellidos.getText().trim();
		llenarTabla(dni, nombres, apellidos);
	}
	protected void do_txtNombres_keyReleased(KeyEvent e) {
		String dni = txtDni.getText().trim();
		String nombres = txtNombres.getText().trim();
		String apellidos = txtApellidos.getText().trim();
		llenarTabla(dni, nombres, apellidos);
	}
	protected void do_txtApellidos_keyReleased(KeyEvent e) {
		String dni = txtDni.getText().trim();
		String nombres = txtNombres.getText().trim();
		String apellidos = txtApellidos.getText().trim();
		llenarTabla(dni, nombres, apellidos);
	}
}
