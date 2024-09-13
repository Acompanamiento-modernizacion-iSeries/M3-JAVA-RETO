package db;

import transacciones.Transaccion;
import java.util.List;

public interface TransaccionDB {
    void agregarTransaccion(String idCuenta, Transaccion transaccion);
    List<Transaccion> obtenerTransaccionesPorCuenta(String idCuenta);
}
