package modelo;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Cuenta {
	private String numeroCuenta;
	private double saldoContable;
	private double saldoDisponible;
	private LocalDate fechaCreacion;
	private String estado;
	private String tipoCuenta;
	private String moneda;
	private Cliente cliente;
	private ArrayList<Transaccion> transacciones;
	public Cuenta() {
		transacciones = new ArrayList<Transaccion>();
	}
	public Cuenta(String tipoCuenta, String moneda, Cliente cliente) {
		numeroCuenta = generarNumeroCuenta();
		saldoContable = saldoDisponible = 0;
		fechaCreacion = LocalDate.now();
		estado = "activa";
		this.tipoCuenta = tipoCuenta;
		this.moneda = moneda;
		this.cliente = cliente;
		transacciones = new ArrayList<Transaccion>();
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public double getSaldoContable() {
		return saldoContable;
	}
	public void setSaldoContable(double saldoContable) {
		this.saldoContable = saldoContable;
	}
	public double getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<Transaccion> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(ArrayList<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}
	private String generarNumeroCuenta() {
		Random rand = new Random();
		String numero = "";
		for (int i = 0; i < 10; i++)
	    {
	        int n = rand.nextInt(10);
	        numero += Integer.toString(n);
	    }
		return numero;
	}
	public String getNumeroCuentaFormateado() {
		return numeroCuenta.substring(0, 3) + "-" + numeroCuenta.substring(3);
	}
	public String getFechaCreacionFormateada() {
		return fechaCreacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	@SuppressWarnings("deprecation")
	public String getSaldoContableFormateado() {
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
		return numberFormat.format(saldoContable);
	}
	@SuppressWarnings("deprecation")
	public String getSaldoDisponibleFormateado() {
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
		return numberFormat.format(saldoDisponible);
	}
}
