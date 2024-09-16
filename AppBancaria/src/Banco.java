import cuentas.Cuenta;
import cuentas.CuentaBasica;
import cuentas.CuentaPremium;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    private List<Cuenta> cuentas = new ArrayList<>();
    private String opcion,numeroCuenta,tipoCuenta;
    private BigDecimal transaccion;
    private BigDecimal saldo;
    private Cuenta cuenta;
    Scanner scanner = new Scanner(System.in);

    public void Menu(){

        if (!crearCuenta())
            return;

        do {
            MostrarOpciones();
            switch (opcion){
                case "1":
                    depositoSucursal();
                    break;
                case "2":
                    depositoCajero();
                    break;
                case "3":
                    depositoCuenta();
                    break;
                case "4":
                    compraFisica();
                    break;
                case "5":
                    compraWeb();
                    break;
                case "6":
                    retiroCajero();
                    break;
                case "7":
                    consultarSaldo();
                    break;
                case "8":
                    ultimas5transacciones();
                    break;
                case "9":
                    MensajeGenerico("Gracias por su confianza!!");
                    break;
                default:
                    MensajeGenerico("Opción no válida");
                    break;
            }
        } while (!opcion.equals("9"));

        scanner.close();

    }


    private void MostrarOpciones(){
        System.out.println("\nMenú:");
        System.out.println("1. Depósito en sucursal");
        System.out.println("2. Depósito en cajero automático");
        System.out.println("3. Depósito desde otra cuenta");
        System.out.println("4. Compra en establecimiento físico");
        System.out.println("5. Compra en página web");
        System.out.println("6. Retiro en cajero");
        System.out.println("7. Consultar saldo");
        System.out.println("8. Consultar historial de transacciones");
        System.out.println("9. Salir");
        opcion = scanner.next();

    }
    private void MensajeGenerico(String mensaje){
        System.out.println(mensaje);
    }

//    public Cuenta buscarCuenta(String numeroCuenta) {
//        for (Cuenta cuenta : cuentas) {
//            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
//                return cuenta;
//            }
//        }
//        MensajeGenerico("Número de cuenta no encontrada");
//        return null;
//    }
//
//    private void ConsultarSaldo(){
//        Cuenta cuenta = buscarCuenta(numeroCuenta);
//        if (cuenta == null)
//            return;
//        MensajeGenerico("El saldo es $"+cuenta.getSaldo().toString());
//    }

    private boolean crearCuenta(){
        MensajeGenerico("Digite\n1:Cuenta Básica\n2:Cuenta Premium");
        tipoCuenta = scanner.next();
        if (!tipoCuenta.equals("1") && (!tipoCuenta.equals("2"))){
            MensajeGenerico("Opción no válida de tipo de cuenta");
            return false;
        }
        MensajeGenerico("Digite el número de la cuenta:");
        numeroCuenta = scanner.next();
        if (numeroCuenta.isEmpty() || (numeroCuenta.isBlank())){
            MensajeGenerico("Número de cuenta no válido");
            return false;
        }
        MensajeGenerico("Digite el saldo inicial de la cuenta $:");
        try {
            saldo = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_EVEN);
        } catch (Exception e){
            MensajeGenerico("Saldo inical no válido");
            MensajeGenerico(e.toString());
            return false;
        }
        //Instancia de la clase abstracta Cuenta
        if (tipoCuenta.equals("1")){
            cuenta = new CuentaBasica(numeroCuenta,saldo);
        }else {
            cuenta = new CuentaPremium(numeroCuenta,saldo);
        }
        MensajeGenerico("Cuenta Creada!!");
        return true;
    }

    private void depositoSucursal(){
        MensajeGenerico("Ingrese el monto del depósito:");
        try{
            transaccion = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_EVEN);
            cuenta.depositoSucursal(transaccion);
        }catch (Exception e){
            MensajeGenerico("Monto del depósito no válido");
            MensajeGenerico(e.toString());
            return;
        }
        MensajeGenerico("Operación exitosa");
    }

    private void depositoCajero(){
        MensajeGenerico("Ingrese el monto del depósito desde el cajero automático:");
        try{
            transaccion = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_EVEN);
            cuenta.depositoCajero(transaccion);
        }catch (Exception e){
            MensajeGenerico("Monto del depósito no válido");
            MensajeGenerico(e.toString());
            return;
        }
        MensajeGenerico("Operación exitosa");
    }

    private void depositoCuenta(){
        MensajeGenerico("Ingrese el monto del depósito desde la cuenta:");
        try{
            transaccion = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_EVEN);
            cuenta.depositoDesdeOtraCuenta(transaccion);
        }catch (Exception e){
            MensajeGenerico("Monto del depósito no válido");
            MensajeGenerico(e.toString());
            return;
        }
        MensajeGenerico("Operación exitosa");
    }

    private void compraFisica(){
        MensajeGenerico("Ingrese el monto de la compra del establecimiento físico:");
        try{
            transaccion = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_EVEN);
            cuenta.compraFisica(transaccion);
        }catch (Exception e){
            MensajeGenerico("Monto del depósito no válido");
            MensajeGenerico(e.toString());
            return;
        }
        MensajeGenerico("Operación exitosa");
    }

    private void compraWeb(){
        MensajeGenerico("Ingrese el monto de la compra desde el sitio Web:");
        try{
            transaccion = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_EVEN);
            cuenta.compraWeb(transaccion);
        }catch (Exception e){
            MensajeGenerico("Monto del depósito no válido");
            MensajeGenerico(e.toString());
            return;
        }
        MensajeGenerico("Operación exitosa");
    }

    private void retiroCajero(){
        MensajeGenerico("Ingrese el monto del retiro del cajero:");
        try{
            transaccion = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_EVEN);
            cuenta.retiroCajero(transaccion);
        }catch (Exception e){
            MensajeGenerico("Monto del retiro no válido");
            MensajeGenerico(e.toString());
            return;
        }
        MensajeGenerico("Operación exitosa");
    }

    private void consultarSaldo(){
        MensajeGenerico("Saldo actual $"+cuenta.getSaldo());
    }

    private void ultimas5transacciones(){
        cuenta.mostrarTransacciones();
    }


}
