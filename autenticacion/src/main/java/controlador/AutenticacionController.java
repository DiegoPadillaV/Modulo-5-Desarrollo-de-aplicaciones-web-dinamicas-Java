package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AutenticacionController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Cerrar sesion o salir
		request.getSession().invalidate(); // Borra los datos de la sesion
		// Redirijo a alguna zona publica
		response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero los campos del formulario de ingreso
		String usuario    = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		
		if("admin".equals(usuario) && "1234".equals(contrasena)) {
			// Si los datos de autenticacion son correctos, guardo un indicador en la sesion
			request.getSession().setAttribute("autenticado", true);
			// Redireccion a la pagina primaria de la zona privada
			response.sendRedirect(getServletContext().getContextPath() + "/administracion1");
		} else {
			// En el caso de que el usuario y/o contrasena sean incorrectos
			request.setAttribute("mensajeError", "El usuario y/o contraseña son incorrecto(s)");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
