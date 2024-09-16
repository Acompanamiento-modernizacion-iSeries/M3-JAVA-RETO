import java.time.LocalDateTime;

public class Transaccion {
    private String tipo;
    private double monto;
    private LocalDateTime fecha;
    private String codigo;

    public Transaccion(String tipo, double monto, LocalDateTime fecha, String codigo) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
        this.codigo = codigo;
    }


    public String toString() {
        return "Código: " + codigo + " | Tipo: " + tipo + " | Monto: " + monto + " | Fecha: " + fecha;
    }
}
