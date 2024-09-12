package usecase;

import modelo.CuentaBasica;
import modelo.CuentaPremium;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Validacion {

    public Integer numeroCuentaPositivo(Scanner scanner){
        boolean valid = false;
        Integer cuenta = 0;
        while (!valid){
            System.out.println("Ingrese el Número de la cuenta:");
            cuenta = scanner.nextInt();
            if (cuenta.compareTo(0) > 0){
                valid = true;
            }else{
                System.out.println("Por favor validar el número de la cuenta ingresado, es igual o inferior a cero.");
            }
        }
        return cuenta;
    }

    public Integer numeroCuentaValido(Scanner scanner, List<CuentaBasica> listaCuentasBasicas,
                                      List<CuentaPremium> listaCuentasPremium){
        boolean valid = false;
        Integer cuenta = 0;
        while (!valid){
            System.out.println("Ingrese el Número de la cuenta:");
            cuenta = scanner.nextInt();
            if (cuenta.compareTo(0) > 0){
                valid = true;
            }else{
                System.out.println("Por favor validar el número de la cuenta ingresado, es igual o inferior a cero.");
            }

            if(valid && !noExisteCuenta(listaCuentasBasicas, listaCuentasPremium, cuenta)){
                valid = false;
            }
        }
        return cuenta;
    }

    public BigDecimal valorSaldoPositivo(Scanner scanner){
        boolean valid = false;
        BigDecimal valor = new BigDecimal("0.0");
        while(!valid){
            System.out.println("Ingrese el Saldo:");
            valor  = scanner.nextBigDecimal();
            if(valor.signum() >= 0){
                valid = true;
            } else {
                System.out.println("Por favor validar el número ingresado, es negativo.");
            }
        }

        return valor;
    }

    public BigDecimal valorTransaccionPositivo(Scanner scanner){
        boolean valid = false;
        BigDecimal valor = new BigDecimal("0.0");
        while(!valid){
            System.out.println("Ingrese Valor de transacción:");
            valor  = scanner.nextBigDecimal();
            if(valor.signum() > 0){
                valid = true;
            } else {
                System.out.println("Por favor validar el número ingresado, es cero o negativo.");
            }
        }
        return valor;
    }

    public boolean noExisteCuenta(List<CuentaBasica> listaCuentasBasicas, List<CuentaPremium> listaCuentasPremium,
                                Integer cuenta){
        boolean valid = false;
        if(listaCuentasBasicas.isEmpty() && listaCuentasPremium.isEmpty()){
            valid = true;
        }else{
            for (CuentaBasica cuentaBasica: listaCuentasBasicas) {
                if(!Objects.equals(cuentaBasica.getNumeroCuenta(), cuenta)){
                    valid = true;
                } else{
                    System.out.println("Por favor validar el número de la cuenta ingresado, ya existe.");
                    valid = false;
                }
            }

            for (CuentaPremium cuentaPremium: listaCuentasPremium) {
                if(!Objects.equals(cuentaPremium.getNumeroCuenta(), cuenta)){
                    valid = true;
                } else{
                    System.out.println("Por favor validar el número de la cuenta ingresado, ya existe.");
                    valid = false;
                }
            }
        }
        return valid;
    }
}
