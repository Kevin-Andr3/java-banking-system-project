package vista;

import javax.swing.JDialog;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VentanaProgramadores extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblProgramadores;
	private JButton btnCerrar;
	private JLabel lblFoto2;
	private JLabel lblFoto3;
	private JLabel lblFoto4;
	private JLabel lblFoto5;
	private JLabel lblBasilioAlvarez;
	private JLabel lblGianellaAnnie;
	private JLabel lblN00396701;
	private JLabel lblCahuanaMoquillaza;
	private JLabel lblHerbertJherson;
	private JLabel lblN00327161;
	private JLabel lblCorcueraParedes;
	private JLabel lblJoseManuel;
	private JLabel lblN00373084;
	private JLabel lblCotrinaSandoval;
	private JLabel lblAlexandraMargoth;
	private JLabel lblN00328935;
	private JLabel lblDeLaCruzVilluga;
	private JLabel lblKevinAndre;
	private JLabel lblN00368206;
	private JLabel lblFoto1;
	
	public VentanaProgramadores() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		{
			lblProgramadores = new JLabel("Programadores");
			lblProgramadores.setHorizontalAlignment(SwingConstants.CENTER);
			lblProgramadores.setForeground(new Color(238, 52, 37));
			lblProgramadores.setFont(new Font("Arial", Font.BOLD, 25));
			lblProgramadores.setBackground(Color.WHITE);
			lblProgramadores.setBounds(316, 50, 251, 30);
			getContentPane().add(lblProgramadores);
		}
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(this);
			btnCerrar.setForeground(new Color(90, 90, 90));
			btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCerrar.setBackground(Color.WHITE);
			btnCerrar.setBounds(367, 440, 150, 35);
			getContentPane().add(btnCerrar);
		}
		{
			lblFoto2 = new JLabel("");
			lblFoto2.setIcon(new ImageIcon(VentanaProgramadores.class.getResource("/imagen/FotoCarnet_CahuanaMoquillaza.jpeg")));
			lblFoto2.setHorizontalAlignment(SwingConstants.CENTER);
			lblFoto2.setForeground(new Color(0, 0, 0));
			lblFoto2.setBackground(new Color(255, 255, 255));
			lblFoto2.setBounds(210, 120, 137, 171);
			getContentPane().add(lblFoto2);
		}
		{
			lblFoto3 = new JLabel("");
			lblFoto3.setIcon(new ImageIcon(VentanaProgramadores.class.getResource("/imagen/fotoCarnet_CorcueraParedes.jpeg")));
			lblFoto3.setForeground(new Color(0, 0, 0));
			lblFoto3.setBackground(new Color(255, 255, 255));
			lblFoto3.setBounds(370, 120, 137, 171);
			getContentPane().add(lblFoto3);
		}
		{
			lblFoto4 = new JLabel("");
			lblFoto4.setIcon(new ImageIcon(VentanaProgramadores.class.getResource("/imagen/fotoCarnet_CotrinaSandoval.jpeg")));
			lblFoto4.setForeground(new Color(0, 0, 0));
			lblFoto4.setBackground(new Color(255, 255, 255));
			lblFoto4.setBounds(530, 120, 137, 171);
			getContentPane().add(lblFoto4);
		}
		{
			lblFoto5 = new JLabel("");
			lblFoto5.setHorizontalAlignment(SwingConstants.CENTER);
			lblFoto5.setIcon(new ImageIcon(VentanaProgramadores.class.getResource("/imagen/FotoCarnet_DeLaCruzVillugas.jpeg")));
			lblFoto5.setForeground(new Color(0, 0, 0));
			lblFoto5.setBackground(new Color(255, 255, 255));
			lblFoto5.setBounds(690, 120, 137, 171);
			getContentPane().add(lblFoto5);
		}
		{
			lblBasilioAlvarez = new JLabel("Basilio Alvarez");
			lblBasilioAlvarez.setHorizontalAlignment(SwingConstants.CENTER);
			lblBasilioAlvarez.setForeground(new Color(90, 90, 90));
			lblBasilioAlvarez.setFont(new Font("Arial", Font.BOLD, 15));
			lblBasilioAlvarez.setBounds(50, 310, 137, 18);
			getContentPane().add(lblBasilioAlvarez);
		}
		{
			lblGianellaAnnie = new JLabel("Gianella Annie");
			lblGianellaAnnie.setHorizontalAlignment(SwingConstants.CENTER);
			lblGianellaAnnie.setForeground(new Color(90, 90, 90));
			lblGianellaAnnie.setFont(new Font("Arial", Font.BOLD, 15));
			lblGianellaAnnie.setBounds(50, 339, 137, 18);
			getContentPane().add(lblGianellaAnnie);
		}
		{
			lblN00396701 = new JLabel("(N00396701)");
			lblN00396701.setHorizontalAlignment(SwingConstants.CENTER);
			lblN00396701.setForeground(new Color(90, 90, 90));
			lblN00396701.setFont(new Font("Arial", Font.BOLD, 15));
			lblN00396701.setBounds(50, 368, 137, 18);
			getContentPane().add(lblN00396701);
		}
		{
			lblCahuanaMoquillaza = new JLabel("Cahuana Moquillaza");
			lblCahuanaMoquillaza.setHorizontalAlignment(SwingConstants.CENTER);
			lblCahuanaMoquillaza.setForeground(new Color(90, 90, 90));
			lblCahuanaMoquillaza.setFont(new Font("Arial", Font.BOLD, 15));
			lblCahuanaMoquillaza.setBounds(200, 310, 157, 18);
			getContentPane().add(lblCahuanaMoquillaza);
		}
		{
			lblHerbertJherson = new JLabel("Herbert Jherson");
			lblHerbertJherson.setHorizontalAlignment(SwingConstants.CENTER);
			lblHerbertJherson.setForeground(new Color(90, 90, 90));
			lblHerbertJherson.setFont(new Font("Arial", Font.BOLD, 15));
			lblHerbertJherson.setBounds(210, 339, 137, 18);
			getContentPane().add(lblHerbertJherson);
		}
		{
			lblN00327161 = new JLabel("(N00327161)");
			lblN00327161.setHorizontalAlignment(SwingConstants.CENTER);
			lblN00327161.setForeground(new Color(90, 90, 90));
			lblN00327161.setFont(new Font("Arial", Font.BOLD, 15));
			lblN00327161.setBounds(210, 368, 137, 18);
			getContentPane().add(lblN00327161);
		}
		{
			lblCorcueraParedes = new JLabel("Corcuera Paredes");
			lblCorcueraParedes.setHorizontalAlignment(SwingConstants.CENTER);
			lblCorcueraParedes.setForeground(new Color(90, 90, 90));
			lblCorcueraParedes.setFont(new Font("Arial", Font.BOLD, 15));
			lblCorcueraParedes.setBounds(370, 310, 137, 18);
			getContentPane().add(lblCorcueraParedes);
		}
		{
			lblJoseManuel = new JLabel("Jose Manuel");
			lblJoseManuel.setHorizontalAlignment(SwingConstants.CENTER);
			lblJoseManuel.setForeground(new Color(90, 90, 90));
			lblJoseManuel.setFont(new Font("Arial", Font.BOLD, 15));
			lblJoseManuel.setBounds(370, 339, 137, 18);
			getContentPane().add(lblJoseManuel);
		}
		{
			lblN00373084 = new JLabel("(N00373084)");
			lblN00373084.setHorizontalAlignment(SwingConstants.CENTER);
			lblN00373084.setForeground(new Color(90, 90, 90));
			lblN00373084.setFont(new Font("Arial", Font.BOLD, 15));
			lblN00373084.setBounds(370, 368, 137, 18);
			getContentPane().add(lblN00373084);
		}
		{
			lblCotrinaSandoval = new JLabel("Cotrina Sandoval");
			lblCotrinaSandoval.setHorizontalAlignment(SwingConstants.CENTER);
			lblCotrinaSandoval.setForeground(new Color(90, 90, 90));
			lblCotrinaSandoval.setFont(new Font("Arial", Font.BOLD, 15));
			lblCotrinaSandoval.setBounds(530, 310, 137, 18);
			getContentPane().add(lblCotrinaSandoval);
		}
		{
			lblAlexandraMargoth = new JLabel("Alexandra Margoth");
			lblAlexandraMargoth.setHorizontalAlignment(SwingConstants.CENTER);
			lblAlexandraMargoth.setForeground(new Color(90, 90, 90));
			lblAlexandraMargoth.setFont(new Font("Arial", Font.BOLD, 15));
			lblAlexandraMargoth.setBounds(530, 339, 137, 18);
			getContentPane().add(lblAlexandraMargoth);
		}
		{
			lblN00328935 = new JLabel("(N00328935)");
			lblN00328935.setHorizontalAlignment(SwingConstants.CENTER);
			lblN00328935.setForeground(new Color(90, 90, 90));
			lblN00328935.setFont(new Font("Arial", Font.BOLD, 15));
			lblN00328935.setBounds(530, 368, 137, 18);
			getContentPane().add(lblN00328935);
		}
		{
			lblDeLaCruzVilluga = new JLabel("De La Cruz Villuga");
			lblDeLaCruzVilluga.setHorizontalAlignment(SwingConstants.CENTER);
			lblDeLaCruzVilluga.setForeground(new Color(90, 90, 90));
			lblDeLaCruzVilluga.setFont(new Font("Arial", Font.BOLD, 15));
			lblDeLaCruzVilluga.setBounds(690, 310, 137, 18);
			getContentPane().add(lblDeLaCruzVilluga);
		}
		{
			lblKevinAndre = new JLabel("Kevin André");
			lblKevinAndre.setHorizontalAlignment(SwingConstants.CENTER);
			lblKevinAndre.setForeground(new Color(90, 90, 90));
			lblKevinAndre.setFont(new Font("Arial", Font.BOLD, 15));
			lblKevinAndre.setBounds(690, 339, 137, 18);
			getContentPane().add(lblKevinAndre);
		}
		{
			lblN00368206 = new JLabel("(N00368206)");
			lblN00368206.setHorizontalAlignment(SwingConstants.CENTER);
			lblN00368206.setForeground(new Color(90, 90, 90));
			lblN00368206.setFont(new Font("Arial", Font.BOLD, 15));
			lblN00368206.setBounds(690, 368, 137, 18);
			getContentPane().add(lblN00368206);
		}
		{
			lblFoto1 = new JLabel("");
			lblFoto1.setIcon(new ImageIcon(VentanaProgramadores.class.getResource("/imagen/fotoCarnet_BasilioAlvarez.jpeg")));
			lblFoto1.setHorizontalAlignment(SwingConstants.CENTER);
			lblFoto1.setForeground(Color.BLACK);
			lblFoto1.setBackground(Color.WHITE);
			lblFoto1.setBounds(50, 120, 137, 171);
			getContentPane().add(lblFoto1);
		}
		setTitle("Programadores");
		setBounds(100, 100, 900, 560);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
	}
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
}
