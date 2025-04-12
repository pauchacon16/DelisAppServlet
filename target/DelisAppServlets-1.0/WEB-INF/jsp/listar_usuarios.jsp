<%-- 
    Document   : listar_usuarios
    Created on : 12/04/2025, 5:03:24 p. m.
    Author     : pauchacon16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usuarios</title>
</head>
<body>
    <h1>Lista de Usuarios</h1>
    <p><a href="${pageContext.request.contextPath}/usuarios?action=nuevo">Crear Nuevo Usuario</a></p>
    <c:if test="${not empty listaUsuarios}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Teléfono</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="usuario" items="${listaUsuarios}">
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.email}</td>
                        <td>${usuario.telefono}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/usuarios?action=editar&id=${usuario.id}">Editar</a>
                            <a href="${pageContext.request.contextPath}/usuarios?action=eliminar&id=${usuario.id}" onclick="return confirm('¿Estás seguro de eliminar este usuario?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty listaUsuarios}">
        <p>No hay usuarios registrados.</p>
    </c:if>
    <c:if test="${not empty param.error}">
        <p style="color:red;">Error: ${param.error}</p>
    </c:if>
</body>
</html>