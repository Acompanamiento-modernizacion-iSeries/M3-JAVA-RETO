package usuarios;

import cuentas.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String usuario;
    private String password;
    private Cuenta cuentaAsociada;
    private List<Transaccion> transacciones;

    public Usuario(String nombre, String usuario, String password, Cuenta cuentaAsociada) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        //Cuenta asociada al usuario.
        this.cuentaAsociada = cuentaAsociada;
        //Listado de transacciones realizadas por el usuario en el sistema.
        this.transacciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public Cuenta getCuentaAsociada() {
        return cuentaAsociada;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        this.transacciones.add(transaccion);
    }
}