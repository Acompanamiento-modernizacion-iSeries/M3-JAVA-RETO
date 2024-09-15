import cuentas.Cuenta;
import db.CuentasDB;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cuenta> cuentasDB = CuentasDB.consultarCuenta();
        CuentasDB.anadirCuenta(new Cuenta(BigInteger.valueOf(1523456), new BigDecimal(1000)));
        for (Cuenta cuentasdb: cuentasDB){
            System.out.println("Cuenta ingresada:"+ cuentasdb.consultarNumCuenta()+" Saldo"+cuentasdb.obtenerSaldo());
        }
    }

}
