package db;

import transacciones.Transaccion;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class TransaccionDBArrayList implements TransaccionDB {
    private Map<String, List<Transaccion>> transaccionesPorCuenta;

    public TransaccionDBArrayList() {
        this.transaccionesPorCuenta = new HashMap<>();
    }

    @Override
    public void agregarTransaccion(String idCuenta, Transaccion transaccion) {
        transaccionesPorCuenta
                .computeIfAbsent(idCuenta, k -> new ArrayList<>())
                .add(transaccion);
    }

    @Override
    public List<Transaccion> obtenerTransaccionesPorCuenta(String idCuenta) {
        return transaccionesPorCuenta.getOrDefault(idCuenta, new ArrayList<>());
    }
}
