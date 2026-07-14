package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Empleado;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaMenuEmpleado extends JFrame implements ActionListener {

	private Empleado empleado;
	private VentanaPrincipal ventanaPrincipal;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmVerPerfil;
	private JMenuItem mntmActualizarPerfil;
	private JMenuItem mntmCambiarContraseña;
	private JMenuItem mntmCerrarSesion;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmVerSolicitudes;
	private JMenu mnNewMenu_2;
	private JMenuItem mntmVerClientes;
	private JLabel lblNewLabel;
	private JLabel lblNombresEmpleado;
	private JLabel lblApellidosEmpleado;
	private JMenu mnProgramadores;
	private JMenuItem mntmVerProgramadores;

	public VentanaMenuEmpleado(VentanaPrincipal ventanaPrincipal, Empleado empleado) {
		setTitle("Menú");
		this.empleado = empleado;
		this.ventanaPrincipal = ventanaPrincipal;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 680, 330);
		{
			menuBar = new JMenuBar();
			menuBar.setBackground(new Color(255, 255, 255));
			setJMenuBar(menuBar);
			{
				mnNewMenu = new JMenu("Perfil");
				mnNewMenu.setBackground(new Color(255, 255, 255));
				mnNewMenu.setFont(new Font("Arial", Font.BOLD, 14));
				menuBar.add(mnNewMenu);
				{
					mntmVerPerfil = new JMenuItem("Ver");
					mntmVerPerfil.setBackground(new Color(255, 255, 255));
					mntmVerPerfil.setForeground(new Color(90, 90, 90));
					mntmVerPerfil.setFont(new Font("Arial", Font.BOLD, 13));
					mntmVerPerfil.addActionListener(this);
					mnNewMenu.add(mntmVerPerfil);
				}
				{
					mntmActualizarPerfil = new JMenuItem("Actualizar");
					mntmActualizarPerfil.setBackground(new Color(255, 255, 255));
					mntmActualizarPerfil.setForeground(new Color(90, 90, 90));
					mntmActualizarPerfil.setFont(new Font("Arial", Font.BOLD, 13));
					mntmActualizarPerfil.addActionListener(this);
					mnNewMenu.add(mntmActualizarPerfil);
				}
				{
					mntmCambiarContraseña = new JMenuItem("Cambiar contraseña");
					mntmCambiarContraseña.setBackground(new Color(255, 255, 255));
					mntmCambiarContraseña.setForeground(new Color(90, 90, 90));
					mntmCambiarContraseña.setFont(new Font("Arial", Font.BOLD, 13));
					mntmCambiarContraseña.addActionListener(this);
					mnNewMenu.add(mntmCambiarContraseña);
				}
				{
					mntmCerrarSesion = new JMenuItem("Cerrar sesión");
					mntmCerrarSesion.setBackground(new Color(255, 255, 255));
					mntmCerrarSesion.setForeground(new Color(90, 90, 90));
					mntmCerrarSesion.setFont(new Font("Arial", Font.BOLD, 13));
					mntmCerrarSesion.addActionListener(this);
					mnNewMenu.add(mntmCerrarSesion);
				}
			}
			{
				mnNewMenu_1 = new JMenu("Solicitudes");
				mnNewMenu_1.setBackground(new Color(255, 255, 255));
				mnNewMenu_1.setFont(new Font("Arial", Font.BOLD, 14));
				menuBar.add(mnNewMenu_1);
				{
					mntmVerSolicitudes = new JMenuItem("Ver");
					mntmVerSolicitudes.setForeground(new Color(90, 90, 90));
					mntmVerSolicitudes.setFont(new Font("Arial", Font.BOLD, 13));
					mntmVerSolicitudes.setBackground(new Color(255, 255, 255));
					mntmVerSolicitudes.addActionListener(this);
					mnNewMenu_1.add(mntmVerSolicitudes);
				}
			}
			{
				mnNewMenu_2 = new JMenu("Clientes");
				mnNewMenu_2.setBackground(new Color(255, 255, 255));
				mnNewMenu_2.setFont(new Font("Arial", Font.BOLD, 14));
				menuBar.add(mnNewMenu_2);
				{
					mntmVerClientes = new JMenuItem("Ver");
					mntmVerClientes.setBackground(new Color(255, 255, 255));
					mntmVerClientes.setForeground(new Color(90, 90, 90));
					mntmVerClientes.setFont(new Font("Arial", Font.BOLD, 13));
					mntmVerClientes.addActionListener(this);
					mnNewMenu_2.add(mntmVerClientes);
				}
			}
			{
				mnProgramadores = new JMenu("Programadores");
				mnProgramadores.setForeground(new Color(90, 90, 90));
				mnProgramadores.setFont(new Font("Arial", Font.BOLD, 14));
				mnProgramadores.setBackground(Color.WHITE);
				menuBar.add(mnProgramadores);
				{
					mntmVerProgramadores = new JMenuItem("Ver");
					mntmVerProgramadores.addActionListener(this);
					mntmVerProgramadores.setForeground(new Color(90, 90, 90));
					mntmVerProgramadores.setFont(new Font("Arial", Font.BOLD, 13));
					mntmVerProgramadores.setBackground(Color.WHITE);
					mnProgramadores.add(mntmVerProgramadores);
				}
			}
		}
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 52, 37));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("Bienvenido/a,");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setBounds(53, 54, 254, 47);
			contentPane.add(lblNewLabel);
		}
		{
			lblNombresEmpleado = new JLabel(empleado.getNombres());
			lblNombresEmpleado.setForeground(Color.WHITE);
			lblNombresEmpleado.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
			lblNombresEmpleado.setBounds(53, 112, 560, 47);
			contentPane.add(lblNombresEmpleado);
		}
		{
			lblApellidosEmpleado = new JLabel(empleado.getApellidos());
			lblApellidosEmpleado.setForeground(Color.WHITE);
			lblApellidosEmpleado.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
			lblApellidosEmpleado.setBounds(53, 170, 560, 47);
			contentPane.add(lblApellidosEmpleado);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmVerProgramadores) {
			do_mntmVerProgramadores_actionPerformed(e);
		}
		if (e.getSource() == mntmVerClientes) {
			do_mntmVerClientes_actionPerformed(e);
		}
		if (e.getSource() == mntmVerSolicitudes) {
			do_mntmVerSolicitudes_actionPerformed(e);
		}
		if (e.getSource() == mntmCerrarSesion) {
			do_mntmCerrarSesion_actionPerformed(e);
		}
		if (e.getSource() == mntmCambiarContraseña) {
			do_mntmCambiarContraseña_actionPerformed(e);
		}
		if (e.getSource() == mntmActualizarPerfil) {
			do_mntmActualizarPerfil_actionPerformed(e);
		}
		if (e.getSource() == mntmVerPerfil) {
			do_mntmVerPerfil_actionPerformed(e);
		}
	}
	protected void do_mntmVerPerfil_actionPerformed(ActionEvent e) {
		VentanaVerPerfil ventanaVerPerfil = new VentanaVerPerfil(empleado);
		ventanaVerPerfil.setVisible(true);
	}
	protected void do_mntmActualizarPerfil_actionPerformed(ActionEvent e) {
		VentanaActualizarPerfil ventanaActualizarPerfil = new VentanaActualizarPerfil(empleado);
		ventanaActualizarPerfil.setVisible(true);
	}
	protected void do_mntmCambiarContraseña_actionPerformed(ActionEvent e) {
		VentanaCambiarContraseña ventanaCambiarContraseña = new VentanaCambiarContraseña(empleado);
		ventanaCambiarContraseña.setVisible(true);
	}
	protected void do_mntmCerrarSesion_actionPerformed(ActionEvent e) {
		ventanaPrincipal.setVisible(true);
		dispose();
	}
	protected void do_mntmVerSolicitudes_actionPerformed(ActionEvent e) {
		VentanaVerSolicitudes verSolicitudes = new VentanaVerSolicitudes(empleado);
		verSolicitudes.setVisible(true);
	}
	protected void do_mntmVerClientes_actionPerformed(ActionEvent e) {
		VentanaVerClientes ventanaVerClientes = new VentanaVerClientes();
		ventanaVerClientes.setVisible(true);
	}
	protected void do_mntmVerProgramadores_actionPerformed(ActionEvent e) {
		VentanaProgramadores ventanaProgramadores = new VentanaProgramadores();
		ventanaProgramadores.setVisible(true);
	}
}
