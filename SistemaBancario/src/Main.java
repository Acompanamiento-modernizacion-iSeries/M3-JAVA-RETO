import cuentas.*;
import baseDeDatos.*;
import usuarios.*;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        //Crear en tiempo de ejecución usuarios(user y pass) y sus cuentas relacionadas.
        inicializarCuentas();
        inicializarUsuarios();

        Banco banco = new Banco();
        //Llamado al menu principal de la aplicación bancaria.
        banco.mostrarMenu();
    }

    private static void inicializarCuentas() {
        CuentasDB.agregarCuenta(new CuentaBasica(new BigDecimal("1000.00"), "101010"));
        CuentasDB.agregarCuenta(new CuentaBasica(new BigDecimal("1500.00"), "111111"));
        CuentasDB.agregarCuenta(new CuentaPremium(new BigDecimal("3000.00"), "202020"));
        CuentasDB.agregarCuenta(new CuentaPremium(new BigDecimal("3500.00"), "212121"));
    }

    private static void inicializarUsuarios() {
        LoginDB.agregarUsuario(new Usuario("Juan Pablo Valderrama", "jvalderr", "p123", CuentasDB.getCuentas().get(0)));
        LoginDB.agregarUsuario(new Usuario("Maria Lopez Castrillon", "mlopez", "p456", CuentasDB.getCuentas().get(1)));
        LoginDB.agregarUsuario(new Usuario("Carlos Ruiz Valencia", "cruiz", "p789", CuentasDB.getCuentas().get(2)));
        LoginDB.agregarUsuario(new Usuario("Daniela Gomez Gomez", "dgomez", "p000", CuentasDB.getCuentas().get(3)));
    }
}