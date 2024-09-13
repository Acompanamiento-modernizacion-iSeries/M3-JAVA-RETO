package db;

import cuentas.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class CuentasDB {

    private static final List<Cuenta> LISTA_CUENTAS = new ArrayList<>();

    public static String agregarCuenta(Cuenta cuenta) {
        String numCuenta = cuenta.consultarNumCuenta();
        LISTA_CUENTAS.add(cuenta);
        return numCuenta;
    }

    public static Cuenta buscarCuenta(String numCuenta) {
        for (Cuenta cuenta : LISTA_CUENTAS) {
            if (cuenta.consultarNumCuenta().equals(numCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public static List<String> listarNumCuentas() {
        List<String> numCuentas = new ArrayList<>();
        LISTA_CUENTAS.forEach(cuenta -> {
            numCuentas.add(cuenta.consultarNumCuenta());
        });
        return numCuentas;
    }
}
