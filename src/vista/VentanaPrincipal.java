package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.Empleado;
import modelo.Persona;
import repositorio.RepositorioCliente;
import repositorio.RepositorioEmpleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIniciarSesion;
	private JTextField txtCorreoElectronico;
	private JLabel lblCorreoElectrnico;
	private JLabel lblNewLabel_1;
	private JButton btnRegistrarse;
	private JPasswordField txtContraseña;
	private JButton btnSalir;
	private JCheckBox chckbxVerContraseña;
	private JLabel lblLogo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		setBackground(new Color(255, 255, 255));
		setTitle("Iniciar sesión");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 470, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			btnIniciarSesion = new JButton("Iniciar Sesión");
			btnIniciarSesion.setForeground(new Color(255, 255, 255));
			btnIniciarSesion.setBackground(new Color(238, 52, 37));
			btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 13));
			btnIniciarSesion.addActionListener(this);
			btnIniciarSesion.setBounds(152, 301, 150, 35);
			contentPane.add(btnIniciarSesion);
		}
		{
			txtCorreoElectronico = new JTextField("carlos.martinez@email.com");
			txtCorreoElectronico.setForeground(new Color(90, 90, 90));
			txtCorreoElectronico.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCorreoElectronico.setBounds(200, 178, 200, 25);
			contentPane.add(txtCorreoElectronico);
			txtCorreoElectronico.setColumns(10);
		}
		{
			lblCorreoElectrnico = new JLabel("Correo electrónico:");
			lblCorreoElectrnico.setForeground(new Color(90, 90, 90));
			lblCorreoElectrnico.setFont(new Font("Arial", Font.BOLD, 13));
			lblCorreoElectrnico.setBackground(new Color(255, 255, 255));
			lblCorreoElectrnico.setBounds(50, 182, 122, 16);
			contentPane.add(lblCorreoElectrnico);
		}
		{
			lblNewLabel_1 = new JLabel("Contraseña:");
			lblNewLabel_1.setForeground(new Color(90, 90, 90));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
			lblNewLabel_1.setBackground(new Color(255, 255, 255));
			lblNewLabel_1.setBounds(50, 221, 76, 16);
			contentPane.add(lblNewLabel_1);
		}
		{
			btnRegistrarse = new JButton("Registrarse");
			btnRegistrarse.setForeground(new Color(90, 90, 90));
			btnRegistrarse.setBackground(new Color(230, 230, 230));
			btnRegistrarse.setFont(new Font("Arial", Font.BOLD, 13));
			btnRegistrarse.addActionListener(this);
			btnRegistrarse.setBounds(50, 356, 150, 35);
			contentPane.add(btnRegistrarse);
		}
		{
			txtContraseña = new JPasswordField("ClaveEjemplo#1");
			txtContraseña.setForeground(new Color(90, 90, 90));
			txtContraseña.setFont(new Font("Arial", Font.PLAIN, 13));
			txtContraseña.setBounds(200, 217, 200, 25);
			txtContraseña.setEchoChar('●');
			contentPane.add(txtContraseña);
		}
		{
			btnSalir = new JButton("Salir");
			btnSalir.setForeground(new Color(90, 90, 90));
			btnSalir.setBackground(new Color(255, 255, 255));
			btnSalir.setFont(new Font("Arial", Font.BOLD, 13));
			btnSalir.addActionListener(this);
			btnSalir.setBounds(250, 356, 150, 35);
			contentPane.add(btnSalir);
		}
		{
			chckbxVerContraseña = new JCheckBox("Ver contraseña");
			chckbxVerContraseña.setForeground(new Color(90, 90, 90));
			chckbxVerContraseña.setFont(new Font("Arial", Font.PLAIN, 11));
			chckbxVerContraseña.setBackground(new Color(255, 255, 255));
			chckbxVerContraseña.addActionListener(this);
			chckbxVerContraseña.setBounds(200, 249, 101, 21);
			contentPane.add(chckbxVerContraseña);
		}
		{
			lblLogo = new JLabel("");
			lblLogo.setBackground(new Color(255, 255, 255));
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagen/logo.jpg")));
			lblLogo.setBounds(187, 50, 100, 100);
			contentPane.add(lblLogo);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chckbxVerContraseña) {
			do_chckbxVerContraseña_actionPerformed(e);
		}
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
		if (e.getSource() == btnRegistrarse) {
			do_btnRegistrarse_actionPerformed(e);
		}
		if (e.getSource() == btnIniciarSesion) {
			do_btnIniciarSesion_actionPerformed(e);
		}
	}
	private int intentos = 0;
	protected void do_btnIniciarSesion_actionPerformed(ActionEvent e) {
		try {
			LocalDateTime fechaHoraBloqueo;
			String correoElectronico = txtCorreoElectronico.getText().trim();
			char[] contraseña = txtContraseña.getPassword();
			if(correoElectronico.isEmpty() || 
					contraseña.length == 0) {
				JOptionPane.showMessageDialog(this, "No debe dejar campos vacíos.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else if(!correoElectronico.contains("@")) {
				JOptionPane.showMessageDialog(this, "El correo electrónico inválido.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			Persona persona = null;
			if(correoElectronico.contains("@empleado.com")) persona = RepositorioEmpleado.consultarEmpleado(correoElectronico);
			else persona = RepositorioCliente.consultarCliente(correoElectronico);
			if(persona == null) {
				JOptionPane.showMessageDialog(this,  "El correo electrónico no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			fechaHoraBloqueo = persona.getFechaHoraBloqueo();
			if(fechaHoraBloqueo != null) {
				Duration duration = Duration.between(persona.getFechaHoraBloqueo(), LocalDateTime.now());
				if(duration.toMinutes() < 30) {
					JOptionPane.showMessageDialog(this,  "Su cuenta se encuentra bloqueada temporalmente. Vuelva a intentarlo después de " + (30 - duration.toMinutes()) + " minutos.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
			if(correoElectronico.contains("@empleado.com")) persona = RepositorioEmpleado.consultarEmpleado(correoElectronico, new String(contraseña));
			else persona = RepositorioCliente.consultarCliente(correoElectronico, new String(contraseña));
			if(persona == null) {
				intentos++;
				String mensaje = "Correo electrónico o contraseña incorrectos. ";
				if(intentos < 5) {
					JOptionPane.showMessageDialog(this, mensaje + "Intente nuevamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
				} else {
					fechaHoraBloqueo = LocalDateTime.now();
					if(correoElectronico.contains("@empleado.com")) {
						persona = RepositorioEmpleado.consultarEmpleado(correoElectronico);
						persona.setFechaHoraBloqueo(fechaHoraBloqueo);
						RepositorioEmpleado.actualizarEmpleado((Empleado) persona);
					}
					else {
						persona = RepositorioCliente.consultarCliente(correoElectronico);
						persona.setFechaHoraBloqueo(fechaHoraBloqueo);
						RepositorioCliente.actualizarCliente(((Cliente) persona));
					}
					JOptionPane.showMessageDialog(this,  mensaje + "Vuelva a intentarlo después de 30 minutos.", "Información", JOptionPane.INFORMATION_MESSAGE);
				}
				return;
			}
			if(correoElectronico.contains("@empleado.com")) {
				VentanaMenuEmpleado ventanaMenuEmpleado = new VentanaMenuEmpleado(this, (Empleado) persona);
				ventanaMenuEmpleado.setVisible(true);
			}else {
				VentanaMenu menu = new VentanaMenu(this, (Cliente) persona);
				menu.setVisible(true);
			}
			limpiarCampos();
			dispose();
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "Error: "+error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void do_btnRegistrarse_actionPerformed(ActionEvent e) {
		VentanaRegistrar ventanaRegistrar = new VentanaRegistrar(this);
		ventanaRegistrar.setVisible(true);
		limpiarCampos();
	}
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	private void limpiarCampos() {
		txtCorreoElectronico.setText("");
		txtContraseña.setText("");
	}
	protected void do_chckbxVerContraseña_actionPerformed(ActionEvent e) {
		if(chckbxVerContraseña.isSelected()) txtContraseña.setEchoChar((char) 0);
		else txtContraseña.setEchoChar('●');
	}
}
