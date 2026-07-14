package vista;

import javax.swing.JButton;
import javax.swing.JDialog;

import modelo.Cliente;
import modelo.Empleado;
import modelo.Persona;
import repositorio.RepositorioCliente;
import repositorio.RepositorioEmpleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class VentanaActualizarPerfil extends JDialog implements ActionListener, KeyListener {
	
	private Persona persona;

	private static final long serialVersionUID = 1L;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblTelfono;
	private JLabel lblDireccin;
	private JLabel lblCorreoElectrnico;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCorreoElectronico;
	private JLabel lblNewLabel;
	private JTextField txtDni;
	private JLabel lblDni;
	private JButton btnGuardar;
	private JButton btnCancelar;

	public VentanaActualizarPerfil(Persona persona) {
		getContentPane().setBackground(new Color(255, 255, 255));
		this.persona = persona;
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Actualizar perfil");
		setBounds(100, 100, 470, 480);
		getContentPane().setLayout(null);
		{
			lblNombres = new JLabel("Nombres:");
			lblNombres.setForeground(new Color(90, 90, 90));
			lblNombres.setFont(new Font("Arial", Font.BOLD, 13));
			lblNombres.setBounds(50, 155, 60, 16);
			getContentPane().add(lblNombres);
		}
		{
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setForeground(new Color(90, 90, 90));
			lblApellidos.setFont(new Font("Arial", Font.BOLD, 13));
			lblApellidos.setBounds(50, 191, 63, 16);
			getContentPane().add(lblApellidos);
		}
		{
			lblTelfono = new JLabel("Teléfono:");
			lblTelfono.setForeground(new Color(90, 90, 90));
			lblTelfono.setFont(new Font("Arial", Font.BOLD, 13));
			lblTelfono.setBounds(50, 227, 60, 16);
			getContentPane().add(lblTelfono);
		}
		{
			lblDireccin = new JLabel("Dirección:");
			lblDireccin.setForeground(new Color(90, 90, 90));
			lblDireccin.setFont(new Font("Arial", Font.BOLD, 13));
			lblDireccin.setBounds(50, 263, 64, 16);
			getContentPane().add(lblDireccin);
		}
		{
			lblCorreoElectrnico = new JLabel("Correo electrónico:");
			lblCorreoElectrnico.setForeground(new Color(90, 90, 90));
			lblCorreoElectrnico.setFont(new Font("Arial", Font.BOLD, 13));
			lblCorreoElectrnico.setBounds(50, 299, 122, 16);
			getContentPane().add(lblCorreoElectrnico);
		}
		{
			txtNombres = new JTextField();
			txtNombres.addKeyListener(this);
			txtNombres.setText((String) null);
			txtNombres.setForeground(new Color(90, 90, 90));
			txtNombres.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNombres.setColumns(10);
			txtNombres.setBackground(Color.WHITE);
			txtNombres.setBounds(200, 151, 200, 25);
			getContentPane().add(txtNombres);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.addKeyListener(this);
			txtApellidos.setText((String) null);
			txtApellidos.setForeground(new Color(90, 90, 90));
			txtApellidos.setFont(new Font("Arial", Font.PLAIN, 13));
			txtApellidos.setColumns(10);
			txtApellidos.setBackground(Color.WHITE);
			txtApellidos.setBounds(200, 187, 200, 25);
			getContentPane().add(txtApellidos);
		}
		{
			txtTelefono = new JTextField();
			txtTelefono.addKeyListener(this);
			txtTelefono.setText((String) null);
			txtTelefono.setForeground(new Color(90, 90, 90));
			txtTelefono.setFont(new Font("Arial", Font.PLAIN, 13));
			txtTelefono.setColumns(10);
			txtTelefono.setBackground(Color.WHITE);
			txtTelefono.setBounds(200, 223, 200, 25);
			getContentPane().add(txtTelefono);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.setText((String) null);
			txtDireccion.setForeground(new Color(90, 90, 90));
			txtDireccion.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDireccion.setColumns(10);
			txtDireccion.setBackground(Color.WHITE);
			txtDireccion.setBounds(200, 259, 200, 25);
			getContentPane().add(txtDireccion);
		}
		{
			txtCorreoElectronico = new JTextField();
			txtCorreoElectronico.setText((String) null);
			txtCorreoElectronico.setForeground(new Color(90, 90, 90));
			txtCorreoElectronico.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCorreoElectronico.setColumns(10);
			txtCorreoElectronico.setBackground(Color.WHITE);
			txtCorreoElectronico.setBounds(200, 295, 200, 25);
			getContentPane().add(txtCorreoElectronico);
		}
		{
			lblNewLabel = new JLabel("Perfil");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setForeground(new Color(238, 52, 37));
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setBounds(195, 49, 63, 30);
			getContentPane().add(lblNewLabel);
		}
		{
			txtDni = new JTextField();
			txtDni.addKeyListener(this);
			txtDni.setText((String) null);
			txtDni.setForeground(new Color(90, 90, 90));
			txtDni.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDni.setColumns(10);
			txtDni.setBackground(Color.WHITE);
			txtDni.setBounds(200, 115, 200, 25);
			getContentPane().add(txtDni);
		}
		{
			lblDni = new JLabel("DNI:");
			lblDni.setForeground(new Color(90, 90, 90));
			lblDni.setFont(new Font("Arial", Font.BOLD, 13));
			lblDni.setBounds(50, 119, 39, 16);
			getContentPane().add(lblDni);
		}
		{
			btnGuardar = new JButton("Guardar");
			btnGuardar.addActionListener(this);
			btnGuardar.setForeground(new Color(255, 255, 255));
			btnGuardar.setFont(new Font("Arial", Font.BOLD, 13));
			btnGuardar.setBackground(new Color(238, 52, 37));
			btnGuardar.setBounds(50, 358, 150, 35);
			getContentPane().add(btnGuardar);
		}
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(this);
			btnCancelar.setForeground(new Color(90, 90, 90));
			btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCancelar.setBackground(Color.WHITE);
			btnCancelar.setBounds(250, 358, 150, 35);
			getContentPane().add(btnCancelar);
		}
		mostrarDatos();
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
		if (e.getSource() == btnGuardar) {
			do_btnGuardar_actionPerformed(e);
		}
	}
	private void mostrarDatos() {
		txtDni.setText(persona.getDni());
		txtNombres.setText(persona.getNombres());
		txtApellidos.setText(persona.getApellidos());
		txtTelefono.setText(persona.getTelefono());
		txtDireccion.setText(persona.getDireccion());
		txtCorreoElectronico.setText(persona.getCorreo());
	}
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		dispose();
	}
	protected void do_btnGuardar_actionPerformed(ActionEvent e) {
		try {
			String dni = txtDni.getText().trim();
			String nombres = txtNombres.getText().trim();
			String apellidos = txtApellidos.getText().trim();
			String telefono = txtTelefono.getText().trim();
			String direccion = txtDireccion.getText().trim();
			String correo = txtCorreoElectronico.getText().trim();
			if(dni.isEmpty() || 
					nombres.isEmpty() || 
					apellidos.isEmpty() || 
					telefono.isEmpty() ||
					direccion.isEmpty() ||
					correo.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No debe dejar campos vacíos.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if(!persona.getDni().equals(dni) &&
					(RepositorioCliente.consultarClienteDni(dni) != null ||
					RepositorioEmpleado.consultarEmpleadoDni(dni) != null)) {
				JOptionPane.showMessageDialog(this,"El DNI ya está registrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if(dni.length() < 8) {
				JOptionPane.showMessageDialog(this, "El DNI debe tener 8 dígitos.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if(telefono.length() < 9) {
				JOptionPane.showMessageDialog(this, "El teléfono debe tener 9 dígitos.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if(!persona.getCorreo().equals(correo) && 
					(RepositorioCliente.consultarCliente(correo) != null ||
					RepositorioEmpleado.consultarEmpleado(correo) != null)) {
					JOptionPane.showMessageDialog(this,"El correo electrónico ya está registrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
			} else if(!correo.contains("@")) {
				JOptionPane.showMessageDialog(this, "El correo electrónico es inválido.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			persona.setDni(dni);
			persona.setNombres(nombres);
			persona.setApellidos(apellidos);
			persona.setTelefono(telefono);
			persona.setDireccion(direccion);
			persona.setCorreo(correo);
			if(persona.getCorreo().contains("@empleado")) RepositorioEmpleado.actualizarEmpleado((Empleado) persona);
			else RepositorioCliente.actualizarCliente((Cliente) persona);
			JOptionPane.showMessageDialog(this,"Datos actualizados correctamente.");
			dispose();
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "Error: "+error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			mostrarDatos();
		}
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtTelefono) {
			do_txtTelefono_keyTyped(e);
		}
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
	protected void do_txtTelefono_keyTyped(KeyEvent e) {
		char caracteres = e.getKeyChar();
		if(Character.isAlphabetic(caracteres)) {
			e.consume();
			JOptionPane.showMessageDialog(this, "El teléfono debe tener números.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} else if (txtTelefono.getText().length() >= 9) {
	        e.consume();
	        JOptionPane.showMessageDialog(this, "El teléfono debe tener 9 dígitos.", "Información", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
}
