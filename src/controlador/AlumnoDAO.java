package controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Alumno;

public class AlumnoDAO {
		
	// métodos que permiten realizar las ALtas, Bajas, Cambios y Consultas
	
	public boolean agregarAlumno(Alumno a) {
		
		// INSERT INTO Alumnos VALUES("S17070162", "Ricardo Benjamin", "Viramontes", "Juárez", 19, 4, "I.S.C.");
		
		String sql = "INSERT INTO Alumnos VALUES(\"S17070162\", \"Ricardo Benjamin\", \"Viramontes\", \"Juárez\", 19, 4, \"I.S.C.\")"; 

		String sql2 = "INSERT INTO Alumnos VALUES('S17070157', 'Cristofer', 'Casas', 'Murillo', 19, 4, 'I.S.C.')"; 
		
		String sql3 = "INSERT INTO Alumnos VALUES('"+a.getNumControl()+"', '"+a.getNombre()+"', '"+a.getPrimerAp()+"', '"+a.getSegundoAp()+"', '"+a.getEdad()+"', '"+a.getSemestre()+"', '"+a.getCarrera()+"')";
		
		ConexionBD conexion = new ConexionBD();
		return conexion.ejecutarInstruccion(sql3);
	
	}
	
	public boolean eliminarAlumnos(String numControl) {
		String sql = "DELETE FROM Alumnos WHERE NumControl = '"+numControl+"';";
		
		ConexionBD conexion = new ConexionBD();
		return conexion.ejecutarInstruccion(sql);
	}
	
	public boolean modificarAlumno(Alumno a1) {
		String sql = "UPDATE Alumnos SET NumControl='"+a1.getNumControl()+"', Nombre='"+a1.getNombre()+"', PrimerAp='"+a1.getPrimerAp()+"', SegundoAp='"+a1.getSegundoAp()+"', Edad="+a1.getEdad()+", Semestre="+a1.getSemestre()+", Carrera='"+a1.getCarrera()+"' WHERE NumControl='"+a1.getNumControl()+"'";
		
		ConexionBD conexion = new ConexionBD();
		return conexion.ejecutarInstruccion(sql);
	}
	
	// ==================================== BUSCAR UN REGISTRO ====================================
	public Alumno buscarAlumno(String dato, String campo) {
		Alumno alumno = new Alumno();
		//SELECT * FROM Alumnos WHERE NumControl = 'S17070162';
		String sql = "SELECT * FROM Alumnos WHERE "+campo+" = '"+dato+"';";
		ConexionBD conexion = new ConexionBD();
		ResultSet rs = conexion.ejecutarConsultaRegistros(sql);
		
		try {
			rs.last();
			alumno.setNumControl(rs.getString(1));
			alumno.setNombre(rs.getString(2));
			alumno.setPrimerAp(rs.getString(3));
			alumno.setSegundoAp(rs.getString(4));
			alumno.setEdad(rs.getByte(5));
			alumno.setSemestre(rs.getByte(6));
			alumno.setCarrera(rs.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alumno;
	}
	
	// ==================================== BUSCAR MULTIPLES REGISTRO ====================================
	
	public ArrayList<Alumno> buscarAlumnos(String filtro) {
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		//RECORRER el ResultSet mientras haya registros
		//SELECT * FROM alumnos;
		
		//rs.first()
		
		String sql = "SELECT * FROM Alumnos WHERE EDAD ='19';";
		ConexionBD conexion = new ConexionBD();
		ResultSet rs = conexion.ejecutarConsultaRegistros(sql);
		
		try {
			rs.first();
			while(!rs.isAfterLast()) {
				Alumno alumno = new Alumno();
				alumno.setNumControl(rs.getString(1));
				alumno.setNombre(rs.getString(2));
				alumno.setPrimerAp(rs.getString(3));
				alumno.setSegundoAp(rs.getString(4));
				alumno.setEdad(rs.getByte(5));
				alumno.setSemestre(rs.getByte(6));
				alumno.setCarrera(rs.getString(7));				
				rs.next();
				listaAlumnos.add(alumno);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaAlumnos;
	}
	
}
