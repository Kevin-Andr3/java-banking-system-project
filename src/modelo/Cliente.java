package modelo;
import java.util.ArrayList;

public class Cliente extends Persona {
	private ArrayList<Cuenta> cuentas;
	private ArrayList<Tarjeta> tarjetas;
	private ArrayList<Transaccion> transacciones;
	private ArrayList<Solicitud> solicitudes;
	public Cliente() {
		cuentas = new ArrayList<Cuenta>();
		tarjetas = new ArrayList<Tarjeta>();
		transacciones = new ArrayList<Transaccion>();
		solicitudes = new ArrayList<Solicitud>();
	}
    public Cliente(String dni, String nombres, String apellidos, String telefono, String direccion, String correo,
			String contraseña) {
		super(dni, nombres, apellidos, telefono, direccion, correo, contraseña);
		cuentas = new ArrayList<Cuenta>();
		tarjetas = new ArrayList<Tarjeta>();
		transacciones = new ArrayList<Transaccion>();
		solicitudes = new ArrayList<Solicitud>();
	}
	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	public ArrayList<Tarjeta> getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(ArrayList<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}
	public ArrayList<Transaccion> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(ArrayList<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}
	public ArrayList<Solicitud> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	public boolean agregarCuenta(Cuenta cuenta) {
        if (buscarCuenta(cuenta.getNumeroCuenta()) != null) return false;
        return cuentas.add(cuenta);
    }
    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) 
        	if (cuenta.getNumeroCuenta().equals(numeroCuenta)) 
        		return cuenta;
        return null;
    }
    public boolean agregarTarjeta(Tarjeta tarjeta) {
        if (buscarTarjeta(tarjeta.getNumeroTarjeta()) != null) return false;
        return tarjetas.add(tarjeta);
    }
    
    public Tarjeta buscarTarjeta(String numeroTarjeta) {
        for (Tarjeta tarjeta : tarjetas) 
        	if (tarjeta.getNumeroTarjeta().equals(numeroTarjeta)) 
        		return tarjeta;
        return null;
    }
    public boolean agregarTransaccion(Transaccion transaccion) {
        if (buscarTransaccion(transaccion.getIdTransaccion()) != null) return false;
        return transacciones.add(transaccion);
    }
    
    public Transaccion buscarTransaccion(String idTransaccion) {
        for (Transaccion transaccion : transacciones) 
        	if (transaccion.getIdTransaccion().equals(idTransaccion)) 
        		return transaccion;
        return null;
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
