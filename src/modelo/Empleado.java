package modelo;

import java.util.ArrayList;

public class Empleado extends Persona {
	private ArrayList<Solicitud> solicitudes;
	public Empleado() {
		solicitudes = new ArrayList<Solicitud>();
	}
	public Empleado(String dni, String nombres, String apellidos, String telefono, String direccion, String correo,
			String contraseña) {
		super(dni, nombres, apellidos, telefono, direccion, correo, contraseña);
		solicitudes = new ArrayList<>();
	}
	public ArrayList<Solicitud> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	public boolean agregarSolicitud(Solicitud solicitud) {
        if (buscarSolicitud(solicitud.getIdSolicitud()) != null) return false;
        return solicitudes.add(solicitud);
    }
	public Solicitud buscarSolicitud(String idSolicitud) {
        for (Solicitud solicitud : solicitudes) 
        	if (solicitud.getIdSolicitud().equals(idSolicitud)) 
        		return solicitud;
        return null;
    }
}
