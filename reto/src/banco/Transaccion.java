package banco;

public class Transaccion {
    private String tipo;
    private double monto;
    private String fechaHora;
    private String codigo;

    public Transaccion(String tipo, double monto, String fechaHora, String codigo) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return String.format("Tipo: %s, Monto: %.2f, Fecha y Hora: %s, Código: %s", tipo, monto, fechaHora, codigo);
    }
}

