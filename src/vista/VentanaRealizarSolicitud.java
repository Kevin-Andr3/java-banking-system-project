package vista;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import modelo.Cliente;
import modelo.Empleado;
import modelo.Solicitud;
import repositorio.RepositorioEmpleado;
import repositorio.RepositorioSolicitud;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaRealizarSolicitud extends JDialog implements ActionListener {
	
	private String asunto;
	private Cliente cliente;

	private static final long serialVersionUID = 1L;
	private JLabel lblAsunto;
	private JComboBox<String> cbxAsunto;
	private JButton btnEnviar;
	private JButton btnCancelar;
	private JLabel lblMoneda;
	private JComboBox<String> cbxMonedas;

	public VentanaRealizarSolicitud(String asunto, Cliente cliente) {
		this.asunto = asunto;
		this.cliente = cliente;
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setTitle("Realizar solicitud");
		setBounds(100, 100, 560, 360);
		getContentPane().setLayout(null);
		{
			lblAsunto = new JLabel("¿Qué tipo de " + (asunto.contains("tarjeta") ? "tarjeta" : "cuenta") + " desea solicitar?");
			lblAsunto.setForeground(new Color(238, 52, 37));
			lblAsunto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAsunto.setFont(new Font("Arial", Font.BOLD, 25));
			lblAsunto.setBounds(0, 50, 544, 30);
			getContentPane().add(lblAsunto);
		}
		{
			cbxAsunto = new JComboBox<String>();
			cbxAsunto.setForeground(new Color(90, 90, 90));
			cbxAsunto.setFont(new Font("Arial", Font.PLAIN, 13));
			if(asunto.contains("cuenta")) {
				String[] tiposCuentas = {"ahorro", "corriente"};
				for (String tipoCuenta : tiposCuentas) {
					cbxAsunto.addItem(tipoCuenta);
				}
			}
			else if(asunto.contains("tarjeta")) {
				String[] tiposTarjetas = {"débito", "crédito"};
				for (String tipoTarjeta : tiposTarjetas) {
					cbxAsunto.addItem(tipoTarjeta);
				}
			}
			cbxAsunto.setBounds(197, 120, 150, 25);
			getContentPane().add(cbxAsunto);
		}
		{
			btnEnviar = new JButton("Enviar");
			btnEnviar.setForeground(new Color(255, 255, 255));
			btnEnviar.setBackground(new Color(238, 52, 37));
			btnEnviar.setFont(new Font("Arial", Font.BOLD, 13));
			btnEnviar.addActionListener(this);
			btnEnviar.setBounds(50, 240, 150, 35);
			getContentPane().add(btnEnviar);
		}
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(new Color(255, 255, 255));
			btnCancelar.setForeground(new Color(90, 90, 90));
			btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCancelar.addActionListener(this);
			btnCancelar.setBounds(345, 240, 150, 35);
			getContentPane().add(btnCancelar);
		}
		if(asunto.contains("cuenta")) {
			{
				lblMoneda = new JLabel("Moneda:");
				lblMoneda.setForeground(new Color(90, 90, 90));
				lblMoneda.setFont(new Font("Arial", Font.BOLD, 13));
				lblMoneda.setBounds(50, 180, 55, 16);
				getContentPane().add(lblMoneda);
			}
			{
				cbxMonedas = new JComboBox<String>();
				cbxMonedas.setForeground(new Color(90, 90, 90));
				cbxMonedas.setFont(new Font("Arial", Font.PLAIN, 13));
				String[] monedas = {"soles", "dólares", "euros", "libras"};
				for (String moneda : monedas) {
					cbxMonedas.addItem(moneda);
				}
				cbxMonedas.setBounds(130, 175, 150, 25);
				getContentPane().add(cbxMonedas);
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEnviar) {
			do_btnEnviar_actionPerformed(e);
		}
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
	}
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		dispose();
	}
	protected void do_btnEnviar_actionPerformed(ActionEvent e) {
		String tipo = (String) cbxAsunto.getSelectedItem();
		String moneda = "";
		if(cbxMonedas != null) moneda = (String) cbxMonedas.getSelectedItem();
		Empleado empleado = RepositorioEmpleado.consultarEmpleadoAleatorio();
    	if(empleado == null) {
    		JOptionPane.showMessageDialog(this, "No hay empleados disponibles, vuelva a intentarlo más tarde.", "Información", JOptionPane.INFORMATION_MESSAGE);
    		return;
    	}
    	asunto = tipo.equals("corriente") ? asunto + " " + tipo : asunto + " de " + tipo;
    	asunto = moneda.isEmpty() ? asunto : asunto + " en " + moneda;
    	Solicitud solicitud = new Solicitud(asunto, cliente, empleado);
    	empleado.agregarSolicitud(solicitud);
    	cliente.agregarSolicitud(solicitud);
    	RepositorioSolicitud.insertarSolicitud(solicitud);
    	JOptionPane.showMessageDialog(this, "La solicitud se realizó con éxito. El empleado " + empleado.getNombres() + " " + empleado.getApellidos() + " atenderá su solicitud.", "Información", JOptionPane.INFORMATION_MESSAGE);
    	dispose();
	}
}
