package MainAplicacion;

import Cuentas.Cuenta;
import Cuentas.CuentaBasica;
import Cuentas.CuentaPremium;
import Servicio.BancoService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BancoService bancoService = new BancoService();

        // Elegir el tipo de cuenta (CuentaBasica o CuentaPremium)
        Cuenta cuenta;
        System.out.println("Seleccione el tipo de cuenta:");
        System.out.println("1. Cuenta Básica");
        System.out.println("2. Cuenta Premium");
        int tipoCuenta = scanner.nextInt();

        // Inicializando la cuenta con un saldo inicial de 1000
        if (tipoCuenta == 1) {
            cuenta = new CuentaBasica("12345", new BigDecimal("1000"));
        } else {
            cuenta = new CuentaPremium("67890", new BigDecimal("1000"));
        }

        int opcion;
        do {
            System.out.println("\nMenú de operaciones Aplicación Bancaria Reto Final - Curso Java 2024:");
            System.out.println("1. Depositar");
            System.out.println("2. Retirar");
            System.out.println("3. Comprar");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Consultar historial");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Operación de depósito
                    System.out.print("Ingrese el monto a depositar: ");
                    BigDecimal montoDeposito = scanner.nextBigDecimal();
                    System.out.println("Seleccione el tipo de depósito:");
                    System.out.println("1. Sucursal (sin costo)");
                    System.out.println("2. Cajero Automático (costo de 2 USD para Cuenta Básica)");
                    System.out.println("3. Desde otra cuenta (costo de 1.5 USD)");
                    int tipoDeposito = scanner.nextInt();
                    String tipoDepositoTexto = "";

                    switch (tipoDeposito) {
                        case 1:
                            tipoDepositoTexto = "sucursal";
                            break;
                        case 2:
                            tipoDepositoTexto = "cajero";
                            break;
                        case 3:
                            tipoDepositoTexto = "otraCuenta";
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }

                    if (!tipoDepositoTexto.isEmpty()) {
                        bancoService.depositar(cuenta, montoDeposito, tipoDepositoTexto);
                    }
                    break;

                case 2:
                    // Operación de retiro
                    System.out.print("Ingrese el monto a retirar: ");
                    BigDecimal montoRetiro = scanner.nextBigDecimal();
                    bancoService.retirar(cuenta, montoRetiro);
                    break;

                case 3:
                    // Operación de compra
                    System.out.print("Ingrese el monto de la compra: ");
                    BigDecimal montoCompra = scanner.nextBigDecimal();
                    System.out.println("Seleccione el tipo de compra:");
                    System.out.println("1. Compra en establecimiento físico (sin costo)");
                    System.out.println("2. Compra en página web (costo adicional de 5 USD por seguro de robo)");
                    int tipoCompra = scanner.nextInt();
                    String tipoCompraTexto = "";

                    switch (tipoCompra) {
                        case 1:
                            tipoCompraTexto = "fisica";
                            break;
                        case 2:
                            tipoCompraTexto = "web";
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }

                    if (!tipoCompraTexto.isEmpty()) {
                        bancoService.comprar(cuenta, montoCompra, tipoCompraTexto);
                    }
                    break;

                case 4:
                    // Consultar saldo actual
                    bancoService.mostrarSaldo(cuenta);
                    break;

                case 5:
                    // Consultar historial de transacciones
                    bancoService.mostrarHistorial(cuenta);
                    break;

                case 6:
                    System.out.println("Gracias por utilizar el sistema bancario.");
                    break;

                default:
                    System.out.println("Opción inválida, por favor seleccione nuevamente.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}
