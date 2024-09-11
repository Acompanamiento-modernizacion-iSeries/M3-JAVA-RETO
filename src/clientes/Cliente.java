package clientes;

import cuentas.Cuenta;

public class Cliente {
    private String nombre;
    private String id;
    private Cuenta cuenta;

    public Cliente(String nombre, String id, Cuenta cuenta) {
        this.nombre = nombre;
        this.id = id;
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }
}