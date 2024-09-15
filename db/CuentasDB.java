package db;

import cuentas.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class CuentasDB {

    private static List<Cuenta> cuentaList = new ArrayList<Cuenta>();

    public static void anadirCuenta(Cuenta cuenta){
        cuentaList.add(cuenta);
    }

    public static List<Cuenta> consultarCuenta(){
        return cuentaList;
    }
}
