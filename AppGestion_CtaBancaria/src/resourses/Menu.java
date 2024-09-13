package resourses;

import cuentas.CuentaBasica;
import cuentas.CuentaPremium;
import db.CuentasDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static String setUp() {
        List<String> numCuentas = new ArrayList<>();

        numCuentas.add(
                CuentasDB.agregarCuenta(new CuentaBasica("andres","1234567890",21000))
        );
        numCuentas.add(
                CuentasDB.agregarCuenta(new CuentaBasica("carlos","1122334455",12000))
        );
        numCuentas.add(
                CuentasDB.agregarCuenta(new CuentaPremium("nestor","1112223334",25000))
        );
        numCuentas.add(
                CuentasDB.agregarCuenta(new CuentaPremium("kevin","1111222233",18000))
        );

        System.out.println("\nSe encuentra gestionando la cuenta " + numCuentas.get(0));
        return numCuentas.get(0);
    }

    public static int principal(Scanner scanner) {
        while (true) {
            System.out.println("Ingrese el numero de la Transaccion a realizar:\n" +
                    " 1. Gestionar otra cuenta existente.\n" +
                    " 2. Consultar el saldo de la cuenta.\n" +
                    " 3. Consultar ultimos movimientos de la cuenta.\n" +
                    " 4. Depositar desde sucursal.\n" +
                    " 5. Depositar desde cajero automatico.\n" +
                    " 6. Transferir a otra cuenta.\n" +
                    " 7. Realizar compra en establecimiento físico.\n" +
                    " 8. Realizar compra en pagina web.\n" +
                    " 9. Retiro en cajero.\n" +
                    " 0. TERMINAR");
            if (scanner.hasNextInt()) return scanner.nextInt();
            else {
                System.out.println("Tipo de transaccion no reconocida!");
                scanner.next(); // Limpiar el buffer del scanner para evitar un bucle infinito
            }
        }
    }

}
