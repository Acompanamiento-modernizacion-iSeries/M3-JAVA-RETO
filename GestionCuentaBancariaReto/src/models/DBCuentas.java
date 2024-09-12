package models;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DBCuentas {

    public static ArrayList<Cuenta> LlenarCuentas() {
        ArrayList<Cuenta> arrayCuentas = new ArrayList<>();
        Cuenta cuentaBasica1 = new CuentaBasica("2547987626", new BigDecimal(5000000));
        arrayCuentas.add(cuentaBasica1);
        Cuenta cuentaPremium1 = new CuentaPremium("9001828365", new BigDecimal(8000000));
        arrayCuentas.add(cuentaPremium1);

        Cuenta cuentaBasica2 = new CuentaBasica("6012266540", new BigDecimal(2000000));
        arrayCuentas.add(cuentaBasica2);
        Cuenta cuentaPremium2 = new CuentaPremium("5789911237", new BigDecimal(6000000));
        arrayCuentas.add(cuentaPremium2);

        Cuenta cuentaBasica3 = new CuentaBasica("1037575512", new BigDecimal(500));
        arrayCuentas.add(cuentaBasica3);
        Cuenta cuentaPremium3 = new CuentaPremium("2973545341", new BigDecimal(100));
        arrayCuentas.add(cuentaPremium3);
        return arrayCuentas;
    }


}
