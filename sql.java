import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductosCRUD {

    private Connection connect() {
        // URL de la base de datos SQLite (se crea si no existe)
        String url = "jdbc:sqlite:productos.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void crearProducto(String nombre, double precio) {
        String sql = "INSERT INTO productos(nombre, precio) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void obtenerProductos() {
        String sql = "SELECT id, nombre, precio FROM productos";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Nombre: " + rs.getString("nombre") +
                                   ", Precio: " + rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarProducto(int id, String nuevoNombre, double nuevoPrecio) {
        String sql = "UPDATE productos SET nombre = ?, precio = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nuevoNombre);
            pstmt.setDouble(2, nuevoPrecio);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProductosCRUD crud = new ProductosCRUD();

        // Ejemplos de uso del CRUD
        crud.crearProducto("Producto A", 19.99);
        crud.crearProducto("Producto B", 29.99);
        System.out.println("Productos existentes:");
        crud.obtenerProductos();

        // Actualizar un producto
        crud.actualizarProducto(1, "Producto A Modificado", 24.99);
        System.out.println("Productos después de la actualización:");
        crud.obtenerProductos();

        // Eliminar un producto
        crud.eliminarProducto(2);
        System.out.println("Productos después de la eliminación:");
        crud.obtenerProductos();
    }
}
