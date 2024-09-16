package cuenta;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Banco {
    private static Scanner scanner = new Scanner(System.in);
    private static Cuenta cuenta;
    private static int tipoCuenta;
    private static String nombre;

    public static void main(String[] args) {
        Boolean cerrar = true;
        leerInformacion();
        while (cerrar) {
            mostrarMenu();
            Integer opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    cuenta.depositar(ingresarValor(), 1);
                    break;
                case 2:
                    cuenta.depositar(ingresarValor(), 2);
                    break;
                case 3:
                    cuenta.depositar(ingresarValor(), 3);
                    break;
                case 4:
                    cuenta.comprar(ingresarValor(), 1);
                    break;
                case 5:
                    cuenta.comprar(ingresarValor(), 2);
                    break;
                case 6:
                    cuenta.retirar(ingresarValor());
                    break;
                case 7:
                    System.out.println("\n El saldo actual de tu cuenta es: " + cuenta.getSaldo());
                    break;
                case 8:
                    System.out.println("\n Sus transacciones son: " + cuenta.getTransacciones());
                    break;
                case 9:
                    cerrar = false;
                    System.out.println("\n Sesion finalizada exitosamente!");
                    break;
                default:
                    System.out.println("\n Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }
    private static void mostrarMenu() {
        System.out.println("\n Sistema Bancario, Cuentas de " + nombre);
        System.out.println("1. Realizar depósito desde sucursal");
        System.out.println("2. Realizar depósito desde cajero automático");
        System.out.println("3. Realizar depósito desde otra cuenta");
        System.out.println("4. Realizar compra en establecimiento físico");
        System.out.println("5. Realizar compra en página web");
        System.out.println("6. Realizar retiro en cajero automático");
        System.out.println("7. Consultar saldo");
        System.out.println("8. Consultar historico (Ultimas 5 transacciones)");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción:");
    }
    private static void leerInformacion(){
        Integer numeroCuenta;
        BigDecimal saldo;

        System.out.println("Ingresa tu nombre:");
        nombre = scanner.next();

        System.out.println("Ingresa tu número de cuenta:");
        numeroCuenta = leerEnteros();

        System.out.println("Ingresa tu saldo inicial de la cuenta:");
        saldo = leerDecimal();

        System.out.println("Ingresa el tipo de cuenta (1. Basica - 2. Premium) :");
        tipoCuenta = leerEnteros();
        validarTipoCuenta(tipoCuenta, numeroCuenta, saldo);
    }

    private static void validarTipoCuenta(Integer tipo, Integer numero, BigDecimal saldo){
        switch (tipo) {
            case 1:
                cuenta = new CuentaBasica(numero, saldo);
                break;
            case 2:
                cuenta = new CuentaPremium(numero, saldo);
                break;
            default:
                System.out.println("\nOpción no válida. Intente nuevamente.");
        }
    }

    private static Integer leerOpcion() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opción no valida.");
                scanner.next();
            }
        }
    }
    private static BigDecimal ingresarValor(){
        System.out.print("\nIngrese valor: ");
        return leerDecimal();
    }

    private static BigDecimal leerDecimal() {
        while (true) {
            try {
                BigDecimal valor = scanner.nextBigDecimal();
                if (valor.compareTo(BigDecimal.ZERO) == 1) {
                    return valor;
                } else {
                    System.out.println("El valor ingresado no puede ser negativo. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next();
            }
        }
    }
    private static Integer leerEnteros() {
        while (true) {
            try {
                Integer entero = scanner.nextInt();
                if (entero > 0) {
                    return entero;
                } else {
                    System.out.println("El valor ingresado no puede ser negativo. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next();
            }
        }
    }
}