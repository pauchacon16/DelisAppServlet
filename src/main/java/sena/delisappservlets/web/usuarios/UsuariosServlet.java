/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sena.delisappservlets.web.usuarios;

import sena.delisappservlets.modulo_gestion_usuarios.GestionUsuarios;
import sena.delisappservlets.modulo_gestion_usuarios.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author jseba
 */
@WebServlet("/usuarios")
public class UsuariosServlet extends HttpServlet{
    private GestionUsuarios gestionUsuarios;

    @Override
    public void init() throws ServletException {
        super.init();
        gestionUsuarios = new GestionUsuarios(); // Inicializa la capa de lógica de negocio
    }

    /**
     * Maneja las solicitudes GET para mostrar formularios o listar usuarios.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            listarUsuarios(request, response);
        } else if (action.equals("nuevo")) {
            mostrarFormularioNuevo(request, response);
        } else if (action.equals("editar")) {
            mostrarFormularioEditar(request, response);
        } else if (action.equals("eliminar")) {
            eliminarUsuario(request, response);
        } else {
            listarUsuarios(request, response); // Acción por defecto
        }
    }

    /**
     * Maneja las solicitudes POST para crear o actualizar usuarios.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("crear")) {
            crearUsuario(request, response);
        } else if (action != null && action.equals("actualizar")) {
            actualizarUsuario(request, response);
        } else {
            listarUsuarios(request, response); // Acción por defecto
        }
    }

    /**
     * Lista todos los usuarios y los envía a una página JSP para su visualización.
     */
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = gestionUsuarios.consultarTodosUsuarios();
        request.setAttribute("listaUsuarios", usuarios);
        request.getRequestDispatcher("/WEB-INF/jsp/listar_usuarios.jsp").forward(request, response);
    }

    /**
     * Muestra el formulario para crear un nuevo usuario.
     */
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/nuevo_usuario.jsp").forward(request, response);
    }

    /**
     * Crea un nuevo usuario a partir de los datos del formulario.
     */
    private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        Usuario nuevoUsuario = new Usuario(nombre, email, telefono);
        gestionUsuarios.insertarUsuario(nuevoUsuario);
        response.sendRedirect(request.getContextPath() + "/usuarios"); // Redirige a la lista de usuarios
    }

    /**
     * Muestra el formulario para editar un usuario existente.
     */
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = gestionUsuarios.consultarUsuario(id);
        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/WEB-INF/jsp/editar_usuario.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/usuarios?error=Usuario no encontrado");
        }
    }

    /**
     * Actualiza la información de un usuario existente.
     */
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        Usuario usuarioActualizado = new Usuario(id, nombre, email, telefono);
        gestionUsuarios.actualizarUsuario(usuarioActualizado);
        response.sendRedirect(request.getContextPath() + "/usuarios"); // Redirige a la lista de usuarios
    }

    /**
     * Elimina un usuario por su ID.
     */
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        gestionUsuarios.eliminarUsuario(id);
        response.sendRedirect(request.getContextPath() + "/usuarios"); // Redirige a la lista de usuarios
    }    
}
