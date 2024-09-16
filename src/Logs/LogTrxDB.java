package Logs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LogTrxDB {
    private static List<Log> logs = new ArrayList<>();

    //agregar cuentas a la lista.
    public static void nuevoLog(String mensaje, TipoTransaccion tipoTransaccion, BigDecimal monto , String codigoTransaccion) {
        Log log = new Log(mensaje, tipoTransaccion, monto, codigoTransaccion);
        logs.add(log);
    }

    // Obtener los últimos cinco registros sin eliminarlos
    public static List<String> obtenerUltimosCincoRegistros() {
        List<String> ultimosCincoLogs = new ArrayList<>();

        // Verificar si la lista tiene logs suficientes
        int start = Math.max(0, logs.size() - 5);
        List<Log> ultimosCinco = logs.subList(start, logs.size());

        // Recorrer los últimos cinco logs y añadir su detalle a la lista de resultados
        for (Log log : ultimosCinco) {
            ultimosCincoLogs.add(log.obtenerDetalleLog());
        }

        return ultimosCincoLogs;
    }

    // Imprimir los últimos cinco registros
    public static void mostrarUltimosCincoRegistros() {
        List<String> logsMostrar = obtenerUltimosCincoRegistros();
        logsMostrar.forEach(System.out::println);
    }





}
