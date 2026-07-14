package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class VentanaMenu extends JFrame implements ActionListener {

	private VentanaPrincipal ventanaPrincipal;
	private Cliente cliente;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmActualizarPerfil;
	private JMenuItem mntmVerPerfil;
	private JMenuItem mntmCerrarSesion;
	private JLabel lblNewLabel;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmVerCuentasBancarias;
	private JMenu mnNewMenu_2;
	private JMenuItem mntmVerTarjetas;
	private JMenuItem mntmCambiarContraseña;
	private JMenuItem mntmVerTransacciones;
	private JMenuItem mntmSolicitarApertura;
	private JMenuItem mntmSolicitarEmision;
	private JMenuItem mntmTransferir;
	private JMenuItem mntmPagar;
	private JMenuItem mntmRetirar;
	private JMenuItem mntmDepositar;
	private JLabel lblNombresCliente;
	private JLabel lblApellidosCliente;
	private JMenu mnSolicitudes;
	private JMenuItem mntmVerSolicitudes;
	private JMenu mnProgramadores;
	private JMenuItem mntmVerProgramadores;

	public VentanaMenu(VentanaPrincipal ventanaPrincipal, Cliente cliente) {
		setBackground(new Color(255, 255, 255));
		this.ventanaPrincipal = ventanaPrincipal;
		this.cliente = cliente;
		
		setTitle("Menú");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 680, 330);
		{
			menuBar = new JMenuBar();
			menuBar.setBackground(new Color(255, 255, 255));
			setJMenuBar(menuBar);
			{
				mnNewMenu = new JMenu("Perfil");
				mnNewMenu.setForeground(new Color(90, 90, 90));
				mnNewMenu.setFont(new Font("Arial", Font.BOLD, 14));
				mnNewMenu.setBackground(new Color(255, 255, 255));
				menuBar.add(mnNewMenu);
				{
					mntmVerPerfil = new JMenuItem("Ver");
					mntmVerPerfil.setForeground(new Color(90, 90, 90));
					mntmVerPerfil.setBackground(new Color(255, 255, 255));
					mntmVerPerfil.setFont(new Font("Arial", Font.BOLD, 13));
					mntmVerPerfil.addActionListener(this);
					mnNewMenu.add(mntmVerPerfil);
				}
				{
					mntmActualizarPerfil = new JMenuItem("Actualizar");
					mntmActualizarPerfil.setForeground(new Color(90, 90, 90));
					mntmActualizarPerfil.setBackground(new Color(255, 255, 255));
					mntmActualizarPerfil.setFont(new Font("Arial", Font.BOLD, 13));
					mntmActualizarPerfil.addActionListener(this);
					mnNewMenu.add(mntmActualizarPerfil);
				}
				{
					mntmCerrarSesion = new JMenuItem("Cerrar sesión");
					mntmCerrarSesion.setForeground(new Color(90, 90, 90));
					mntmCerrarSesion.setBackground(new Color(255, 255, 255));
					mntmCerrarSesion.setFont(new Font("Arial", Font.BOLD, 13));
					mntmCerrarSesion.addActionListener(this);
					{
						mntmCambiarContraseña = new JMenuItem("Cambiar contraseña");
						mntmCambiarContraseña.setForeground(new Color(90, 90, 90));
						mntmCambiarContraseña.setBackground(new Color(255, 255, 255));
						mntmCambiarContraseña.setFont(new Font("Arial", Font.BOLD, 13));
						mntmCambiarContraseña.addActionListener(this);
						mnNewMenu.add(mntmCambiarContraseña);
					}
					mnNewMenu.add(mntmCerrarSesion);
				}
			}
			{
				mnNewMenu_1 = new JMenu("Cuentas");
				mnNewMenu_1.setForeground(new Color(90, 90, 90));
				mnNewMenu_1.setBackground(new Color(255, 255, 255));
				mnNewMenu_1.setFont(new Font("Arial", Font.BOLD, 14));
				menuBar.add(mnNewMenu_1);
				{
					mntmVerCuentasBancarias = new JMenuItem("Ver");
					mntmVerCuentasBancarias.setFont(new Font("Arial", Font.BOLD, 13));
					mntmVerCuentasBancarias.setForeground(new Color(90, 90, 90));
					mntmVerCuentasBancarias.setBackground(new Color(255, 255, 255));
					mntmVerCuentasBancarias.addActionListener(this);
					mnNewMenu_1.add(mntmVerCuentasBancarias);
				}
				{
					mntmSolicitarApertura = new JMenuItem("Solicitar apertura");
					mntmSolicitarApertura.setFont(new Font("Arial", Font.BOLD, 13));
					mntmSolicitarApertura.setForeground(new Color(90, 90, 90));
					mntmSolicitarApertura.setBackground(new Color(255, 255, 255));
					mntmSolicitarApertura.addActionListener(this);
					mnNewMenu_1.add(mntmSolicitarApertura);
				}
			}
			{
				mnNewMenu_2 = new JMenu("Tarjetas");
				mnNewMenu_2.setForeground(new Color(90, 90, 90));
				mnNewMenu_2.setBackground(new Color(255, 255, 255));
				mnNewMenu_2.setFont(new Font("Arial", Font.BOLD, 14));
				menuBar.add(mnNewMenu_2);
				{
					mntmVerTarjetas = new JMenuItem("Ver");
					mntmVerTarjetas.setFont(new Font("Arial", Font.BOLD, 13));
					mntmVerTarjetas.setForeground(new Color(90, 90, 90));
					mntmVerTarjetas.setBackground(new Color(255, 255, 255));
					mntmVerTarjetas.addActionListener(this);
					mnNewMenu_2.add(mntmVerTarjetas);
				}
				{
					mntmSolicitarEmision = new JMenuItem("Solicitar emisión");
					mntmSolicitarEmision.setFont(new Font("Arial", Font.BOLD, 13));
					mntmSolicitarEmision.setForeground(new Color(90, 90, 90));
					mntmSolicitarEmision.setBackground(new Color(255, 255, 255));
					mntmSolicitarEmision.addActionListener(this);
					mnNewMenu_2.add(mntmSolicitarEmision);
				}
			}
			
			JMenu mnTransacción = new JMenu("Transacciones");
			mnTransacción.setForeground(new Color(90, 90, 90));
			mnTransacción.setBackground(new Color(255, 255, 255));
			mnTransacción.setFont(new Font("Arial", Font.BOLD, 14));
			menuBar.add(mnTransacción);
			
			mntmVerTransacciones = new JMenuItem("Ver");
			mntmVerTransacciones.setForeground(new Color(90, 90, 90));
			mntmVerTransacciones.setBackground(new Color(255, 255, 255));
			mntmVerTransacciones.setFont(new Font("Arial", Font.BOLD, 13));
			mntmVerTransacciones.addActionListener(this);
			mnTransacción.add(mntmVerTransacciones);
			{
				mntmTransferir = new JMenuItem("Transferir");
				mntmTransferir.setForeground(new Color(90, 90, 90));
				mntmTransferir.setBackground(new Color(255, 255, 255));
				mntmTransferir.setFont(new Font("Arial", Font.BOLD, 13));
				mntmTransferir.addActionListener(this);
				mnTransacción.add(mntmTransferir);
			}
			{
				mntmPagar = new JMenuItem("Pagar");
				mntmPagar.setForeground(new Color(90, 90, 90));
				mntmPagar.setBackground(new Color(255, 255, 255));
				mntmPagar.setFont(new Font("Arial", Font.BOLD, 13));
				mntmPagar.addActionListener(this);
				mnTransacción.add(mntmPagar);
			}
			{
				mntmRetirar = new JMenuItem("Retirar");
				mntmRetirar.setForeground(new Color(90, 90, 90));
				mntmRetirar.setBackground(new Color(255, 255, 255));
				mntmRetirar.setFont(new Font("Arial", Font.BOLD, 13));
				mntmRetirar.addActionListener(this);
				mnTransacción.add(mntmRetirar);
			}
			{
				mntmDepositar = new JMenuItem("Depositar");
				mntmDepositar.setForeground(new Color(90, 90, 90));
				mntmDepositar.setBackground(new Color(255, 255, 255));
				mntmDepositar.setFont(new Font("Arial", Font.BOLD, 13));
				mntmDepositar.addActionListener(this);
				mnTransacción.add(mntmDepositar);
			}
			{
				mnSolicitudes = new JMenu("Solicitudes");
				mnSolicitudes.setForeground(new Color(90, 90, 90));
				mnSolicitudes.setFont(new Font("Arial", Font.BOLD, 14));
				mnSolicitudes.setBackground(Color.WHITE);
				menuBar.add(mnSolicitudes);
				{
					mntmVerSolicitudes = new JMenuItem("Ver");
					mntmVerSolicitudes.addActionListener(this);
					mntmVerSolicitudes.setForeground(new Color(90, 90, 90));
					mntmVerSolicitudes.setFont(new Font("Arial", Font.BOLD, 13));
					mntmVerSolicitudes.setBackground(Color.WHITE);
					mnSolicitudes.add(mntmVerSolicitudes);
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
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBackground(new Color(255, 255, 255));
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
			lblNewLabel.setBounds(50, 50, 254, 47);
			contentPane.add(lblNewLabel);
		}
		{
			lblNombresCliente = new JLabel(cliente.getNombres());
			lblNombresCliente.setForeground(new Color(255, 255, 255));
			lblNombresCliente.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
			lblNombresCliente.setBounds(50, 108, 560, 47);
			contentPane.add(lblNombresCliente);
		}
		{
			lblApellidosCliente = new JLabel(cliente.getApellidos());
			lblApellidosCliente.setForeground(Color.WHITE);
			lblApellidosCliente.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
			lblApellidosCliente.setBounds(50, 166, 560, 47);
			contentPane.add(lblApellidosCliente);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmVerProgramadores) {
			do_mntmVerProgramadores_actionPerformed(e);
		}
		if (e.getSource() == mntmVerSolicitudes) {
			do_mntmVerSolicitudes_actionPerformed(e);
		}
		if (e.getSource() == mntmDepositar) {
			do_mntmDepositar_actionPerformed(e);
		}
		if (e.getSource() == mntmRetirar) {
			do_mntmRetirar_actionPerformed(e);
		}
		if (e.getSource() == mntmPagar) {
			do_mntmPagar_actionPerformed(e);
		}
		if (e.getSource() == mntmTransferir) {
			do_mntmTransferir_actionPerformed(e);
		}
		if (e.getSource() == mntmSolicitarEmision) {
			do_mntmSolicitarEmision_actionPerformed(e);
		}
		if (e.getSource() == mntmSolicitarApertura) {
			do_mntmSolicitarApertura_actionPerformed(e);
		}
		if (e.getSource() == mntmCambiarContraseña) {
			do_mntmCambiarContraseña_actionPerformed(e);
		}
		if (e.getSource() == mntmVerTransacciones) {
			do_mntmVerTransacciones_actionPerformed(e);
		}
		if (e.getSource() == mntmVerTarjetas) {
			do_mntmVerTarjetas_actionPerformed(e);
		}
		if (e.getSource() == mntmVerCuentasBancarias) {
			do_mntmVerCuentasBancarias_actionPerformed(e);
		}
		if (e.getSource() == mntmActualizarPerfil) {
			do_mntmActualizarPerfil_actionPerformed(e);
		}
		if (e.getSource() == mntmVerPerfil) {
			do_mntmVerPerfil_actionPerformed(e);
		}
		if (e.getSource() == mntmCerrarSesion) {
			do_mntmCerrarSesion_actionPerformed(e);
		}
	}
	protected void do_mntmCerrarSesion_actionPerformed(ActionEvent e) {
		ventanaPrincipal.setVisible(true);
		dispose();
	}
	protected void do_mntmVerPerfil_actionPerformed(ActionEvent e) {
		VentanaVerPerfil ventanaVerPerfil = new VentanaVerPerfil(cliente);
		ventanaVerPerfil.setVisible(true);
	}
	protected void do_mntmActualizarPerfil_actionPerformed(ActionEvent e) {
		VentanaActualizarPerfil ventanaActualizarPerfil = new VentanaActualizarPerfil(cliente);
		ventanaActualizarPerfil.setVisible(true);
	}
	protected void do_mntmVerCuentasBancarias_actionPerformed(ActionEvent e) {
		VentanaVerCuentasBancarias ventanaVerCuentasBancarias = new VentanaVerCuentasBancarias(cliente);
		ventanaVerCuentasBancarias.setVisible(true);
	}
	protected void do_mntmVerTarjetas_actionPerformed(ActionEvent e) {		VentanaVerTarjetas ventanaVerTarjetas = new VentanaVerTarjetas(cliente);
		ventanaVerTarjetas.setVisible(true);
	}
	protected void do_mntmCambiarContraseña_actionPerformed(ActionEvent e) {
		VentanaCambiarContraseña ventanaCambiarContraseña = new VentanaCambiarContraseña(cliente);
		ventanaCambiarContraseña.setVisible(true);
	}
	protected void do_mntmVerTransacciones_actionPerformed(ActionEvent e) {
		VentanaVerTransacciones ventanaVerTransacciones = new VentanaVerTransacciones(cliente);
		ventanaVerTransacciones.setVisible(true);
	}
	protected void do_mntmSolicitarApertura_actionPerformed(ActionEvent e) {
		VentanaRealizarSolicitud ventanaRealizarSolicitud = new VentanaRealizarSolicitud("Apertura de cuenta", cliente);
		ventanaRealizarSolicitud.setVisible(true);
	}
	protected void do_mntmSolicitarEmision_actionPerformed(ActionEvent e) {
		VentanaRealizarSolicitud ventanaRealizarSolicitud = new VentanaRealizarSolicitud("Emisión de tarjeta", cliente);
		ventanaRealizarSolicitud.setVisible(true);
	}
	protected void do_mntmTransferir_actionPerformed(ActionEvent e) {
		VentanaTransaccion ventanaTransaccion = new VentanaTransaccion(cliente, "Transferir");
		ventanaTransaccion.setVisible(true);
	}
	protected void do_mntmPagar_actionPerformed(ActionEvent e) {
		VentanaTransaccion ventanaTransaccion = new VentanaTransaccion(cliente, "Pagar");
		ventanaTransaccion.setVisible(true);
	}
	protected void do_mntmRetirar_actionPerformed(ActionEvent e) {
		VentanaTransaccion ventanaTransaccion = new VentanaTransaccion(cliente, "Retirar");
		ventanaTransaccion.setVisible(true);
	}
	protected void do_mntmDepositar_actionPerformed(ActionEvent e) {
		VentanaTransaccion ventanaTransaccion = new VentanaTransaccion(cliente, "Depositar");
		ventanaTransaccion.setVisible(true);
	}
	protected void do_mntmVerSolicitudes_actionPerformed(ActionEvent e) {
		VentanaVerSolicitudes verSolicitudes = new VentanaVerSolicitudes(cliente);
		verSolicitudes.setVisible(true);
	}
	protected void do_mntmVerProgramadores_actionPerformed(ActionEvent e) {
		VentanaProgramadores ventanaProgramadores = new VentanaProgramadores();
		ventanaProgramadores.setVisible(true);
	}
}
