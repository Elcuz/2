public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private Rol rol;

    public Usuario(String nombreUsuario, String contrasena, Rol rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y setters
}

public enum Rol {
    ADMINISTRADOR,
    USUARIO_NORMAL
}
public class Producto {
    private String nombre;
    private String descripcion;

    public Producto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y setters
}
import java.util.ArrayList;
import java.util.List;

public class Aplicacion {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();

    // Agregar usuarios y productos/servicios a las listas

    // Implementa métodos para editar, agregar y eliminar productos/servicios,
    // asegurando que verifiques los roles de los usuarios antes de permitir estas acciones.
}
public boolean usuarioPuedeEditarProducto(Usuario usuario) {
    return usuario.getRol() == Rol.ADMINISTRADOR;
}

public boolean usuarioPuedeAgregarProducto(Usuario usuario) {
    return usuario.getRol() == Rol.ADMINISTRADOR;
}

public boolean usuarioPuedeEliminarProducto(Usuario usuario) {
    return usuario.getRol() == Rol.ADMINISTRADOR;
}