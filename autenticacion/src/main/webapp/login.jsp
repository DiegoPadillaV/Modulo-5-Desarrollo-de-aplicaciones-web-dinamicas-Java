<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
	</head>
	<body>
	<%@ include file="/WEB-INF/includes/menu.jsp" %>
		<h1>Ingreso</h1>
		
		<c:if test="${not empty mensajeError}">
			<div style="background: red; color: white; padding: 15px; margin: 15px;">
				${mensajeError}
			</div>
		</c:if>
		
		<!-- ${pageContext.request.contextPath}/autenticacion -> /nombre-su-proyecto/autenticacion -->
		<form action="${pageContext.request.contextPath}/autenticacion" method="post">
			Usuario: <input type="text" name="usuario" />
			Password: <input type="password" name="contrasena" />
			<button type="submit">Ingresar</button>
		</form>
	</body>
</html>