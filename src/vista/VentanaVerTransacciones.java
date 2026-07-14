package vista;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Transaccion;
import repositorio.RepositorioTransaccion;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class VentanaVerTransacciones extends JDialog implements ActionListener, ItemListener, KeyListener {
	
	private Cliente cliente;

	private static final long serialVersionUID = 1L;
	private JLabel lblTransacciones;
	private JComboBox<String> cbxTipoTransaccion;
	private JLabel lblTipoOperación;
	private JLabel lblDescripcin;
	private JTextField txtDescripcion;
	private JScrollPane scrollPane;
	private JButton btnCerrar;
	private JTable tableTransacciones;
	private DefaultTableModel defaultTableModel;
	private JButton btnVerDetalles;

	public VentanaVerTransacciones(Cliente cliente) {
		this.cliente = cliente;
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Ver transacciones");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 820, 563);
		getContentPane().setLayout(null);
		{
			lblTransacciones = new JLabel("Transacciones");
			lblTransacciones.setForeground(new Color(238, 52, 37));
			lblTransacciones.setHorizontalAlignment(SwingConstants.CENTER);
			lblTransacciones.setFont(new Font("Arial", Font.BOLD, 25));
			lblTransacciones.setBounds(314, 50, 175, 30);
			getContentPane().add(lblTransacciones);
		}
		{
			cbxTipoTransaccion = new JComboBox<String>();
			cbxTipoTransaccion.addItemListener(this);
			cbxTipoTransaccion.setForeground(new Color(90, 90, 90));
			cbxTipoTransaccion.setFont(new Font("Arial", Font.PLAIN, 13));
			String[] tiposTransacciones = {"todos", "transferir", "pagar", "retirar", "depositar"};
			for (String tipoTransaccion : tiposTransacciones) {
				cbxTipoTransaccion.addItem(tipoTransaccion);
			}
			cbxTipoTransaccion.setBounds(200, 116, 200, 25);
			getContentPane().add(cbxTipoTransaccion);
		}
		{
			lblTipoOperación = new JLabel("Tipo de transacción:");
			lblTipoOperación.setForeground(new Color(90, 90, 90));
			lblTipoOperación.setFont(new Font("Arial", Font.BOLD, 13));
			lblTipoOperación.setBounds(50, 120, 129, 16);
			getContentPane().add(lblTipoOperación);
		}
		{
			lblDescripcin = new JLabel("Descripción:");
			lblDescripcin.setForeground(new Color(90, 90, 90));
			lblDescripcin.setFont(new Font("Arial", Font.BOLD, 13));
			lblDescripcin.setBounds(445, 120, 78, 16);
			getContentPane().add(lblDescripcin);
		}
		{
			txtDescripcion = new JTextField();
			txtDescripcion.addKeyListener(this);
			txtDescripcion.setForeground(new Color(90, 90, 90));
			txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDescripcion.setColumns(10);
			txtDescripcion.setBounds(550, 116, 200, 25);
			getContentPane().add(txtDescripcion);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(50, 170, 700, 250);
			getContentPane().add(scrollPane);
			{
				tableTransacciones = new JTable();
				tableTransacciones.setFillsViewportHeight(true);
				tableTransacciones.setForeground(new Color(90, 90, 90));
				tableTransacciones.setFont(new Font("Arial", Font.PLAIN, 13));
				tableTransacciones.setBackground(new Color(255, 255, 255));
				tableTransacciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] columnas = new String[] {"ID", "Tipo", "Descripción", "Fecha y hora", "Monto"};
				defaultTableModel = new DefaultTableModel(columnas, 0) {
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {
		                return false;
		            }
				};
				tableTransacciones.setModel(defaultTableModel);
				scrollPane.setViewportView(tableTransacciones);
			}
		}
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setBackground(new Color(255, 255, 255));
			btnCerrar.setForeground(new Color(90, 90, 90));
			btnCerrar.addActionListener(this);
			btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCerrar.setBounds(600, 440, 150, 35);
			getContentPane().add(btnCerrar);
		}
		{
			btnVerDetalles = new JButton("Ver detalles");
			btnVerDetalles.addActionListener(this);
			btnVerDetalles.setForeground(new Color(255, 255, 255));
			btnVerDetalles.setFont(new Font("Arial", Font.BOLD, 13));
			btnVerDetalles.setBackground(new Color(238, 52, 37));
			btnVerDetalles.setBounds(50, 446, 150, 35);
			getContentPane().add(btnVerDetalles);
		}
		llenarTabla("","");
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
	private void llenarTabla(String tipoTransaccion, String descripcion) {
		ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
		if(tipoTransaccion.isEmpty() && descripcion.isEmpty()) {
			transacciones = RepositorioTransaccion.consultarTransaccion(cliente.getIdPersona());
			cliente.setTransacciones(transacciones);
		}
		else transacciones = RepositorioTransaccion.consultarTransaccion(cliente.getIdPersona(), tipoTransaccion, descripcion);
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
	protected void do_btnVerDetalles_actionPerformed(ActionEvent e) {
		int posicionFilaSeleccionada = tableTransacciones.getSelectedRow();
		if(posicionFilaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona una transacción.", "Información", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String id = (String) tableTransacciones.getValueAt(posicionFilaSeleccionada, 0);
		Transaccion transaccion = cliente.buscarTransaccion(id);
		if(transaccion == null) {
			JOptionPane.showMessageDialog(this, "La transacción seleccionada no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		VentanaVerTransaccion ventanaVerTransaccion = new VentanaVerTransaccion(transaccion);
		ventanaVerTransaccion.setVisible(true);
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cbxTipoTransaccion) {
			do_cbxTipoTransaccion_itemStateChanged(e);
		}
	}
	protected void do_cbxTipoTransaccion_itemStateChanged(ItemEvent e) {
		String tipoTransaccion = (String) cbxTipoTransaccion.getSelectedItem();
		String descripcion = "";
		if(txtDescripcion != null) descripcion = txtDescripcion.getText().trim();
		if(tableTransacciones != null) llenarTabla((tipoTransaccion.equals("todos") ? "" : tipoTransaccion), descripcion);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtDescripcion) {
			do_txtDescripcion_keyReleased(e);
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void do_txtDescripcion_keyReleased(KeyEvent e) {
		String tipoTransaccion = (String) cbxTipoTransaccion.getSelectedItem();
		String descripcion = "";
		if(txtDescripcion != null) descripcion = txtDescripcion.getText().trim();
		if(tableTransacciones != null) llenarTabla((tipoTransaccion.equals("todos") ? "" : tipoTransaccion), descripcion);
	}
}
