package db;

import cuentas.Cuenta;
import cuentas.CuentaBasica;
import cuentas.CuentaPremium;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CuentasDB {

    private static List<Cuenta> cuentaList = new ArrayList<Cuenta>(
            List.of(
                    new CuentaBasica(new BigDecimal(1001), 1),
                    new CuentaBasica(new BigDecimal(1002), 2),
                    new CuentaBasica(new BigDecimal(1000), 3),
                    new CuentaBasica(new BigDecimal(1000), 4),
                    new CuentaBasica(new BigDecimal(1000), 5),
                    new CuentaPremium(new BigDecimal(1000), 6),
                    new CuentaPremium(new BigDecimal(1000), 7),
                    new CuentaPremium(new BigDecimal(1000), 8),
                    new CuentaPremium(new BigDecimal(1000), 9),
                    new CuentaPremium(new BigDecimal(1000), 10)
            )
    );

    public Cuenta getCuenta(Integer numeroCuenta) {
        for (Cuenta cuenta : cuentaList) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    };
}
