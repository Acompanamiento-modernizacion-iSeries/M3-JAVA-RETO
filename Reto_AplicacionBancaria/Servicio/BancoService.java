package Servicio;

import Cuentas.Cuenta;

import java.math.BigDecimal;

public class BancoService {

    public void depositar(Cuenta cuenta, BigDecimal monto, String tipoDeposito) {
        cuenta.depositar(monto, tipoDeposito);
        System.out.println("Depósito realizado correctamente.");
    }

    public void retirar(Cuenta cuenta, BigDecimal monto) {
        cuenta.retirar(monto);
        System.out.println("Retiro realizado correctamente.");
    }

    public void comprar(Cuenta cuenta, BigDecimal monto, String tipoCompra) {
        cuenta.comprar(monto, tipoCompra);
        System.out.println("Compra realizada correctamente.");
    }

    public void mostrarSaldo(Cuenta cuenta) {
        System.out.println("Saldo actual: " + cuenta.getSaldo());
    }

    public void mostrarHistorial(Cuenta cuenta) {
        cuenta.obtenerUltimasTransacciones(5).forEach(System.out::println);
    }
}
