package repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import conexión.ConexiónMySQL;
import modelo.Cliente;

public class RepositorioCliente {
	public static ArrayList<Cliente> listarCliente(){
		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String procedimientoAlmacenado = "{CALL sp_listarCliente()}";
			connection = ConexiónMySQL.getconexión();
			callableStatement = connection.prepareCall(procedimientoAlmacenado);
			resultSet = callableStatement.executeQuery();
			Cliente cliente;
			while (resultSet.next()) {
				cliente = new Cliente();
				cliente.setIdPersona(resultSet.getString(1));
                cliente.setDni(resultSet.getString(2));
                cliente.setNombres(resultSet.getString(3));
                cliente.setApellidos(resultSet.getString(4));
                cliente.setTelefono(resultSet.getString(5));
                cliente.setDireccion(resultSet.getString(6));
                cliente.setCorreo(resultSet.getString(7));
                cliente.setContraseña(resultSet.getString(8));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
				clientes.add(cliente);
			}
		} catch (Exception e) {
			System.out.println("Error al listar cliente: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return clientes;
	}
	public static void insertarCliente(Cliente cliente) {
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			String procedimientoAlmacenado = "{CALL sp_insertarCliente(?,?,?,?,?,?,?,?,?)}";
			connection = ConexiónMySQL.getconexión();
			callableStatement = connection.prepareCall(procedimientoAlmacenado);
			callableStatement.setString(1, cliente.getIdPersona());
			callableStatement.setString(2, cliente.getDni());
			callableStatement.setString(3, cliente.getNombres());
			callableStatement.setString(4, cliente.getApellidos());
			callableStatement.setString(5, cliente.getTelefono());
			callableStatement.setString(6, cliente.getDireccion());
			callableStatement.setString(7, cliente.getCorreo());
			callableStatement.setString(8, cliente.getContraseña());
			LocalDateTime fechaHoraBloqueo = cliente.getFechaHoraBloqueo();
			callableStatement.setTimestamp(9, fechaHoraBloqueo == null ? null : Timestamp.valueOf(fechaHoraBloqueo));
			callableStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar cliente: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}
	public static void actualizarCliente(Cliente cliente) {
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			String procedimientoAlmacenado = "{CALL sp_actualizarCliente(?,?,?,?,?,?,?,?,?)}";
			connection = ConexiónMySQL.getconexión();
			callableStatement = connection.prepareCall(procedimientoAlmacenado);
			callableStatement.setString(1, cliente.getIdPersona());
			callableStatement.setString(2, cliente.getDni());
			callableStatement.setString(3, cliente.getNombres());
			callableStatement.setString(4, cliente.getApellidos());
			callableStatement.setString(5, cliente.getTelefono());
			callableStatement.setString(6, cliente.getDireccion());
			callableStatement.setString(7, cliente.getCorreo());
			callableStatement.setString(8, cliente.getContraseña());
			LocalDateTime fechaHoraBloqueo = cliente.getFechaHoraBloqueo();
			callableStatement.setTimestamp(9, fechaHoraBloqueo == null ? null : Timestamp.valueOf(fechaHoraBloqueo));
			callableStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar cliente: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}
	public static Cliente consultarIdCliente(String idPersona) {
		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		Cliente cliente = null;
		try {
			String procedimientoAlmacenado = "{CALL sp_consultarIdCliente(?)}";
			connection = ConexiónMySQL.getconexión();
			callableStatement = connection.prepareCall(procedimientoAlmacenado);
			callableStatement.setString(1, idPersona);
			resultSet = callableStatement.executeQuery();
			if (resultSet.next()) {
				cliente = new Cliente();
				cliente.setIdPersona(resultSet.getString(1));
                cliente.setDni(resultSet.getString(2));
                cliente.setNombres(resultSet.getString(3));
                cliente.setApellidos(resultSet.getString(4));
                cliente.setTelefono(resultSet.getString(5));
                cliente.setDireccion(resultSet.getString(6));
                cliente.setCorreo(resultSet.getString(7));
                cliente.setContraseña(resultSet.getString(8));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
			}
		} catch (Exception e) {
			System.out.println("Error al consultar id de cliente: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return cliente;
	}
	public static Cliente consultarClienteDni(String dni) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cliente cliente = null;
		try {
			String consulta = "SELECT p.* FROM clientes c "
					+ "INNER JOIN personas p ON c.id_cliente = p.id_persona "
					+ "WHERE p.dni = ?;";
			connection = ConexiónMySQL.getconexión();
			preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, dni);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				cliente = new Cliente();
				cliente.setIdPersona(resultSet.getString(1));
                cliente.setDni(resultSet.getString(2));
                cliente.setNombres(resultSet.getString(3));
                cliente.setApellidos(resultSet.getString(4));
                cliente.setTelefono(resultSet.getString(5));
                cliente.setDireccion(resultSet.getString(6));
                cliente.setCorreo(resultSet.getString(7));
                cliente.setContraseña(resultSet.getString(8));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
			}
		} catch (Exception e) {
			System.out.println("Error al consultar cliente por dni: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return cliente;
	}
	public static Cliente consultarCliente(String correo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cliente cliente = null;
		try {
			String consulta = "SELECT p.* FROM clientes c "
					+ "INNER JOIN personas p ON c.id_cliente = p.id_persona "
					+ "WHERE p.correo = ?;";
			connection = ConexiónMySQL.getconexión();
			preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, correo);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				cliente = new Cliente();
				cliente.setIdPersona(resultSet.getString(1));
                cliente.setDni(resultSet.getString(2));
                cliente.setNombres(resultSet.getString(3));
                cliente.setApellidos(resultSet.getString(4));
                cliente.setTelefono(resultSet.getString(5));
                cliente.setDireccion(resultSet.getString(6));
                cliente.setCorreo(resultSet.getString(7));
                cliente.setContraseña(resultSet.getString(8));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
			}
		} catch (Exception e) {
			System.out.println("Error al consultar cliente por correo: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return cliente;
	}
	public static Cliente consultarCliente(String correo, String contraseña) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cliente cliente = null;
		try {
			String consulta = "SELECT p.* FROM clientes c "
					+ "INNER JOIN personas p ON c.id_cliente = p.id_persona "
					+ "WHERE p.correo = ? AND p.contraseña = ?;";
			connection = ConexiónMySQL.getconexión();
			preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, correo);
			preparedStatement.setString(2, contraseña);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				cliente = new Cliente();
				cliente.setIdPersona(resultSet.getString(1));
                cliente.setDni(resultSet.getString(2));
                cliente.setNombres(resultSet.getString(3));
                cliente.setApellidos(resultSet.getString(4));
                cliente.setTelefono(resultSet.getString(5));
                cliente.setDireccion(resultSet.getString(6));
                cliente.setCorreo(resultSet.getString(7));
                cliente.setContraseña(resultSet.getString(8));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
			}
		} catch (Exception e) {
			System.out.println("Error al consultar cliente por correo y contraseña: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return cliente;
	}
	public static ArrayList<Cliente> consultarCliente(String dni, String nombres, String apellidos){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String consulta = "SELECT p.* FROM clientes c "
					+ "INNER JOIN personas p ON c.id_cliente = p.id_persona "
					+ "WHERE p.dni LIKE '%" + dni 
					+ "%' AND p.nombres LIKE '%" + nombres 
					+ "%' AND p.apellidos LIKE '%" + apellidos + "%';";
			connection = ConexiónMySQL.getconexión();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(consulta);
			Cliente cliente;
			while (resultSet.next()) {
				cliente = new Cliente();
				cliente.setIdPersona(resultSet.getString(1));
                cliente.setDni(resultSet.getString(2));
                cliente.setNombres(resultSet.getString(3));
                cliente.setApellidos(resultSet.getString(4));
                cliente.setTelefono(resultSet.getString(5));
                cliente.setDireccion(resultSet.getString(6));
                cliente.setCorreo(resultSet.getString(7));
                cliente.setContraseña(resultSet.getString(8));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
				clientes.add(cliente);
			}
		} catch (Exception e) {
			System.out.println("Error al consultar cliente por dni, nombres y apellidos: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(statement != null) statement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return clientes;
	}
}
