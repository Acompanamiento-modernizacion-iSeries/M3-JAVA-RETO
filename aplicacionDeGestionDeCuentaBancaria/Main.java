import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Cuenta> cuentas = new HashMap<>();
        String numeroCuenta;

        while (true) {
            System.out.println("1. Crear Cuenta Básica");
            System.out.println("2. Crear Cuenta Premium");
            System.out.println("3. Realizar operación");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Consultar historial");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el número de cuenta: ");
                    numeroCuenta = scanner.next();
                    if (cuentas.containsKey(numeroCuenta)) {
                        System.out.println("********************");
                        System.out.println("La cuenta ya existe.");
                        System.out.println("Por favor agregue otra");
                        System.out.println("********************");
                        break;
                    }
                    System.out.print("Introduce el saldo inicial: ");
                    double saldoInicial = scanner.nextDouble();
                    if (saldoInicial < 0) {
                        System.out.println("El saldo inicial no puede ser negativo.");
                        break;
                    }
                    cuentas.put(numeroCuenta, new CuentaBasica(numeroCuenta, saldoInicial));
                    break;
                case 2:
                    System.out.print("Introduce el número de cuenta: ");
                    numeroCuenta = scanner.next();
                    if (cuentas.containsKey(numeroCuenta)) {
                        System.out.println("********************");
                        System.out.println("La cuenta ya existe.");
                        System.out.println("Por favor agregue otra");
                        System.out.println("********************");
                        break;
                    }
                    System.out.print("Introduce el saldo inicial: ");
                    saldoInicial = scanner.nextDouble();
                    if (saldoInicial < 0) {
                        System.out.println("El saldo inicial no puede ser negativo.");
                        break;
                    }
                    cuentas.put(numeroCuenta, new CuentaPremium(numeroCuenta, saldoInicial));
                    break;
                case 3:
                    System.out.print("Introduce el número de cuenta: ");
                    numeroCuenta = scanner.next();
                    Cuenta cuenta = cuentas.get(numeroCuenta);
                    if (cuenta == null) {
                        System.out.println("La cuenta no existe.");
                        break;
                    }
                    System.out.println("1. Depósito en sucursal");
                    System.out.println("2. Depósito en cajero");
                    System.out.println("3. Depósito desde otra cuenta");
                    System.out.println("4. Compra en establecimiento físico");
                    System.out.println("5. Compra en página web");
                    System.out.println("6. Retiro en cajero");
                    System.out.print("Elige una operación: ");
                    int operacion = scanner.nextInt();
                    System.out.print("Introduce el monto: ");
                    double monto = scanner.nextDouble();
                    switch (operacion) {
                        case 1:
                            cuenta.depositoSucursal(monto);
                            break;
                        case 2:
                            cuenta.depositoCajero(monto);
                            break;
                        case 3:
                            cuenta.depositoCuenta(monto);
                            break;
                        case 4:
                            cuenta.compraFisico(monto);
                            break;
                        case 5:
                            cuenta.compraWeb(monto);
                            break;
                        case 6:
                            cuenta.retiroCajero(monto);
                            break;
                    }
                    break;
                case 4:
                    System.out.print("Introduce el número de cuenta: ");
                    numeroCuenta = scanner.next();
                    cuenta = cuentas.get(numeroCuenta);
                    if (cuenta == null) {
                        System.out.println("La cuenta no existe.");
                        break;
                    }
                    System.out.println("Saldo: " + cuenta.consultarSaldo());
                    break;
                case 5:
                    System.out.print("Introduce el número de cuenta: ");
                    numeroCuenta = scanner.next();
                    cuenta = cuentas.get(numeroCuenta);
                    if (cuenta == null) {
                        System.out.println("La cuenta no existe.");
                        break;
                    }
                    List<Transaccion> historial = cuenta.consultarHistorial();
                    int start = Math.max(0, historial.size() - 5);
                    for (int i = start; i < historial.size(); i++) {
                        Transaccion transaccion = historial.get(i);
                        System.out.println("Tipo: " + transaccion.getTipo());
                        System.out.println("Monto: " + transaccion.getMonto());
                        System.out.println("Fecha: " + transaccion.getFecha());
                        System.out.println("Código: " + transaccion.getCodigo());
                        System.out.println();
                    }
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
            }
        }
    }
}