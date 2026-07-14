package vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import modelo.Cliente;
import modelo.Cuenta;
import modelo.Transaccion;
import repositorio.RepositorioCuenta;
import repositorio.RepositorioTransaccion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class VentanaTransaccion extends JDialog implements ActionListener, KeyListener {
	
	private Cliente cliente;
	private String tipo;

	private static final long serialVersionUID = 1L;
	private JButton btnCancelar;
	private JLabel lblTransaccion;
	private JLabel lblNumeroCuentaOrigen;
	private JLabel lblNumeroCuentaDestino;
	private JTextField txtNumeroCuentaDestino;
	private JLabel lblMonto;
	private JLabel lblMotivo;
	private JTextField txtMotivoPagar;
	private JButton btnContinuar;
	private JTextField txtNumeroCuentaOrigen;
	private JTextField txtMonto;

	public VentanaTransaccion(Cliente cliente, String tipo) {
		this.cliente = cliente;
		this.tipo = tipo.toLowerCase();
		
		getContentPane().setForeground(new Color(90, 90, 90));
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Transacción");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 530, 410);
		getContentPane().setLayout(null);
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(new Color(255, 255, 255));
			btnCancelar.setForeground(new Color(90, 90, 90));
			btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
			btnCancelar.addActionListener(this);
			btnCancelar.setBounds(309, 290, 150, 35);
			getContentPane().add(btnCancelar);
		}
		{
			lblTransaccion = new JLabel(tipo);
			lblTransaccion.setForeground(new Color(238, 52, 37));
			lblTransaccion.setHorizontalAlignment(SwingConstants.CENTER);
			lblTransaccion.setFont(new Font("Arial", Font.BOLD, 25));
			lblTransaccion.setBounds(187, 50, 129, 30);
			getContentPane().add(lblTransaccion);
		}
		if(!tipo.equalsIgnoreCase("retirar")){
			{
				lblNumeroCuentaDestino = new JLabel("Número de cuenta de destino:");
				lblNumeroCuentaDestino.setForeground(new Color(90, 90, 90));
				lblNumeroCuentaDestino.setFont(new Font("Arial", Font.BOLD, 13));
				lblNumeroCuentaDestino.setBounds(50, 156, 191, 16);
				getContentPane().add(lblNumeroCuentaDestino);
			}
			{
				txtNumeroCuentaDestino = new JTextField();
				txtNumeroCuentaDestino.addKeyListener(this);
				txtNumeroCuentaDestino.setBackground(new Color(255, 255, 255));
				txtNumeroCuentaDestino.setForeground(new Color(90, 90, 90));
				txtNumeroCuentaDestino.setFont(new Font("Arial", Font.PLAIN, 13));
				txtNumeroCuentaDestino.setBounds(259, 152, 200, 25);
				getContentPane().add(txtNumeroCuentaDestino);
				txtNumeroCuentaDestino.setColumns(10);
	            getContentPane().add(txtNumeroCuentaDestino);
			}
		}
		{
			lblMonto = new JLabel("Monto a " + tipo.toLowerCase() + ":");
			lblMonto.setForeground(new Color(90, 90, 90));
			lblMonto.setFont(new Font("Arial", Font.BOLD, 13));
			lblMonto.setBounds(50, 192, 129, 16);
			getContentPane().add(lblMonto);
		}
		if(tipo.equalsIgnoreCase("pagar")){
			{
				lblMotivo = new JLabel("Motivo a pagar:");
				lblMotivo.setForeground(new Color(90, 90, 90));
				lblMotivo.setFont(new Font("Arial", Font.BOLD, 13));
				lblMotivo.setBounds(50, 228, 99, 16);
				getContentPane().add(lblMotivo);
			}
			{
				txtMotivoPagar = new JTextField();
				txtMotivoPagar.setBackground(new Color(255, 255, 255));
				txtMotivoPagar.setForeground(new Color(90, 90, 90));
				txtMotivoPagar.setFont(new Font("Arial", Font.PLAIN, 13));
				txtMotivoPagar.setColumns(10);
				txtMotivoPagar.setBounds(259, 224, 200, 25);
				getContentPane().add(txtMotivoPagar);
			}
		}
		{
			btnContinuar = new JButton("Continuar");
			btnContinuar.setBackground(new Color(238, 52, 37));
			btnContinuar.setForeground(new Color(255, 255, 255));
			btnContinuar.setFont(new Font("Arial", Font.BOLD, 13));
			btnContinuar.addActionListener(this);
			btnContinuar.setBounds(50, 290, 150, 35);
			getContentPane().add(btnContinuar);
		}
		{
			txtMonto = new JTextField();
			txtMonto.addKeyListener(this);
			txtMonto.setForeground(new Color(90, 90, 90));
			txtMonto.setFont(new Font("Arial", Font.PLAIN, 13));
			txtMonto.setColumns(10);
			txtMonto.setBackground(new Color(255, 255, 255));
			txtMonto.setBounds(259, 190, 200, 25);
			getContentPane().add(txtMonto);
		}
		if(!tipo.equalsIgnoreCase("depositar")){
			{
				lblNumeroCuentaOrigen = new JLabel("Número de cuenta de origen:");
				lblNumeroCuentaOrigen.setForeground(new Color(90, 90, 90));
				lblNumeroCuentaOrigen.setFont(new Font("Arial", Font.BOLD, 13));
				lblNumeroCuentaOrigen.setBounds(50, 120, 186, 16);
				getContentPane().add(lblNumeroCuentaOrigen);
			}
			{
				txtNumeroCuentaOrigen = new JTextField();
				txtNumeroCuentaOrigen.addKeyListener(this);
				txtNumeroCuentaOrigen.setBackground(new Color(255, 255, 255));
				txtNumeroCuentaOrigen.setForeground(new Color(90, 90, 90));
				txtNumeroCuentaOrigen.setFont(new Font("Arial", Font.PLAIN, 13));
				txtNumeroCuentaOrigen.setColumns(10);
				txtNumeroCuentaOrigen.setBounds(260, 116, 200, 25);
				getContentPane().add(txtNumeroCuentaOrigen);
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnContinuar) {
			do_btnContinuar_actionPerformed(e);
		}
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
	}
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		dispose();
	}
	private String obtenerNumeroCuenta(String numeroCuenta) {
		if(numeroCuenta.contains("-")) return numeroCuenta.substring(0, 3) + numeroCuenta.substring(4);
		return numeroCuenta;
	}
	protected void do_btnContinuar_actionPerformed(ActionEvent e) {
		try {
			Cliente clienteAutenticar = cliente, clienteOrigen = null, clienteDestino = null;
			Cuenta cuentaOrigen = null, cuentaDestino = null;
			String descripcion = "";
			String numeroOrigen = "";
			String numeroDestino = "";
			String motivoPagar = "";
			if(txtNumeroCuentaOrigen != null) numeroOrigen = obtenerNumeroCuenta(txtNumeroCuentaOrigen.getText().trim());
			if(txtNumeroCuentaDestino != null) numeroDestino = obtenerNumeroCuenta(txtNumeroCuentaDestino.getText().trim());
			if(txtMotivoPagar != null) motivoPagar = txtMotivoPagar.getText().trim();
			if(txtMonto.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "El campo monto está vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			double montoEnviado = Double.parseDouble(txtMonto.getText().trim()), montoRecibido = 0;
			if(montoEnviado < 0 || montoEnviado >= 10000000000.0) {
				JOptionPane.showMessageDialog(this, "El monto es inválido.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if(montoEnviado == 0) {
				JOptionPane.showMessageDialog(this, "El monto no puede ser cero.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(tipo.equals("retirar") || tipo.equals("transferir") || tipo.equals("pagar")) {
				if(numeroOrigen.isEmpty()) {
					JOptionPane.showMessageDialog(this, "El campo número de cuenta de origen está vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				cuentaOrigen = RepositorioCuenta.consultarNumeroCuenta(numeroOrigen);
				if(cuentaOrigen == null) {
					JOptionPane.showMessageDialog(this, "La cuenta de origen no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}else if(montoEnviado > cuentaOrigen.getSaldoDisponible()) {
					JOptionPane.showMessageDialog(this, "El saldo de la cuenta de origen es insuficiente.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				clienteAutenticar = clienteOrigen = cuentaOrigen.getCliente();
				descripcion += "Número de cuenta de origen: " + numeroOrigen + ";";
			}
			if(tipo.equals("depositar") || tipo.equals("transferir") || tipo.equals("pagar")){
				if(numeroDestino.isEmpty()) {
					JOptionPane.showMessageDialog(this, "El campo número de cuenta de destino está vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				cuentaDestino = RepositorioCuenta.consultarNumeroCuenta(numeroDestino);
				if(cuentaDestino == null) {
					JOptionPane.showMessageDialog(this, "La cuenta de destino no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if((tipo.equalsIgnoreCase("transferir") || tipo.equalsIgnoreCase("pagar")) && cuentaOrigen.getNumeroCuenta().equals(cuentaDestino.getNumeroCuenta())) {
					JOptionPane.showMessageDialog(this, "No se puede " + tipo + " a la misma cuenta.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				clienteDestino = cuentaDestino.getCliente();
				descripcion += "Número de cuenta de destino: " + numeroDestino + ";";
				if(tipo.equals("depositar")) clienteAutenticar = clienteDestino;
			}
			if(tipo.equals("pagar")) {
				if(motivoPagar.isEmpty()) {
					JOptionPane.showMessageDialog(this, "El campo motivo a pagar está vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				descripcion += "Motivo: " + motivoPagar + ";";
			}
			if(cuentaOrigen!=null) {
				if(cuentaOrigen.getEstado().equals("cancelada")){
					JOptionPane.showMessageDialog(this, "La cuenta de origen está cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
			if(cuentaDestino!=null) {
				if(cuentaDestino.getEstado().equals("cancelada")) {
					JOptionPane.showMessageDialog(this, "La cuenta de destino está cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				montoRecibido = montoEnviado;
				if(tipo.equals("transferir") || tipo.equals("pagar")) {
					String monedaOrigen = cuentaOrigen.getMoneda();
					String monedaDestino = cuentaDestino.getMoneda();
					montoRecibido = calcularConversión(monedaOrigen, monedaDestino, montoEnviado);
					descripcion += "Monto enviado: " + obtenerMontoFormateado(monedaOrigen, montoEnviado) + 
							";Monto recibido: " + obtenerMontoFormateado(monedaDestino, montoRecibido) + 
							";Cambio: " + monedaOrigen + " - " + monedaDestino;
				}
			}
			VentanaAutenticar ventanaAutenticar = new VentanaAutenticar(clienteAutenticar);
			ventanaAutenticar.setVisible(true);
			if(ventanaAutenticar.getEstadoAutenticacion()) {			
				Transaccion transaccion = new Transaccion(tipo, descripcion, montoEnviado, cliente);
				RepositorioTransaccion.insertarTransaccion(transaccion);
				if(clienteOrigen != null && clienteDestino != null) {
					if(clienteOrigen.equals(clienteDestino)) clienteOrigen.agregarTransaccion(transaccion);
					else {
						clienteOrigen.agregarTransaccion(transaccion);
						clienteDestino.agregarTransaccion(transaccion);
					}
					cuentaOrigen.setSaldoDisponible(cuentaOrigen.getSaldoDisponible() - montoEnviado);
					cuentaOrigen.setSaldoContable(cuentaOrigen.getSaldoContable() - montoEnviado);
					cuentaDestino.setSaldoDisponible(cuentaDestino.getSaldoDisponible() + montoRecibido);
					cuentaDestino.setSaldoContable(cuentaDestino.getSaldoContable() + montoRecibido);
					RepositorioCuenta.actualizarCuenta(cuentaOrigen);
					RepositorioCuenta.actualizarCuenta(cuentaDestino);
				}else if(cuentaDestino==null) {
					clienteOrigen.agregarTransaccion(transaccion);
					cuentaOrigen.setSaldoDisponible(cuentaOrigen.getSaldoDisponible() - montoEnviado);
					cuentaOrigen.setSaldoContable(cuentaOrigen.getSaldoContable() - montoEnviado);
					RepositorioCuenta.actualizarCuenta(cuentaOrigen);
				}else if(cuentaOrigen==null) {
					clienteDestino.agregarTransaccion(transaccion);
					cuentaDestino.setSaldoDisponible(cuentaDestino.getSaldoDisponible() + montoRecibido);
					cuentaDestino.setSaldoContable(cuentaDestino.getSaldoContable() + montoRecibido);
					RepositorioCuenta.actualizarCuenta(cuentaDestino);
				}
				int seleccion = JOptionPane.showConfirmDialog(this,
		                "La autenticación se realizó con éxito, la transacción se ejecutó. ¿Deseas descargar el comprobante?",
		                "Confirmar descarga del comprobante", JOptionPane.YES_NO_OPTION);
		        if (seleccion == JOptionPane.YES_OPTION) {
		        	JFileChooser fileChooser = new JFileChooser();
		    	    fileChooser.setDialogTitle("Descargar comprobante");
		    	    fileChooser.setSelectedFile(new java.io.File("comprobante-" + transaccion.getIdTransaccion() + ".txt"));
		    	    seleccion = fileChooser.showSaveDialog(this);
		    	    if (seleccion == JFileChooser.APPROVE_OPTION) {
		    	        java.io.File archivo = fileChooser.getSelectedFile();
		    	        try {
		    	        	FileWriter writer = new FileWriter(archivo);
		    	            writer.write("Comprobante de la transacción\n");
		    	            writer.write("---------------------------------------------------------------\n");
		    	            writer.write("ID: " + transaccion.getIdTransaccion() + "\n");
		    	            writer.write("Tipo: " + transaccion.getTipoTransaccion() + "\n");
		            		writer.write("Descripción:\n");
		            		String[] textoSeparado = transaccion.getDescripcion().split(";");
		            		for (int i = 0; i < textoSeparado.length; i++) {
		            			writer.write("\t" + textoSeparado[i].trim() + "\n");
		            		}
	        				writer.write("Fecha y hora: " + transaccion.getFechaHoraFormateada() + "\n");
	        				writer.write("Monto: " + transaccion.getMonto() + "\n");
	        				writer.write("---------------------------------------------------------------\n");
		    	        	writer.close();
		    	            JOptionPane.showMessageDialog(this, "Comprobante descargado correctamente.");
		    	        } catch (IOException ex) {
		    	            JOptionPane.showMessageDialog(this, "Error al descargar el comprobante.", "Error", JOptionPane.ERROR_MESSAGE);
		    	            ex.printStackTrace();
		    	        }
		    	    }
		        }
			} else {
				JOptionPane.showMessageDialog(this, "La autenticación falló.", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
			dispose();
		} catch (NumberFormatException error) {
			JOptionPane.showMessageDialog(this, "El monto es inválido.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "Error: " + error, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private double calcularConversión(String monedaOrigen, String monedaDestino, double monto) {
		final double SOL_A_DOLAR = 0.27;
	    final double SOL_A_EURO = 0.24;
	    final double SOL_A_LIBRA = 0.20;
	    final double DOLAR_A_SOL = 1 / SOL_A_DOLAR;
	    final double DOLAR_A_EURO = 0.92;
	    final double DOLAR_A_LIBRA = 0.79;
	    final double EURO_A_SOL = 1 / SOL_A_EURO;
	    final double EURO_A_DOLAR = 1.09;
	    final double EURO_A_LIBRA = 0.85;
	    final double LIBRA_A_SOL = 1 / SOL_A_LIBRA;
	    final double LIBRA_A_DOLAR = 1.26;
	    final double LIBRA_A_EURO = 1.18;
	    if (monedaOrigen.equals("soles")) {
	        if (monedaDestino.equals("soles")) return monto;
	        else if (monedaDestino.equals("dólares")) return monto * SOL_A_DOLAR;
	        else if (monedaDestino.equals("euros")) return monto * SOL_A_EURO;
	        else if (monedaDestino.equals("libras")) return monto * SOL_A_LIBRA;
	    } else if (monedaOrigen.equals("dólares")) {
	        if (monedaDestino.equals("soles")) return monto * DOLAR_A_SOL;
	        else if (monedaDestino.equals("dólares")) return monto;
	        else if (monedaDestino.equals("euros")) return monto * DOLAR_A_EURO;
	        else if (monedaDestino.equals("libras")) return monto * DOLAR_A_LIBRA;
	    } else if (monedaOrigen.equals("euros")) {
	        if (monedaDestino.equals("soles")) return monto * EURO_A_SOL;
	        else if (monedaDestino.equals("dólares")) return monto * EURO_A_DOLAR;
	        else if (monedaDestino.equals("euros")) return monto;
	        else if (monedaDestino.equals("libras")) return monto * EURO_A_LIBRA;
	    } else if (monedaOrigen.equals("libras")) {
	        if (monedaDestino.equals("soles")) return monto * LIBRA_A_SOL;
	        else if (monedaDestino.equals("dólares")) return monto * LIBRA_A_DOLAR;
	        else if (monedaDestino.equals("euros")) return monto * LIBRA_A_EURO;
	        else if (monedaDestino.equals("libras")) return monto;
	    }
	    return monto;
	}
	@SuppressWarnings("deprecation")
	private String obtenerMontoFormateado(String moneda, double monto) {
		Locale locale;
		switch (moneda) {
	        case "dólares":
	        	locale = new Locale("en", "US");
	            break;
	        case "euros":
	        	locale = new Locale("es", "ES");
	            break;
	        case "libras":
	        	locale = new Locale("en", "GB");
	            break;
	        default:
	        	locale = new Locale("es", "PE");
	            break;
	    }
	    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		return numberFormat.format(monto);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtNumeroCuentaDestino) {
			do_txtNumeroCuentaDestino_keyTyped(e);
		}
		if (e.getSource() == txtNumeroCuentaOrigen) {
			do_txtNumeroCuentaOrigen_keyTyped(e);
		}
		if (e.getSource() == txtMonto) {
			do_txtMonto_keyTyped(e);
		}
	}
	protected void do_txtMonto_keyTyped(KeyEvent e) {
		char caracteres = e.getKeyChar();
		if(Character.isAlphabetic(caracteres)) {
			e.consume();
			JOptionPane.showMessageDialog(this, "El monto debe tener números.", "Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	protected void do_txtNumeroCuentaOrigen_keyTyped(KeyEvent e) {
		char caracteres = e.getKeyChar();
		if(Character.isAlphabetic(caracteres)) {
			e.consume();
			JOptionPane.showMessageDialog(this, "El número de cuenta debe tener números.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} else if(txtNumeroCuentaOrigen.getText().length() >= 11) {
			e.consume();
			JOptionPane.showMessageDialog(this, "El número de cuenta debe tener 10 dígitos u 11 dígitos si tiene la separación.", "Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	protected void do_txtNumeroCuentaDestino_keyTyped(KeyEvent e) {
		char caracteres = e.getKeyChar();
		if(Character.isAlphabetic(caracteres)) {
			e.consume();
			JOptionPane.showMessageDialog(this, "El número de cuenta debe tener números.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} else if(txtNumeroCuentaDestino.getText().length() >= 11) {
			e.consume();
			JOptionPane.showMessageDialog(this, "El número de cuenta debe tener 10 dígitos u 11 dígitos si tiene la separación.", "Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
