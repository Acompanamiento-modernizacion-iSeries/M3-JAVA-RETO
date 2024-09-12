package baseDeDatos;

import cuentas.*;
import java.util.ArrayList;
import java.util.List;

public class CuentasDB {

    private static List<Cuenta> cuentas = new ArrayList<>();

    //agregar cuentas a la lista.
    public static void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    //retornar la lista de cuentas.
    public static List<Cuenta> getCuentas() {
        return cuentas;
    }

    //buscar una cuenta por su número y retornarla si se encuentra.
    public static Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
}