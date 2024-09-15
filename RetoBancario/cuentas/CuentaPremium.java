package cuentas;

import java.math.BigDecimal;

public class CuentaPremium extends Cuenta {
    private static final BigDecimal CostoDesdeOtraCuenta = new BigDecimal("1.5");
    private static final BigDecimal CostoDesdePaginaWeb = new BigDecimal("5");
    private static final BigDecimal CostoRetiroCajero = new BigDecimal("1");

    public CuentaPremium(Integer numeroCuenta, BigDecimal saldo){
        super(numeroCuenta, saldo);
    }

    @Override
    public void retiroCajero(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0 && cantidad.add(CostoRetiroCajero).compareTo(saldo) <= 0) {
            System.out.println("Saldo Antes del Retiro: " + saldo);
            saldo = saldo.subtract(cantidad).subtract(CostoRetiroCajero);
            System.out.println("Retiro en cajero automático realizado. Costo: " + CostoRetiroCajero );

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
            saldo = saldo.add(cantidad).subtract(CostoDesdeOtraCuenta);
            System.out.println("Depósito desde otra cuenta realizado. Costo: " + CostoDesdeOtraCuenta );

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
            saldo = saldo.subtract(cantidad).subtract(CostoDesdePaginaWeb);
            System.out.println("Compra en página web realizada. Costo adicional: " + CostoDesdePaginaWeb );

            agregarTransaccion("Compra Pagina Web: ", cantidad);
        } else {
            System.out.println("Monto inválido o saldo insuficiente.");
        }
    }
    
}
