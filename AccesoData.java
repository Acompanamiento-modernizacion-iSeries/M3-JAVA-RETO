package RetoFinal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class AccesoData {
    private static List<Cuenta> cuentaList = new ArrayList<>();


    public static void agregarCuenta(Cuenta cuenta) {
        cuentaList.add(cuenta);
    }

    public static List<Cuenta> getcuentaslis() {
        return cuentaList;
    }

    public static Cuenta buscarCuenta(BigInteger numeroCuenta) {
        for (Cuenta cuenta : cuentaList) {
            if (cuenta.getnumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
    public static void buscarlog(BigInteger numeroCuenta) {
        Cuenta cuenta;
        int x=0;
        for(int i=cuentaList.size()-1; i > 0; i-- ){
            cuenta =cuentaList.get(i);
            if (cuenta.getnumeroCuenta().equals(numeroCuenta) && cuenta instanceof Logtransaccion ) {
                cuenta.mostrarInformacion();
                x++;
            }
            if (x>4){
                break;
            }
        }

    }
}
