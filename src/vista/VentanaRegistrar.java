package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.Empleado;
import repositorio.RepositorioCliente;
import repositorio.RepositorioEmpleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class VentanaRegistrar extends JDialog implements ActionListener, KeyListener {

	private VentanaPrincipal ventanaPrincipal;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtNombres;
	private JLabel lblNewLabel_2;
	private JTextField txtApellidos;
	private JLabel lblNewLabel_3;
	private JTextField txtTelefono;
	private JLabel lblNewLabel_4;
	private JTextField txtDireccion;
	private JTextField txtCorreoElectronico;
	private JLabel lblCorreoElectrnico;
	private JLabel lblNewLabel_5;
	private JPasswordField txtContraseña;
	private JButton btnRegistrarse;
	private JButton btnCancelar;
	private JCheckBox chckbxVerContraseña;
	private JLabel lblNewLabel_6;
	private JTextField txtDNI;

	public VentanaRegistrar(VentanaPrincipal ventanaPrincipal) {
		setBackground(new Color(255, 255, 255));
		this.ventanaPrincipal = ventanaPrincipal;
		setTitle("Registrar");
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 470, 530);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNewLabel = new JLabel("Registrarse");
			lblNewLabel.setForeground(new Color(238, 52, 37));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
			lblNewLabel.setBounds(158, 50, 138, 30);
			contentPanel.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("Nombres:");
			lblNewLabel_1.setForeground(new Color(90, 90, 90));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
			lblNewLabel_1.setBounds(50, 156, 60, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtNombres = new JTextField();
			txtNombres.addKeyListener(this);
			txtNombres.setForeground(new Color(90, 90, 90));
			txtNombres.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNombres.setBounds(200, 152, 200, 25);
			contentPanel.add(txtNombres);
			txtNombres.setColumns(10);
		}
		{
			lblNewLabel_2 = new JLabel("Apellidos:");
			lblNewLabel_2.setForeground(new Color(90, 90, 90));
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
			lblNewLabel_2.setBounds(50, 192, 63, 16);
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.addKeyListener(this);
			txtApellidos.setForeground(new Color(90, 90, 90));
			txtApellidos.setFont(new Font("Arial", Font.PLAIN, 13));
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(200, 188, 200, 25);
			contentPanel.add(txtApellidos);
		}
		{
			lblNewLabel_3 = new JLabel("Teléfono:");
			lblNewLabel_3.setForeground(new Color(90, 90, 90));
			lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 13));
			lblNewLabel_3.setBounds(50, 228, 60, 16);
			contentPanel.add(lblNewLabel_3);
		}
		{
			txtTelefono = new JTextField();
			txtTelefono.addKeyListener(this);
			txtTelefono.setForeground(new Color(90, 90, 90));
			txtTelefono.setFont(new Font("Arial", Font.PLAIN, 13));
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(200, 224, 200, 25);
			contentPanel.add(txtTelefono);
		}
		{
			lblNewLabel_4 = new JLabel("Dirección:");
			lblNewLabel_4.setForeground(new Color(90, 90, 90));
			lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 13));
			lblNewLabel_4.setBounds(50, 264, 64, 16);
			contentPanel.add(lblNewLabel_4);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.setForeground(new Color(90, 90, 90));
			txtDireccion.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(200, 260, 200, 25);
			contentPanel.add(txtDireccion);
		}
		{
			txtCorreoElectronico = new JTextField();
			txtCorreoElectronico.setForeground(new Color(90, 90, 90));
			txtCorreoElectronico.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCorreoElectronico.setColumns(10);
			txtCorreoElectronico.setBounds(200, 296, 200, 25);
			contentPanel.add(txtCorreoElectronico);
		}
		{
			lblCorreoElectrnico = new JLabel("Correo electrónico:");
			lblCorreoElectrnico.setForeground(new Color(90, 90, 90));
			lblCorreoElectrnico.setFont(new Font("Arial", Font.BOLD, 13));
			lblCorreoElectrnico.setBounds(50, 300, 122, 16);
			contentPanel.add(lblCorreoElectrnico);
		}
		{
			lblNewLabel_5 = new JLabel("Contraseña:");
			lblNewLabel_5.setForeground(new Color(90, 90, 90));
			lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 13));
			lblNewLabel_5.setBounds(50, 336, 76, 16);
			contentPanel.add(lblNewLabel_5);
		}
		{
			txtContraseña = new JPasswordField();
			txtContraseña.setForeground(new Color(90, 90, 90));
			txtContraseña.setFont(new Font("Arial", Font.PLAIN, 13));
			txtContraseña.setBounds(200, 332, 200, 25);
			txtContraseña.setEchoChar('●');
			contentPanel.add(txtContraseña);
		}
		{
			btnRegistrarse = new JButton("Registrarse");
			btnRegistrarse.setForeground(new Color(255, 255, 255));
			btnRegistrarse.setBackground(new Color(238, 52, 37));
			btnRegistrarse.setFont(new Font("Arial", Font.BOLD, 13));
			btnRegistrarse.addActionListener(this);
			btnRegistrarse.setBounds(50, 411, 150, 35);
			contentPanel.add(btnRegistrarse);
		}
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(new Color(255, 255, 255));
			btnCancelar.setForeground(new Color(90, 90, 90));
			btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCancelar.addActionListener(this);
			btnCancelar.setBounds(250, 411, 150, 35);
			contentPanel.add(btnCancelar);
		}
		{
			chckbxVerContraseña = new JCheckBox("Ver contraseña");
			chckbxVerContraseña.setForeground(new Color(90, 90, 90));
			chckbxVerContraseña.setFont(new Font("Arial", Font.PLAIN, 11));
			chckbxVerContraseña.setBackground(new Color(255, 255, 255));
			chckbxVerContraseña.addActionListener(this);
			chckbxVerContraseña.setBounds(200, 364, 101, 21);
			contentPanel.add(chckbxVerContraseña);
		}
		{
			lblNewLabel_6 = new JLabel("DNI:");
			lblNewLabel_6.setForeground(new Color(90, 90, 90));
			lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 13));
			lblNewLabel_6.setBounds(50, 120, 42, 16);
			contentPanel.add(lblNewLabel_6);
		}
		{
			txtDNI = new JTextField();
			txtDNI.addKeyListener(this);
			txtDNI.setForeground(new Color(90, 90, 90));
			txtDNI.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDNI.setColumns(10);
			txtDNI.setBounds(200, 116, 200, 25);
			contentPanel.add(txtDNI);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chckbxVerContraseña) {
			do_chckbxVerContraseña_actionPerformed(e);
		}
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
		if (e.getSource() == btnRegistrarse) {
			do_btnRegistrarse_actionPerformed(e);
		}
	}
	protected void do_btnRegistrarse_actionPerformed(ActionEvent e) {
		try {
			String dni = txtDNI.getText().trim();
			String nombres = txtNombres.getText().trim();
			String apellidos = txtApellidos.getText().trim();
			String telefono = txtTelefono.getText().trim();
			String direccion = txtDireccion.getText().trim();
			String correoElectronico = txtCorreoElectronico.getText().trim();
			char[] contraseña = txtContraseña.getPassword();
			if(dni.isEmpty() || 
					nombres.isEmpty() || 
					apellidos.isEmpty() || 
					telefono.isEmpty() ||
					direccion.isEmpty() ||
					correoElectronico.isEmpty() || 
					contraseña.length == 0) {
				JOptionPane.showMessageDialog(this, "No debe dejar campos vacíos.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else if(!correoElectronico.contains("@")) {
				JOptionPane.showMessageDialog(this, "El correo electrónico es inválido.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else if(telefono.length() < 9) {
				JOptionPane.showMessageDialog(this, "El teléfono debe tener 9 dígitos.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else if(dni.length() < 8) {
				JOptionPane.showMessageDialog(this, "El DNI debe tener 8 dígitos.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else if(contraseña.length < 8) {
				JOptionPane.showMessageDialog(this, "La contraseña debe tener como mínimo 8 caracteres.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else if(RepositorioEmpleado.consultarEmpleado(correoElectronico) != null ||
					RepositorioCliente.consultarCliente(correoElectronico) != null) {
				JOptionPane.showMessageDialog(this, "Correo electrónico ya registrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else if(RepositorioEmpleado.consultarEmpleadoDni(dni) != null ||
					RepositorioCliente.consultarClienteDni(dni) != null) {
				JOptionPane.showMessageDialog(this, "DNI ya registrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(correoElectronico.contains("@empleado")) {
				Empleado empleado= new Empleado(dni, nombres, apellidos, telefono, direccion, correoElectronico, new String(contraseña));
				RepositorioEmpleado.insertarEmpleado(empleado);
				VentanaMenuEmpleado ventanaMenuEmpleado = new VentanaMenuEmpleado(ventanaPrincipal, empleado);
				ventanaMenuEmpleado.setVisible(true);
			}else {
				Cliente cliente = new Cliente(dni, nombres, apellidos, telefono, direccion, correoElectronico, new String(contraseña));
				RepositorioCliente.insertarCliente(cliente);
				VentanaMenu menu = new VentanaMenu(ventanaPrincipal, cliente);
				menu.setVisible(true);
			}
			limpiarCampos();
			ventanaPrincipal.dispose();
			dispose();
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "Error: "+error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void limpiarCampos() {
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtCorreoElectronico.setText("");
		txtContraseña.setText("");
	}
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		limpiarCampos();
		dispose();
	}
	protected void do_chckbxVerContraseña_actionPerformed(ActionEvent e) {
		if(chckbxVerContraseña.isSelected()) txtContraseña.setEchoChar((char) 0);
		else txtContraseña.setEchoChar('●');
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
		if (e.getSource() == txtDNI) {
			do_txtDNI_keyTyped(e);
		}
	}
	protected void do_txtDNI_keyTyped(KeyEvent e) {
		char caracter = e.getKeyChar();
		if(Character.isAlphabetic(caracter)) {
			e.consume();
			JOptionPane.showMessageDialog(this, "El DNI debe tener números.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} else if (txtDNI.getText().length() >= 8) {
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
