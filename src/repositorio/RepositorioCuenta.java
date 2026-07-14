package repositorio;

import java.sql.*;
import java.util.ArrayList;

import conexión.ConexiónMySQL;
import modelo.Cliente;
import modelo.Cuenta;

public class RepositorioCuenta {
    public static ArrayList<Cuenta> listarCuenta() {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        try {
        	connection = ConexiónMySQL.getconexión();
        	callableStatement = connection.prepareCall("{CALL sp_listarCuenta()}");
            ResultSet resultSet = callableStatement.executeQuery();
            Cuenta cuenta;
            while (resultSet.next()) {
                cuenta = new Cuenta();
                cuenta.setNumeroCuenta(resultSet.getString(1));
                cuenta.setSaldoContable(resultSet.getDouble(2));
                cuenta.setSaldoDisponible(resultSet.getDouble(3));
                cuenta.setFechaCreacion(resultSet.getDate(4).toLocalDate());
                cuenta.setEstado(resultSet.getString(5));
                cuenta.setTipoCuenta(resultSet.getString(6));
                cuenta.setMoneda(resultSet.getString(7));
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(9));
                cliente.setDni(resultSet.getString(10));
                cliente.setNombres(resultSet.getString(11));
                cliente.setApellidos(resultSet.getString(12));
                cliente.setTelefono(resultSet.getString(13));
                cliente.setDireccion(resultSet.getString(14));
                cliente.setCorreo(resultSet.getString(15));
                cliente.setContraseña(resultSet.getString(16));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(17);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                cuenta.setCliente(cliente);
                cuentas.add(cuenta);
            }
        } catch (Exception e) {
            System.out.println("Error al listar cuenta: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return cuentas;
    }
    public static void insertarCuenta(Cuenta cuenta) {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
        try {
        	String procedimientoAlmacenado = "{CALL sp_insertarCuenta(?, ?, ?, ?, ?, ?, ?, ?)}";
        	connection = ConexiónMySQL.getconexión();
            callableStatement = connection.prepareCall(procedimientoAlmacenado);
            callableStatement.setString(1, cuenta.getNumeroCuenta());
            callableStatement.setDouble(2, cuenta.getSaldoContable());
            callableStatement.setDouble(3, cuenta.getSaldoDisponible());
            callableStatement.setDate(4, Date.valueOf(cuenta.getFechaCreacion()));
            callableStatement.setString(5, cuenta.getEstado());
            callableStatement.setString(6, cuenta.getTipoCuenta());
            callableStatement.setString(7, cuenta.getMoneda());
            callableStatement.setString(8, cuenta.getCliente().getIdPersona());
            callableStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error al insertar cuenta: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
    }
    public static Cuenta consultarNumeroCuenta(String numeroCuenta) {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
    	ResultSet resultSet = null;
        Cuenta cuenta = null;
        try {
        	String procedimientoAlmacenado = "{CALL sp_consultarNumeroCuenta(?)}";
        	connection = ConexiónMySQL.getconexión();
        	callableStatement = connection.prepareCall(procedimientoAlmacenado);
        	callableStatement.setString(1, numeroCuenta);
            resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                cuenta = new Cuenta();
                cuenta.setNumeroCuenta(resultSet.getString(1));
                cuenta.setSaldoContable(resultSet.getDouble(2));
                cuenta.setSaldoDisponible(resultSet.getDouble(3));
                cuenta.setFechaCreacion(resultSet.getDate(4).toLocalDate());
                cuenta.setEstado(resultSet.getString(5));
                cuenta.setTipoCuenta(resultSet.getString(6));
                cuenta.setMoneda(resultSet.getString(7));
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(9));
                cliente.setDni(resultSet.getString(10));
                cliente.setNombres(resultSet.getString(11));
                cliente.setApellidos(resultSet.getString(12));
                cliente.setTelefono(resultSet.getString(13));
                cliente.setDireccion(resultSet.getString(14));
                cliente.setCorreo(resultSet.getString(15));
                cliente.setContraseña(resultSet.getString(16));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(17);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                cuenta.setCliente(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar número de cuenta: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return cuenta;
    }
    public static void actualizarCuenta(Cuenta cuenta) {
    	Connection connection = null;
    	CallableStatement callableStatement = null;
        try {
        	String procedimientoAlmacenado = "{CALL sp_actualizarCuenta(?, ?, ?, ?)}";
        	connection = ConexiónMySQL.getconexión();
        	callableStatement = connection.prepareCall(procedimientoAlmacenado);
        	callableStatement.setString(1, cuenta.getNumeroCuenta());
        	callableStatement.setDouble(2, cuenta.getSaldoContable());
        	callableStatement.setDouble(3, cuenta.getSaldoDisponible());
        	callableStatement.setString(4, cuenta.getEstado());
        	callableStatement.executeQuery();
        } catch (Exception e) {
            System.out.println("Error al actualizar cuenta: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(callableStatement != null) callableStatement.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
    }
    public static ArrayList<Cuenta> consultarCuenta(String idCliente) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        try {
        	String consulta = "SELECT cu.*, p.* FROM cuentas cu "
        			+ "INNER JOIN clientes cl ON cl.id_cliente = cu.id_cliente "
        			+ "INNER JOIN personas p ON p.id_persona = cl.id_cliente "
        			+ "WHERE cu.id_cliente = ?;";
        	connection = ConexiónMySQL.getconexión();
        	preparedStatement = connection.prepareCall(consulta);
        	preparedStatement.setString(1, idCliente);
            resultSet = preparedStatement.executeQuery();
            Cuenta cuenta;
            while (resultSet.next()) {
            	cuenta = new Cuenta();
                cuenta.setNumeroCuenta(resultSet.getString(1));
                cuenta.setSaldoContable(resultSet.getDouble(2));
                cuenta.setSaldoDisponible(resultSet.getDouble(3));
                cuenta.setFechaCreacion(resultSet.getDate(4).toLocalDate());
                cuenta.setEstado(resultSet.getString(5));
                cuenta.setTipoCuenta(resultSet.getString(6));
                cuenta.setMoneda(resultSet.getString(7));
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getString(9));
                cliente.setDni(resultSet.getString(10));
                cliente.setNombres(resultSet.getString(11));
                cliente.setApellidos(resultSet.getString(12));
                cliente.setTelefono(resultSet.getString(13));
                cliente.setDireccion(resultSet.getString(14));
                cliente.setCorreo(resultSet.getString(15));
                cliente.setContraseña(resultSet.getString(16));
                Timestamp fechaHoraBloqueo = resultSet.getTimestamp(17);
                cliente.setFechaHoraBloqueo(fechaHoraBloqueo == null? null:fechaHoraBloqueo.toLocalDateTime());
                cuenta.setCliente(cliente);
                cuentas.add(cuenta);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar cuenta por id del cliente: " + e);
        } finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
        return cuentas;
    }
}
