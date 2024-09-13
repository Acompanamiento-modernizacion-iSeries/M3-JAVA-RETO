import java.math.BigDecimal;

public class CuentaPremium extends Cuenta {
    private static final BigDecimal COSTO_DESDE_OTRA_CUENTA = new BigDecimal("1.5");
    private static final BigDecimal COSTO_PAGINA_WEB = new BigDecimal("5");
    private static final BigDecimal COSTO_RETIRO_CAJERO = new BigDecimal("1");

    public CuentaPremium(String numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    // Implementación del método retiro en CuentaPremium
    @Override
    public void retiroCajero(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0 && cantidad.add(COSTO_RETIRO_CAJERO).compareTo(saldo) <= 0) {
            System.out.println("Saldo Antes del Retiro: " + saldo);
            saldo = saldo.subtract(cantidad).subtract(COSTO_RETIRO_CAJERO);
            System.out.println("Retiro en cajero automático realizado. Costo: " + COSTO_RETIRO_CAJERO );

            agregarTransaccion("Retiro en cajero automático: ", cantidad);
        } else {
            System.out.println("Monto inválido o saldo insuficiente para realizar el retiro.");
        }
    }

    @Override
    public void depositoSucursal(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Saldo Antes del Deposito: " + saldo);
            saldo = saldo.add(cantidad);
            System.out.println("Depósito en sucursal realizado. Saldo actual: " + saldo);

            agregarTransaccion("Deposito por Sucursal: ", cantidad);
        } else {
            System.out.println("El monto a depositar debe ser mayor a 0.");
        }
    }

    @Override
    public void depositoCajeroAutomatico(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Saldo Antes del Deposito: " + saldo);
            saldo = saldo.add(cantidad);
            System.out.println("Depósito en cajero automático realizado (sin costo).");

            agregarTransaccion("Deposito por Cajero Automatico: ", cantidad);
        } else {
            System.out.println("El monto a depositar debe ser mayor a 0.");
        }
    }

    @Override
    public void depositoDesdeOtraCuenta(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Saldo Antes del Deposito: " + saldo);
            saldo = saldo.add(cantidad).subtract(COSTO_DESDE_OTRA_CUENTA);
            System.out.println("Depósito desde otra cuenta realizado. Costo: " + COSTO_DESDE_OTRA_CUENTA );

            agregarTransaccion("Deposito desde otra Cuenta: ", cantidad);
        } else {
            System.out.println("El monto a depositar debe ser mayor a 0.");
        }
    }

    @Override
    public void compraEstablecimiento(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0 && cantidad.compareTo(saldo) <= 0) {
            saldo = saldo.subtract(cantidad);
            System.out.println("Compra en establecimiento físico realizada. ");

            agregarTransaccion("Compra Establecimiento: ", cantidad);
        } else {
            System.out.println("Monto inválido o saldo insuficiente.");
        }
    }

    @Override
    public void compraPaginaWeb(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0 && cantidad.compareTo(saldo) <= 0) {
            saldo = saldo.subtract(cantidad).subtract(COSTO_PAGINA_WEB);
            System.out.println("Compra en página web realizada. Costo adicional: " + COSTO_PAGINA_WEB );

            agregarTransaccion("Compra Pagina Web: ", cantidad);
        } else {
            System.out.println("Monto inválido o saldo insuficiente.");
        }
    }


}
