package vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

import modelo.Persona;
import java.awt.Color;

public class VentanaVerPerfil extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnCerrar;
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
	private Persona persona;
	private JTextField txtDni;
	private JLabel lblDni;

	public VentanaVerPerfil(Persona persona) {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		this.persona = persona;
		setTitle("Ver perfil");
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 470, 480);
		getContentPane().setLayout(null);
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setForeground(new Color(90, 90, 90));
			btnCerrar.setBackground(new Color(255, 255, 255));
			btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(152, 359, 150, 35);
			getContentPane().add(btnCerrar);
		}
		{
			lblNombres = new JLabel("Nombres:");
			lblNombres.setForeground(new Color(90, 90, 90));
			lblNombres.setFont(new Font("Arial", Font.BOLD, 13));
			lblNombres.setBounds(50, 156, 60, 16);
			getContentPane().add(lblNombres);
		}
		{
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setForeground(new Color(90, 90, 90));
			lblApellidos.setFont(new Font("Arial", Font.BOLD, 13));
			lblApellidos.setBounds(50, 192, 63, 16);
			getContentPane().add(lblApellidos);
		}
		{
			lblTelfono = new JLabel("Teléfono:");
			lblTelfono.setForeground(new Color(90, 90, 90));
			lblTelfono.setFont(new Font("Arial", Font.BOLD, 13));
			lblTelfono.setBounds(50, 228, 60, 16);
			getContentPane().add(lblTelfono);
		}
		{
			lblDireccin = new JLabel("Dirección:");
			lblDireccin.setForeground(new Color(90, 90, 90));
			lblDireccin.setFont(new Font("Arial", Font.BOLD, 13));
			lblDireccin.setBounds(50, 264, 64, 16);
			getContentPane().add(lblDireccin);
		}
		{
			lblCorreoElectrnico = new JLabel("Correo electrónico:");
			lblCorreoElectrnico.setForeground(new Color(90, 90, 90));
			lblCorreoElectrnico.setFont(new Font("Arial", Font.BOLD, 13));
			lblCorreoElectrnico.setBounds(50, 300, 122, 16);
			getContentPane().add(lblCorreoElectrnico);
		}
		{
			txtNombres = new JTextField();
			txtNombres.setBackground(new Color(255, 255, 255));
			txtNombres.setForeground(new Color(90, 90, 90));
			txtNombres.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNombres.setEditable(false);
			txtNombres.setBounds(200, 152, 200, 25);
			getContentPane().add(txtNombres);
			txtNombres.setColumns(10);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.setBackground(new Color(255, 255, 255));
			txtApellidos.setForeground(new Color(90, 90, 90));
			txtApellidos.setFont(new Font("Arial", Font.PLAIN, 13));
			txtApellidos.setEditable(false);
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(200, 188, 200, 25);
			getContentPane().add(txtApellidos);
		}
		{
			txtTelefono = new JTextField();
			txtTelefono.setBackground(new Color(255, 255, 255));
			txtTelefono.setForeground(new Color(90, 90, 90));
			txtTelefono.setFont(new Font("Arial", Font.PLAIN, 13));
			txtTelefono.setEditable(false);
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(200, 224, 200, 25);
			getContentPane().add(txtTelefono);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.setBackground(new Color(255, 255, 255));
			txtDireccion.setForeground(new Color(90, 90, 90));
			txtDireccion.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDireccion.setEditable(false);
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(200, 260, 200, 25);
			getContentPane().add(txtDireccion);
		}
		{
			txtCorreoElectronico = new JTextField();
			txtCorreoElectronico.setBackground(new Color(255, 255, 255));
			txtCorreoElectronico.setForeground(new Color(90, 90, 90));
			txtCorreoElectronico.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCorreoElectronico.setEditable(false);
			txtCorreoElectronico.setColumns(10);
			txtCorreoElectronico.setBounds(200, 296, 200, 25);
			getContentPane().add(txtCorreoElectronico);
		}
		{
			lblNewLabel = new JLabel("Perfil");
			lblNewLabel.setBackground(new Color(255, 255, 255));
			lblNewLabel.setForeground(new Color(238, 52, 37));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
			lblNewLabel.setBounds(195, 50, 63, 30);
			getContentPane().add(lblNewLabel);
		}
		{
			txtDni = new JTextField();
			txtDni.setBackground(new Color(255, 255, 255));
			txtDni.setForeground(new Color(90, 90, 90));
			txtDni.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDni.setEditable(false);
			txtDni.setText((String) null);
			txtDni.setColumns(10);
			txtDni.setBounds(200, 116, 200, 25);
			getContentPane().add(txtDni);
		}
		{
			lblDni = new JLabel("DNI:");
			lblDni.setForeground(new Color(90, 90, 90));
			lblDni.setFont(new Font("Arial", Font.BOLD, 13));
			lblDni.setBounds(50, 120, 26, 16);
			getContentPane().add(lblDni);
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
		txtDni.setText(persona.getDni());
		txtNombres.setText(persona.getNombres());
		txtApellidos.setText(persona.getApellidos());
		txtTelefono.setText(persona.getTelefono());
		txtDireccion.setText(persona.getDireccion());
		txtCorreoElectronico.setText(persona.getCorreo());		
	}
}
