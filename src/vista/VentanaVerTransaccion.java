package vista;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import modelo.Transaccion;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaVerTransaccion extends JDialog implements ActionListener {
	
	private Transaccion transaccion;

	private static final long serialVersionUID = 1L;
	private JLabel lblTransaccin;
	private JLabel lblId;
	private JLabel lblTipo;
	private JTextField txtId;
	private JTextField txtTipo;
	private JLabel lblMonto;
	private JTextField txtMonto;
	private JLabel lblFechaYHora;
	private JTextField txtFechaHora;
	private JLabel lblDescripcin;
	private JScrollPane scrollPane;
	private JTextArea txtDescripcion;
	private JButton btnCerrar;

	public VentanaVerTransaccion(Transaccion transaccion) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		this.transaccion = transaccion;
		setTitle("Ver Transacción");
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 820, 430);
		getContentPane().setLayout(null);
		{
			lblTransaccin = new JLabel("Transacción");
			lblTransaccin.setHorizontalAlignment(SwingConstants.CENTER);
			lblTransaccin.setForeground(new Color(238, 52, 37));
			lblTransaccin.setFont(new Font("Arial", Font.BOLD, 25));
			lblTransaccin.setBounds(328, 50, 147, 30);
			getContentPane().add(lblTransaccin);
		}
		{
			lblId = new JLabel("ID:");
			lblId.setForeground(new Color(90, 90, 90));
			lblId.setFont(new Font("Arial", Font.BOLD, 13));
			lblId.setBounds(50, 120, 17, 16);
			getContentPane().add(lblId);
		}
		{
			lblTipo = new JLabel("Tipo:");
			lblTipo.setForeground(new Color(90, 90, 90));
			lblTipo.setFont(new Font("Arial", Font.BOLD, 13));
			lblTipo.setBounds(50, 156, 32, 16);
			getContentPane().add(lblTipo);
		}
		{
			txtId = new JTextField();
			txtId.setText((String) null);
			txtId.setForeground(new Color(90, 90, 90));
			txtId.setFont(new Font("Arial", Font.PLAIN, 13));
			txtId.setEditable(false);
			txtId.setColumns(10);
			txtId.setBackground(Color.WHITE);
			txtId.setBounds(190, 116, 200, 25);
			getContentPane().add(txtId);
		}
		{
			txtTipo = new JTextField();
			txtTipo.setText((String) null);
			txtTipo.setForeground(new Color(90, 90, 90));
			txtTipo.setFont(new Font("Arial", Font.PLAIN, 13));
			txtTipo.setEditable(false);
			txtTipo.setColumns(10);
			txtTipo.setBackground(Color.WHITE);
			txtTipo.setBounds(190, 152, 200, 25);
			getContentPane().add(txtTipo);
		}
		{
			lblMonto = new JLabel("Monto:");
			lblMonto.setForeground(new Color(90, 90, 90));
			lblMonto.setFont(new Font("Arial", Font.BOLD, 13));
			lblMonto.setBounds(450, 156, 43, 16);
			getContentPane().add(lblMonto);
		}
		{
			txtMonto = new JTextField();
			txtMonto.setText((String) null);
			txtMonto.setForeground(new Color(90, 90, 90));
			txtMonto.setFont(new Font("Arial", Font.PLAIN, 13));
			txtMonto.setEditable(false);
			txtMonto.setColumns(10);
			txtMonto.setBackground(Color.WHITE);
			txtMonto.setBounds(550, 152, 200, 25);
			getContentPane().add(txtMonto);
		}
		{
			lblFechaYHora = new JLabel("Fecha y hora:");
			lblFechaYHora.setForeground(new Color(90, 90, 90));
			lblFechaYHora.setFont(new Font("Arial", Font.BOLD, 13));
			lblFechaYHora.setBounds(410, 120, 87, 16);
			getContentPane().add(lblFechaYHora);
		}
		{
			txtFechaHora = new JTextField();
			txtFechaHora.setText((String) null);
			txtFechaHora.setForeground(new Color(90, 90, 90));
			txtFechaHora.setFont(new Font("Arial", Font.PLAIN, 13));
			txtFechaHora.setEditable(false);
			txtFechaHora.setColumns(10);
			txtFechaHora.setBackground(Color.WHITE);
			txtFechaHora.setBounds(550, 116, 200, 25);
			getContentPane().add(txtFechaHora);
		}
		{
			lblDescripcin = new JLabel("Descripción:");
			lblDescripcin.setForeground(new Color(90, 90, 90));
			lblDescripcin.setFont(new Font("Arial", Font.BOLD, 13));
			lblDescripcin.setBounds(50, 192, 78, 16);
			getContentPane().add(lblDescripcin);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(190, 188, 560, 100);
			getContentPane().add(scrollPane);
			{
				txtDescripcion = new JTextArea();
				txtDescripcion.setEditable(false);
				txtDescripcion.setBackground(new Color(255, 255, 255));
				txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
				txtDescripcion.setForeground(new Color(90, 90, 90));
				scrollPane.setViewportView(txtDescripcion);
			}
		}
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(this);
			btnCerrar.setForeground(new Color(90, 90, 90));
			btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCerrar.setBackground(Color.WHITE);
			btnCerrar.setBounds(327, 320, 150, 35);
			getContentPane().add(btnCerrar);
		}
		mostrarDatos();
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
		txtId.setText(transaccion.getIdTransaccion());
		txtTipo.setText(transaccion.getTipoTransaccion());
		txtFechaHora.setText(transaccion.getFechaHoraFormateada());
		txtMonto.setText(Double.toString(transaccion.getMonto()));
		String[] textoSeparado = transaccion.getDescripcion().split(";");
		for (int i = 0; i < textoSeparado.length; i++) {
			txtDescripcion.append(textoSeparado[i].trim() + "\n");
		}
	}
}
