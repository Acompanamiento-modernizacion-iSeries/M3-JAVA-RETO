import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private String tipo;
    private double monto;
    private LocalDateTime fechaHora;
    private String codigoUnico;

    public Transaccion(String tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.codigoUnico = UUID.randomUUID().toString();
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "tipo='" + tipo + '\'' +
                ", monto=" + monto +
                ", fechaHora=" + fechaHora +
                ", codigoUnico='" + codigoUnico.substring(0, 5) + '\'' +
                '}';
    }
}