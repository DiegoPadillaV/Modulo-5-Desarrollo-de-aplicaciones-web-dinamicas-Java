package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdministracionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdministracionController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Verificamos si el usuario esta autenticado
		Object autenticadoObj = request.getSession().getAttribute("autenticado");
		boolean autenticado = false;
		if(autenticadoObj != null) {
			// Lo convierto a boolean
			autenticado = (Boolean) autenticadoObj;
		}
		
		if(autenticado) {
			// Si esta autenticado muestra la zona privada
			String ruta = request.getServletPath();
			String jsp = "";
			
			switch (ruta) {
			case "/administracion1": 
				jsp = "/WEB-INF/admin/administracion1.jsp";
				break;
			case "/administracion2":
				jsp = "/WEB-INF/admin/administracion2.jsp";
				break;
			}
			
			request.getRequestDispatcher(jsp).forward(request, response);;
		}else {
			// Sino lo envio al formulario de ingreso
			response.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
