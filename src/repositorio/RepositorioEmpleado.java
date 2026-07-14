package repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import conexión.ConexiónMySQL;
import modelo.Empleado;

public class RepositorioEmpleado {
	public static ArrayList<Empleado> listarEmpleado(){
		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		try {
			String procedimientoAlmacenado = "{CALL sp_listarEmpleado()}";
			connection = ConexiónMySQL.getconexión();
			callableStatement = connection.prepareCall(procedimientoAlmacenado);
			resultSet = callableStatement.executeQuery();
			Empleado empleado;
			while (resultSet.next()) {
				empleado = new Empleado();
				empleado.setIdPersona(resultSet.getString(1));
				empleado.setDni(resultSet.getString(2));
				empleado.setNombres(resultSet.getString(3));
				empleado.setApellidos(resultSet.getString(4));
				empleado.setTelefono(resultSet.getString(5));
				empleado.setDireccion(resultSet.getString(6));
				empleado.setCorreo(resultSet.getString(7));
				empleado.setContraseña(resultSet.getString(8));
				Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
				empleados.add(empleado);
			}
		} catch (Exception e) {
			System.out.println("Error al listar empleado: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return empleados;
	}
	public static void insertarEmpleado(Empleado empleado) {
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			String procedimientoAlmacenado = "{CALL sp_insertarEmpleado(?,?,?,?,?,?,?,?,?)}";
			connection = ConexiónMySQL.getconexión();
			callableStatement = connection.prepareCall(procedimientoAlmacenado);
			callableStatement.setString(1, empleado.getIdPersona());
			callableStatement.setString(2, empleado.getDni());
			callableStatement.setString(3, empleado.getNombres());
			callableStatement.setString(4, empleado.getApellidos());
			callableStatement.setString(5, empleado.getTelefono());
			callableStatement.setString(6, empleado.getDireccion());
			callableStatement.setString(7, empleado.getCorreo());
			callableStatement.setString(8, empleado.getContraseña());
			LocalDateTime fechaHoraBloqueo = empleado.getFechaHoraBloqueo();
			callableStatement.setTimestamp(9, fechaHoraBloqueo == null ? null : Timestamp.valueOf(fechaHoraBloqueo));
			callableStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar empleado: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}
	public static void actualizarEmpleado(Empleado empleado) {
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			String procedimientoAlmacenado = "{CALL sp_actualizarEmpleado(?,?,?,?,?,?,?,?,?)}";
			connection = ConexiónMySQL.getconexión();
			callableStatement = connection.prepareCall(procedimientoAlmacenado);
			callableStatement.setString(1, empleado.getIdPersona());
			callableStatement.setString(2, empleado.getDni());
			callableStatement.setString(3, empleado.getNombres());
			callableStatement.setString(4, empleado.getApellidos());
			callableStatement.setString(5, empleado.getTelefono());
			callableStatement.setString(6, empleado.getDireccion());
			callableStatement.setString(7, empleado.getCorreo());
			callableStatement.setString(8, empleado.getContraseña());
			LocalDateTime fechaHoraBloqueo = empleado.getFechaHoraBloqueo();
			callableStatement.setTimestamp(9, fechaHoraBloqueo == null ? null : Timestamp.valueOf(fechaHoraBloqueo));
			callableStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar empleado: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}
	public static Empleado consultarIdEmpleado(String idPersona) {
		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		Empleado empleado = null;
		try {
			String procedimientoAlmacenado = "{CALL sp_consultarIdEmpleado(?)}";
			connection = ConexiónMySQL.getconexión();
			callableStatement = connection.prepareCall(procedimientoAlmacenado);
			callableStatement.setString(1, idPersona);
			resultSet = callableStatement.executeQuery();
			if (resultSet.next()) {
				empleado = new Empleado();
				empleado.setIdPersona(resultSet.getString(1));
				empleado.setDni(resultSet.getString(2));
				empleado.setNombres(resultSet.getString(3));
				empleado.setApellidos(resultSet.getString(4));
				empleado.setTelefono(resultSet.getString(5));
				empleado.setDireccion(resultSet.getString(6));
				empleado.setCorreo(resultSet.getString(7));
				empleado.setContraseña(resultSet.getString(8));
				Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
			}
		} catch (Exception e) {
			System.out.println("Error al consultar id de empleado: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return empleado;
	}
	public static Empleado consultarEmpleadoDni(String dni) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Empleado empleado = null;
		try {
			String consulta = "SELECT p.* FROM empleados e "
					+ "INNER JOIN personas p ON e.id_empleado = p.id_persona "
					+ "WHERE p.dni = ?;";
			connection = ConexiónMySQL.getconexión();
			preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, dni);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				empleado = new Empleado();
				empleado.setIdPersona(resultSet.getString(1));
				empleado.setDni(resultSet.getString(2));
				empleado.setNombres(resultSet.getString(3));
				empleado.setApellidos(resultSet.getString(4));
				empleado.setTelefono(resultSet.getString(5));
				empleado.setDireccion(resultSet.getString(6));
				empleado.setCorreo(resultSet.getString(7));
				empleado.setContraseña(resultSet.getString(8));
				Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
			}
		} catch (Exception e) {
			System.out.println("Error al consultar empleado por dni: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return empleado;
	}
	public static Empleado consultarEmpleado(String correo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Empleado empleado = null;
		try {
			String consulta = "SELECT p.* FROM empleados e "
					+ "INNER JOIN personas p ON e.id_empleado = p.id_persona "
					+ "WHERE p.correo = ?;";
			connection = ConexiónMySQL.getconexión();
			preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, correo);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				empleado = new Empleado();
				empleado.setIdPersona(resultSet.getString(1));
				empleado.setDni(resultSet.getString(2));
				empleado.setNombres(resultSet.getString(3));
				empleado.setApellidos(resultSet.getString(4));
				empleado.setTelefono(resultSet.getString(5));
				empleado.setDireccion(resultSet.getString(6));
				empleado.setCorreo(resultSet.getString(7));
				empleado.setContraseña(resultSet.getString(8));
				Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
			}
		} catch (Exception e) {
			System.out.println("Error al consultar empleado por correo: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return empleado;
	}
	public static Empleado consultarEmpleado(String correo, String contraseña) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Empleado empleado = null;
		try {
			String consulta = "SELECT p.* FROM empleados e "
					+ "INNER JOIN personas p ON e.id_empleado = p.id_persona "
					+ "WHERE p.correo = ? AND p.contraseña = ?;";
			connection = ConexiónMySQL.getconexión();
			preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, correo);
			preparedStatement.setString(2, contraseña);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				empleado = new Empleado();
				empleado.setIdPersona(resultSet.getString(1));
				empleado.setDni(resultSet.getString(2));
				empleado.setNombres(resultSet.getString(3));
				empleado.setApellidos(resultSet.getString(4));
				empleado.setTelefono(resultSet.getString(5));
				empleado.setDireccion(resultSet.getString(6));
				empleado.setCorreo(resultSet.getString(7));
				empleado.setContraseña(resultSet.getString(8));
				Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
			}
		} catch (Exception e) {
			System.out.println("Error al consultar empleado por correo y contraseña: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return empleado;
	}
	public static Empleado consultarEmpleadoAleatorio() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Empleado empleado = null;
		try {
			String consulta = "SELECT p.* FROM empleados e "
					+ "INNER JOIN personas p ON e.id_empleado = p.id_persona "
					+ "ORDER BY RAND() LIMIT 1";
			connection = ConexiónMySQL.getconexión();
			preparedStatement = connection.prepareStatement(consulta);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				empleado = new Empleado();
				empleado.setIdPersona(resultSet.getString(1));
				empleado.setDni(resultSet.getString(2));
				empleado.setNombres(resultSet.getString(3));
				empleado.setApellidos(resultSet.getString(4));
				empleado.setTelefono(resultSet.getString(5));
				empleado.setDireccion(resultSet.getString(6));
				empleado.setCorreo(resultSet.getString(7));
				empleado.setContraseña(resultSet.getString(8));
				Timestamp fechaHoraBloqueo = resultSet.getTimestamp(9);
				empleado.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
			}
		} catch (Exception e) {
			System.out.println("Error al consultar empleado aleatorio: " + e);
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
		return empleado;
	}
}
