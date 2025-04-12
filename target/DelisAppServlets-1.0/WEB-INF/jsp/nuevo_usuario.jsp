<%-- 
    Document   : nuevo_usuario
    Created on : 12/04/2025, 5:03:54 p. m.
    Author     : pauchacon16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Usuario</title>
</head>
<body>
    <h1>Crear Nuevo Usuario</h1>
    <form action="${pageContext.request.contextPath}/usuarios?action=crear" method="post">
        <div>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="telefono">Teléfono:</label>
            <input type="text" id="telefono" name="telefono">
        </div>
        <button type="submit">Guardar</button>
        <p><a href="${pageContext.request.contextPath}/usuarios">Volver a la lista</a></p>
    </form>
</body>
</html>
