package resourses;

import java.math.BigDecimal;
import java.util.Date;

public class Transaccion {

    protected String tipoTransacc;
    protected String numCuenta;
    protected BigDecimal vlrTransacc;
    protected Date fecha;
    protected Integer codigoTransacc;

    public Transaccion(String tipoTransacc, String numCuenta, double vlrTransacc, Date fecha) {
        this.tipoTransacc = tipoTransacc;
        this.numCuenta = numCuenta;
        this.vlrTransacc = BigDecimal.valueOf(vlrTransacc);
        this.fecha = fecha;
    }

    public String consultarNumCuenta() {
        return numCuenta;
    }

    public void asignarCodigoTransacc(Integer codigoTransacc) {
        this.codigoTransacc = codigoTransacc;
    }
}
