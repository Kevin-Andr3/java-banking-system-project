package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Tarjeta {
	private String numeroTarjeta;
	private String tipoTarjeta;
	private String estado;
	private Cliente cliente;
	private LocalDate fechaVencimiento;
	public Tarjeta() {}
	public Tarjeta(String tipoTarjeta, Cliente cliente) {
		this.numeroTarjeta = generarNumeroTarjeta();
	    this.tipoTarjeta = tipoTarjeta;
	    this.estado = "activa";
	    this.fechaVencimiento = LocalDate.now().plusYears(2);
	    this.cliente = cliente;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	private String generarNumeroTarjeta() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
	public String getFechaVencimientoFormateada() {
		return fechaVencimiento.format(DateTimeFormatter.ofPattern("MM/yy"));
	}
	public String getNumeroTarjetaFormateado() {
		String numero = "";
		for (int i = 0; i < numeroTarjeta.length(); i++) {
			if(i % 4 == 0) numero += " ";
			numero += numeroTarjeta.charAt(i);
		}
	    return numero;
	}
}
