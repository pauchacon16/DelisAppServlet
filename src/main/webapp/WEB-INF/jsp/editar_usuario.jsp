<%-- 
    Document   : editar_usuario
    Created on : 12/04/2025, 5:05:07 p. m.
    Author     : pauchacon16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuario</title>
</head>
<body>
    <h1>Editar Usuario</h1>
    <form action="${pageContext.request.contextPath}/usuarios?action=actualizar" method="post">
        <input type="hidden" name="id" value="${usuario.id}">
        <div>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${usuario.email}" required>
        </div>
        <div>
            <label for="telefono">Teléfono:</label>
            <input type="text" id="telefono" name="telefono" value="${usuario.telefono}">
        </div>
        <button type="submit">Actualizar</button>
        <p><a href="${pageContext.request.contextPath}/usuarios">Volver a la lista</a></p>
    </form>
</body>
</html>
