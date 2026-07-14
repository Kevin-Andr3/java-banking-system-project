package vista;
import repositorio.RepositorioSolicitud;
import repositorio.RepositorioTarjeta;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Cuenta;
import modelo.Empleado;
import modelo.Persona;
import modelo.Solicitud;
import modelo.Tarjeta;
import repositorio.RepositorioCuenta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaVerSolicitudes extends JDialog implements ActionListener {
	
	private Persona persona;

	private static final long serialVersionUID = 1L;
	private JLabel lblSolicitudes;
	private JScrollPane scrollPane;
	private JButton btnCerrar;
	private JButton btnVerCliente;
	private JTable tableSolicitudes;
	private JButton btnAceptar;
	private JButton btnRechazar;

	public VentanaVerSolicitudes(Persona persona) {
		this.persona = persona;
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setTitle("Ver solicitudes");
		setBounds(100, 100, 820, 530);
		getContentPane().setLayout(null);
		{
			lblSolicitudes = new JLabel("Solicitudes");
			lblSolicitudes.setForeground(new Color(238, 52, 37));
			lblSolicitudes.setHorizontalAlignment(SwingConstants.CENTER);
			lblSolicitudes.setFont(new Font("Arial", Font.BOLD, 25));
			lblSolicitudes.setBounds(340, 46, 133, 30);
			getContentPane().add(lblSolicitudes);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(50, 120, 700, 250);
			getContentPane().add(scrollPane);
			{
				tableSolicitudes = new JTable();
				tableSolicitudes.setFillsViewportHeight(true);
				tableSolicitudes.setForeground(new Color(90, 90, 90));
				tableSolicitudes.setBackground(new Color(255, 255, 255));
				tableSolicitudes.setFont(new Font("Arial", Font.PLAIN, 13));
				tableSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tableSolicitudes);
			}
		}
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setBackground(new Color(255, 255, 255));
			btnCerrar.setForeground(new Color(90, 90, 90));
			btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(600, 406, 150, 35);
			getContentPane().add(btnCerrar);
		}
		if(persona.getCorreo().contains("@empleado")){
			{
				btnVerCliente = new JButton("Ver perfil del cliente");
				btnVerCliente.setBackground(new Color(238, 52, 37));
				btnVerCliente.setForeground(new Color(255, 255, 255));
				btnVerCliente.setFont(new Font("Arial", Font.BOLD, 13));
				btnVerCliente.addActionListener(this);
				btnVerCliente.setBounds(50, 406, 200, 35);
				getContentPane().add(btnVerCliente);
			}
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.setBackground(new Color(230, 230, 230));
				btnAceptar.setForeground(new Color(90, 90, 90));
				btnAceptar.setFont(new Font("Arial", Font.BOLD, 13));
				btnAceptar.addActionListener(this);
				btnAceptar.setBounds(260, 406, 150, 35);
				getContentPane().add(btnAceptar);
			}
			{
				btnRechazar = new JButton("Rechazar");
				btnRechazar.setBackground(new Color(230, 230, 230));
				btnRechazar.setForeground(new Color(90, 90, 90));
				btnRechazar.setFont(new Font("Arial", Font.BOLD, 13));
				btnRechazar.addActionListener(this);
				btnRechazar.setBounds(420, 406, 150, 35);
				getContentPane().add(btnRechazar);
			}
		}
		llenarTabla();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRechazar) {
			do_btnRechazar_actionPerformed(e);
		}
		if (e.getSource() == btnAceptar) {
			do_btnAceptar_actionPerformed(e);
		}
		if (e.getSource() == btnVerCliente) {
			do_btnVerCliente_actionPerformed(e);
		}
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
	}
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
	protected void do_btnVerCliente_actionPerformed(ActionEvent e) {
		Solicitud solicitud = obtenerSolicitud();
		if(solicitud == null) return;
		VentanaVerDetallesCliente ventanaVerDetallesCliente = new VentanaVerDetallesCliente(solicitud.getCliente());
		ventanaVerDetallesCliente.setVisible(true);
	}
	protected void do_btnAceptar_actionPerformed(ActionEvent e) {
		try {
			Solicitud solicitud = obtenerSolicitud();
			if(solicitud == null) return;
			else if(solicitud.getEstado().equals("aceptada") || solicitud.getEstado().equals("rechazada")) {
				JOptionPane.showMessageDialog(this, "La solicitud ya fue resuelta.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			VentanaAutenticar ventanaAutenticar = new VentanaAutenticar(persona);
			ventanaAutenticar.setVisible(true);
			if(ventanaAutenticar.getEstadoAutenticacion()) {
				Cliente cliente = solicitud.getCliente();
				if(solicitud.getAsunto().contains("cuenta")) {
					String asunto = solicitud.getAsunto();
					String moneda = asunto.split(" en ")[1];
					Cuenta cuenta = null;
					if(solicitud.getAsunto().contains("corriente")) cuenta = new Cuenta("corriente", moneda, cliente);
					else if(solicitud.getAsunto().contains("ahorro")) cuenta = new Cuenta("ahorro", moneda, cliente);
					cliente.agregarCuenta(cuenta);
					RepositorioCuenta.insertarCuenta(cuenta);
				}
				else if (solicitud.getAsunto().contains("tarjeta")) {
					Tarjeta tarjeta = null;
					if(solicitud.getAsunto().contains("débito")) tarjeta = new Tarjeta("débito", cliente);
					else if(solicitud.getAsunto().contains("crédito")) tarjeta = new Tarjeta("crédito", cliente);
					cliente.agregarTarjeta(tarjeta);
					RepositorioTarjeta.insertarTarjeta(tarjeta);
				}
				solicitud.setEstado("aceptada");
				solicitud.setFechaResolucion(LocalDate.now());
				RepositorioSolicitud.actualizarSolicitud(solicitud);
				llenarTabla();
    		} else {
    			JOptionPane.showMessageDialog(this, "La autenticación falló.", "Información", JOptionPane.INFORMATION_MESSAGE);
    		}
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "Ocurrio un error, vuelva a intentarlo más tarde.", "Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(error);
		}
	}
	protected void do_btnRechazar_actionPerformed(ActionEvent e) {
		try {
			Solicitud solicitud = obtenerSolicitud();
			if(solicitud == null) return;
			else if(solicitud.getEstado().equals("aceptada") || solicitud.getEstado().equals("rechazada")) {
				JOptionPane.showMessageDialog(this, "La solicitud ya fue resuelta.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			VentanaAutenticar ventanaAutenticar = new VentanaAutenticar(persona);
			ventanaAutenticar.setVisible(true);
			if(ventanaAutenticar.getEstadoAutenticacion()) {
				solicitud.setEstado("rechazada");
				solicitud.setFechaResolucion(LocalDate.now());
				RepositorioSolicitud.actualizarSolicitud(solicitud);
				llenarTabla();
			} else {
    			JOptionPane.showMessageDialog(this, "La autenticación falló.", "Información", JOptionPane.INFORMATION_MESSAGE);
    		}
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "Ocurrio un error, vuelva a intentarlo más tarde.", "Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(error);
		}
	}
	private Solicitud obtenerSolicitud() {
		int posicionFilaSeleccionada = tableSolicitudes.getSelectedRow();
		if (posicionFilaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona una solicitud.", "Información", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		String idSolicitud = tableSolicitudes.getValueAt(posicionFilaSeleccionada, 0).toString();
		Solicitud solicitud = null;
		if(persona.getCorreo().contains("@empleado")) {
			solicitud = ((Empleado) persona).buscarSolicitud(idSolicitud);
		} else {
			solicitud = ((Empleado) persona).buscarSolicitud(idSolicitud);
		}
		if (solicitud == null) {
			JOptionPane.showMessageDialog(this, "La solicitud seleccionada no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		return solicitud;
	}
	private void llenarTabla() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
		if(persona.getCorreo().contains("@empleado")) {
			solicitudes = RepositorioSolicitud.consultarSolicitudEmpleado(persona.getIdPersona());
			((Empleado) persona).setSolicitudes(solicitudes);
		} else {
			solicitudes = RepositorioSolicitud.consultarSolicitudCliente(persona.getIdPersona());
			((Cliente) persona).setSolicitudes(solicitudes);
		}
		defaultTableModel.addColumn("Id");
		defaultTableModel.addColumn("Asunto");
		defaultTableModel.addColumn("Estado");
		defaultTableModel.addColumn("Fecha de creación");
		defaultTableModel.addColumn("Fecha de resolución");
		if(!persona.getCorreo().contains("@empleado")) defaultTableModel.addColumn("Empleado");
		defaultTableModel.setRowCount(solicitudes.size());
		Iterator<Solicitud> iterator = solicitudes.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			Solicitud solicitud = iterator.next();
			defaultTableModel.setValueAt(solicitud.getIdSolicitud(), i, 0);
			defaultTableModel.setValueAt(solicitud.getAsunto(), i, 1);
			defaultTableModel.setValueAt(solicitud.getEstado(), i, 2);
			defaultTableModel.setValueAt(solicitud.getFechaCreacionFormateada(), i, 3);
			defaultTableModel.setValueAt(solicitud.getFechaResolucionFormateada(), i, 4);
			if(!persona.getCorreo().contains("@empleado")) {
				Empleado empleado = solicitud.getEmpleado();
				defaultTableModel.setValueAt(empleado.getNombres() + " " + empleado.getApellidos(), i, 5);
			}
			i++;
		}
		tableSolicitudes.setModel(defaultTableModel);
	}
}
