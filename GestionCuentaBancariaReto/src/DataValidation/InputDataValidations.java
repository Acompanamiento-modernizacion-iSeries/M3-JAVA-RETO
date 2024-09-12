package DataValidation;

import models.Cuenta;

import java.util.ArrayList;
import java.util.Scanner;

public class InputDataValidations {

   public Integer ValidarTipoTransaccion(){
        Integer opcionTransaccion;
        Scanner scan = new Scanner(System.in);
        while (true){
            String opcionTransaccionTexto = scan.next();
            try {
                opcionTransaccion = Integer.parseInt(opcionTransaccionTexto);
                if (opcionTransaccion.equals(1) ||
                        opcionTransaccion.equals(2) ||
                        opcionTransaccion.equals(3) ||
                        opcionTransaccion.equals(4) ||
                        opcionTransaccion.equals(5) ||
                        opcionTransaccion.equals(6) ||
                        opcionTransaccion.equals(7) ||
                        opcionTransaccion.equals(8) ||
                        opcionTransaccion.equals(9)){
                    break;
                }else{
                    System.out.println("Ingrese un tipo de transacción correcta");
                }
            }catch (Exception e){
                System.out.println("Ingrese solo valores númericos");
            }
        }

        return opcionTransaccion;
    }


    public Cuenta buscarCuenta(ArrayList<Cuenta> cuentas, String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.consularNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

}
