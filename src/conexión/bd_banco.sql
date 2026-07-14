CREATE DATABASE bd_banco;
USE bd_banco;

# CREACIÓN DE TABLAS
CREATE TABLE personas(
	id_persona CHAR(36) PRIMARY KEY,
	dni CHAR(8) NOT NULL UNIQUE,
	nombres VARCHAR(200) NOT NULL,
	apellidos VARCHAR(200) NOT NULL,
	telefono CHAR(9) NOT NULL UNIQUE,
	direccion varchar(300) NOT NULL,
	correo varchar(200) NOT NULL UNIQUE,
	contraseña varchar(300) NOT NULL,
    fecha_hora_bloqueo DATETIME
);
CREATE TABLE clientes(
	id_cliente CHAR(36) PRIMARY KEY,
	FOREIGN KEY (id_cliente) REFERENCES personas(id_persona)
);
CREATE TABLE empleados(
    id_empleado CHAR(36) PRIMARY KEY,
	FOREIGN KEY (id_empleado) REFERENCES personas(id_persona)
);
CREATE TABLE cuentas(
    numero_cuenta CHAR(10) PRIMARY KEY,
    saldo_contable DECIMAL(13,2) NOT NULL,
    saldo_disponible DECIMAL(13,2) NOT NULL,
    fecha_creacion DATE NOT NULL,
    estado ENUM('activa', 'cancelada') NOT NULL,
    tipo_cuenta ENUM('ahorro', 'corriente') NOT NULL,
    moneda ENUM('soles', 'dólares', 'euros', 'libras') NOT NULL,
    id_cliente CHAR(36) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);
CREATE TABLE solicitudes(
    id_solicitud CHAR(36) PRIMARY KEY,
    asunto VARCHAR(150) NOT NULL,
    estado ENUM('rechazada', 'aceptada', 'pendiente') NOT NULL,
    fecha_creacion DATE NOT NULL,
    fecha_resolucion DATE,
    id_cliente CHAR(36) NOT NULL,
    id_empleado CHAR(36) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado)
);
CREATE TABLE tarjetas(
    numero_tarjeta CHAR(16) PRIMARY KEY,
    estado ENUM('activa', 'bloqueada', 'vencida') NOT NULL,
    tipo_tarjeta ENUM('débito', 'crédito') NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    id_cliente CHAR(36) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);
