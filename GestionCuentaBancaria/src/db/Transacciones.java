package db;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transacciones {
    private List<Transaccion> transacciones;
    public Transacciones() {
        this.transacciones = new ArrayList<>();
    }

    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public List<Transaccion> obtenerUltimasTransacciones(int n) {
        int size = transacciones.size();
        return transacciones.subList(Math.max(size - n, 0), size);
    }

    public static class Transaccion {
        private String tipo;
        private String subTipo;
        private BigDecimal monto;
        private LocalDateTime fechaHora;
        private String transaccionId;
        private BigDecimal costo;

        public Transaccion(String tipo, String subTipo, BigDecimal monto, BigDecimal costo) {
            this.tipo = tipo;
            this.subTipo = subTipo;
            this.monto = monto;
            this.costo = costo;
            this.fechaHora = LocalDateTime.now();
            this.transaccionId = UUID.randomUUID().toString();
        }

        public String getTipo() {
            return tipo;
        }

        public BigDecimal getMonto() {
            return monto;
        }

        public LocalDateTime getFechaHora() {
            return fechaHora;
        }

        public String getCodigoUnico() {
            return transaccionId;
        }

        @Override
        public String toString() {
            return "Transaccion{" +
                    "tipo='" + tipo + '\'' +
                    ", subTipo='" + subTipo + '\'' +
                    ", monto=" + monto +
                    ", fechaHora=" + fechaHora +
                    ", transaccionId='" + transaccionId + '\'' +
                    ", costo=" + costo +
                    '}' + '\n';
        }
    }
}
