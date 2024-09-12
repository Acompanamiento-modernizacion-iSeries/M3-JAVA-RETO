package models;

import java.math.BigDecimal;

public class ResutadoTransaccion {
    private BigDecimal costoTransaccion;
    private String resultado;

    public ResutadoTransaccion() {
    }

    public BigDecimal consultarCostoTransaccion() {
        return costoTransaccion;
    }

    public void asignarCostoTransaccion(BigDecimal costoTransaccion) {
        this.costoTransaccion = costoTransaccion;
    }

    public String consultarResultado() {
        return resultado;
    }

    public void asignarResultado(String resultado) {
        this.resultado = resultado;
    }
}
