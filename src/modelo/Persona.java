package modelo;

import java.time.LocalDateTime;
import java.util.UUID;

public class Persona {
	protected String idPersona;
	protected String dni;
	protected String nombres;
	protected String apellidos;
	protected String telefono;
	protected String direccion;
	protected String correo;
	protected String contraseña;
	protected LocalDateTime fechaHoraBloqueo;
	public Persona() {}
	public Persona(String dni, String nombres, String apellidos, String telefono, String direccion, String correo,
			String contraseña) {
		this.idPersona = UUID.randomUUID().toString();
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.contraseña = contraseña;
		this.fechaHoraBloqueo = null;
	}
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public LocalDateTime getFechaHoraBloqueo() {
		return fechaHoraBloqueo;
	}
	public void setFechaHoraBloqueo(LocalDateTime fechaHoraBloqueo) {
		this.fechaHoraBloqueo = fechaHoraBloqueo;
	}
}
