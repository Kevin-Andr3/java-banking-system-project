package repositorio;

import java.sql.*;
import java.util.ArrayList;

import conexión.ConexiónMySQL;
import modelo.Cliente;
import modelo.Transaccion;

public class RepositorioTransaccion {
	public static ArrayList<Transaccion> listarTransaccion() {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
        ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
        try {
        	connection = ConexiónMySQL.getconexión();
        	callableStatement = connection.prepareCall("{CALL sp_listarTransaccion()}");
            ResultSet resultSet = callableStatement.executeQuery();
            Transaccion transaccion;
            while (resultSet.next()) {
                transaccion = new Transaccion();
                transaccion.setIdTransaccion(resultSet.getString(1));
                transaccion.setTipoTransaccion(resultSet.getString(2));
                transaccion.setDescripcion(resultSet.getString(3));
                transaccion.setFechaHora(resultSet.getTimestamp(4).toLocalDateTime());
                transaccion.setMonto(resultSet.getDouble(5));
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(7));
                cliente.setDni(resultSet.getString(8));
                cliente.setNombres(resultSet.getString(9));
                cliente.setApellidos(resultSet.getString(10));
                cliente.setTelefono(resultSet.getString(11));
                cliente.setDireccion(resultSet.getString(12));
                cliente.setCorreo(resultSet.getString(13));
                cliente.setContraseña(resultSet.getString(14));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(15);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                transaccion.setCliente(cliente);
                transacciones.add(transaccion);
            }
        } catch (Exception e) {
            System.out.println("Error al listar transacción: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return transacciones;
	}
	public static void insertarTransaccion(Transaccion transaccion) {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
        try {
        	String procedimientoAlmacenado = "{CALL sp_insertarTransaccion(?, ?, ?, ?, ?, ?)}";
        	connection = ConexiónMySQL.getconexión();
            callableStatement = connection.prepareCall(procedimientoAlmacenado);
            callableStatement.setString(1, transaccion.getIdTransaccion());
            callableStatement.setString(2, transaccion.getTipoTransaccion());
            callableStatement.setString(3, transaccion.getDescripcion());
            callableStatement.setTimestamp(4, Timestamp.valueOf((transaccion.getFechaHora())));
            callableStatement.setDouble(5, transaccion.getMonto());
            callableStatement.setString(6, transaccion.getCliente().getIdPersona());
            callableStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error al insertar transacción: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}
	public static Transaccion consultarIdTransaccion(String idTransaccion) {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
    	ResultSet resultSet = null;
    	Transaccion transaccion= null;
        try {
        	String procedimientoAlmacenado = "{CALL sp_consultarIdTransaccion(?)}";
        	connection = ConexiónMySQL.getconexión();
        	callableStatement = connection.prepareCall(procedimientoAlmacenado);
        	callableStatement.setString(1, idTransaccion);
            resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
            	transaccion = new Transaccion();
                transaccion.setIdTransaccion(resultSet.getString(1));
                transaccion.setTipoTransaccion(resultSet.getString(2));
                transaccion.setDescripcion(resultSet.getString(3));
                transaccion.setFechaHora(resultSet.getTimestamp(4).toLocalDateTime());
                transaccion.setMonto(resultSet.getDouble(5));
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(7));
                cliente.setDni(resultSet.getString(8));
                cliente.setNombres(resultSet.getString(9));
                cliente.setApellidos(resultSet.getString(10));
                cliente.setTelefono(resultSet.getString(11));
                cliente.setDireccion(resultSet.getString(12));
                cliente.setCorreo(resultSet.getString(13));
                cliente.setContraseña(resultSet.getString(14));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(15);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                transaccion.setCliente(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar id de transacción: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return transaccion;
    }
	public static ArrayList<Transaccion> consultarTransaccionDescripcion(String descripcion) {
    	Connection connection = null;
    	Statement statement = null;
    	ResultSet resultSet = null;
    	ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
        try {
        	String consulta = "SELECT t.*, p.* FROM transacciones t "
        			+ "INNER JOIN clientes c ON c.id_cliente = t.id_cliente "
        			+ "INNER JOIN personas p ON p.id_persona = c.id_cliente "
        			+ "WHERE t.descripcion LIKE '%" + descripcion + "%';";
        	connection = ConexiónMySQL.getconexión();
        	statement = connection.createStatement();
            resultSet = statement.executeQuery(consulta);
            Transaccion transaccion;
            while (resultSet.next()) {
            	transaccion = new Transaccion();
                transaccion.setIdTransaccion(resultSet.getString(1));
                transaccion.setTipoTransaccion(resultSet.getString(2));
                transaccion.setDescripcion(resultSet.getString(3));
                transaccion.setFechaHora(resultSet.getTimestamp(4).toLocalDateTime());
                transaccion.setMonto(resultSet.getDouble(5));
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(7));
                cliente.setDni(resultSet.getString(8));
                cliente.setNombres(resultSet.getString(9));
                cliente.setApellidos(resultSet.getString(10));
                cliente.setTelefono(resultSet.getString(11));
                cliente.setDireccion(resultSet.getString(12));
                cliente.setCorreo(resultSet.getString(13));
                cliente.setContraseña(resultSet.getString(14));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(15);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                transaccion.setCliente(cliente);
                transacciones.add(transaccion);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar transacción por descripción: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(statement != null) statement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return transacciones;
    }
	public static ArrayList<Transaccion> consultarTransaccion(String idCliente) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
        try {
        	String consulta = "SELECT t.*, p.* FROM transacciones t "
        			+ "INNER JOIN clientes c ON c.id_cliente = t.id_cliente "
        			+ "INNER JOIN personas p ON p.id_persona = c.id_cliente "
        			+ "WHERE t.id_cliente = ?;";
        	connection = ConexiónMySQL.getconexión();
        	preparedStatement = connection.prepareStatement(consulta);
        	preparedStatement.setString(1, idCliente);
            resultSet = preparedStatement.executeQuery();
            Transaccion transaccion;
            while (resultSet.next()) {
            	transaccion = new Transaccion();
                transaccion.setIdTransaccion(resultSet.getString(1));
                transaccion.setTipoTransaccion(resultSet.getString(2));
                transaccion.setDescripcion(resultSet.getString(3));
                transaccion.setFechaHora(resultSet.getTimestamp(4).toLocalDateTime());
                transaccion.setMonto(resultSet.getDouble(5));
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(7));
                cliente.setDni(resultSet.getString(8));
                cliente.setNombres(resultSet.getString(9));
                cliente.setApellidos(resultSet.getString(10));
                cliente.setTelefono(resultSet.getString(11));
                cliente.setDireccion(resultSet.getString(12));
                cliente.setCorreo(resultSet.getString(13));
                cliente.setContraseña(resultSet.getString(14));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(15);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                transaccion.setCliente(cliente);
                transacciones.add(transaccion);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar transacción por id de cliente: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return transacciones;
    }
	public static ArrayList<Transaccion> consultarTransaccion(String idCliente, String tipoTransaccion, String descripcion) {
    	Connection connection = null;
    	Statement statement = null;
    	ResultSet resultSet = null;
    	ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
        try {
        	String consulta = "SELECT t.*, p.* FROM transacciones t "
        			+ "INNER JOIN clientes c ON c.id_cliente = t.id_cliente "
        			+ "INNER JOIN personas p ON p.id_persona = c.id_cliente "
        			+ "WHERE t.id_cliente = '" + idCliente
        			+ "' AND t.tipo_transaccion LIKE '%" + tipoTransaccion
        			+ "%' AND t.descripcion LIKE '%" + descripcion + "%'";
        	connection = ConexiónMySQL.getconexión();
        	statement = connection.createStatement();
            resultSet = statement.executeQuery(consulta);
            Transaccion transaccion;
            while (resultSet.next()) {
            	transaccion = new Transaccion();
                transaccion.setIdTransaccion(resultSet.getString(1));
                transaccion.setTipoTransaccion(resultSet.getString(2));
                transaccion.setDescripcion(resultSet.getString(3));
                transaccion.setFechaHora(resultSet.getTimestamp(4).toLocalDateTime());
                transaccion.setMonto(resultSet.getDouble(5));
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(7));
                cliente.setDni(resultSet.getString(8));
                cliente.setNombres(resultSet.getString(9));
                cliente.setApellidos(resultSet.getString(10));
                cliente.setTelefono(resultSet.getString(11));
                cliente.setDireccion(resultSet.getString(12));
                cliente.setCorreo(resultSet.getString(13));
                cliente.setContraseña(resultSet.getString(14));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(15);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                transaccion.setCliente(cliente);
                transacciones.add(transaccion);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar transacción por id de cliente, tipo de transacción y descripción: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(statement != null) statement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return transacciones;
    }
}
