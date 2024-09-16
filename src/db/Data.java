package db;

import banco.*;

import java.math.BigDecimal;
import java.util.*;

public class Data {
    private static Map<String, Cliente> clientes = new HashMap<>();
    private static Map<String, Cuenta> cuentas = new HashMap<>();
    private static List<Transaccion> transacciones = new ArrayList<>();

    public static void inicializarDatos() {
        Cliente cliente1 = new Cliente("1", "Juan Pérez", "juan@email.com");
        Cliente cliente2 = new Cliente("2", "María López", "maria@email.com");
        Cliente cliente3 = new Cliente("3", "Carlos Rodríguez", "carlos@email.com");

        clientes.put(cliente1.getId(), cliente1);
        clientes.put(cliente2.getId(), cliente2);
        clientes.put(cliente3.getId(), cliente3);

        Cuenta cuenta1 = new CuentaBasica("1001", new BigDecimal("1000.00"), cliente1);
        Cuenta cuenta2 = new CuentaPremium("2001", new BigDecimal("5000.00"), cliente2);
        Cuenta cuenta3 = new CuentaBasica("1002", new BigDecimal("2000.00"), cliente3);

        cuentas.put(cuenta1.getNumeroCuenta(), cuenta1);
        cuentas.put(cuenta2.getNumeroCuenta(), cuenta2);
        cuentas.put(cuenta3.getNumeroCuenta(), cuenta3);

        // Generacion aleatoria de transacciones
        Random random = new Random();
        String[] tiposTransaccion = {"Depósito", "Retiro", "Compra"};
        for (int i = 0; i < 50; i++) {
            String tipo = tiposTransaccion[random.nextInt(tiposTransaccion.length)];
            BigDecimal monto = new BigDecimal(random.nextInt(1000) + 1);
            String numeroCuenta = "100" + (random.nextInt(2) + 1);
            Transaccion transaccion = new Transaccion(tipo, monto, numeroCuenta);
            transacciones.add(transaccion);
            cuentas.get(numeroCuenta).registrarTransaccion(transaccion);
        }
    }

    public static Cliente getCliente(String id) {
        return clientes.get(id);
    }

    public static Cuenta getCuenta(String numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }

    public static List<Transaccion> getTransacciones() {
        return new ArrayList<>(transacciones);
    }
}
