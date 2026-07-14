package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Solicitud {
	private String idSolicitud;
	private String asunto;
	private String estado;
	private LocalDate fechaCreacion;
	private LocalDate fechaResolucion;
	private Cliente cliente;
	private Empleado empleado;
	public Solicitud() {}
	public Solicitud(String asunto, Cliente cliente, Empleado empleado) {
		idSolicitud = UUID.randomUUID().toString();
		this.asunto = asunto;
		estado = "pendiente";
		fechaCreacion = LocalDate.now();
		fechaResolucion = null;
		this.cliente = cliente;
		this.empleado = empleado;
	}
	public String getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDate getFechaResolucion() {
		return fechaResolucion;
	}
	public void setFechaResolucion(LocalDate fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public String getFechaCreacionFormateada() {
		return fechaCreacion.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
	}
	public String getFechaResolucionFormateada() {
		return fechaResolucion == null ? "--/--/--" : fechaResolucion.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
	}
}
