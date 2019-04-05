package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
	
	private Connection conexion;
	private Statement stm;
	private PreparedStatement pstm; // Para proyecto final con objetivo de evitar SQL Injection
	ResultSet rs = null;
	
	public ConexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
									 //127.0.0.1  
			//String url = "jdbc:mysql://localhost/BD_Escuela";
			String url = "jdbc:mysql://localhost/BD_Escuela?serverTimezone=UTC";
			conexion = DriverManager.getConnection(url, "root", "password");
						
			//System.out.println("Magia magia con BD, ya casi soy ISC");
			
		} catch (ClassNotFoundException e) {
			System.out.println("No se encontró el controlador.");
			//System.out.println("Mejor me dedico a las redes ='(.");
		} catch (SQLException e) {
			System.out.println("No se pudo conectar al servidor.");
			//System.out.println("Mejor me dedico a las redes ='(.");
			e.printStackTrace();
		} finally {
			// Codigo que siempre se ejecuta
			// Cierre de la conexión a la BD
		}
	}
	
	public void cerrarConexion() {
		try {
			stm.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Métodos que ejecuten las operaciones ABCC ( DDL, DML, SQL)
	// Un método para DDL y DML
	// Otro método para SQL
	
	public boolean ejecutarInstruccion(String sql) {
		try {
			stm = conexion.prepareStatement(sql);
			int ejecucion;
			ejecucion = stm.executeUpdate(sql);
			return ejecucion == 1?true:false;
		} catch (SQLException e) {
			System.out.println("No se pudo ejecutar la instrucción SQL");
			return false;
		}
	}
	
	// Otro método para SQL (CONSULTAS)
	public ResultSet ejecutarConsultaRegistros(String sql) {
		
		try {
			stm = conexion.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("No se pudo ejecutar la consulta SQL");
		}
		
		return rs;
	}
	
	// mysql --version
	// mysql -u root -p
	// CREATE DATABASE BD_Escuela
	public static void main(String[] args) {
		new ConexionBD();
	}
	
}
