package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaccion {
	private String idTransaccion;
	private String tipoTransaccion;
	private String descripcion;
	private LocalDateTime fechaHora;
	private double monto;
	private Cliente Cliente;
	public Transaccion() {}
	public Transaccion(String tipoTransaccion, String descripcion, double monto, Cliente Cliente) {
		idTransaccion = UUID.randomUUID().toString();
		this.tipoTransaccion = tipoTransaccion;
		this.descripcion = descripcion;
		fechaHora = LocalDateTime.now();
		this.monto = monto;
		this.Cliente = Cliente;
	}
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Cliente getCliente() {
		return Cliente;
	}
	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}
	public String getFechaHoraFormateada() {
		return fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss"));
	}
}
