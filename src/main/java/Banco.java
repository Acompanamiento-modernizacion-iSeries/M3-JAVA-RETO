import modelo.CuentaBasica;
import modelo.CuentaPremium;
import modelo.HistorialTransaccion;
import modelo.Transaccion;
import usecase.Validacion;

import java.math.BigDecimal;
import java.util.*;

public class Banco {

    private static List<Transaccion> listTransaccion = new ArrayList<>();
    private static HistorialTransaccion historialTransaccion = new HistorialTransaccion(listTransaccion);
    private static Long ID = 1L;

    public static void main(String[] args) {
        var opcion = 0;
        var opcionCuentas = 0;
        var opcionSalir = 9;


        Scanner scanner = new Scanner(System.in);
        Validacion validacion = new Validacion();
        List<CuentaBasica> listaCuentasBasica = new ArrayList<>();
        List<CuentaPremium> listaCuentasPremium = new ArrayList<>();

        while (opcionCuentas != 3){
            System.out.println("Hola");
            System.out.println("Ingrese el tipo de cuenta a ingresar: basica o premium");
            System.out.println("Basica:     1");
            System.out.println("Premium:    2");
            System.out.println("Salir:      3");
            opcionCuentas = scanner.nextInt();

            if(opcionCuentas == 1){
                CuentaBasica cuentaBasica = new CuentaBasica();
                cuentaBasica.setNumeroCuenta(validacion.numeroCuentaValido(scanner, listaCuentasBasica,
                        listaCuentasPremium));
                cuentaBasica.setSaldo(validacion.valorSaldoPositivo(scanner));
                listaCuentasBasica.add(cuentaBasica);
            }else if(opcionCuentas == 2){
                CuentaPremium cuentaPremium = new CuentaPremium();
                cuentaPremium.setNumeroCuenta(validacion.numeroCuentaValido(scanner, listaCuentasBasica,
                        listaCuentasPremium));
                cuentaPremium.setSaldo(validacion.valorSaldoPositivo(scanner));
                listaCuentasPremium.add(cuentaPremium);
            }
        }

        while (opcion < opcionSalir){
            System.out.println("Por favor seleccione unas de las siguientes opciones:");
            System.out.println("- Consultar saldo cuenta:               1");
            System.out.println("- Realizar deposito sucursal:           2");
            System.out.println("- Realizar deposito cajero automatico:  3");
            System.out.println("- Realizar deposito desde otra cuenta:  4");
            System.out.println("- Compra establecimiento fisico:        5");
            System.out.println("- Compra pagina Web:                    6");
            System.out.println("- Retiro cajero:                        7");
            System.out.println("- Consultar historial de transacciones: 8");
            System.out.println("- Salir:                                " + opcionSalir);

            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("Su saldo total en la cuenta es: " +
                            consultarSaldo(validacion.numeroCuentaPositivo(scanner), listaCuentasBasica,
                                    listaCuentasPremium));
                    break;
                case 2:
                    System.out.println("Su saldo total en la cuenta es: " +
                            depositoSucursal(validacion.numeroCuentaPositivo(scanner), listaCuentasBasica,
                                    listaCuentasPremium, validacion.valorTransaccionPositivo(scanner)));
                    break;
                case 3:
                    System.out.println("Su saldo total en la cuenta es: " +
                            depositoCajero(validacion.numeroCuentaPositivo(scanner), listaCuentasBasica,
                                    listaCuentasPremium, validacion.valorTransaccionPositivo(scanner)));
                    break;
                case 4:
                    System.out.println("Su saldo total en la cuenta es: " +
                            depositoOtraCuenta(validacion.numeroCuentaPositivo(scanner), listaCuentasBasica,
                                    listaCuentasPremium, validacion.valorTransaccionPositivo(scanner)));
                    break;
                case 5:
                    System.out.println("Su saldo total en la cuenta es: " +
                            compraFisico(validacion.numeroCuentaPositivo(scanner), listaCuentasBasica,
                                    listaCuentasPremium, validacion.valorTransaccionPositivo(scanner)));
                    break;
                case 6:
                    System.out.println("Su saldo total en la cuenta es: " +
                            compraWeb(validacion.numeroCuentaPositivo(scanner), listaCuentasBasica,
                                    listaCuentasPremium, validacion.valorTransaccionPositivo(scanner)));
                    break;
                case 7:
                    System.out.println("Su saldo total en la cuenta es: " +
                            retiroCajero(validacion.numeroCuentaPositivo(scanner), listaCuentasBasica,
                                    listaCuentasPremium, validacion.valorTransaccionPositivo(scanner)));
                    break;
                case 8:
                    System.out.println("Historico de transacciones: ");
                    Integer cuenta = validacion.numeroCuentaPositivo(scanner);
                    int count = 0;
                    for (Transaccion transaccion : historialTransaccion.getListHistorialTransaccion()) {
                        if(Objects.equals(transaccion.getNumeroCuenta(), cuenta)){
                            System.out.println("Id: " + transaccion.getId() +
                                    " | Tipo transacción: " + transaccion.getTipoTransaccion() +
                                    " | Monto: " + transaccion.getMonto() +
                                    " | Fecha: " + transaccion.getFecha() +
                                    " | Cuenta: " + transaccion.getNumeroCuenta());
                            count++;
                        }
                        if(count >= 5){
                            break;
                        }
                    }
                    break;
                case 9:
                    System.out.println("Que tenga un buen día.");
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    private static BigDecimal consultarSaldo(Integer cuenta, List<CuentaBasica> listaCuentasBasicas,
                                  List<CuentaPremium> listaCuentasPremium){
        for (CuentaBasica cuentaBasica: listaCuentasBasicas) {
            if(Objects.equals(cuentaBasica.getNumeroCuenta(), cuenta)){
                return cuentaBasica.getSaldo();
            }
        }
        for (CuentaPremium cuentaPremium: listaCuentasPremium) {
            if(Objects.equals(cuentaPremium.getNumeroCuenta(), cuenta)){
                return cuentaPremium.getSaldo();
            }
        }
        return null;
    }

    private static BigDecimal depositoSucursal(Integer cuenta, List<CuentaBasica> listaCuentasBasicas,
                                               List<CuentaPremium> listaCuentasPremium, BigDecimal valor){
        for (CuentaBasica cuentaBasica: listaCuentasBasicas) {
            if(Objects.equals(cuentaBasica.getNumeroCuenta(), cuenta)){
                cuentaBasica.depositoSucursal(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Deposito sucursal", valor, cuenta));
            }
        }
        for (CuentaPremium cuentaPremium: listaCuentasPremium) {
            if(Objects.equals(cuentaPremium.getNumeroCuenta(), cuenta)){
                cuentaPremium.depositoSucursal(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Deposito sucursal", valor, cuenta));
            }
        }
        return consultarSaldo(cuenta, listaCuentasBasicas, listaCuentasPremium);
    }

    private static BigDecimal depositoCajero(Integer cuenta, List<CuentaBasica> listaCuentasBasicas,
                                               List<CuentaPremium> listaCuentasPremium, BigDecimal valor){
        for (CuentaBasica cuentaBasica: listaCuentasBasicas) {
            if(Objects.equals(cuentaBasica.getNumeroCuenta(), cuenta)){
                cuentaBasica.depositoCajeroAuto(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Deposito cajero", valor, cuenta));
            }
        }
        for (CuentaPremium cuentaPremium: listaCuentasPremium) {
            if(Objects.equals(cuentaPremium.getNumeroCuenta(), cuenta)){
                cuentaPremium.depositoCajeroAuto(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Deposito cajero", valor, cuenta));
            }
        }
        return consultarSaldo(cuenta, listaCuentasBasicas, listaCuentasPremium);
    }

    private static BigDecimal depositoOtraCuenta(Integer cuenta, List<CuentaBasica> listaCuentasBasicas,
                                             List<CuentaPremium> listaCuentasPremium, BigDecimal valor){
        for (CuentaBasica cuentaBasica: listaCuentasBasicas) {
            if(Objects.equals(cuentaBasica.getNumeroCuenta(), cuenta)){
                cuentaBasica.depositoOtraCuenta(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Deposito otra cuenta", valor, cuenta));
            }
        }
        for (CuentaPremium cuentaPremium: listaCuentasPremium) {
            if(Objects.equals(cuentaPremium.getNumeroCuenta(), cuenta)){
                cuentaPremium.depositoOtraCuenta(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Deposito otra cuenta", valor, cuenta));
            }
        }
        return consultarSaldo(cuenta, listaCuentasBasicas, listaCuentasPremium);
    }

    private static BigDecimal compraFisico(Integer cuenta, List<CuentaBasica> listaCuentasBasicas,
                                                 List<CuentaPremium> listaCuentasPremium, BigDecimal valor){
        for (CuentaBasica cuentaBasica: listaCuentasBasicas) {
            if(Objects.equals(cuentaBasica.getNumeroCuenta(), cuenta)){
                cuentaBasica.compraFisico(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Compra fisico", valor, cuenta));
            }
        }
        for (CuentaPremium cuentaPremium: listaCuentasPremium) {
            if(Objects.equals(cuentaPremium.getNumeroCuenta(), cuenta)){
                cuentaPremium.compraFisico(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Compra fisico", valor, cuenta));
            }
        }
        return consultarSaldo(cuenta, listaCuentasBasicas, listaCuentasPremium);
    }

    private static BigDecimal compraWeb(Integer cuenta, List<CuentaBasica> listaCuentasBasicas,
                                        List<CuentaPremium> listaCuentasPremium, BigDecimal valor){
        for (CuentaBasica cuentaBasica: listaCuentasBasicas) {
            if(Objects.equals(cuentaBasica.getNumeroCuenta(), cuenta)){
                cuentaBasica.compraWeb(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Compra Web", valor, cuenta));
            }
        }
        for (CuentaPremium cuentaPremium: listaCuentasPremium) {
            if(Objects.equals(cuentaPremium.getNumeroCuenta(), cuenta)){
                cuentaPremium.compraWeb(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Compra Web", valor, cuenta));
            }
        }
        return consultarSaldo(cuenta, listaCuentasBasicas, listaCuentasPremium);
    }

    private static BigDecimal retiroCajero(Integer cuenta, List<CuentaBasica> listaCuentasBasicas,
                                           List<CuentaPremium> listaCuentasPremium, BigDecimal valor){
        for (CuentaBasica cuentaBasica: listaCuentasBasicas) {
            if(Objects.equals(cuentaBasica.getNumeroCuenta(), cuenta)){
                cuentaBasica.retiroCajero(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Retiro cajero", valor, cuenta));
            }
        }
        for (CuentaPremium cuentaPremium: listaCuentasPremium) {
            if(Objects.equals(cuentaPremium.getNumeroCuenta(), cuenta)){
                cuentaPremium.retiroCajero(valor);
                historialTransaccion.getListHistorialTransaccion().add(
                        createTransaccionHistorico("Retiro cajero", valor, cuenta));
            }
        }
        return consultarSaldo(cuenta, listaCuentasBasicas, listaCuentasPremium);
    }

    private static Transaccion createTransaccionHistorico(String tipoTransaccion, BigDecimal monto, Integer numeroCuenta){
        Transaccion transaccion = new Transaccion();
        transaccion.setId(ID++);
        transaccion.setTipoTransaccion(tipoTransaccion);
        transaccion.setMonto(monto);
        transaccion.setFecha(new Date());
        transaccion.setNumeroCuenta(numeroCuenta);
        return transaccion;
    }
}
