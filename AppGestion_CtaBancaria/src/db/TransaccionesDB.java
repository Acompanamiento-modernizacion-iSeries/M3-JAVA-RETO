package db;

import resourses.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class TransaccionesDB {

    private static final List<Transaccion> LISTA_TRANSACCIONES = new ArrayList<>();

    public static void agregarTransaccion(Transaccion transaccion) {
        transaccion.asignarCodigoTransacc(LISTA_TRANSACCIONES.size()+1);
        LISTA_TRANSACCIONES.add(transaccion);
    }

    public static List<Transaccion> buscarTransacciones(String numCuenta, int numRegistros) {
        List<Transaccion> listaTransaccNumCta = new ArrayList<>();
        for (int transac = LISTA_TRANSACCIONES.size()-1; transac >= 0; transac--) {
            if (LISTA_TRANSACCIONES.get(transac).consultarNumCuenta().equals(numCuenta)) {
                listaTransaccNumCta.add(LISTA_TRANSACCIONES.get(transac));
            }
            if (listaTransaccNumCta.size() == numRegistros) {
                return listaTransaccNumCta;
            }
        }
        return listaTransaccNumCta;
    }

}
