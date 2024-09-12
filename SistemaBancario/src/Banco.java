import cuentas.*;
import baseDeDatos.*;
import usuarios.*;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

public class Banco {
    //Menu para inicio de sesión.
    public void mostrarMenu() {
        Usuario usuarioValido = null;

        // Bucle para solicitar datos de ingreso usuario hasta que sean válidos.
        while (usuarioValido == null) {
            String usuario = JOptionPane.showInputDialog("Ingrese su nombre de usuario \n(o escriba 'salir' para cerrar el sistema):");
            if (usuario == null || usuario.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacío.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }

            if (usuario.equalsIgnoreCase("salir")) {
                JOptionPane.showMessageDialog(null, "¡Gracias por usar nuestros servicios, Hasta pronto!");
                return;
            }

            String password = JOptionPane.showInputDialog("Ingrese su contraseña:");
            if (password == null || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }

            usuarioValido = validarUsuario(usuario, password);
            if (usuarioValido == null) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Inténtelo de nuevo.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }

            // Menú de opciones para usuario una ves logueado.
            String menu = "Bienvenid@: " + usuarioValido.getNombre() + "\n"
                    + "\n¿Qúe deseas realizar hoy?\n"
                    + "\n1. Depósito desde sucursal.\n"
                    + "2. Depósito desde cajero.\n"
                    + "3. Depósito a otra cuenta.\n"
                    + "4. Compra en establecimiento físico.\n"
                    + "5. Compra en página web.\n"
                    + "6. Retiro en Cajero.\n"
                    + "7. Consultar saldo.\n"
                    + "8. Consultar historial de transacciones.\n"
                    + "9. Salir del sistema.\n"
                    + "\n10. Cerrar sesión.\n"
                    + "\nElige una opción entre (1-10):";

            while (true) {
                String opcionStr = JOptionPane.showInputDialog(menu);
                int opcion;
                try {
                    opcion = Integer.parseInt(opcionStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Opción inválida. debe elegir un número entre 1 y 10.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                switch (opcion) {
                    case 1:
                        depositoSucursal(usuarioValido);
                        break;
                    case 2:
                        depositoCajero(usuarioValido);
                        break;
                    case 3:
                        depositoOtraCuenta(usuarioValido);
                        break;
                    case 4:
                        compraFisica(usuarioValido);
                        break;
                    case 5:
                        compraWeb(usuarioValido);
                        break;
                    case 6:
                        retiroCajero(usuarioValido);
                        break;
                    case 7:
                        consultarSaldo(usuarioValido);
                        break;
                    case 8:
                        historialTransaccion(usuarioValido);
                        break;
                    case 9:
                        JOptionPane.showMessageDialog(null, "¡Gracias por usar nuestros servicios, Hasta pronto!");
                        return;
                    case 10:
                        JOptionPane.showMessageDialog(null, "Sesión cerrada. Por favor, inicie sesión nuevamente.");
                        usuarioValido = null;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida. debe elegir un número entre 1 y 10.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                }

                if (opcion == 10) {
                    break;
                }
            }
        }
    }

    //Validar un usuario y contraseña VS Listado de usuarios.
    private Usuario validarUsuario(String usuario, String password) {
        for (Usuario u : LoginDB.getUsuarios()) {
            if (u.getUsuario().equals(usuario) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    private void depositoSucursal(Usuario usuario) {
        String montoStr = JOptionPane.showInputDialog("Ingrese el monto a depositar:");
        BigDecimal monto;
        //validar monto ingresado.
        try {
            monto = new BigDecimal(montoStr);
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(null, "El monto a depositar debe ser mayor que cero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto a depositar inválido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Se obtiene la cuenta asociada al usuario logueado.
        Cuenta cuenta = usuario.getCuentaAsociada();
        BigDecimal saldoAnterior = cuenta.getSaldo();
        //Se realiza el depósito.
        cuenta.depositoSucursal(monto);
        BigDecimal saldoNuevo = cuenta.getSaldo();
        //Calculo del costo generado por está transacción.
        BigDecimal costo = saldoAnterior.add(monto).subtract(saldoNuevo);

        JOptionPane.showMessageDialog(null, "Depósito desde sucursal realizado con éxito. \nNuevo saldo: $" + cuenta.getSaldo());

        // Registrar la transacción y sus datos.
        Transaccion transaccion = new Transaccion("Depósito desde Sucursal.", monto, costo);
        usuario.agregarTransaccion(transaccion);
    }

    private void depositoCajero(Usuario usuario) {
        String montoStr = JOptionPane.showInputDialog("Ingrese el monto a depositar:");
        BigDecimal monto;
        //validar monto ingresado.
        try {
            monto = new BigDecimal(montoStr);
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(null, "El monto a depositar debe ser mayor que cero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto a despositar inválido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Se obtiene la cuenta asociada al usuario logueado.
        Cuenta cuenta = usuario.getCuentaAsociada();
        try {
            BigDecimal saldoAnterior = cuenta.getSaldo();
            //Se realiza el depósito.
            cuenta.depositoCajero(monto);
            BigDecimal saldoNuevo = cuenta.getSaldo();
            //Calculo del costo generado por está transacción.
            BigDecimal costo = saldoAnterior.add(monto).subtract(saldoNuevo);

            JOptionPane.showMessageDialog(null, "Depósito desde cajero realizado con éxito. \nNuevo saldo: $"
                                           + cuenta.getSaldo());

            // Registrar la transacción y sus datos.
            Transaccion transaccion = new Transaccion("Depósito desde Cajero.", monto, costo);
            usuario.agregarTransaccion(transaccion);
        }
        //capturar posible excepción generada por el metodo depositoCajero.
        catch (UnsupportedOperationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void depositoOtraCuenta(Usuario usuario) {
        String numeroCuentaDestino = JOptionPane.showInputDialog("Ingrese el número de cuenta destino:");
        //validar que la cuenta ingresada no sea vacía.
        if (numeroCuentaDestino == null || numeroCuentaDestino.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El número de cuenta destino no puede estar vacío.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //buscar y recuperar la cuenta a la que se le quiere depositar.
        Cuenta cuentaDestino = CuentasDB.buscarCuentaPorNumero(numeroCuentaDestino);
        //si no hay objeto de cuenta es porque no existe en la lista.
        if (cuentaDestino == null) {
            JOptionPane.showMessageDialog(null, "La cuenta destino no existe.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String montoStr = JOptionPane.showInputDialog("Ingrese el monto a depositar:");
        BigDecimal monto;
        //validar monto ingresado.
        try {
            monto = new BigDecimal(montoStr);
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(null, "El monto a despositar debe ser mayor que cero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto a depositar inválido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Se obtiene la cuenta asociada al usuario logueado.
        Cuenta cuentaOrigen = usuario.getCuentaAsociada();
        try {
            BigDecimal saldoAnterior = cuentaOrigen.getSaldo();
            //Se resta el saldo a la cuenta origen desde donde sale el dinero a enviar.
            cuentaOrigen.depositoOtraCuenta(monto, numeroCuentaDestino);
            BigDecimal saldoNuevo = cuentaOrigen.getSaldo();
            //Calculo del costo generado por está transacción.
            BigDecimal costo = saldoAnterior.subtract(saldoNuevo).subtract(monto);
            //Se realiza el deposito en la cuenta destino. agregar el dinero enviado.
            cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));

            JOptionPane.showMessageDialog(null, "Depósito a cuenta Número: " +cuentaDestino.getNumeroCuenta()+
                                           " Realizado exitosamente. " + "\nNuevo saldo: $" + cuentaOrigen.getSaldo());

            // Registrar la transacción y sus datos cuenta origen.
            Transaccion transaccion = new Transaccion("Depósito a cuenta Número: "+cuentaDestino.getNumeroCuenta(), monto, costo);
            usuario.agregarTransaccion(transaccion);

            // se busca la cuenta destino a quien pertenece.
            Usuario usuarioDestino = LoginDB.buscarUsuarioPorCuenta(numeroCuentaDestino);
            if (usuarioDestino != null) {
                //se agrega la transaccion al usuario que recibe el dinero.
                Transaccion transaccionDestino = new Transaccion("Depósito recibido de cuenta Número: " + cuentaOrigen.getNumeroCuenta()+
                                                                "\nPersona que envía: "+usuario.getNombre(), monto, BigDecimal.ZERO);
                usuarioDestino.agregarTransaccion(transaccionDestino);
            }
        }
        //capturar posible excepción generada por el metodo depositoOtraCuenta.
        catch (UnsupportedOperationException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void compraFisica(Usuario usuario) {
        String montoStr = JOptionPane.showInputDialog("Ingrese valor de la compra realizada en establecimiento físico:");
        BigDecimal monto;
        //validar monto ingresado.
        try {
            monto = new BigDecimal(montoStr);
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(null, "El valor de la compra debe ser mayor que cero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor de compra inválido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Se obtiene la cuenta asociada al usuario logueado.
        Cuenta cuenta = usuario.getCuentaAsociada();
        try {
            BigDecimal saldoAnterior = cuenta.getSaldo();
            //Se realiza la compra física.
            cuenta.compraFisico(monto);
            BigDecimal saldoNuevo = cuenta.getSaldo();
            //Calculo del costo generado por está transacción.
            BigDecimal costo = saldoAnterior.subtract(saldoNuevo).subtract(monto);

            JOptionPane.showMessageDialog(null, "Compra en establecimiento físico realizada con éxito. \nNuevo saldo: $"
                                            + cuenta.getSaldo());

            // Registrar la transacción y sus datos.
            Transaccion transaccion = new Transaccion("Compra física.", monto, costo);
            usuario.agregarTransaccion(transaccion);
        }
        //capturar posible excepción generada por el metodo compraFisico.
        catch (UnsupportedOperationException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void compraWeb(Usuario usuario) {
        String montoStr = JOptionPane.showInputDialog("Ingrese valor de la compra realizada en página WEB:");
        BigDecimal monto;
        //validar monto ingresado.
        try {
            monto = new BigDecimal(montoStr);
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(null, "El valor de la compra debe ser mayor que cero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor de compra inválido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Se obtiene la cuenta asociada al usuario logueado.
        Cuenta cuenta = usuario.getCuentaAsociada();
        try {
            BigDecimal saldoAnterior = cuenta.getSaldo();
            //Se realiza la compra WEB.
            cuenta.compraWeb(monto);
            BigDecimal saldoNuevo = cuenta.getSaldo();
            //Calculo del costo generado por está transacción.
            BigDecimal costo = saldoAnterior.subtract(saldoNuevo).subtract(monto);

            JOptionPane.showMessageDialog(null, "Compra en página WEB realizada con éxito. \nNuevo saldo: $" + cuenta.getSaldo());

            // Registrar la transacción
            Transaccion transaccion = new Transaccion("Compra WEB.", monto, costo);
            usuario.agregarTransaccion(transaccion);
        }catch (UnsupportedOperationException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void retiroCajero(Usuario usuario) {
        String montoStr = JOptionPane.showInputDialog("Ingrese valor a retirar de la cuenta:");
        BigDecimal monto;
        //validar monto ingresado.
        try {
            monto = new BigDecimal(montoStr);
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(null, "El valor a retirar debe ser mayor que cero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor a retirar inválido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Se obtiene la cuenta asociada al usuario logueado.
        Cuenta cuenta = usuario.getCuentaAsociada();
        try {
            BigDecimal saldoAnterior = cuenta.getSaldo();
            //Se realiza el retiro desde cajero.
            cuenta.retiroCajero(monto);
            BigDecimal saldoNuevo = cuenta.getSaldo();
            //Calculo del costo generado por está transacción.
            BigDecimal costo = saldoAnterior.subtract(saldoNuevo).subtract(monto);

            JOptionPane.showMessageDialog(null, "Retiro realizado con éxito. \nNuevo saldo: $" + cuenta.getSaldo());

            // Registrar la transacción
            Transaccion transaccion = new Transaccion("Retiro desde Cajero.", monto, costo);
            usuario.agregarTransaccion(transaccion);
        }catch (UnsupportedOperationException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void consultarSaldo(Usuario usuario) {
        Cuenta cuenta = usuario.getCuentaAsociada();
        //se busca que tipo de cuenta es.
        String tipoCuenta = cuenta instanceof CuentaPremium ? "Premium" : "Básica";
        //se arma string con todos los datos de la cuenta.
        String mensaje = "Nombre: " + usuario.getNombre() + "\n"
                + "Número de Cuenta: " + cuenta.getNumeroCuenta() + "\n"
                + "Tipo de Cuenta: " + tipoCuenta + "\n"
                + "Saldo Actual: $" + cuenta.getSaldo();
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void historialTransaccion(Usuario usuario) {
        //se crea y recupera listado de transacciones ya realizadas.
        List<Transaccion> transacciones = usuario.getTransacciones();
        //se calcula cuantas transacciones se han hecho.
        int totalTransacciones = transacciones.size();
        //se busca el indice correcto para no iniciar desde indices negativos si hay menos de 5 transacciones.
        int inicio = Math.max(totalTransacciones - 5, 0);

        //Si no hay transacciones se muestra mensaje.
        if (totalTransacciones == 0) {
            JOptionPane.showMessageDialog(null, "No hay transacciones realizadas.","Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            //se crea un stringbuilder para ir concatenando los datos de las transacciones.
            StringBuilder historial = new StringBuilder("Últimas 5 transacciones:\n");

            //ciclo para recorrer las transacciones y armar el string con los datos.
            //se recorre de atrás hacía adelante para mostrar las ultimas 5.
            for (int i = totalTransacciones - 1; i >= inicio; i--) {
                //recuperar transacción con variable i.
                Transaccion transaccion = transacciones.get(i);

                //se concatenan los datos al string builder.
                historial.append("Código: ").append(transaccion.getCodigoUnico()).append("\n")
                        .append("Tipo: ").append(transaccion.getTipo()).append("\n")
                        .append("Monto: $").append(transaccion.getMonto()).append("\n")
                        .append("Costo: $").append(transaccion.getCosto()).append("\n")
                        .append("Fecha y Hora: ").append(transaccion.getFechaHora()).append("\n\n");
            }

            // se convierte el string builder a string para que funcione el JoptionPane.
            JOptionPane.showMessageDialog(null, historial.toString());
        }
    }

}
