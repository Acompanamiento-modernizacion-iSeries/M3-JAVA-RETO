package db;

import cuentas.Cuenta;
import cuentas.CuentaBasica;
import cuentas.CuentaPremium;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CuentasDB {
    private static List<Cuenta> listaCuentas = new ArrayList<>();

    static {
        listaCuentas.add(new CuentaBasica("123456", new BigDecimal(1000)));
        listaCuentas.add(new CuentaPremium("234567", new BigDecimal(1500)));
        listaCuentas.add(new CuentaBasica("345678", new BigDecimal(500)));
        listaCuentas.add(new CuentaPremium("456789", new BigDecimal(2000)));
        listaCuentas.add(new CuentaBasica("567890", new BigDecimal(1200)));
        listaCuentas.add(new CuentaPremium("678901", new BigDecimal(3000)));
    }

    public static List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }
}