CREATE TABLE transacciones(
    id_transaccion CHAR(36) PRIMARY KEY,
    tipo_transaccion ENUM('transferir', 'pagar', 'retirar', 'depositar') NOT NULL,
    descripcion varchar(500),
    fecha_hora DATETIME NOT NULL,
    monto DECIMAL(13,2) NOT NULL,
    id_cliente CHAR(36) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

# CREACIÓN DE LOS PROCEDIMIENTOS ALMACENADOS
-- Para Clientes:
CREATE PROCEDURE sp_listarCliente(
) SELECT p.* FROM clientes c 
INNER JOIN personas p ON p.id_persona = c.id_cliente;
DELIMITER //
CREATE PROCEDURE sp_insertarCliente(
	id_persona CHAR(36),
	dni CHAR(8),
	nombres VARCHAR(200),
	apellidos VARCHAR(200),
	telefono CHAR(9),
	direccion varchar(300),
	correo varchar(200),
	contraseña varchar(300),
    fecha_hora_bloqueo DATETIME
)
BEGIN
	INSERT INTO personas 
    VALUES (id_persona, dni, nombres, apellidos, telefono, direccion, correo, contraseña, fecha_hora_bloqueo);
    INSERT INTO clientes 
    VALUES (id_persona);
END //
DELIMITER ;
CREATE PROCEDURE sp_actualizarCliente(
	id_persona CHAR(36),
	dni CHAR(8),
	nombres VARCHAR(200),
	apellidos VARCHAR(200),
	telefono CHAR(9),
	direccion varchar(300),
	correo varchar(200),
	contraseña varchar(300),
    fecha_hora_bloqueo DATETIME
) UPDATE clientes c 
INNER JOIN personas p ON p.id_persona = c.id_cliente
SET p.dni = dni, p.nombres = nombres, p.apellidos = apellidos, p.telefono = telefono, p.direccion = direccion, p.correo = correo, p.contraseña = contraseña, p.fecha_hora_bloqueo = fecha_hora_bloqueo
WHERE c.id_cliente = id_persona;
CREATE PROCEDURE sp_consultarIdCliente(
	id_cliente CHAR(36)
) SELECT p.* FROM clientes c 
INNER JOIN personas p ON p.id_persona = c.id_cliente 
WHERE c.id_cliente = id_persona;

-- Para Empleados:
CREATE PROCEDURE sp_listarEmpleado(
) SELECT p.* FROM empleados e 
INNER JOIN personas p ON p.id_persona = e.id_empleado;
DELIMITER //
CREATE PROCEDURE sp_insertarEmpleado(
	id_persona CHAR(36),
	dni CHAR(8),
	nombres VARCHAR(200),
	apellidos VARCHAR(200),
	telefono CHAR(9),
	direccion varchar(300),
	correo varchar(200),
	contraseña varchar(300),
    fecha_hora_bloqueo DATETIME
)
BEGIN
	INSERT INTO personas 
    VALUES (id_persona, dni, nombres, apellidos, telefono, direccion, correo, contraseña, fecha_hora_bloqueo);
    INSERT INTO empleados 
    VALUES (id_persona);
END //
DELIMITER ;
CREATE PROCEDURE sp_actualizarEmpleado(
	id_persona CHAR(36),
	dni CHAR(8),
	nombres VARCHAR(200),
	apellidos VARCHAR(200),
	telefono CHAR(9),
	direccion varchar(300),
	correo varchar(200),
	contraseña varchar(300),
    fecha_hora_bloqueo DATETIME
) UPDATE personas p 
INNER JOIN empleados e ON e.id_empleado = p.id_persona
SET p.dni = dni, p.nombres = nombres, p.apellidos = apellidos, p.telefono = telefono, p.direccion = direccion, p.correo = correo, p.contraseña = contraseña, p.fecha_hora_bloqueo = fecha_hora_bloqueo
WHERE e.id_empleado = id_persona;
CREATE PROCEDURE sp_consultarIdEmpleado(
	id_persona CHAR(36)
) SELECT p.* FROM personas p 
INNER JOIN empleados e ON e.id_empleado = p.id_persona 
WHERE e.id_empleado = id_persona;

-- Para Cuentas:
CREATE PROCEDURE sp_listarCuenta(
) SELECT cu.*, p.* FROM cuentas cu 
INNER JOIN clientes cl ON cl.id_cliente = cu.id_cliente 
INNER JOIN personas p ON p.id_persona = cl.id_cliente;
CREATE PROCEDURE sp_insertarCuenta(
	numero_cuenta CHAR(10),
    saldo_contable DECIMAL(13,2),
    saldo_disponible DECIMAL(13,2),
    fecha_creacion DATE,
    estado ENUM('activa', 'cancelada'),
    tipo_cuenta ENUM('ahorro', 'corriente'),
    moneda ENUM('soles', 'dólares', 'euros', 'libras'),
    id_cliente CHAR(36)
) INSERT INTO cuentas 
VALUES (numero_cuenta, saldo_contable, saldo_disponible, fecha_creacion, estado, tipo_cuenta, moneda, id_cliente);
CREATE PROCEDURE sp_actualizarCuenta(
	numero_cuenta CHAR(10),
    saldo_contable DECIMAL(13,2),
    saldo_disponible DECIMAL(13,2),
    estado ENUM('activa', 'cancelada')
) UPDATE cuentas c 
SET c.saldo_contable = saldo_contable, c.saldo_disponible = saldo_disponible, c.estado = estado 
WHERE c.numero_cuenta = numero_cuenta;
CREATE PROCEDURE sp_consultarNumeroCuenta(
	numero_cuenta CHAR(10)
) SELECT cu.*, p.* FROM cuentas cu 
INNER JOIN clientes cl ON cl.id_cliente = cu.id_cliente 
INNER JOIN personas p ON p.id_persona = cl.id_cliente 
WHERE cu.numero_cuenta = numero_cuenta;

-- Para Solicitud:
CREATE PROCEDURE sp_listarSolicitud(
) SELECT s.*, pc.*, pe.* FROM solicitudes s
INNER JOIN clientes c ON c.id_cliente = s.id_cliente 
INNER JOIN personas pc ON pc.id_persona = c.id_cliente
INNER JOIN empleados e ON e.id_empleado = s.id_empleado 
INNER JOIN personas pe ON pe.id_persona = e.id_empleado;
CREATE PROCEDURE sp_insertarSolicitud(
	id_solicitud CHAR(36),
    asunto VARCHAR(150),
    estado ENUM('rechazada', 'aceptada', 'pendiente'),
    fecha_creacion DATE,
    fecha_resolucion DATE,
    id_cliente CHAR(36),
    id_empleado CHAR(36)
) INSERT INTO solicitudes 
VALUES (id_solicitud, asunto, estado, fecha_creacion, fecha_resolucion, id_cliente, id_empleado);
CREATE PROCEDURE sp_actualizarSolicitud(
	id_solicitud CHAR(36),
    estado ENUM('rechazada', 'aceptada', 'pendiente'),
    fecha_resolucion DATE
) UPDATE solicitudes s 
SET s.estado = estado, s.fecha_resolucion = fecha_resolucion 
WHERE s.id_solicitud = id_solicitud;
CREATE PROCEDURE sp_consultarIdSolicitud(
	id_solicitud CHAR(36)
) SELECT s.*, pc.*, pe.* FROM solicitudes s 
INNER JOIN clientes c ON c.id_cliente = s.id_cliente INNER JOIN personas pc ON pc.id_persona = c.id_cliente
INNER JOIN empleados e ON e.id_empleado = s.id_empleado INNER JOIN personas pe ON pe.id_persona = e.id_empleado
WHERE s.id_solicitud = id_solicitud;

-- Para Tarjetas:
CREATE PROCEDURE sp_listarTarjeta(
) SELECT t.*, p.* FROM tarjetas t 
INNER JOIN clientes c ON c.id_cliente = t.id_cliente 
INNER JOIN personas p ON p.id_persona = c.id_cliente;
CREATE PROCEDURE sp_insertarTarjeta(
	numero_tarjeta CHAR(16),
    estado ENUM('activa', 'bloqueada', 'vencida'),
    tipo_tarjeta ENUM('débito', 'crédito'),
    fecha_vencimiento DATE,
    id_cliente CHAR(36)
) INSERT INTO tarjetas 
VALUES (numero_tarjeta, estado, tipo_tarjeta, fecha_vencimiento, id_cliente);
CREATE PROCEDURE sp_actualizarTarjeta(
	numero_tarjeta CHAR(16),
    estado ENUM('activa', 'bloqueada', 'vencida')
) UPDATE tarjetas t 
SET t.estado = estado 
WHERE t.numero_tarjeta = numero_tarjeta;
CREATE PROCEDURE sp_consultarNumeroTarjeta(
	numero_tarjeta CHAR(16)
) SELECT t.*, p.* FROM tarjetas t 
INNER JOIN clientes c ON c.id_cliente = t.id_cliente 
INNER JOIN personas p ON p.id_persona = c.id_cliente 
WHERE t.numero_tarjeta = numero_tarjeta;

-- Para Transacciones:
CREATE PROCEDURE sp_listarTransaccion(
) SELECT t.*, p.* FROM transacciones t 
INNER JOIN clientes c ON c.id_cliente = t.id_cliente 
INNER JOIN personas p ON p.id_persona = c.id_cliente;
CREATE PROCEDURE sp_insertarTransaccion(
	id_transaccion CHAR(36),
    tipo_transaccion ENUM('transferir', 'pagar', 'retirar', 'depositar'),
    descripcion varchar(500),
    fecha_hora DATETIME,
    monto DECIMAL(13,2),
    id_cliente CHAR(36)
) INSERT INTO transacciones 
VALUES (id_transaccion, tipo_transaccion, descripcion, fecha_hora, monto, id_cliente);
CREATE PROCEDURE sp_eliminarTransaccion(
	id_transaccion CHAR(36)
) DELETE FROM transacciones t 
WHERE t.id_transaccion = id_transaccion;
CREATE PROCEDURE sp_consultarIdTransaccion(
	id_transaccion CHAR(36)
) SELECT t.*, p.* FROM transacciones t 
INNER JOIN clientes c ON c.id_cliente = t.id_cliente 
INNER JOIN personas p ON p.id_persona = c.id_cliente 
WHERE t.id_transaccion = id_transaccion;

#INSERCIÓN DE DATOS
CALL sp_insertarEmpleado('000b93b3-c539-4b17-adc9-fccd71e29b6a', '00000001', 'Juan', 'Pérez García', '987654321', 'Calle Falsa 123, Distrito Imaginario, Ciudad Ejemplo', 'juan.perez@empleado.com', 'ClaveEjemplo#1', null);
CALL sp_insertarEmpleado('111b93b3-c539-4b17-adc9-fccd71e29b6a', '00000002', 'María', 'López Rodríguez', '912345678', 'Avenida Siempre Viva 742, Sector Demo, Ciudad Ejemplo', 'maria.lopez@empleado.com', 'ClaveEjemplo#2', null);
CALL sp_insertarCliente('222b93b3-c539-4b17-adc9-fccd71e29b6a', '00000003', 'Carlos', 'Martínez Sánchez', '955501003', 'Jirón Desconocido 456, Urb. Modelo, Ciudad Ejemplo', 'carlos.martinez@email.com', 'ClaveEjemplo#1', null);
CALL sp_insertarCliente('333b93b3-c539-4b17-adc9-fccd71e29b6a', '00000004', 'Ana', 'Gonzales Castillo', '933112233', 'Pasaje Inventado 789, Zona Test, Ciudad Ejemplo', 'ana.gonzales@email.com', 'ClaveEjemplo#2', null);

CALL sp_insertarSolicitud('0baf9d61-a61f-42a1-ae1a-1753ae39292d', 'Apertura de cuenta de ahorro en soles', 'aceptada', (DATE_SUB(current_date(), INTERVAL 1 MONTH)), current_date(), '222b93b3-c539-4b17-adc9-fccd71e29b6a', '000b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarSolicitud('1d2a3144-05db-4e97-a7bf-1d1b54ad5151', 'Apertura de cuenta corriente en dólares', 'aceptada', (DATE_SUB(current_date(), INTERVAL 2 MONTH)), current_date(), '222b93b3-c539-4b17-adc9-fccd71e29b6a', '000b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarSolicitud('749b6118-52cf-475f-93c2-fdd4b763910b', 'Apertura de cuenta de ahorro en soles', 'aceptada', (DATE_SUB(current_date(), INTERVAL 3 MONTH)), current_date(), '333b93b3-c539-4b17-adc9-fccd71e29b6a', '111b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarSolicitud('762b3a5b-d06b-42a6-9e03-a3023857fa02', 'Apertura de cuenta corriente en dólares', 'aceptada', (DATE_SUB(current_date(), INTERVAL 4 MONTH)), current_date(), '333b93b3-c539-4b17-adc9-fccd71e29b6a', '111b93b3-c539-4b17-adc9-fccd71e29b6a');

CALL sp_insertarSolicitud('010b6118-52cf-475f-93c2-fdd4b763910b', 'Apertura de cuenta de ahorro en euros', 'rechazada', (DATE_SUB(current_date(), INTERVAL 3 MONTH)), current_date(), '222b93b3-c539-4b17-adc9-fccd71e29b6a', '000b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarSolicitud('101b3a5b-d06b-42a6-9e03-a3023857fa02', 'Apertura de cuenta corriente en libras', 'rechazada', (DATE_SUB(current_date(), INTERVAL 4 MONTH)), current_date(), '333b93b3-c539-4b17-adc9-fccd71e29b6a', '111b93b3-c539-4b17-adc9-fccd71e29b6a');

CALL sp_insertarSolicitud('d00e6ee6-77df-45b1-9618-29369f86c6f3', 'Emisión de tarjeta de crédito', 'aceptada', (DATE_SUB(current_date(), INTERVAL 4 MONTH)), current_date(), '222b93b3-c539-4b17-adc9-fccd71e29b6a', '000b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarSolicitud('f11e6ee6-77df-45b1-9618-29369f86c6f3', 'Emisión de tarjeta de débito', 'aceptada', (DATE_SUB(current_date(), INTERVAL 3 MONTH)), current_date(), '222b93b3-c539-4b17-adc9-fccd71e29b6a', '000b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarSolicitud('000b6118-52cf-475f-93c2-fdd4b763910b', 'Emisión de tarjeta de crédito', 'aceptada', (DATE_SUB(current_date(), INTERVAL 2 MONTH)), current_date(), '333b93b3-c539-4b17-adc9-fccd71e29b6a', '111b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarSolicitud('9a9b3144-05db-4e97-a7bf-1d1b54ad5151', 'Emisión de tarjeta de débito', 'aceptada', (DATE_SUB(current_date(), INTERVAL 1 MONTH)), current_date(), '333b93b3-c539-4b17-adc9-fccd71e29b6a', '111b93b3-c539-4b17-adc9-fccd71e29b6a');

CALL sp_insertarSolicitud('020e6ee6-77df-45b1-9618-29369f86c6f3', 'Emisión de tarjeta de débito', 'rechazada', (DATE_SUB(current_date(), INTERVAL 3 MONTH)), current_date(), '222b93b3-c539-4b17-adc9-fccd71e29b6a', '000b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarSolicitud('202b6118-52cf-475f-93c2-fdd4b763910b', 'Emisión de tarjeta de crédito', 'rechazada', (DATE_SUB(current_date(), INTERVAL 2 MONTH)), current_date(), '333b93b3-c539-4b17-adc9-fccd71e29b6a', '111b93b3-c539-4b17-adc9-fccd71e29b6a');

CALL sp_insertarCuenta('0023768492', '1500', '1500', current_date(), 'activa', 'ahorro', 'soles', '222b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarCuenta('1123768492', '800', '800', current_date(), 'activa', 'corriente', 'dólares', '222b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarCuenta('9923768492', '1200', '1200', current_date(), 'activa', 'ahorro', 'soles', '333b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarCuenta('6623768492', '900', '900', current_date(), 'activa', 'corriente', 'dólares', '333b93b3-c539-4b17-adc9-fccd71e29b6a');

CALL sp_insertarTarjeta('0004952923404121', 'activa', 'crédito', current_date(), '222b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarTarjeta('1114952923404121', 'activa', 'débito', (DATE_ADD(current_date(), INTERVAL 3 YEAR)), '222b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarTarjeta('2224952923404121', 'activa', 'crédito', (DATE_ADD(current_date(), INTERVAL 3 YEAR)), '333b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarTarjeta('3334952923404121', 'activa', 'débito', (DATE_ADD(current_date(), INTERVAL 3 YEAR)), '333b93b3-c539-4b17-adc9-fccd71e29b6a');

CALL sp_insertarTransaccion('1efb7df2-3dc5-4bbc-913f-0a330618911a', 'depositar', 'Número de cuenta de destino: 0023768492;', (DATE_SUB(NOW(), INTERVAL 8 HOUR)), 1500, '222b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarTransaccion('509b93b3-c539-4b17-adc9-fccd71e29b6a', 'depositar', 'Número de cuenta de destino: 1123768492;', (DATE_SUB(NOW(), INTERVAL 6 HOUR)), 800, '222b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarTransaccion('7bb28d38-3e5e-4923-b6e7-9232d32dc831', 'depositar', 'Número de cuenta de destino: 9923768492;', (DATE_SUB(NOW(), INTERVAL 4 HOUR)), 1200, '333b93b3-c539-4b17-adc9-fccd71e29b6a');
CALL sp_insertarTransaccion('9993aa27-49fc-41da-84ec-bf69f94d508c', 'depositar', 'Número de cuenta de destino: 6623768492;', (DATE_SUB(NOW(), INTERVAL 2 HOUR)), 900, '333b93b3-c539-4b17-adc9-fccd71e29b6a');

/*
CALL sp_listarEmpleado();
CALL sp_listarCliente();
CALL sp_listarCuenta();
CALL sp_listarTarjeta();
CALL sp_listarSolicitud();
CALL sp_listarTransaccion();
*/