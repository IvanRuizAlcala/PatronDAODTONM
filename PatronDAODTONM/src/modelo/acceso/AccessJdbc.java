package modelo.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import mapper.Adapter;

public class AccessJdbc {


	// Estos datos de configuracion deben estar en otro sitio
	private String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private String URL_BASEDATOS = "jdbc:mysql://localhost:3306";
	private String BBDD = "";
	private Connection conexion = null;
	private boolean accesible = false;

	public AccessJdbc(String bBDD, String user, String password) throws ClassNotFoundException, SQLException {
		super();
		this.BBDD = bBDD;
		Class.forName(CONTROLADOR);
		conexion = DriverManager.getConnection(URL_BASEDATOS + "/" + this.BBDD, user, password);
		accesible = true;
	}

	public ResultSet execute(String query) {
		ResultSetMetaData metaDatos = null;
		if (accesible) {
			ResultSet conjuntoResultados = null;
			Statement instruccion = null;
			try {
				instruccion = conexion.createStatement();
				conjuntoResultados = instruccion.executeQuery(query);
				metaDatos = conjuntoResultados.getMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return conjuntoResultados;
		}
		return null;
	}

	public void update(String query, Adapter adapter) {
		PreparedStatement instruccion = null;
		try {
			instruccion = conexion.prepareStatement(query);
			adapter.adapt(instruccion);
			// Ejecutar la sentencia SQL
			instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int executeUpdate(String query) {
		if (accesible) {
			Statement instruccion = null;
			try {
				instruccion = conexion.createStatement();
				int filasAfectadas = instruccion.executeUpdate(query);
				return filasAfectadas;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			} finally {
				try {
					if (instruccion != null) {
						instruccion.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}
	public String getBBDD() {
		return BBDD;
	}

	public void setBBDD(String bBDD) {
		BBDD = bBDD;
	}
}
