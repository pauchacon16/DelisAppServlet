/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sena.delisappservlets.modulo_gestion_usuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sena.delisappservlets.modulo_gestion_usuarios.Usuario;

/**
 *
 * @author pauchacon16
 */
public class GestionUsuarios {
    // Constantes para la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos";
    private static final String USUARIO = "nombre_de_usuario";
    private static final String CONTRASENIA = "contraseña";

    /**
     * Establece una conexión a la base de datos.
     *
     * @return Objeto Connection si la conexión es exitosa, null en caso contrario.
     * @throws SQLException Si ocurre un error al intentar conectar a la base de datos.
     */
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto Usuario a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, email, telefono) VALUES (?, ?, ?)";
        try (Connection conexion = conectar();
            PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefono());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Consulta un usuario por su ID.
     *
     * @param idUsuario El ID del usuario a consultar.
     * @return Un objeto Usuario si se encuentra, null en caso contrario.
     */
    public Usuario consultarUsuario(int idUsuario) {
        String sql = "SELECT id, nombre, email, telefono FROM usuarios WHERE id = ?";
        try (Connection conexion = conectar();
            PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);
            ResultSet resultado = pstmt.executeQuery();
            if (resultado.next()) {
                return new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("email"),
                        resultado.getString("telefono")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar usuario: " + e.getMessage());
        }
        return null;
    }

    /**
     * Consulta todos los usuarios de la base de datos.
     *
     * @return Una lista de objetos Usuario.
     */
    public List<Usuario> consultarTodosUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT id, nombre, email, telefono FROM usuarios";
        try (Connection conexion = conectar();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            ResultSet resultado = pstmt.executeQuery()) {
            while (resultado.next()) {
                listaUsuarios.add(new Usuario(
                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("email"),
                    resultado.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar todos los usuarios: " + e.getMessage());
        }
        return listaUsuarios;
    }

    /**
     * Actualiza la información de un usuario existente en la base de datos.
     *
     * @param usuario El objeto Usuario con la información actualizada.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, telefono = ? WHERE id = ?";
        try (Connection conexion = conectar();
            PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefono());
            pstmt.setInt(4, usuario.getId());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param idUsuario El ID del usuario a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conexion = conectar();
            PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
