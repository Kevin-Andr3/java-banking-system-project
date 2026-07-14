package repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import conexión.ConexiónMySQL;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Solicitud;

public class RepositorioSolicitud {
	public static ArrayList<Solicitud> listarSolicitud() {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
    	ResultSet resultSet = null;
        ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
        try {
        	connection = ConexiónMySQL.getconexión();
        	callableStatement = connection.prepareCall("{CALL sp_listarSolicitud()}");
            resultSet = callableStatement.executeQuery();
            Timestamp fechaHoraBloqueo;
            Solicitud solicitud;
            while (resultSet.next()) {
                solicitud = new Solicitud();
                solicitud.setIdSolicitud(resultSet.getString(1));
                solicitud.setAsunto(resultSet.getString(2));
                solicitud.setEstado(resultSet.getString(3));
                solicitud.setFechaCreacion(resultSet.getDate(4).toLocalDate());
                Date fechaResolucion = resultSet.getDate(5);
                solicitud.setFechaResolucion(fechaResolucion != null ? fechaResolucion.toLocalDate() : null);
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(8));
                cliente.setDni(resultSet.getString(9));
                cliente.setNombres(resultSet.getString(10));
                cliente.setApellidos(resultSet.getString(11));
                cliente.setTelefono(resultSet.getString(12));
                cliente.setDireccion(resultSet.getString(13));
                cliente.setCorreo(resultSet.getString(14));
                cliente.setContraseña(resultSet.getString(15));
                fechaHoraBloqueo = resultSet.getTimestamp(16);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                solicitud.setCliente(cliente);
                Empleado empleado = new Empleado();
                empleado.setIdPersona(resultSet.getString(17));
				empleado.setDni(resultSet.getString(18));
				empleado.setNombres(resultSet.getString(19));
				empleado.setApellidos(resultSet.getString(20));
				empleado.setTelefono(resultSet.getString(21));
				empleado.setDireccion(resultSet.getString(22));
				empleado.setCorreo(resultSet.getString(23));
				empleado.setContraseña(resultSet.getString(24));
				fechaHoraBloqueo = resultSet.getTimestamp(25);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                solicitud.setEmpleado(empleado);
                solicitudes.add(solicitud);
            }
        } catch (Exception e) {
            System.out.println("Error al listar solicitud: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return solicitudes;
	}
	public static void insertarSolicitud(Solicitud solicitud) {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
        try {
        	String procedimientoAlmacenado = "{CALL sp_insertarSolicitud(?, ?, ?, ?, ?, ?, ?)}";
        	connection = ConexiónMySQL.getconexión();
            callableStatement = connection.prepareCall(procedimientoAlmacenado);
            callableStatement.setString(1, solicitud.getIdSolicitud());
            callableStatement.setString(2, solicitud.getAsunto());
            callableStatement.setString(3, solicitud.getEstado());
            callableStatement.setDate(4, Date.valueOf((solicitud.getFechaCreacion())));
            callableStatement.setDate(5, null);
            callableStatement.setString(6, solicitud.getCliente().getIdPersona());
            callableStatement.setString(7, solicitud.getEmpleado().getIdPersona());
            callableStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error al insertar solicitud: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}
	public static void actualizarSolicitud(Solicitud solicitud) {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
        try {
        	String procedimientoAlmacenado = "{CALL sp_actualizarSolicitud(?, ?, ?)}";
        	connection = ConexiónMySQL.getconexión();
        	callableStatement = connection.prepareCall(procedimientoAlmacenado);
        	callableStatement.setString(1, solicitud.getIdSolicitud());
        	callableStatement.setString(2, solicitud.getEstado());
        	callableStatement.setDate(3, Date.valueOf(solicitud.getFechaResolucion()));
        	callableStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error al actualizar solicitud: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
    }
	public static Solicitud consultarIdSolicitud(String idSolicitud) {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
    	ResultSet resultSet = null;
    	Solicitud solicitud = null;
        try {
        	String procedimientoAlmacenado = "{CALL sp_consultarIdSolicitud(?)}";
        	connection = ConexiónMySQL.getconexión();
        	callableStatement = connection.prepareCall(procedimientoAlmacenado);
        	callableStatement.setString(1, idSolicitud);
            resultSet = callableStatement.executeQuery();
            Timestamp fechaHoraBloqueo;
            if (resultSet.next()) {
            	solicitud = new Solicitud();
                solicitud.setIdSolicitud(resultSet.getString(1));
                solicitud.setAsunto(resultSet.getString(2));
                solicitud.setEstado(resultSet.getString(3));
                solicitud.setFechaCreacion(resultSet.getDate(4).toLocalDate());
                Date fechaResolucion = resultSet.getDate(5);
                solicitud.setFechaResolucion(fechaResolucion != null ? fechaResolucion.toLocalDate() : null);
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(8));
                cliente.setDni(resultSet.getString(9));
                cliente.setNombres(resultSet.getString(10));
                cliente.setApellidos(resultSet.getString(11));
                cliente.setTelefono(resultSet.getString(12));
                cliente.setDireccion(resultSet.getString(13));
                cliente.setCorreo(resultSet.getString(14));
                cliente.setContraseña(resultSet.getString(15));
                fechaHoraBloqueo = resultSet.getTimestamp(16);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                solicitud.setCliente(cliente);
                Empleado empleado = new Empleado();
                empleado.setIdPersona(resultSet.getString(17));
				empleado.setDni(resultSet.getString(18));
				empleado.setNombres(resultSet.getString(19));
				empleado.setApellidos(resultSet.getString(20));
				empleado.setTelefono(resultSet.getString(21));
				empleado.setDireccion(resultSet.getString(22));
				empleado.setCorreo(resultSet.getString(23));
				empleado.setContraseña(resultSet.getString(24));
				fechaHoraBloqueo = resultSet.getTimestamp(25);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                solicitud.setEmpleado(empleado);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar id de solicitud: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return solicitud;
    }
	public static ArrayList<Solicitud> consultarSolicitudCliente(String idCliente) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
        ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
        try {
        	String consulta = "SELECT s.*, pc.*, pe.* FROM solicitudes s "
        			+ "INNER JOIN clientes c ON c.id_cliente = s.id_cliente "
        			+ "INNER JOIN personas pc ON pc.id_persona = c.id_cliente "
        			+ "INNER JOIN empleados e ON e.id_empleado = s.id_empleado "
        			+ "INNER JOIN personas pe ON pe.id_persona = e.id_empleado "
					+ "WHERE s.id_cliente = ?;";
        	connection = ConexiónMySQL.getconexión();
        	preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, idCliente);
            resultSet = preparedStatement.executeQuery();
            Timestamp fechaHoraBloqueo;
            Solicitud solicitud;
            while (resultSet.next()) {
            	solicitud = new Solicitud();
                solicitud.setIdSolicitud(resultSet.getString(1));
                solicitud.setAsunto(resultSet.getString(2));
                solicitud.setEstado(resultSet.getString(3));
                solicitud.setFechaCreacion(resultSet.getDate(4).toLocalDate());
                Date fechaResolucion = resultSet.getDate(5);
                solicitud.setFechaResolucion(fechaResolucion != null ? fechaResolucion.toLocalDate() : null);
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(8));
                cliente.setDni(resultSet.getString(9));
                cliente.setNombres(resultSet.getString(10));
                cliente.setApellidos(resultSet.getString(11));
                cliente.setTelefono(resultSet.getString(12));
                cliente.setDireccion(resultSet.getString(13));
                cliente.setCorreo(resultSet.getString(14));
                cliente.setContraseña(resultSet.getString(15));
                fechaHoraBloqueo = resultSet.getTimestamp(16);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                solicitud.setCliente(cliente);
                Empleado empleado = new Empleado();
                empleado.setIdPersona(resultSet.getString(17));
				empleado.setDni(resultSet.getString(18));
				empleado.setNombres(resultSet.getString(19));
				empleado.setApellidos(resultSet.getString(20));
				empleado.setTelefono(resultSet.getString(21));
				empleado.setDireccion(resultSet.getString(22));
				empleado.setCorreo(resultSet.getString(23));
				empleado.setContraseña(resultSet.getString(24));
				fechaHoraBloqueo = resultSet.getTimestamp(25);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                solicitud.setEmpleado(empleado);
                solicitudes.add(solicitud);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar solicitud por cliente: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return solicitudes;
	}
	public static ArrayList<Solicitud> consultarSolicitudEmpleado(String idEmpleado) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
        ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
        try {
        	String consulta = "SELECT s.*, pc.*, pe.* FROM solicitudes s "
        			+ "INNER JOIN clientes c ON c.id_cliente = s.id_cliente "
        			+ "INNER JOIN personas pc ON pc.id_persona = c.id_cliente "
        			+ "INNER JOIN empleados e ON e.id_empleado = s.id_empleado "
        			+ "INNER JOIN personas pe ON pe.id_persona = e.id_empleado "
					+ "WHERE s.id_empleado = ?;";
        	connection = ConexiónMySQL.getconexión();
        	preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, idEmpleado);
            resultSet = preparedStatement.executeQuery();
            Timestamp fechaHoraBloqueo;
            Solicitud solicitud;
            while (resultSet.next()) {
            	solicitud = new Solicitud();
                solicitud.setIdSolicitud(resultSet.getString(1));
                solicitud.setAsunto(resultSet.getString(2));
                solicitud.setEstado(resultSet.getString(3));
                solicitud.setFechaCreacion(resultSet.getDate(4).toLocalDate());
                Date fechaResolucion = resultSet.getDate(5);
                solicitud.setFechaResolucion(fechaResolucion != null ? fechaResolucion.toLocalDate() : null);
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(8));
                cliente.setDni(resultSet.getString(9));
                cliente.setNombres(resultSet.getString(10));
                cliente.setApellidos(resultSet.getString(11));
                cliente.setTelefono(resultSet.getString(12));
                cliente.setDireccion(resultSet.getString(13));
                cliente.setCorreo(resultSet.getString(14));
                cliente.setContraseña(resultSet.getString(15));
                fechaHoraBloqueo = resultSet.getTimestamp(16);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                solicitud.setCliente(cliente);
                Empleado empleado = new Empleado();
                empleado.setIdPersona(resultSet.getString(17));
				empleado.setDni(resultSet.getString(18));
				empleado.setNombres(resultSet.getString(19));
				empleado.setApellidos(resultSet.getString(20));
				empleado.setTelefono(resultSet.getString(21));
				empleado.setDireccion(resultSet.getString(22));
				empleado.setCorreo(resultSet.getString(23));
				empleado.setContraseña(resultSet.getString(24));
				fechaHoraBloqueo = resultSet.getTimestamp(25);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                solicitud.setEmpleado(empleado);
                solicitudes.add(solicitud);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar solicitud por empleado: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return solicitudes;
	}
}