package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Servlet implementation class AlumnosController
 */
public class AlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumnosController() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperamos los datos enviados desde el formulario
		String nombre = request.getParameter("nombre");
		String carrera = request.getParameter("carrera");
		
		try {
			// Recuperar conexion desde la piscina de Tomcat
			InitialContext initialContext = new InitialContext();
			Context contexto = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) contexto.lookup("jdbc/postgres");
			// end recupera conexion
			
			try (
				Connection conexion = dataSource.getConnection();
			    PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO alumnos(nombre, carrera) VALUES(?,?)"); 
			) {
				// Reemplazamos con los valores necesarios para la declaracion
				declaracion.setString(1, nombre);
				declaracion.setString(2, carrera);
				
				// Ejecutamos el SQL en la BD
				int filasInsertadas = declaracion.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		} catch(NamingException e) {
			e.printStackTrace();
		}
		
		// Redireccionar
		response.sendRedirect("/bd-web");
	}

}
