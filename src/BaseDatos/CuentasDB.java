package BaseDatos;

import Cuentas.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class CuentasDB {
    private static List<Cuenta> cuentaList = new ArrayList<Cuenta>();
    public static void LlenarCuentas(Cuenta cuenta) {
        cuentaList.add(cuenta);


    }

    public static Cuenta buscarCuenta(String nrocuenta)
    {
        // recorrer la lista y llevarla a mi variable cuenta
        for (Cuenta cuenta : cuentaList) {
            if (cuenta.consultarNrocuenta().equals(nrocuenta))
            {
               return cuenta;
            }

        }
        // cuenta no existe
        return null;
    }
}
