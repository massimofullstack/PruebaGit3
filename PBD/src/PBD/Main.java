package PBD;


import java.io.IOException;
import java.sql.*;

public class Main {

    private static Connection conexion = null;

    // ================= CONEXIÓN =================

    public boolean conectar() {
        System.out.println("--- conectar ---");

        String url = "jdbc:oracle:thin:@//localhost:1521/xe";
        String usuario = "system";
        String contraseña = "12345";

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión correcta");
            return true;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean desconectar() {
        System.out.println("--- desconectar ---");

        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Desconexión correcta");
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static String leer(String prompt) {
        try {
            StringBuilder buffer = new StringBuilder();
            System.out.print(prompt);

            int c;
            while ((c = System.in.read()) != '\n' && c != -1) {
                buffer.append((char) c);
            }

            return buffer.toString().trim();

        } catch (IOException e) {
            return "";
        }
    }

    // =========================================================
    // ========================== CLIENTES ======================
    // =========================================================

    public void crearCliente() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO Clientes (Nombre, Apellidos, DNI, Correo) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, leer("Nombre: "));
            ps.setString(2, leer("Apellidos: "));
            ps.setString(3, leer("DNI: "));
            ps.setString(4, leer("Correo: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarClientes() {
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Clientes");

            while (rs.next()) {
                System.out.println(
                        rs.getString(1) + " - " +
                        rs.getString(2) + " - " +
                        rs.getString(3) + " - " +
                        rs.getString(4)
                );
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarCliente() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "UPDATE Clientes SET Nombre=?, Apellidos=?, Correo=? WHERE DNI=?"
            );

            ps.setString(1, leer("Nombre: "));
            ps.setString(2, leer("Apellidos: "));
            ps.setString(3, leer("Correo: "));
            ps.setString(4, leer("DNI: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarCliente() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "DELETE FROM Clientes WHERE DNI=?"
            );

            ps.setString(1, leer("DNI: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================================================
    // ========================= ARTÍCULOS ======================
    // =========================================================

    public void insertarArticulo() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO Articulos (Nombre, Cantidad, Precio) VALUES (?, ?, ?)"
            );

            ps.setString(1, leer("Nombre: "));
            ps.setInt(2, Integer.parseInt(leer("Cantidad: ")));
            ps.setDouble(3, Double.parseDouble(leer("Precio: ")));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarArticulos() {
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Articulos");

            while (rs.next()) {
                System.out.println(
                        rs.getString(1) + " - " +
                        rs.getInt(2) + " - " +
                        rs.getDouble(3)
                );
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarArticulo() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "DELETE FROM Articulos WHERE Nombre=?"
            );

            ps.setString(1, leer("Nombre: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void modificarArticulo() {
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"UPDATE Articulos SET Cantidad=?, Precio=? WHERE Nombre=?"
			);

			ps.setInt(1, Integer.parseInt(leer("Cantidad: ")));
			ps.setDouble(2, Double.parseDouble(leer("Precio: ")));
			ps.setString(3, leer("Nombre: "));

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    // =========================================================
    // ======================= PROVEEDORES ======================
    // =========================================================

    public void insertarProveedor() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO Proveedores (Nombre, CIF, CP, Calle) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, leer("Nombre: "));
            ps.setString(2, leer("CIF: "));
            ps.setString(3, leer("CP: "));
            ps.setString(4, leer("Calle: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarProveedores() {
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Proveedores");

            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2));
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarProveedor() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "DELETE FROM Proveedores WHERE CIF=?"
            );

            ps.setString(1, leer("CIF: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void modificarProveedor() {
		try {
			PreparedStatement ps = conexion.prepareStatement("UPDATE Proveedores SET CP=?, Calle=? WHERE CIF=?");

			ps.setString(1, leer("CP: "));
			ps.setString(2, leer("Calle: "));
			ps.setString(3, leer("CIF: "));

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    // =========================================================
    // ========================= EMPLEADOS ======================
    // =========================================================

    public void insertarEmpleado() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO Empleado (Nombre, Apellidos, DNI, Cargo) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, leer("Nombre: "));
            ps.setString(2, leer("Apellidos: "));
            ps.setString(3, leer("DNI: "));
            ps.setString(4, leer("Cargo: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEmpleados() {
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Empleado");

            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2));
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarEmpleado() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "DELETE FROM Empleado WHERE DNI=?"
            );

            ps.setString(1, leer("DNI: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================================================
    // ========================= CARGOS =========================
    // =========================================================

    public void insertarCargo() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO Cargo (Nombre) VALUES (?)"
            );

            ps.setString(1, leer("Cargo: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarCargos() {
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Cargo");

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================================================
    // ========================= PEDIDOS ========================
    // =========================================================

    public void CrearPedido() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                "INSERT INTO Pedido (Fecha_pedido, Fecha_entrega, DNI_cliente, DNI_empleado) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, leer("Fecha pedido: "));
            ps.setString(2, leer("Fecha entrega: "));
            ps.setString(3, leer("DNI cliente: "));
            ps.setString(4, leer("DNI empleado: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void mostrarPedidos() {
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Pedido");

            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2));
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarPedido() {
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "DELETE FROM Pedido WHERE Fecha_pedido=?"
            );

            ps.setString(1, leer("Fecha pedido: "));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================================================
    // =========================== MAIN =========================
    // =========================================================
    public static void main(String[] args) {

        Main app = new Main();

        if (!app.conectar()) {
            System.out.println("Error: Conexión no realizada.");
            return;
        }

        int opcionPrincipal = -1;

        while (opcionPrincipal != 0) {

            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestión Clientes");
            System.out.println("2. Gestión Artículos");
            System.out.println("3. Gestión Proveedores");
            System.out.println("4. Gestión Pedidos");
            System.out.println("5. Gestión Empleados");
            System.out.println("6. Gestión Cargos");
            System.out.println("0. Salir");

            try {
                opcionPrincipal = Integer.parseInt(leer("Elige opción: "));
            } catch (Exception e) {
                opcionPrincipal = -1;
            }

            switch (opcionPrincipal) {

                // ================= CLIENTES =================
                case 1:
                    int opcClientes = -1;

                    while (opcClientes != 0) {
                        System.out.println("\n--- GESTIÓN CLIENTES ---");
                        System.out.println("1. Crear cliente");
                        System.out.println("2. Eliminar cliente");
                        System.out.println("3. Modificar cliente");
                        System.out.println("4. Mostrar todos");
                        System.out.println("0. Volver");

                        opcClientes = Integer.parseInt(leer("Opción: "));

                        switch (opcClientes) {
                            case 1: app.crearCliente(); break;
                            case 2: app.borrarCliente(); break;
                            case 3: app.modificarCliente(); break;
                            case 4: app.mostrarClientes(); break;
                            case 0: break;
                            default: System.out.println("Opción inválida");
                        }
                    }
                    break;

                // ================= ARTÍCULOS =================
                case 2:
                    int opcArticulos = -1;

                    while (opcArticulos != 0) {
                        System.out.println("\n--- GESTIÓN ARTÍCULOS ---");
                        System.out.println("1. Insertar");
                        System.out.println("2. Mostrar");
                        System.out.println("3. Borrar");
                        System.out.println("4. Modificar");
                        System.out.println("0. Volver");

                        opcArticulos = Integer.parseInt(leer("Opción: "));

                        switch (opcArticulos) {
                            case 1: app.insertarArticulo(); break;
                            case 2: app.mostrarArticulos(); break;
                            case 3: app.borrarArticulo(); break;
                            case 4: app.modificarArticulo(); break;
                            case 0: break;
                            default: System.out.println("Opción inválida");
                        }
                    }
                    break;

                // ================= PROVEEDORES =================
                case 3:
                    int opcProveedor = -1;

                    while (opcProveedor != 0) {
                        System.out.println("\n--- GESTIÓN PROVEEDORES ---");
                        System.out.println("1. Insertar");
                        System.out.println("2. Mostrar");
                        System.out.println("3. Borrar");
                        System.out.println("4. Modificar");
                        System.out.println("0. Volver");

                        opcProveedor = Integer.parseInt(leer("Opción: "));

                        switch (opcProveedor) {
                            case 1: app.insertarProveedor(); break;
                            case 2: app.mostrarProveedores(); break;
                            case 3: app.borrarProveedor(); break;
                            case 4: app.modificarProveedor(); break;
                            case 0: break;
                            default: System.out.println("Opción inválida");
                        }
                    }
                    break;

                // ================= PEDIDOS =================
                case 4:
                    int opcPedidos = -1;

                    while (opcPedidos != 0) {
                        System.out.println("\n--- GESTIÓN PEDIDOS ---");
                        System.out.println("1. Insertar pedido");
                        System.out.println("2. Mostrar pedidos");
                        System.out.println("3. Borrar pedido");
                        System.out.println("0. Volver");

                        opcPedidos = Integer.parseInt(leer("Opción: "));

                        switch (opcPedidos) {
                            case 1: app.CrearPedido(); break;
                            case 2: app.mostrarPedidos(); break;
                            case 3: app.borrarPedido(); break;
                            case 0: break;
                            default: System.out.println("Opción inválida");
                        }
                    }
                    break;

                // ================= EMPLEADOS =================
                case 5:
                    int opcEmpleados = -1;

                    while (opcEmpleados != 0) {
                        System.out.println("\n--- GESTIÓN EMPLEADOS ---");
                        System.out.println("1. Insertar empleado");
                        System.out.println("2. Mostrar empleados");
                        System.out.println("3. Borrar empleado");
                        System.out.println("0. Volver");

                        opcEmpleados = Integer.parseInt(leer("Opción: "));

                        switch (opcEmpleados) {
                            case 1: app.insertarEmpleado(); break;
                            case 2: app.mostrarEmpleados(); break;
                            case 3: app.borrarEmpleado(); break;
                            case 0: break;
                            default: System.out.println("Opción inválida");
                        }
                    }
                    break;

                // ================= CARGOS =================
                case 6:
                    int opcCargos = -1;

                    while (opcCargos != 0) {
                        System.out.println("\n--- GESTIÓN CARGOS ---");
                        System.out.println("1. Insertar cargo");
                        System.out.println("2. Mostrar cargos");
                        System.out.println("0. Volver");

                        opcCargos = Integer.parseInt(leer("Opción: "));

                        switch (opcCargos) {
                            case 1: app.insertarCargo(); break;
                            case 2: app.mostrarCargos(); break;
                            case 0: break;
                            default: System.out.println("Opción inválida");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }

        app.desconectar();
        System.out.println("Fin del programa");
    }
    }