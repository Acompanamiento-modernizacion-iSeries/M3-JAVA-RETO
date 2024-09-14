import java.util.Date;

class Transaccion {
    private String tipo;
    private double monto;
    private Date fecha;
    private String codigo;

    public Transaccion(String tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = new Date();
        this.codigo = java.util.UUID.randomUUID().toString();
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCodigo() {
        return codigo;
    }
}