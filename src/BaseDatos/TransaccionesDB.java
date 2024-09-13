package BaseDatos;
import Cuentas.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class TransaccionesDB {

    private static List<Transaccion> transaccionList = new ArrayList<Transaccion>();
    public static void LlenarTransaccion(Transaccion trx) {
        transaccionList.add(trx);
    }

    public static void buscarHistorialTrx()
    {
        // tamaño de la lista (tope Superior)
        int totalTrx =  transaccionList.size();
        int inicio = totalTrx - 1;
        int hasta = Math.min(totalTrx, 5);
        if (totalTrx == 0 ) {
            System.out.println("No hay transacciones para mostrar");
            return;

        }

        // recorrer la lista y buscar las ultimas 5 transacciones
        System.out.println("Ultimas 5 transacciones:");
        for (int i = inicio; i > (inicio - hasta); i --) {
            Transaccion trx = transaccionList.get(i);
            System.out.println("\n Tipo transaccion: " + trx.consultarTipoTransaccion());
            System.out.println("\n Monto:  $" + trx.consultarMonto());
            System.out.println("\n Fecha: " + trx.consultarFechaTrx());
            System.out.println("\n Codigo Transaccion: " + trx.consultarCodigotrx());
           }

    }

}
