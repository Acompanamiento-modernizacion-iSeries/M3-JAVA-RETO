package resourses;

import cuentas.Cuenta;
import cuentas.CuentaBasica;
import cuentas.CuentaPremium;
import db.CuentasDB;
import db.TransaccionesDB;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Movimiento {

    public static String seleccionarCuenta(Scanner scanner) {
        String numCuenta;

        List<String> listaNumCuentas = CuentasDB.listarNumCuentas();
        System.out.println("*** LISTADO DE CUENTAS ***");

        listaNumCuentas.forEach(System.out::println);

        while (true) {
            System.out.println("Ingrese el Numero de la Cuenta que quiere gestionar:");
            numCuenta = scanner.next();

            if (CuentasDB.buscarCuenta(numCuenta.toLowerCase()) != null) {
                System.out.println("Ahora se encuentra gestionando la cuenta " + numCuenta + "\n");
                return numCuenta;
            }
            else
                System.out.println("No existe una cuenta con el número proporcionado!");
        }
    }

    public static void consultaSaldo(Cuenta cuenta) {
        System.out.println("El saldo de la Cuenta es: " + cuenta.consultarSaldoDisponible() + "\n");
    }

    public static void consultaTransacciones(String numCuenta) {
        int numTransacc = 5;

        List<Transaccion> listaTransacciones = TransaccionesDB.buscarTransacciones(numCuenta, numTransacc);
        if (listaTransacciones.size() > 0) {
            System.out.println("*** LISTADO DE LAS ULTIMAS " + numTransacc + " TRANSACCIONES ***");
            System.out.println("Codigo | Tipo | Valor | Fecha");

            listaTransacciones.forEach(transaccion -> {
                System.out.println(
                        transaccion.codigoTransacc + " | " + transaccion.tipoTransacc + " | " +
                        transaccion.vlrTransacc + " | " + transaccion.fecha);
            });
            System.out.println("\n");
        } else
            System.out.println("Aún no tiene transacciones registradas para esta cuenta");
    }

    public static void deposito(Scanner scanner, Cuenta cuenta, String tipoTransacc, Double comision) {
        double vlrTransacc;
        boolean transaccOk;
        String msgNoOk = "Lo sentimos, debe ingresar un valor igual o mayor a 0!";

        System.out.println("Ingrese el Valor del Deposito :");
        if (scanner.hasNextDouble()) vlrTransacc = scanner.nextDouble();
        else {
            System.out.println(msgNoOk);
            scanner.next(); // Limpiar el buffer del scanner
            return;
        }

        transaccOk = cuenta.deposito(vlrTransacc-comision);
        if (transaccOk) {
            TransaccionesDB.agregarTransaccion(new Transaccion(
                    tipoTransacc,
                    cuenta.consultarNumCuenta(),
                    vlrTransacc-comision,
                    new Date()
            ));
            System.out.println("Deposito por " + vlrTransacc + " realizado!\n");
        } else
            System.out.println(msgNoOk);
    }

    public static void transferencia(Scanner scanner, Cuenta cuentaOrigen) {
        double vlrTransacc;
        double comision;
        boolean transaccOk;
        String msgNoOk = "Lo sentimos, debe ingresar un valor igual o mayor a 0 y menor o igual al saldo disponible!\n";

        System.out.println("Ingrese el Valor de la Transferencia :");
        if (scanner.hasNextDouble()) vlrTransacc = scanner.nextDouble();
        else {
            System.out.println(msgNoOk);
            scanner.next(); // Limpiar el buffer del scanner
            return;
        }

        System.out.println("Ingrese el Numero de la Cuenta Destino :");
        String numCtaDestino = scanner.next();
        if (CuentasDB.buscarCuenta(numCtaDestino) != null) {
            transaccOk = cuentaOrigen.retiro(vlrTransacc);
            if (transaccOk) {
                TransaccionesDB.agregarTransaccion(new Transaccion(
                        "deposito a otra cuenta",
                        cuentaOrigen.consultarNumCuenta(),
                        vlrTransacc,
                        new Date()
                ));
            } else {
                System.out.println("Lo sentimos, no tiene recursos suficientes para realizar esta transacción!\n");
                return;
            }

            if (CuentasDB.buscarCuenta(numCtaDestino).getClass().getName().equals("cuentas.CuentaBasica"))
                comision = CuentaBasica.comisionDepositoOtraCta();
            else
                comision = CuentaPremium.comisionDepositoOtraCta();

            transaccOk = CuentasDB.buscarCuenta(numCtaDestino)
                    .deposito(vlrTransacc-comision);
            if (transaccOk) {
                TransaccionesDB.agregarTransaccion(new Transaccion(
                        "deposito desde otra cuenta",
                        CuentasDB.buscarCuenta(numCtaDestino).consultarNumCuenta(),
                        vlrTransacc-comision,
                        new Date()
                ));
                System.out.println("Transferencia por " + vlrTransacc + " realizada!\n");
            }
        } else
            System.out.println("No existe una cuenta con el número proporcionado!\n");
    }

    public static void retiro(Scanner scanner, Cuenta cuenta, String tipoTransacc, Double comision) {
        double vlrTransacc;
        boolean transaccOk;
        String msgNoOk = "Lo sentimos, debe ingresar un valor igual o mayor a 0 y menor o igual al saldo disponible!";

        System.out.println("Ingrese el Valor de la Transaccion :");
        if (scanner.hasNextDouble()) vlrTransacc = scanner.nextDouble();
        else {
            System.out.println(msgNoOk);
            scanner.next(); // Limpiar el buffer del scanner
            return;
        }

        transaccOk = cuenta.retiro(vlrTransacc+comision);
        if (transaccOk) {
            TransaccionesDB.agregarTransaccion(new Transaccion(
                    tipoTransacc,
                    cuenta.consultarNumCuenta(),
                    vlrTransacc+comision,
                    new Date()
            ));
            System.out.println("Transaccion por " + vlrTransacc + " realizada!\n");
        } else
            System.out.println("Lo sentimos, no tiene recursos suficientes para realizar esta transacción!\n");
    }
}
