import java.util.ArrayList;
import java.util.List;

public class HistorialTransacciones {
    private List<Transaccion> transacciones;

    public HistorialTransacciones() {
        this.transacciones = new ArrayList<>();
    }

    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public List<Transaccion> obtenerUltimasTransacciones(int n) {
        int size = transacciones.size();
        return transacciones.subList(Math.max(size - n, 0), size);
    }
}